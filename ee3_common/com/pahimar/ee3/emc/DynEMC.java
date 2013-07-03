package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.core.util.OreStack;
import com.pahimar.ee3.core.util.RecipeHelper;
import com.pahimar.ee3.emc.graph.WeightedDirectedGraph;
import com.pahimar.ee3.emc.graph.WeightedEdge;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.crafting.RecipeRegistry;
import com.pahimar.ee3.item.crafting.RecipesPotions;
import com.pahimar.ee3.item.crafting.RecipesSmelting;
import com.pahimar.ee3.item.crafting.RecipesVanilla;

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

    private void init() {

        RecipeRegistry recipeManager = RecipeRegistry.getInstance();

        Multimap<CustomWrappedStack, List<CustomWrappedStack>> recipes = HashMultimap.create();
        Set<CustomWrappedStack> recipeKeySet = null;
        Iterator<CustomWrappedStack> recipeKeySetIterator = null;
        CustomWrappedStack recipeOutput = null;

        // Add potion recipes
        recipes.putAll(RecipesPotions.getPotionRecipes());

        // Add smelting recipes in the vanilla smelting manager
        recipes.putAll(RecipesSmelting.getSmeltingRecipes());

        // Add recipes in the vanilla crafting manager
        recipes.putAll(RecipesVanilla.getVanillaRecipes());

        // Add recipes gathered via IMC
        // TODO Gather IMC recipes

        // Add items that have no recipe

        // Iterate through every recipe in the map, and add them to the registry
        recipeKeySet = recipes.keySet();
        recipeKeySetIterator = recipeKeySet.iterator();
        recipeOutput = null;

        while (recipeKeySetIterator.hasNext()) {
            recipeOutput = recipeKeySetIterator.next();

            for (List<CustomWrappedStack> recipeInputs : recipes.get(recipeOutput)) {
                recipeManager.addRecipe(recipeOutput, recipeInputs);
            }
        }

        LogHelper.debug(recipeManager.toString());
    }

    @SuppressWarnings("unused")
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
                        if (wrappedRecipeInput.getWrappedStack() instanceof ItemStack) {
                            ItemStack wrappedItemStack = (ItemStack) wrappedRecipeInput.getWrappedStack();
                            if (wrappedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) {

                                wrappedRecipeInput.setStackSize(1);

                                if (!discoveredItems.contains(wrappedRecipeInput)) {
                                    discoveredItems.add(wrappedRecipeInput);
                                }
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

                            CustomWrappedStack customWrappedStack = new CustomWrappedStack(itemStack);

                            if (!discoveredItems.contains(customWrappedStack)) {
                                discoveredItems.add(customWrappedStack);
                            }
                        }
                    }
                }
                else {

                    ItemStack itemStack = new ItemStack(Item.itemsList[i]);

                    CustomWrappedStack customWrappedStack = new CustomWrappedStack(itemStack);
                    if (!discoveredItems.contains(customWrappedStack)) {
                        discoveredItems.add(customWrappedStack);
                    }
                }
            }
        }

        /**
         * Now that we have discovered as many items as possible, trim out the
         * items that are black listed
         */
        for (CustomWrappedStack customWrappedStack : EmcBlackList.getInstance().getBlackListStacks()) {

            while (discoveredItems.contains(customWrappedStack)) {
                discoveredItems.remove(customWrappedStack);
            }
        }

        for (CustomWrappedStack customWrappedStack : discoveredItems) {

            if (!graph.nodeExists(customWrappedStack)) {
                graph.addNode(customWrappedStack);
            }
        }
    }

    @SuppressWarnings("unused")
    private void populateGraph() {

        for (CustomWrappedStack customWrappedStack : discoveredItems) {

            ArrayList<IRecipe> recipes = RecipeHelper.getReverseRecipes(customWrappedStack);

            for (IRecipe recipe : recipes) {

                ArrayList<CustomWrappedStack> recipeInputs = RecipeHelper.getCollatedRecipeInputs(recipe);

                for (CustomWrappedStack wrappedRecipeInput : recipeInputs) {

                    float weight = wrappedRecipeInput.getStackSize();

                    CustomWrappedStack recipeInput = null;

                    if (wrappedRecipeInput.getWrappedStack() instanceof ItemStack) {
                        ItemStack itemStack = (ItemStack) wrappedRecipeInput.getWrappedStack();

                        if (OreDictionary.getOreID(itemStack) != -1) {
                            recipeInput = new CustomWrappedStack(new OreStack(itemStack));
                        }
                        else {
                            recipeInput = new CustomWrappedStack(itemStack);
                        }
                    }
                    else if (wrappedRecipeInput.getWrappedStack() instanceof OreStack) {
                        recipeInput = new CustomWrappedStack((OreStack) wrappedRecipeInput.getWrappedStack());
                    }

                    try {
                        if (recipeInput != null) {
                            graph.addEdge(customWrappedStack, recipeInput, weight);
                        }
                    }
                    catch (NoSuchElementException e) {
                        LogHelper.severe(e.getMessage() + ";\nFrom: [" + customWrappedStack + "]\nTo: [" + wrappedRecipeInput + "]");
                    }
                }
            }
        }
    }

    public int size() {

        return graph.size();
    }

    public void printDebugDump() {

        LogHelper.debug("***** START NODES *****");
        Iterator<CustomWrappedStack> nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomWrappedStack node = nodeIter.next();
            LogHelper.debug("Node: " + node);
        }
        LogHelper.debug("***** END NODES *****");

        LogHelper.debug("***** START EDGES FROM *****");
        nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomWrappedStack node = nodeIter.next();
            Set<WeightedEdge<CustomWrappedStack>> edgesFrom = graph.edgesFrom(node);
            for (WeightedEdge<CustomWrappedStack> edge : edgesFrom) {
                LogHelper.debug("Crafting Output: " + node);
                LogHelper.debug("Crafting Input: " + edge.getTarget());
                LogHelper.debug("Weight: " + edge.getWeight());
                LogHelper.debug("");
            }
        }
        LogHelper.debug("***** END EDGES FROM *****");

        LogHelper.debug("***** START EDGES TO *****");
        nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomWrappedStack node = nodeIter.next();
            Set<WeightedEdge<CustomWrappedStack>> edgesTo = graph.edgesTo(node);
            Iterator<WeightedEdge<CustomWrappedStack>> edgeIter = edgesTo.iterator();
            while (edgeIter.hasNext()) {
                WeightedEdge<CustomWrappedStack> edge = edgeIter.next();
                LogHelper.debug("From: " + node);
                LogHelper.debug("To: " + edge.getTarget());
                LogHelper.debug("Weight: " + edge.getWeight());
                LogHelper.debug("");
            }
        }
        LogHelper.debug("***** END EDGES TO *****");
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("DynEMC Node Count: %s", graph.size()));

        return stringBuilder.toString();
    }
}
