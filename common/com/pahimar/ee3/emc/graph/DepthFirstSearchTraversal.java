package com.pahimar.ee3.emc.graph;

import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.item.CustomWrappedStack;


public class DepthFirstSearchTraversal {

    public static WeightedDirectedGraph<CustomWrappedStack> spanningTree = new WeightedDirectedGraph<CustomWrappedStack>();
    
    public static void executeDFS(WeightedDirectedGraph<CustomWrappedStack> graph, CustomWrappedStack node) {
        
        // Label node as discovered
        if (!spanningTree.containsNode(node)) {
            spanningTree.addNode(node);
        }
        
        LogHelper.debug("Node: " + node);
        LogHelper.debug("To :" + graph.edgesTo(node));
        LogHelper.debug("From: " + graph.edgesFrom(node));
        for (WeightedEdge<CustomWrappedStack> edgeToNode : graph.edgesTo(node)) {
            LogHelper.debug("To iteration: " + edgeToNode);
            
            if (!spanningTree.containsNode(edgeToNode.target)) {
                spanningTree.addNode(edgeToNode.target);
            }
            
            if (!spanningTree.containsWeightedEdge(edgeToNode.target, node, edgeToNode.weight)) {
                spanningTree.addEdge(edgeToNode.target, node, edgeToNode.weight);
            }
        }
    }
}
