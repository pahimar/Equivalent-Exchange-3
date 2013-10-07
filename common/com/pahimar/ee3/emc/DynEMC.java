package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.graph.Node;
import com.pahimar.ee3.graph.WeightedDirectedGraph;
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
        
    }

    public void printDebugDump() {

        LogHelper.debug("Total node count: " + graph.getAllNodes().size());
        LogHelper.debug("Leaf node count: " + graph.getLeafNodes().size());
        LogHelper.debug("Orphan node count: " + graph.getOrphanNodes().size());
        LogHelper.debug("Compound node count: " + graph.getCompoundNodes().size());
        LogHelper.debug("'Critical' node count: " + (graph.getLeafNodes().size() - graph.getOrphanNodes().size()));
        
        LogHelper.debug("");
        
        List<Node<CustomWrappedStack>> nodes = new ArrayList<Node<CustomWrappedStack>>();
        nodes.addAll(graph.getLeafNodes());
        Collections.sort(nodes);
        
        for (Node<CustomWrappedStack> node : nodes) {
            LogHelper.debug("Node: " + node);
//            LogHelper.debug("Edges FROM this Node:");
//            for (WeightedDirectedEdge<CustomWrappedStack> fromEdge : graph.edgesFrom(node)) {
//                LogHelper.debug(" * " + fromEdge);
//            }
//            LogHelper.debug("Edges TO this Node:");
//            for (WeightedDirectedEdge<CustomWrappedStack> toEdge : graph.edgesTo(node)) {
//                LogHelper.debug(" * " + toEdge);
//            }
//            LogHelper.debug("");
        }
    }
}
