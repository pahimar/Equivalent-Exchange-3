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

public class WeightedDirectedGraph<T, E extends WeightedEdge<T>> implements Iterable<T> {

    private final Map<T, SortedSet<E>> graph = new HashMap<T, SortedSet<E>>();
    private List<T> orderedNodes = new ArrayList<T>();

    public boolean addNode(T node)
    {
        // Ignore nodes already added
        if (graph.containsKey(node)) {
            return false;
        }

        orderedNodes.add(node);
        graph.put(node, new TreeSet<E>(new Comparator<E>()
        {
            public int compare(E o1, E o2) {
                return orderedNodes.indexOf(o1)-orderedNodes.indexOf(o2);
            }
        }));
        
        return true;
    }
    
    public void addEdge(T from, T to)
    {
        if (!(graph.containsKey(from) && graph.containsKey(to)))
        {
            throw new NoSuchElementException("Missing nodes from graph");
        }
        
        @SuppressWarnings("unchecked")
        E edge = (E) new WeightedEdge<T>(1, to);

        graph.get(from).add(edge);
    }
    
    public void addEdge(T from, T to, int weight)
    {
        if (!(graph.containsKey(from) && graph.containsKey(to)))
        {
            throw new NoSuchElementException("Missing nodes from graph");
        }
        
        @SuppressWarnings("unchecked")
        E edge = (E) new WeightedEdge<T>(weight, to);

        graph.get(from).add(edge);
    }
    
    @Override
    public Iterator<T> iterator() {

        return orderedNodes.iterator();
    }

}
