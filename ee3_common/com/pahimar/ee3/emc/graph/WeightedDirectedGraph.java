package com.pahimar.ee3.emc.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;

import com.pahimar.ee3.core.util.LogHelper;

public class WeightedDirectedGraph<T> implements Iterable<T> {

    private final Map<T, SortedSet<WeightedEdge<T>>> graph = new HashMap<T, SortedSet<WeightedEdge<T>>>();
    private List<T> orderedNodes = new ArrayList<T>();

    public boolean addNode(T node) {

        // Ignore nodes already added
        if (graph.containsKey(node)) {
            return false;
        }

        orderedNodes.add(node);
        graph.put(node, new TreeSet<WeightedEdge<T>>(new Comparator<WeightedEdge<T>>() {

            public int compare(WeightedEdge<T> o1, WeightedEdge<T> o2) {

                return orderedNodes.indexOf(o1.getTarget()) - orderedNodes.indexOf(o2.getTarget());
            }
        }));

        return true;
    }

    public void addEdge(T from, T to) {

        addEdge(from, to, 1);
    }

    public void addEdge(T from, T to, float weight) {

        if (!(graph.containsKey(from) && graph.containsKey(to))) {
            if (!(graph.containsKey(from))) {
                LogHelper.log(Level.SEVERE, "From node doesn't exist: " + from.toString());
            }
            if (!(graph.containsKey(to))) {
                LogHelper.log(Level.SEVERE, "To node doesn't exist: " + to.toString());
            }
            throw new NoSuchElementException("Missing nodes from graph");
        }

        // If a directed edge of the same weight doesn't already exist, add the new edge
        if (!edgeExists(from, to, weight)) {
            graph.get(from).add(new WeightedEdge<T>(weight, to));
        }
    }

    public boolean edgeExists(T from, T to) {

        if (!(graph.containsKey(from) && graph.containsKey(to))) {
            throw new NoSuchElementException("Missing nodes from graph");
        }

        Iterator<WeightedEdge<T>> edgeIterator = graph.get(from).iterator();

        while (edgeIterator.hasNext()) {
            if (edgeIterator.next().getTarget().equals(to)) {
                return true;
            }
        }

        return false;
    }

    public boolean edgeExists(T from, T to, float weight) {

        if (!(graph.containsKey(from) && graph.containsKey(to))) {
            if (!(graph.containsKey(from))) {
                LogHelper.log(Level.SEVERE, "From node doesn't exist: " + from.toString());
                LogHelper.log(Level.SEVERE, "To node: " + to.toString());
            }
            if (!(graph.containsKey(to))) {
                LogHelper.log(Level.SEVERE, "To node doesn't exist: " + to.toString());
                LogHelper.log(Level.SEVERE, "From node: " + from.toString());
            }
            throw new NoSuchElementException("Missing nodes from graph");
        }

        return graph.get(from).contains(new WeightedEdge<T>(weight, to));
    }

    public boolean nodeExists(T node) {

        return graph.containsKey(node);
    }

    public Set<WeightedEdge<T>> edgesFrom(T from) {

        if (!graph.containsKey(from)) {
            throw new NoSuchElementException("Missing node from graph");
        }

        return Collections.unmodifiableSortedSet(graph.get(from));
    }

    public Set<WeightedEdge<T>> edgesTo(T to) {

        if (!graph.containsKey(to)) {
            throw new NoSuchElementException("Missing node from graph");
        }

        Set<WeightedEdge<T>> edgesTo = new TreeSet<WeightedEdge<T>>(new Comparator<WeightedEdge<T>>() {

            public int compare(WeightedEdge<T> o1, WeightedEdge<T> o2) {

                return orderedNodes.indexOf(o1.getTarget()) - orderedNodes.indexOf(o2.getTarget());
            }
        });

        for (T node : graph.keySet()) {
            Set<WeightedEdge<T>> edgesFrom = edgesFrom(node);

            for (WeightedEdge<T> fromEdge : edgesFrom) {
                if (fromEdge.getTarget().equals(to)) {
                    edgesTo.add(fromEdge);
                }
            }
        }

        return Collections.unmodifiableSet(edgesTo);
    }
    
    public void removeNode(T node) {
        
        if (!(graph.containsKey(node))) {
            throw new NoSuchElementException("Missing node from graph");
        }
        
        // Remove all edges from and to the node
        removeAllEdgesFrom(node);
        removeAllEdgesTo(node);
        
        // Remove the node
        graph.remove(node);
    }

    public void removeEdge(T from, T to) {

        removeEdge(from, to, 1);
    }

    public void removeEdge(T from, T to, float weight) {

        if (!(graph.containsKey(from) && graph.containsKey(to))) {
            throw new NoSuchElementException("Missing nodes from graph");
        }

        graph.get(from).remove(new WeightedEdge<T>(weight, to));
    }

    public void removeAllEdgesFrom(T node) {

        if (!(graph.containsKey(node))) {
            throw new NoSuchElementException("Missing node from graph");
        }

        graph.get(node).clear();
    }
    
    public void removeAllEdgesTo(T node) {

        if (!(graph.containsKey(node))) {
            throw new NoSuchElementException("Missing node from graph");
        }

        for (T aNode : graph.keySet()) {
            Set<WeightedEdge<T>> edgesFrom = edgesFrom(aNode);

            for (WeightedEdge<T> fromEdge : edgesFrom) {
                if (fromEdge.getTarget().equals(node)) {
                    graph.get(aNode).remove(fromEdge);
                }
            }
        }
    }

    public void removeAllEdgesBetween(T firstNode, T secondNode) {

        if (!(graph.containsKey(firstNode) && graph.containsKey(secondNode))) {
            throw new NoSuchElementException("Missing nodes from graph");
        }

        for (WeightedEdge<T> edgeFrom : edgesFrom(firstNode)) {
            if (edgeFrom.getTarget().equals(secondNode)) {
                graph.get(firstNode).remove(edgeFrom);
            }
        }

        for (WeightedEdge<T> edgeFrom : edgesFrom(secondNode)) {
            if (edgeFrom.getTarget().equals(firstNode)) {
                graph.get(secondNode).remove(edgeFrom);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {

        return orderedNodes.iterator();
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

}
