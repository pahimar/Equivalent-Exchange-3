package com.pahimar.ee3.array;

import com.google.common.collect.ImmutableSortedSet;
import com.pahimar.ee3.api.array.AlchemyArray;
import com.pahimar.ee3.util.LoaderUtils;
import com.pahimar.ee3.util.LogHelper;
import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.SortedSet;
import java.util.TreeSet;

public class AlchemyArrayRegistry
{
    public static final Marker ALCHEMY_ARRAY_MARKER = MarkerManager.getMarker("EE3_ALCHEMY_ARRAY", LogHelper.MOD_MARKER);
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
            LogHelper.trace(ALCHEMY_ARRAY_MARKER, "[{}]: Mod with ID '{}' added alchemy array {}", LoaderUtils.getLoaderState(), Loader.instance().activeModContainer().getModId(), alchemyArray);
            return registeredAlchemyArrays.add(alchemyArray);
        }

        return false;
    }
}
