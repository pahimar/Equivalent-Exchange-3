package com.pahimar.ee3.emc.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

public class WeightedDirectedGraph<T> implements Iterable<T> {

    private final Map<T, SortedSet<WeightedEdge<T>>> graph = new HashMap<T, SortedSet<WeightedEdge<T>>>();
    private List<T> orderedNodes = new ArrayList<T>();

    public boolean addNode(T node)
    {
        // Ignore nodes already added
        if (graph.containsKey(node)) {
            return false;
        }

        orderedNodes.add(node);
        graph.put(node, new TreeSet<WeightedEdge<T>>(new Comparator<WeightedEdge<T>>()
        {
            public int compare(WeightedEdge<T> o1, WeightedEdge<T> o2) {
                return orderedNodes.indexOf(o1)-orderedNodes.indexOf(o2);
            }
        }));
        
        return true;
    }
    
    public void addEdge(T from, T to)
    {
        addEdge(from, to, 1);
    }
    
    public void addEdge(T from, T to, int weight)
    {
        if (!(graph.containsKey(from) && graph.containsKey(to)))
        {
            throw new NoSuchElementException("Missing nodes from graph");
        }
        
        WeightedEdge<T> edge = new WeightedEdge<T>(weight, to);

        graph.get(from).add(edge);
    }
    
    @Override
    public Iterator<T> iterator() {

        return orderedNodes.iterator();
    }

}
