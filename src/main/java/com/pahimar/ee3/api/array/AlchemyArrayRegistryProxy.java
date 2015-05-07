package com.pahimar.ee3.api.array;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;

import java.util.SortedSet;

public class AlchemyArrayRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static boolean registerAlchemyArray(AlchemyArray alchemyArray)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getAlchemyArrayRegistry().registerAlchemyArray(alchemyArray);
        }

        return false;
    }

    public static SortedSet<AlchemyArray> getRegisteredAlchemyArrays()
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getAlchemyArrayRegistry().getRegisteredAlchemyArrays();
        }

        return null;
    }

    private static class EE3Wrapper
    {
        private static EquivalentExchange3 ee3mod;
    }

    private static void init()
    {
        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }
}
