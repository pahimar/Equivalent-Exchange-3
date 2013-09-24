package com.pahimar.ee3.emc.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.collect.ImmutableList;

public class WeightedDirectedGraph<T extends Comparable<T>>
        implements Iterable<Node<T>> {

    // FIXME This whole class should be considered very broken right now, as it
    // needs to be adjusted now that we are wrapping objects into Nodes

    private final Map<Node<T>, SortedSet<WeightedEdge<Node<T>>>> graph = new HashMap<Node<T>, SortedSet<WeightedEdge<Node<T>>>>();

    /**
     * Checks whether or not the provided Node exists in the graph
     * 
     * @param node
     *            The Node we are looking for in the graph
     * @return true if the Node exists in the graph, false otherwise
     */
    public boolean containsNode(Node<T> node) {

        return graph.containsKey(node);
    }

    /**
     * Checks whether or not the provided object exists in the graph
     * 
     * @param nodeObject
     *            The object that we are checking if it is contained within a
     *            Node in the graph
     * @return true if there exists a Node in the graph that contains the
     *         provided object, false otherwise
     */
    public boolean containsNode(T nodeObject) {

        return this.containsNode(new Node<T>(nodeObject));
    }

    /**
     * Checks whether or not there exists any edge between two Nodes
     * 
     * @param sourceNode
     *            The source Node of an edge
     * @param destinationNode
     *            The destination Node of an edge
     * @return true if there exists any WeightedEdge<T> between two Nodes, where
     *         the source Node is sourceNode and the destination Node is
     *         destinationNode
     */
    public boolean containsEdge(Node<T> sourceNode, Node<T> destinationNode) {

        if (this.containsNode(sourceNode) && this.containsNode(destinationNode)) {

            Iterator<WeightedEdge<Node<T>>> edgeIterator = graph.get(sourceNode).iterator();

            while (edgeIterator.hasNext()) {
                if (edgeIterator.next().destinationNode.equals(destinationNode)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks whether or not there exists any edge between two Nodes
     * 
     * @param sourceObject
     *            Object that is contained within the source Node of an edge
     * @param destinationNode
     *            Object that is contained within the destination Node of an
     *            edge
     * @return true if there exists any WeightedEdge<T> between two Nodes, where
     *         the source Node contains sourceObject and the destination Node
     *         contains destinationNode
     */
    public boolean containsEdge(T sourceObject, T destinationNode) {

        return this.containsEdge(new Node<T>(sourceObject), new Node<T>(destinationNode));
    }

    /**
     * Checks whether or not there exists an edge between two Nodes with a
     * specific edge weight
     * 
     * @param sourceNode
     *            The from Node of an edge
     * @param targetNode
     *            The to Node of an edge
     * @param edgeWeight
     *            The edge weight of an edge
     * @return true if there exists a WeightedEdge<T> from fromNode to toNode
     *         with the specified edge weight edgeWeight, false otherwise
     */
    public boolean containsEdge(Node<T> sourceNode, Node<T> targetNode, float edgeWeight) {

        if (this.containsNode(sourceNode) && this.containsNode(targetNode)) {
            return graph.get(sourceNode).contains(new WeightedEdge<Node<T>>(targetNode, edgeWeight));
        }

        return false;
    }

    /**
     * Checks whether or not there exists an edge between two Nodes with the
     * specified Node contents and with a specific edge weight
     * 
     * @param sourceObject
     *            Object that is contained within the from Node of an edge
     * @param targetObject
     *            Object that is contained within the to Node of an edge
     * @param edgeWeight
     *            The edge weight of an edge
     * @return true if there exists a WeightedEdge<T> from fromNode to toNode
     *         with the specified edge weight edgeWeight, false otherwise
     */
    public boolean containsEdge(T sourceObject, T targetObject, float edgeWeight) {

        return this.containsEdge(new Node<T>(sourceObject), new Node<T>(targetObject), edgeWeight);
    }

    /**
     * Adds the specified Node to the graph
     * 
     * @param node
     *            Node to be added to the graph
     * @return true if the Node was successfully added to the graph, false
     *         otherwise
     */
    public boolean addNode(Node<T> node) {

        if (this.containsNode(node)) {
            return false;
        }

        graph.put(node, new TreeSet<WeightedEdge<Node<T>>>());

        return true;
    }

    /**
     * Adds the specified object to the graph
     * 
     * @param nodeObject
     *            Object to encapsulate in a Node and add to the graph
     * @return true if the Node was successfully added to the graph, false
     *         otherwise
     */
    public boolean addNode(T nodeObject) {

        return this.addNode(new Node<T>(nodeObject));
    }

    /**
     * Adds a WeightedEdge from Node sourceNode to Node destinationNode with
     * edge weight edgeWeight, if those Nodes exist in the graph and a
     * WeightedEdge doesn't already exist with the specified parameters
     * 
     * @param sourceNode
     *            The source Node for the WeightedEdge
     * @param destinationNode
     *            The C Node for the WeightedEdge
     * @param edgeWeight
     *            The edge weight for the WeightedEdge
     */
    public void addEdge(Node<T> sourceNode, Node<T> destinationNode, float edgeWeight) {

        if (this.containsNode(sourceNode) && this.containsNode(destinationNode) && !this.containsEdge(sourceNode, destinationNode, edgeWeight)) {
            graph.get(sourceNode).add(new WeightedEdge<Node<T>>(destinationNode, edgeWeight));
        }
    }

    /**
     * Adds a WeightedEdge from Node sourceNode to Node destinationNode with a
     * default edge weight of 1, if those Nodes exist in the graph and a
     * WeightedEdge doesn't already exist with the specified parameters
     * 
     * @param sourceNode
     *            The source Node for the WeightedEdge
     * @param destinationNode
     *            The destination Node for the WeightedEdge
     */
    public void addEdge(Node<T> sourceNode, Node<T> destinationNode) {

        this.addEdge(sourceNode, destinationNode, 1);
    }

    /**
     * Adds a WeightedEdge from sourceObject to destinationObject with edge
     * weight edgeWeight, if Nodes exist in the graph containing those objects
     * and a WeightedEdge doesn't already exist with the specified parameters
     * 
     * @param sourceObject
     *            The source Object for the WeightedEdge
     * @param destinationObject
     *            The destination Object for the WeightedEdge
     * @param edgeWeight
     *            The edge weight for the WeightedEdge
     */
    public void addEdge(T sourceObject, T destinationObject, float edgeWeight) {

        this.addEdge(new Node<T>(sourceObject), new Node<T>(destinationObject), edgeWeight);
    }

    /**
     * Adds a WeightedEdge from sourceObject to destinationObject with a default
     * edge weight of 1, if Nodes exist in the graph containing those objects
     * and a WeightedEdge doesn't already exist with the specified parameters
     * 
     * @param sourceObject
     *            The source Object for the WeightedEdge
     * @param destinationObject
     *            The destination Object for the WeightedEdge
     * @param edgeWeight
     *            The edge weight for the WeightedEdge
     */
    public void addEdge(T sourceObject, T destinationObject) {

        this.addEdge(sourceObject, destinationObject, 1);
    }

    /**
     * Get a list of all WeightedEdges that start at the provided Node
     * 
     * @param sourceNode
     *            The Node we are gathering all WeightedEdges from
     * @return An ImmutableList of all the WeightedEdges that start from the
     *         from Node
     */
    public ImmutableList<WeightedEdge<Node<T>>> edgesFrom(Node<T> sourceNode) {

        if (this.containsNode(sourceNode)) {
            return ImmutableList.copyOf(graph.get(sourceNode));
        }

        return ImmutableList.of();
    }

    /**
     * Removes the specified Node from the graph, and all WeightedEdges for
     * which node is either the source or destination
     * 
     * @param node
     */
    public void removeNode(Node<T> node) {

        if (this.containsNode(node)) {

            this.removeEdgesTo(node);
            this.removeEdgesFrom(node);
            graph.remove(node);
        }
    }

    /**
     * Removes the Node that contains the specified node object
     * 
     * @param nodeObject
     */
    public void removeNode(T nodeObject) {

        this.removeNode(new Node<T>(nodeObject));
    }

    public void removeEdge(Node<T> sourceNode, Node<T> destinationNode, float weight) {

        if (this.containsNode(sourceNode) && this.containsNode(destinationNode)) {
            graph.get(sourceNode).remove(new WeightedEdge<Node<T>>(destinationNode, weight));
        }
    }

    public void removeEdge(Node<T> sourceNode, Node<T> destinationNode) {

        this.removeEdge(sourceNode, destinationNode, 1);
    }

    public void removeEdge(T sourceObject, T destinationObject, float weight) {

        this.removeEdge(new Node<T>(sourceObject), new Node<T>(destinationObject), weight);
    }

    public void removeEdge(T sourceObject, T destinationObject) {

        this.removeEdge(sourceObject, destinationObject, 1);
    }

    /**
     * Remove all WeightedEdges that start at Node node
     * 
     * @param node
     */
    public void removeEdgesFrom(Node<T> node) {

        if (this.containsNode(node)) {
            graph.get(node).clear();
        }
    }

    public void removeEdgesFrom(T nodeObject) {

        this.removeEdgesFrom(new Node<T>(nodeObject));
    }

    /**
     * Removes all WeightedEdges that have Node node as the destination
     * 
     * @param node
     */
    public void removeEdgesTo(Node<T> node) {

        if (this.containsNode(node)) {

            Iterator<Node<T>> nodeIterator = this.iterator();

            while (nodeIterator.hasNext()) {

                Node<T> graphNode = nodeIterator.next();

                List<WeightedEdge<Node<T>>> fromEdges = this.edgesFrom(graphNode);

                for (WeightedEdge<Node<T>> fromEdge : fromEdges) {
                    if (fromEdge.destinationNode.equals(node)) {
                        graph.get(graphNode).remove(fromEdge);
                    }
                }
            }
        }
    }

    public void removeEdgesTo(T nodeObject) {

        this.removeEdgesTo(new Node<T>(nodeObject));
    }

    /**
     * Removes all WeightedEdges between Nodes firstNode and secondNode
     * 
     * @param firstNode
     * @param secondNode
     */
    public void removeEdgesBetween(Node<T> firstNode, Node<T> secondNode) {

        if (this.containsNode(firstNode) && this.containsNode(secondNode)) {

            for (WeightedEdge<Node<T>> fromEdge : this.edgesFrom(firstNode)) {
                if (fromEdge.destinationNode.equals(secondNode)) {
                    graph.get(firstNode).remove(fromEdge);
                }
            }

            for (WeightedEdge<Node<T>> fromEdge : this.edgesFrom(secondNode)) {
                if (fromEdge.destinationNode.equals(firstNode)) {
                    graph.get(secondNode).remove(fromEdge);
                }
            }
        }
    }

    public void removeEdgesBetween(T firstObject, T secondObject) {

        this.removeEdgesBetween(new Node<T>(firstObject), new Node<T>(secondObject));
    }

    /**
     * 
     * @param sourceObject
     * @return
     */
    public ImmutableList<WeightedEdge<Node<T>>> edgesFrom(T sourceObject) {

        return this.edgesFrom(new Node<T>(sourceObject));
    }

    /**
     * 
     * @param destinationNode
     * @return
     */
    public ImmutableList<WeightedEdge<Node<T>>> edgesTo(Node<T> destinationNode) {

        ImmutableList.Builder<WeightedEdge<Node<T>>> edgesToTargetNodeList = ImmutableList.builder();

        if (this.containsNode(destinationNode)) {

            for (Node<T> graphNode : this.getAllNodes()) {

                if (!graphNode.equals(destinationNode)) {

                    List<WeightedEdge<Node<T>>> edgesFromGraphNode = edgesFrom(graphNode);

                    for (WeightedEdge<Node<T>> fromEdge : edgesFromGraphNode) {
                        
                        if (fromEdge.destinationNode.equals(destinationNode)) {
                            edgesToTargetNodeList.add(new WeightedEdge<Node<T>>(graphNode, fromEdge.weight));
                        }
                    }
                }
            }
        }

        return edgesToTargetNodeList.build();
    }

    /**
     * 
     * @param destinationObject
     * @return
     */
    public ImmutableList<WeightedEdge<Node<T>>> edgesTo(T destinationObject) {

        return this.edgesTo(new Node<T>(destinationObject));
    }

    /**
     * Retrieves an ImmutableList of all Nodes in the graph
     * 
     * @return An ImmutableList of all Nodes in the graph
     */
    public ImmutableList<Node<T>> getAllNodes() {

        if (graph.keySet() != null) {
            return ImmutableList.copyOf(graph.keySet());
        }
        else {
            return ImmutableList.of();
        }
    }

    /**
     * Retrieves an ImmutableList of all leaf Nodes in the graph A leaf node is
     * defined as a Node that has no edges from edge
     * 
     * @return An ImmutableList of all leaf Nodes in the graph
     */
    public ImmutableList<Node<T>> getLeafNodes() {

        ImmutableList.Builder<Node<T>> leafNodeList = ImmutableList.builder();

        Iterator<Node<T>> nodeIterator = this.iterator();

        while (nodeIterator.hasNext()) {
            Node<T> currentNode = nodeIterator.next();

            if (this.edgesFrom(currentNode).size() == 0) {
                leafNodeList.add(currentNode);
            }
        }

        return leafNodeList.build();
    }

    /**
     * Retrieves an ImmutableList of all "orphan" Nodes in the graph An "orphan"
     * Node is defined as a Node that has no edges from it, and no edges to it
     * (they are isolated in the graph) Orphan Nodes technically qualify as leaf
     * Nodes
     * 
     * @return An ImmutableList of all "orphan" Nodes in the graph
     */
    public ImmutableList<Node<T>> getOrphanNodes() {

        ImmutableList.Builder<Node<T>> orphanNodeList = ImmutableList.builder();

        Iterator<Node<T>> nodeIterator = this.iterator();

        while (nodeIterator.hasNext()) {
            Node<T> currentNode = nodeIterator.next();

            if ((this.edgesFrom(currentNode).size() == 0) && (this.edgesTo(currentNode).size() == 0)) {
                orphanNodeList.add(currentNode);
            }
        }

        return orphanNodeList.build();
    }

    /**
     * Retrieves an ImmutableList of all "compound" Nodes in the graph. A
     * "compound" Node is defined as a Node that has edges from it (therefore,
     * it is not a leaf Node)
     * 
     * @return An ImmutableList of all "compound" Nodes in the graph
     */
    public ImmutableList<Node<T>> getCompoundNodes() {

        ImmutableList.Builder<Node<T>> compoundNodeList = ImmutableList.builder();

        Iterator<Node<T>> nodeIterator = this.iterator();

        while (nodeIterator.hasNext()) {
            Node<T> currentNode = nodeIterator.next();

            if (this.edgesFrom(currentNode).size() > 0) {
                compoundNodeList.add(currentNode);
            }
        }

        return compoundNodeList.build();
    }

    /**
     * Returns the Node count of the graph
     * 
     * @return The number of Nodes in the graph
     */
    public int size() {

        return graph.size();
    }

    /**
     * Returns whether or not the graph is empty (contains no Nodes)
     * 
     * @return true if the graph contains no Nodes, false otherwise
     */
    public boolean isEmpty() {

        return graph.isEmpty();
    }

    /**
     * Returns a String representation of this graph
     */
    @Override
    public String toString() {

        return graph.toString();
    }

    /**
     * Returns an iterator of all the Nodes in the graph
     */
    @Override
    public Iterator<Node<T>> iterator() {

        return this.getAllNodes().iterator();
    }
}
