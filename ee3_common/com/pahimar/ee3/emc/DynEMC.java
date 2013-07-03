package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

    private ArrayList<CustomWrappedStack> discoveredStacks;
    private ArrayList<CustomWrappedStack> stacksWithoutRecipes;
    
    private WeightedDirectedGraph<CustomWrappedStack> graph;

    private DynEMC() {

        discoveredStacks = new ArrayList<CustomWrappedStack>();
        stacksWithoutRecipes = new ArrayList<CustomWrappedStack>();
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

        // Add potion recipes
        recipes.putAll(RecipesPotions.getPotionRecipes());

        // Add smelting recipes in the vanilla smelting manager
        recipes.putAll(RecipesSmelting.getSmeltingRecipes());

        // Add recipes in the vanilla crafting manager
        recipes.putAll(RecipesVanilla.getVanillaRecipes());

        // Add recipes gathered via IMC
        // TODO Gather IMC recipes

        // Populate the discovered stacks list with all stacks that we are involved in a recipe we are aware of 
        discoverStacks(recipes);
        
        // Add items that have no recipe, using the list of discovered stacks to determine if it's in a recipe or not
        for (CustomWrappedStack stack : stacksWithoutRecipes) {
            recipes.put(stack, new ArrayList<CustomWrappedStack>());
        }

        // Iterate through every recipe in the map, and add them to the registry
        Set<CustomWrappedStack> recipeKeySet = recipes.keySet();
        Iterator<CustomWrappedStack> recipeKeySetIterator = recipeKeySet.iterator();
        CustomWrappedStack recipeOutput = null;

        while (recipeKeySetIterator.hasNext()) {
            recipeOutput = recipeKeySetIterator.next();
            
            for (List<CustomWrappedStack> recipeInputs : recipes.get(recipeOutput)) {
                recipeManager.addRecipe(recipeOutput, recipeInputs);
            }
        }
    }

    private void discoverStacks(Multimap<CustomWrappedStack, List<CustomWrappedStack>> recipes) {
        
        Set<CustomWrappedStack> recipeKeySet = recipes.keySet();
        Iterator<CustomWrappedStack> recipeKeySetIterator = recipeKeySet.iterator();
        CustomWrappedStack recipeOutput = null;
        
        while (recipeKeySetIterator.hasNext()) {
            recipeOutput = recipeKeySetIterator.next();
            
            if (!discoveredStacks.contains(new CustomWrappedStack(recipeOutput.getWrappedStack()))) {
                discoveredStacks.add(new CustomWrappedStack(recipeOutput.getWrappedStack()));
            }
            
            for (List<CustomWrappedStack> recipeInputs : recipes.get(recipeOutput)) {
                for (CustomWrappedStack recipeInput : recipeInputs) {
                    
                    if (!discoveredStacks.contains(new CustomWrappedStack(recipeInput.getWrappedStack()))) {
                        discoveredStacks.add(new CustomWrappedStack(recipeInput.getWrappedStack()));
                    }
                }
            }
        }

        ArrayList<ItemStack> subItemList = new ArrayList<ItemStack>();
        
        for (int i = 0; i < Item.itemsList.length; i++) {
            if (Item.itemsList[i] != null) {
                if (Item.itemsList[i].getHasSubtypes()) {

                    subItemList.clear();
                    Item.itemsList[i].getSubItems(i, Item.itemsList[i].getCreativeTab(), subItemList);

                    for (ItemStack itemStack : subItemList) {
                        if (itemStack != null) {

                            CustomWrappedStack customWrappedStack = new CustomWrappedStack(itemStack);

                            if (!discoveredStacks.contains(customWrappedStack)) {
                                discoveredStacks.add(customWrappedStack);
                                stacksWithoutRecipes.add(customWrappedStack);
                            }
                        }
                    }
                }
                else {
                    
                    CustomWrappedStack customWrappedStack = new CustomWrappedStack(new ItemStack(Item.itemsList[i]));
                    
                    if (!discoveredStacks.contains(customWrappedStack)) {
                        discoveredStacks.add(customWrappedStack);
                        stacksWithoutRecipes.add(customWrappedStack);
                    }
                }
            }
        }
    }

    @SuppressWarnings("unused")
    private void populateGraph() {

        for (CustomWrappedStack customWrappedStack : discoveredStacks) {

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
