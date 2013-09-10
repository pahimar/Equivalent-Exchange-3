package com.pahimar.ee3.emc.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.collect.ImmutableList;

public class WeightedDirectedGraph<T> implements Iterable<T> {

    private final Map<T, SortedSet<WeightedEdge<T>>> graph = new HashMap<T, SortedSet<WeightedEdge<T>>>();

    public boolean addNode(T node) {

        if (containsNode(node)) {
            return false;
        }

        graph.put(node, new TreeSet<WeightedEdge<T>>());

        return true;
    }

    public void addEdge(T from, T to) {

        addEdge(from, to, 1);
    }

    public void addEdge(T from, T to, float weight) {

        if (containsNode(from) && containsNode(to) && !containsWeightedEdge(from, to, weight)) {
            graph.get(from).add(new WeightedEdge<T>(to, weight));
        }
    }

    /***
     * 
     * @param node
     *            The node we are checking for
     * @return True if specified node exists in the graph, false otherwise
     */
    public boolean containsNode(T node) {

        return graph.containsKey(node);
    }

    /***
     * 
     * @param from
     *            The 'from' node in the graph
     * @param to
     *            The 'to' node in the graph
     * @return True if there exists at least one WeightedEdge from the 'from'
     *         node going to the 'to' node
     */
    public boolean containsAnyEdge(T from, T to) {

        if (containsNode(from)) {

            Iterator<WeightedEdge<T>> edgeIterator = graph.get(from).iterator();

            while (edgeIterator.hasNext()) {
                if (edgeIterator.next().target.equals(to)) {
                    return true;
                }
            }
        }

        return false;
    }

    /***
     * 
     * @param from
     *            The 'from' node in the graph
     * @param to
     *            The 'to' node in the graph
     * @param weight
     *            The weight of the WeightedEdge we are looking for
     * @return
     */
    public boolean containsWeightedEdge(T from, T to, float weight) {

        if (containsNode(from)) {
            return graph.get(from).contains(new WeightedEdge<T>(to, weight));
        }

        return false;
    }

    /***
     * 
     * @param node
     * @return An ImmutableSet of WeightedEdges from the specified node
     */
    public ImmutableList<WeightedEdge<T>> edgesFrom(T node) {

        if (containsNode(node)) {
            return ImmutableList.copyOf(graph.get(node));
        }
        
        return ImmutableList.of();
    }

    public ImmutableList<WeightedEdge<T>> edgesTo(T targetNode) {

        ImmutableList.Builder<WeightedEdge<T>> edgesToTargetNodeList = ImmutableList.builder();
        
        if (containsNode(targetNode)) {
    
            for (T graphNode : getAllNodes()) {
                
                if (!graphNode.equals(targetNode)) {
                    
                    ImmutableList<WeightedEdge<T>> edgesFromGraphNode = edgesFrom(graphNode);
    
                    for (WeightedEdge<T> edgeFromGraphNode : edgesFromGraphNode) {
                        if (edgeFromGraphNode.target.equals(targetNode)) {
                            edgesToTargetNodeList.add(new WeightedEdge<T>(graphNode, edgeFromGraphNode.weight));
                        }
                    }
                }
            }
        }

        return edgesToTargetNodeList.build();
    }

    public void removeNode(T node) {

        if (containsNode(node)) {

            // Remove all edges from and to the node
            removeEdgesFrom(node);
            removeEdgesTo(node);
    
            // Remove the node
            graph.remove(node);
        }
    }

    public void removeEdge(T from, T to) {

        removeEdge(from, to, 1);
    }

    public void removeEdge(T from, T to, float weight) {

        if (containsNode(from) && containsNode(to)) {
            graph.get(from).remove(new WeightedEdge<T>(to, weight));
        }
    }

    public void removeEdgesFrom(T node) {

        if (containsNode(node)) {
            graph.get(node).clear();
        }
    }

    public void removeEdgesTo(T targetNode) {

        if (containsNode(targetNode)) {
            for (T graphNode : graph.keySet()) {
                
                ImmutableList<WeightedEdge<T>> edgesFromGraphNode = edgesFrom(graphNode);
    
                for (WeightedEdge<T> edgeFromGraphNode : edgesFromGraphNode) {
                    if (edgeFromGraphNode.target.equals(targetNode)) {
                        graph.get(graphNode).remove(edgeFromGraphNode);
                    }
                }
            }
        }
    }

    public void removeAllEdgesBetween(T firstNode, T secondNode) {

        if (containsNode(firstNode) && containsNode(secondNode)) {
            for (WeightedEdge<T> edgeFromFirstNode : edgesFrom(firstNode)) {
                if (edgeFromFirstNode.target.equals(secondNode)) {
                    graph.get(firstNode).remove(edgeFromFirstNode);
                }
            }
    
            for (WeightedEdge<T> edgeFromSecondNode : edgesFrom(secondNode)) {
                if (edgeFromSecondNode.target.equals(firstNode)) {
                    graph.get(secondNode).remove(edgeFromSecondNode);
                }
            }
        }
    }

    public ImmutableList<T> getAllNodes() {

        if (graph.keySet() != null) {
            return ImmutableList.copyOf(graph.keySet());
        }
        else {
            return ImmutableList.of();
        }
    }

    public ImmutableList<T> getCriticalNodes() {

        ImmutableList.Builder<T> criticalNodesList = ImmutableList.builder();

        Iterator<T> nodeIterator = this.iterator();

        while (nodeIterator.hasNext()) {
            T currentNode = nodeIterator.next();

            if (this.edgesFrom(currentNode).size() == 0) {
                criticalNodesList.add(currentNode);
            }
        }

        return criticalNodesList.build();
    }

    public ImmutableList<T> getOrphanNodes() {

        ImmutableList.Builder<T> orphanNodesList = ImmutableList.builder();

        Iterator<T> nodeIterator = this.iterator();

        while (nodeIterator.hasNext()) {
            T currentNode = nodeIterator.next();

            if (this.edgesFrom(currentNode).size() == 0 && this.edgesTo(currentNode).size() == 0) {
                orphanNodesList.add(currentNode);
            }
        }

        return orphanNodesList.build();
    }

    @Override
    public Iterator<T> iterator() {

        return this.getAllNodes().iterator();
    }

    public int size() {

        return graph.size();
    }

    public boolean isEmpty() {

        return graph.isEmpty();
    }

    @Override
    public String toString() {

        return graph.toString();
    }
    
    public void DepthFirstSearch(T node) {

        /**
         * The following is specified to EE3 (as opposed to this generalized Weighted Directed Graph implementation
         * 
         * - The edges from a node depict what "makes" that node
         * - If there are no edges from a node, then nothing "makes the node"
         * - If nothing "makes" the node the node, then it is a critical node
         * - A Depth First Search using using critical nodes will return a spanning tree of the graph
         */
    }
}
