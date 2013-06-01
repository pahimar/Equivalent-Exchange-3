package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.core.util.OreStack;
import com.pahimar.ee3.core.util.RecipeHelper;
import com.pahimar.ee3.emc.graph.WeightedDirectedGraph;
import com.pahimar.ee3.emc.graph.WeightedEdge;
import com.pahimar.ee3.item.CustomStackWrapper;

public class DynEMC {

    private static DynEMC dynEMC = null;

    private ArrayList<CustomStackWrapper> discoveredItems;
    private WeightedDirectedGraph<CustomStackWrapper> graph;

    private DynEMC() {

        discoveredItems = new ArrayList<CustomStackWrapper>();
        graph = new WeightedDirectedGraph<CustomStackWrapper>();

        init();
    }

    public static DynEMC getInstance() {

        if (dynEMC == null) {
            dynEMC = new DynEMC();
        }

        return dynEMC;
    }

    public List<CustomStackWrapper> getDiscoveredItems() {

        return discoveredItems;
    }

    private void init() {

        populateItemList();
    }

    private void populateItemList() {
        ArrayList<ItemStack> subItems = new ArrayList<ItemStack>();

        /*
         * Add all entries from the OreDictionary
         */
        for (String oreName : OreDictionary.getOreNames()) {

            CustomStackWrapper customWrappedStack = new CustomStackWrapper(new OreStack(oreName));

            if (!discoveredItems.contains(customWrappedStack)) {
                discoveredItems.add(customWrappedStack);
            }
        }

        for (Object recipe : CraftingManager.getInstance().getRecipeList()) {
            if (recipe instanceof IRecipe) {
                ItemStack craftingResult = ((IRecipe) recipe).getRecipeOutput();

                if (craftingResult != null) {
                    if (craftingResult.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
                        CustomStackWrapper wrappedCraftingResult = new CustomStackWrapper(craftingResult);

                        if (!discoveredItems.contains(wrappedCraftingResult)) {
                            discoveredItems.add(wrappedCraftingResult);
                        }
                    }

                    for (CustomStackWrapper wrappedRecipeInput : RecipeHelper.getCollatedRecipeInputs((IRecipe) recipe)) {
                        if ((wrappedRecipeInput.getItemStack() != null) && (wrappedRecipeInput.getItemStack().getItemDamage() == OreDictionary.WILDCARD_VALUE)) {

                            wrappedRecipeInput.setWrappedStackSize(1);
                            if (!discoveredItems.contains(wrappedRecipeInput)) {
                                discoveredItems.add(wrappedRecipeInput);
                            }
                        }
                    }
                }
            }
        }

        /*
         * For every possible item (and sub item), add them to the discovered
         * items list
         */
        for (int i = 0; i < Item.itemsList.length; i++) {
            if (Item.itemsList[i] != null) {
                if (Item.itemsList[i].getHasSubtypes()) {

                    subItems.clear();
                    Item.itemsList[i].getSubItems(i, Item.itemsList[i].getCreativeTab(), subItems);

                    for (ItemStack itemStack : subItems) {
                        if (itemStack != null) {

                            CustomStackWrapper customStackWrapper = new CustomStackWrapper(itemStack);

                            if (!discoveredItems.contains(customStackWrapper)) {
                                discoveredItems.add(customStackWrapper);
                            }
                        }
                    }
                }
                else {

                    ItemStack itemStack = new ItemStack(Item.itemsList[i]);

                    CustomStackWrapper customStackWrapper = new CustomStackWrapper(itemStack);
                    if (!discoveredItems.contains(customStackWrapper)) {
                        discoveredItems.add(customStackWrapper);
                    }
                }
            }
        }

        /**
         * Now that we have discovered as many items as possible, trim out the
         * items that are black listed
         */
        for (CustomStackWrapper customStackWrapper : EmcBlackList.getInstance().getBlackListStacks()) {

            while (discoveredItems.contains(customStackWrapper)) {
                discoveredItems.remove(customStackWrapper);
            }
        }

        for (CustomStackWrapper customWrappedStack : discoveredItems) {

            if (!graph.nodeExists(customWrappedStack)) {
                graph.addNode(customWrappedStack);
            }
        }

        for (CustomStackWrapper customWrappedStack : discoveredItems) {

            ArrayList<IRecipe> recipes = RecipeHelper.getReverseRecipes(customWrappedStack);

            for (IRecipe recipe : recipes) {

                ArrayList<CustomStackWrapper> recipeInputs = RecipeHelper.getCollatedRecipeInputs(recipe);

                System.out.println(recipeInputs);
                
                for (CustomStackWrapper wrappedRecipeInput : recipeInputs) {

                    if (wrappedRecipeInput.getItemStack() != null) {
                        ItemStack itemStack = wrappedRecipeInput.getItemStack();

                        if (OreDictionary.getOreID(itemStack) != -1) {
                            wrappedRecipeInput = new CustomStackWrapper(new OreStack(itemStack));
                        }
                    }

                    float weight = wrappedRecipeInput.getWrappedStackSize();
                    wrappedRecipeInput.setWrappedStackSize(1);

                    try {
                        graph.addEdge(customWrappedStack, wrappedRecipeInput, weight);
                    }
                    catch (NoSuchElementException e) {
                        LogHelper.log(Level.SEVERE, e.getMessage() + " from: [" + customWrappedStack + "], to: [" + wrappedRecipeInput + "]");
                    }
                }
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        
        LogHelper.log(Level.INFO, "***** START NODES *****");
        Iterator<CustomStackWrapper> nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomStackWrapper node = nodeIter.next();
            LogHelper.log(Level.INFO, "Node: " + node);
        }
        LogHelper.log(Level.INFO, "***** END NODES *****");
        
        LogHelper.log(Level.INFO, "***** START EDGES FROM *****");
        nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomStackWrapper node = nodeIter.next();
            Set<WeightedEdge<CustomStackWrapper>> edgesFrom = graph.edgesFrom(node);
            for (WeightedEdge<CustomStackWrapper> edge : edgesFrom) {
                LogHelper.log(Level.INFO, "Crafting Output: " + node);
                LogHelper.log(Level.INFO, "Crafting Input: " + edge.getTarget());
                LogHelper.log(Level.INFO, "Weight: " + edge.getWeight());
                LogHelper.log(Level.INFO, "");
            }
        }
        LogHelper.log(Level.INFO, "***** END EDGES FROM *****");
        
//        LogHelper.log(Level.INFO, "***** START EDGES TO *****");
//        nodeIter = graph.iterator();
//        while (nodeIter.hasNext()) {
//            CustomStackWrapper node = nodeIter.next();
//            Set<WeightedEdge<CustomStackWrapper>> edgesTo = graph.edgesTo(node);
//            for (WeightedEdge<CustomStackWrapper> edge : edgesTo) {
//                LogHelper.log(Level.INFO, "From: " + node);
//                LogHelper.log(Level.INFO, "To: " + edge.getTarget());
//                LogHelper.log(Level.INFO, "Weight: " + edge.getWeight());
//                LogHelper.log(Level.INFO, "");
//            }
//        }
//        LogHelper.log(Level.INFO, "***** END EDGES TO *****");

        return stringBuilder.toString();
    }
}
