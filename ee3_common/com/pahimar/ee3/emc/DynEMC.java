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
import com.pahimar.ee3.item.CustomWrappedStack;

public class DynEMC {

    private static DynEMC dynEMC = null;

    private ArrayList<CustomWrappedStack> discoveredItems;
    private WeightedDirectedGraph<CustomWrappedStack> graph;

    private DynEMC() {

        discoveredItems = new ArrayList<CustomWrappedStack>();
        graph = new WeightedDirectedGraph<CustomWrappedStack>();

        init();
    }

    public static DynEMC getInstance() {

        if (dynEMC == null) {
            dynEMC = new DynEMC();
        }

        return dynEMC;
    }

    public List<CustomWrappedStack> getDiscoveredItems() {

        return discoveredItems;
    }

    private void init() {

        populateItemList();
        populateGraph();
    }

    private void populateItemList() {
        ArrayList<ItemStack> subItems = new ArrayList<ItemStack>();

        /*
         * Add all entries from the OreDictionary
         */
        for (String oreName : OreDictionary.getOreNames()) {

            CustomWrappedStack customWrappedStack = new CustomWrappedStack(new OreStack(oreName));

            if (!discoveredItems.contains(customWrappedStack)) {
                discoveredItems.add(customWrappedStack);
            }
        }

        for (Object recipe : CraftingManager.getInstance().getRecipeList()) {
            if (recipe instanceof IRecipe) {
                ItemStack craftingResult = ((IRecipe) recipe).getRecipeOutput();

                if (craftingResult != null) {
                    if (craftingResult.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
                        CustomWrappedStack wrappedCraftingResult = new CustomWrappedStack(craftingResult);

                        if (!discoveredItems.contains(wrappedCraftingResult)) {
                            discoveredItems.add(wrappedCraftingResult);
                        }
                    }

                    for (CustomWrappedStack wrappedRecipeInput : RecipeHelper.getCollatedRecipeInputs((IRecipe) recipe)) {
                        if ((wrappedRecipeInput.getItemStack() != null) && (wrappedRecipeInput.getItemStack().getItemDamage() == OreDictionary.WILDCARD_VALUE)) {

                            wrappedRecipeInput.setStackSize(1);
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

                            CustomWrappedStack customStackWrapper = new CustomWrappedStack(itemStack);

                            if (!discoveredItems.contains(customStackWrapper)) {
                                discoveredItems.add(customStackWrapper);
                            }
                        }
                    }
                }
                else {

                    ItemStack itemStack = new ItemStack(Item.itemsList[i]);

                    CustomWrappedStack customStackWrapper = new CustomWrappedStack(itemStack);
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
        for (CustomWrappedStack customStackWrapper : EmcBlackList.getInstance().getBlackListStacks()) {

            while (discoveredItems.contains(customStackWrapper)) {
                discoveredItems.remove(customStackWrapper);
            }
        }

        for (CustomWrappedStack customWrappedStack : discoveredItems) {

            if (!graph.nodeExists(customWrappedStack)) {
                graph.addNode(customWrappedStack);
            }
        }
    }
    
    private void populateGraph() {
        
        for (CustomWrappedStack customWrappedStack : discoveredItems) {

            ArrayList<IRecipe> recipes = RecipeHelper.getReverseRecipes(customWrappedStack);

            for (IRecipe recipe : recipes) {

                ArrayList<CustomWrappedStack> recipeInputs = RecipeHelper.getCollatedRecipeInputs(recipe);
                
                for (CustomWrappedStack wrappedRecipeInput : recipeInputs) {

                    float weight = wrappedRecipeInput.getStackSize();
                    
                    CustomWrappedStack recipeInput = null;
                    
                    if (wrappedRecipeInput.getItemStack() != null) {
                        ItemStack itemStack = wrappedRecipeInput.getItemStack();

                        if (OreDictionary.getOreID(itemStack) != -1) {
                            recipeInput = new CustomWrappedStack(new OreStack(itemStack));
                        }
                        else {
                            recipeInput = new CustomWrappedStack(itemStack);
                        }
                    }
                    else if (wrappedRecipeInput.getOreStack() != null) {
                        recipeInput = new CustomWrappedStack(wrappedRecipeInput.getOreStack());
                    }

                    try {
                        if (recipeInput != null) {
                            graph.addEdge(customWrappedStack, recipeInput, weight);
                        }
                    }
                    catch (NoSuchElementException e) {
                        LogHelper.log(Level.SEVERE, e.getMessage() + ";\nFrom: [" + customWrappedStack + "]\nTo: [" + wrappedRecipeInput + "]");
                    }
                }
            }
        }
    }

    @Override
    // TODO Make this an actual toString and take out the logging into a debug method
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        
        LogHelper.log(Level.INFO, "***** START NODES *****");
        Iterator<CustomWrappedStack> nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomWrappedStack node = nodeIter.next();
            LogHelper.log(Level.INFO, "Node: " + node);
        }
        LogHelper.log(Level.INFO, "***** END NODES *****");
        
        LogHelper.log(Level.INFO, "***** START EDGES FROM *****");
        nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomWrappedStack node = nodeIter.next();
            Set<WeightedEdge<CustomWrappedStack>> edgesFrom = graph.edgesFrom(node);
            for (WeightedEdge<CustomWrappedStack> edge : edgesFrom) {
                LogHelper.log(Level.INFO, "Crafting Output: " + node);
                LogHelper.log(Level.INFO, "Crafting Input: " + edge.getTarget());
                LogHelper.log(Level.INFO, "Weight: " + edge.getWeight());
                LogHelper.log(Level.INFO, "");
            }
        }
        LogHelper.log(Level.INFO, "***** END EDGES FROM *****");
        
        LogHelper.log(Level.INFO, "***** START EDGES TO *****");
        nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomWrappedStack node = nodeIter.next();
            Set<WeightedEdge<CustomWrappedStack>> edgesTo = graph.edgesTo(node);
            Iterator<WeightedEdge<CustomWrappedStack>> edgeIter = edgesTo.iterator();
            while (edgeIter.hasNext()) {
                WeightedEdge<CustomWrappedStack> edge = edgeIter.next();
                LogHelper.log(Level.INFO, "From: " + node);
                LogHelper.log(Level.INFO, "To: " + edge.getTarget());
                LogHelper.log(Level.INFO, "Weight: " + edge.getWeight());
                LogHelper.log(Level.INFO, "");
            }
        }
        LogHelper.log(Level.INFO, "***** END EDGES TO *****");

        return stringBuilder.toString();
    }
}
