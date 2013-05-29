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

                return orderedNodes.indexOf(o1) - orderedNodes.indexOf(o2);
            }
        }));

        return true;
    }

    public void addEdge(T from, T to) {

        addEdge(from, to, 1);
    }

    public void addEdge(T from, T to, int weight) {

        if (!(graph.containsKey(from) && graph.containsKey(to))) {
            throw new NoSuchElementException("Missing nodes from graph");
        }

        graph.get(from).add(new WeightedEdge<T>(weight, to));
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

    public boolean edgeExists(T from, T to, int weight) {

        if (!(graph.containsKey(from) && graph.containsKey(to))) {
            throw new NoSuchElementException("Missing nodes from graph");
        }

        return graph.get(from).contains(new WeightedEdge<T>(weight, to));
    }

    public Set<WeightedEdge<T>> edgesFrom(T from) {

        if (!graph.containsKey(from)) {
            throw new NoSuchElementException("Missing node from graph");
        }

        return Collections.unmodifiableSortedSet(graph.get(from));
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
