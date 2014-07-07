package com.pahimar.ee3.api;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;

public class EnergyValueRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static void addValue(IEnergyValue energyValue)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return;
        }

        // TODO
    }

    private static void init()
    {
        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }

    public static void getValue(Object object)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return;
        }

        EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValue(object);
    }

    public static void getValue(Object object, boolean strict)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return;
        }

        EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValue(object, strict);
    }

    private static class EE3Wrapper
    {
        private static EquivalentExchange3 ee3mod;
    }
}
