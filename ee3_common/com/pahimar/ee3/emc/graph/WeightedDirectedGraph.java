package com.pahimar.ee3.emc.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;


public class WeightedDirectedGraph<T, E> implements Iterable<T> {

    private final Map<T, SortedSet<E>> graph = new HashMap<T, SortedSet<E>>();
    private List<T> orderedNodes = new ArrayList<T>();
    
    @Override
    public Iterator<T> iterator() {

        return orderedNodes.iterator();
    }

}
