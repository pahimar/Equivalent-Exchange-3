package com.pahimar.ee3.array;

import com.google.common.collect.ImmutableSortedSet;
import com.pahimar.ee3.api.AlchemyArray;

import java.util.SortedSet;
import java.util.TreeSet;

public class AlchemyArrayRegistry
{
    private static AlchemyArrayRegistry alchemyArrayRegistry = null;
    private SortedSet<AlchemyArray> registeredAlchemyArrays;

    private AlchemyArrayRegistry()
    {
    }

    public static AlchemyArrayRegistry getInstance()
    {
        if (alchemyArrayRegistry == null)
        {
            alchemyArrayRegistry = new AlchemyArrayRegistry();
            alchemyArrayRegistry.init();
        }

        return alchemyArrayRegistry;
    }

    private void init()
    {
        registeredAlchemyArrays = new TreeSet<AlchemyArray>();
    }

    public SortedSet<AlchemyArray> getRegisteredAlchemyArrays()
    {
        return ImmutableSortedSet.copyOf(registeredAlchemyArrays);
    }

    public boolean registerAlchemyArray(AlchemyArray alchemyArray)
    {
        if (!registeredAlchemyArrays.contains(alchemyArray))
        {
            return registeredAlchemyArrays.add(alchemyArray);
        }

        return false;
    }
}
