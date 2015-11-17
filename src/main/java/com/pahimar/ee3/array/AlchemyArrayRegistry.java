package com.pahimar.ee3.array;

import com.google.common.collect.ImmutableSortedSet;
import com.pahimar.ee3.api.array.AlchemyArray;
import com.pahimar.ee3.util.LoaderHelper;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.Loader;

import java.util.SortedSet;
import java.util.TreeSet;

public class AlchemyArrayRegistry
{
    private static AlchemyArrayRegistry alchemyArrayRegistry = null;
    private static final Object singletonSyncRoot = new Object();

    private SortedSet<AlchemyArray> registeredAlchemyArrays;

    private AlchemyArrayRegistry()
    {
    }

    public static AlchemyArrayRegistry getInstance()
    {
        if (alchemyArrayRegistry == null)
        {
            synchronized (singletonSyncRoot)
            {
                if(alchemyArrayRegistry == null)
                {
                    alchemyArrayRegistry = new AlchemyArrayRegistry();
                    alchemyArrayRegistry.init();
                }
            }
        }

        return alchemyArrayRegistry;
    }

    private void init()
    {
        registeredAlchemyArrays = new TreeSet<AlchemyArray>();
    }

    public AlchemyArray getAlchemyArray(int index)
    {
        if (registeredAlchemyArrays != null)
        {
            AlchemyArray[] alchemyArrays = new AlchemyArray[0];
            alchemyArrays = registeredAlchemyArrays.toArray(alchemyArrays);

            if (index < alchemyArrays.length)
            {
                return alchemyArrays[index];
            }
        }

        return null;
    }

    public SortedSet<AlchemyArray> getRegisteredAlchemyArrays()
    {
        return ImmutableSortedSet.copyOf(registeredAlchemyArrays);
    }

    public boolean registerAlchemyArray(AlchemyArray alchemyArray)
    {
        if (!registeredAlchemyArrays.contains(alchemyArray))
        {
            LogHelper.trace(String.format("AbilityRegistry[%s]: Mod with ID '%s' added alchemy array %s", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), alchemyArray));
            return registeredAlchemyArrays.add(alchemyArray);
        }

        return false;
    }
}
