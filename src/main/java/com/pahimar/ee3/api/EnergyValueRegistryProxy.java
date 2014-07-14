package com.pahimar.ee3.api;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;

public final class EnergyValueRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public final static void addPreAssignedEnergyValue(Object object, int energyValue)
    {
        addPreAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public final static void addPreAssignedEnergyValue(Object object, float energyValue)
    {
        addPreAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public final static void addPreAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return;
        }

        EE3Wrapper.ee3mod.getEnergyValueRegistry().addPreAssignedEnergyValue(object, energyValue);
    }

    public final static void addPostAssignedEnergyValue(Object object, int energyValue)
    {
        addPostAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public final static void addPostAssignedEnergyValue(Object object, float energyValue)
    {
        addPostAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public final static void addPostAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return;
        }

        EE3Wrapper.ee3mod.getEnergyValueRegistry().addPostAssignedEnergyValue(object, energyValue);
    }

    public final static boolean hasEnergyValue(Object object)
    {
        return hasEnergyValue(object, false);
    }

    public final static boolean hasEnergyValue(Object object, boolean strict)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return false;
        }

        return EE3Wrapper.ee3mod.getEnergyValueRegistry().hasEnergyValue(object, strict);
    }

    public final static EnergyValue getEnergyValue(Object object)
    {
        return getEnergyValue(object, false);
    }

    public final static EnergyValue getEnergyValue(Object object, boolean strict)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return null;
        }

        return EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValue(object, strict);
    }

    private static void init()
    {
        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }

    private static class EE3Wrapper
    {
        private static EquivalentExchange3 ee3mod;
    }
}
