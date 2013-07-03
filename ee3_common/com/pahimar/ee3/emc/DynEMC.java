package com.pahimar.ee3.emc;

import java.util.Iterator;
import java.util.Set;

import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.emc.graph.WeightedDirectedGraph;
import com.pahimar.ee3.emc.graph.WeightedEdge;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.crafting.RecipeRegistry;

public class DynEMC {

    private static DynEMC dynEMC = null;
    
    private WeightedDirectedGraph<CustomWrappedStack> graph;

    private DynEMC() {

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

        @SuppressWarnings("unused")
        RecipeRegistry recipeManager = RecipeRegistry.getInstance();
        
        populateGraph();
    }

    private void populateGraph() {

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
