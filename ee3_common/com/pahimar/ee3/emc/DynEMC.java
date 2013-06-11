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
        
    }

    /**
     * Discovers all instances of ItemStacks with wild card meta values in the vanilla Crafting Manager
     * @return A list of CustomWrappedStacks that contains all wild card meta ItemStacks in the vanilla Crafting Manager
     */
    private ArrayList<CustomWrappedStack> findWildCards() {
        
        ArrayList<CustomWrappedStack> wildCards = new ArrayList<CustomWrappedStack>();
        
        for (Object recipe : CraftingManager.getInstance().getRecipeList()) {
            
            if (recipe instanceof IRecipe) {
                if (((IRecipe) recipe).getRecipeOutput() instanceof ItemStack) {
                    CustomWrappedStack recipeOutput = new CustomWrappedStack(((IRecipe) recipe).getRecipeOutput());
                    ArrayList<CustomWrappedStack> recipeInputs = RecipeHelper.getRecipeInputs((IRecipe) recipe);
                    ItemStack itemStack = null;
                    
                    if (recipeOutput.getWrappedStack() instanceof ItemStack) {
                        
                        itemStack = (ItemStack) recipeOutput.getWrappedStack();
                        
                        if (itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE && OreDictionary.getOreID(itemStack) == -1) {
                            
                            if (!wildCards.contains(recipeOutput)) {
                                wildCards.add(recipeOutput);
                            }
                        }
                    }
                    
                    for (CustomWrappedStack inputStack : recipeInputs) {
                        
                        if (inputStack.getWrappedStack() instanceof ItemStack) {
                            
                            itemStack = (ItemStack) inputStack.getWrappedStack();
                            
                            if (itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE && OreDictionary.getOreID(itemStack) == -1) {
                                
                                if (!wildCards.contains(inputStack)) {
                                    wildCards.add(inputStack);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return wildCards;
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

    public List<CustomWrappedStack> getAllNodes() {

        ArrayList<CustomWrappedStack> allNodes = new ArrayList<CustomWrappedStack>();

        Iterator<CustomWrappedStack> nodeIter = graph.iterator();

        while (nodeIter.hasNext()) {
            allNodes.add(nodeIter.next());
        }

        return allNodes;
    }

    public List<CustomWrappedStack> getCriticalNodes() {

        ArrayList<CustomWrappedStack> criticalNodes = new ArrayList<CustomWrappedStack>();

        Iterator<CustomWrappedStack> nodeIter = graph.iterator();

        while (nodeIter.hasNext()) {
            CustomWrappedStack currentNode = nodeIter.next();

            if (graph.edgesFrom(currentNode).size() == 0) {
                criticalNodes.add(currentNode);
            }
        }

        return criticalNodes;
    }

    public List<CustomWrappedStack> getOrphanNodes() {

        ArrayList<CustomWrappedStack> criticalNodes = new ArrayList<CustomWrappedStack>();

        Iterator<CustomWrappedStack> nodeIter = graph.iterator();

        while (nodeIter.hasNext()) {
            CustomWrappedStack currentNode = nodeIter.next();

            if ((graph.edgesFrom(currentNode).size() == 0) && (graph.edgesTo(currentNode).size() == 0)) {
                criticalNodes.add(currentNode);
            }
        }

        return criticalNodes;
    }

    public void printDebugDump() {

        LogHelper.info("***** START NODES *****");
        Iterator<CustomWrappedStack> nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomWrappedStack node = nodeIter.next();
            LogHelper.info("Node: " + node);
        }
        LogHelper.info("***** END NODES *****");

        LogHelper.info("***** START EDGES FROM *****");
        nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomWrappedStack node = nodeIter.next();
            Set<WeightedEdge<CustomWrappedStack>> edgesFrom = graph.edgesFrom(node);
            for (WeightedEdge<CustomWrappedStack> edge : edgesFrom) {
                LogHelper.info("Crafting Output: " + node);
                LogHelper.info("Crafting Input: " + edge.getTarget());
                LogHelper.info("Weight: " + edge.getWeight());
                LogHelper.info("");
            }
        }
        LogHelper.info("***** END EDGES FROM *****");

        LogHelper.info("***** START EDGES TO *****");
        nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            CustomWrappedStack node = nodeIter.next();
            Set<WeightedEdge<CustomWrappedStack>> edgesTo = graph.edgesTo(node);
            Iterator<WeightedEdge<CustomWrappedStack>> edgeIter = edgesTo.iterator();
            while (edgeIter.hasNext()) {
                WeightedEdge<CustomWrappedStack> edge = edgeIter.next();
                LogHelper.info("From: " + node);
                LogHelper.info("To: " + edge.getTarget());
                LogHelper.info("Weight: " + edge.getWeight());
                LogHelper.info("");
            }
        }
        LogHelper.info("***** END EDGES TO *****");
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("DynEMC Node Count: %s", graph.size()));

        return stringBuilder.toString();
    }
}
