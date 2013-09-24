package com.pahimar.ee3.emc;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.graph.Node;
import com.pahimar.ee3.graph.WeightedDirectedGraph;
import com.pahimar.ee3.graph.WeightedEdge;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.crafting.RecipeRegistry;

public class DynEMC {

    private static DynEMC dynEMC = null;

    private RecipeRegistry recipeRegistry;
    public final WeightedDirectedGraph<CustomWrappedStack> graph;

    private DynEMC() {

        recipeRegistry = RecipeRegistry.getInstance();
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

        populateGraph();
    }

    private void populateGraph() {

        for (CustomWrappedStack discoveredStack : recipeRegistry.getDiscoveredStacks()) {
            graph.addNode(discoveredStack);
        }

        Multimap<CustomWrappedStack, List<CustomWrappedStack>> recipeMappings = recipeRegistry.getRecipeMappings();

        Set<CustomWrappedStack> recipeKeySet = recipeMappings.keySet();
        Iterator<CustomWrappedStack> recipeKeySetIterator = recipeKeySet.iterator();
        CustomWrappedStack recipeOutput = null;

        while (recipeKeySetIterator.hasNext()) {
            recipeOutput = recipeKeySetIterator.next();

            for (List<CustomWrappedStack> recipeInputs : recipeMappings.get(recipeOutput)) {

                CustomWrappedStack unWrappedRecipeOutput = new CustomWrappedStack(recipeOutput.getWrappedStack());

                if (graph.containsNode(unWrappedRecipeOutput)) {
                    for (CustomWrappedStack recipeInput : recipeInputs) {

                        // Unwrapped the wrapped stacks so that we actually find them in the graph

                        CustomWrappedStack unWrappedRecipeInput = new CustomWrappedStack(recipeInput.getWrappedStack());

                        if (graph.containsNode(unWrappedRecipeInput)) {
                            if (recipeOutput.getStackSize() != 0) {
                                graph.addEdge(unWrappedRecipeOutput, unWrappedRecipeInput, (recipeInput.getStackSize() * 1.0f) / recipeOutput.getStackSize());
                            }
                        }
                        else {
                            LogHelper.debug("Recipe output '" + unWrappedRecipeOutput.toString() + "' exists in the crafting relationship graph");
                            LogHelper.debug("Recipe input '" + unWrappedRecipeInput.toString() + "' does not exist in the crafting relationship graph");
                        }
                    }
                }
                else {
                    LogHelper.debug("Recipe output '" + unWrappedRecipeOutput.toString() + "' does not exist in the crafting relationship graph");
                }
            }
        }
    }

    public void printDebugDump() {

        LogHelper.debug("Total node count: " + graph.getAllNodes().size());
        LogHelper.debug("Leaf node count: " + graph.getLeafNodes().size());
        LogHelper.debug("Orphan node count: " + graph.getOrphanNodes().size());
        LogHelper.debug("Compound node count: " + graph.getCompoundNodes().size());
//        LogHelper.debug("");
//        LogHelper.debug("***** START NODES *****");
//
//        Iterator<Node<CustomWrappedStack>> nodeIter = graph.iterator();
//        while (nodeIter.hasNext()) {
//            Node<CustomWrappedStack> node = nodeIter.next();
//            LogHelper.debug("Node: " + node);
//        }
//        LogHelper.debug("***** END NODES *****");
//
//        LogHelper.debug("");
//
//        LogHelper.debug("***** START EDGES FROM *****");
//        nodeIter = graph.iterator();
//        while (nodeIter.hasNext()) {
//            Node<CustomWrappedStack> node = nodeIter.next();
//            List<WeightedEdge<Node<CustomWrappedStack>>> edgesFrom = graph.edgesFrom(node);
//            for (WeightedEdge<Node<CustomWrappedStack>> edge : edgesFrom) {
//                LogHelper.debug("Crafting Output: " + node + ", Crafting Input: " + edge.destinationNode + ", Weight: " + edge.weight);
//            }
//        }
//        LogHelper.debug("***** END EDGES FROM *****");

        LogHelper.debug("");

        LogHelper.debug("***** START EDGES TO *****");
        Iterator<Node<CustomWrappedStack>> nodeIter = graph.iterator();
        while (nodeIter.hasNext()) {
            
            Node<CustomWrappedStack> node = nodeIter.next();
            List<WeightedEdge<Node<CustomWrappedStack>>> edgesTo = graph.edgesTo(node);
            
            for (WeightedEdge<Node<CustomWrappedStack>> edge : edgesTo) {
                LogHelper.debug("Crafting Input: " + node + ", Crafting Output: " + edge.destinationNode + ", Weight: " + edge.weight);
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
