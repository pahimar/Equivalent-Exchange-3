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
                    }
                }
            }
        }
    }

    public void printDebugDump() {

        LogHelper.debug("Total node count: " + graph.getAllNodes().size());
        LogHelper.debug("Leaf node count: " + graph.getLeafNodes().size());
        LogHelper.debug("Orphan node count: " + graph.getOrphanNodes().size());
        LogHelper.debug("Compound node count: " + graph.getCompoundNodes().size());
        LogHelper.debug("'Critical' node count: " + (graph.getLeafNodes().size() - graph.getOrphanNodes().size()));
        
        LogHelper.debug("");
        
        for (Node<CustomWrappedStack> node : graph.getAllNodes()) {
            LogHelper.debug("Node: " + node);
            LogHelper.debug("Edges FROM Node");
            for (WeightedEdge<CustomWrappedStack> fromEdge : graph.edgesFrom(node)) {
                LogHelper.debug(" * " + fromEdge);
            }
            LogHelper.debug("Edges TO Node");
            for (WeightedEdge<CustomWrappedStack> toEdge : graph.edgesTo(node)) {
                LogHelper.debug(" * " + toEdge);
            }
            LogHelper.debug("");
        }
    }
}
