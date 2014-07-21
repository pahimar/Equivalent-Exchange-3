package com.pahimar.ee3.api;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;

import java.util.List;

public final class EnergyValueRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static void addPreAssignedEnergyValue(Object object, int energyValue)
    {
        addPreAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public static void addPreAssignedEnergyValue(Object object, float energyValue)
    {
        addPreAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public static void addPreAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return;
        }

        EE3Wrapper.ee3mod.getEnergyValueRegistry().addPreAssignedEnergyValue(object, energyValue);
    }

    public static void addPostAssignedEnergyValue(Object object, int energyValue)
    {
        addPostAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public static void addPostAssignedEnergyValue(Object object, float energyValue)
    {
        addPostAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public static void addPostAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return;
        }

        EE3Wrapper.ee3mod.getEnergyValueRegistry().addPostAssignedEnergyValue(object, energyValue);
    }

    public static boolean hasEnergyValue(Object object)
    {
        return hasEnergyValue(object, false);
    }

    public static boolean hasEnergyValue(Object object, boolean strict)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return false;
        }

        return EE3Wrapper.ee3mod.getEnergyValueRegistry().hasEnergyValue(object, strict);
    }

    public static EnergyValue getEnergyValue(Object object)
    {
        return getEnergyValue(object, false);
    }

    public static EnergyValue getEnergyValue(Object object, boolean strict)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return null;
        }

        return EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValue(object, strict);
    }

    public List getStacksInRange(int start, int finish)
    {
        return getStacksInRange(new EnergyValue(start), new EnergyValue(finish));
    }

    public List getStacksInRange(float start, float finish)
    {
        return getStacksInRange(new EnergyValue(start), new EnergyValue(finish));
    }

    public List getStacksInRange(EnergyValue start, EnergyValue finish)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return null;
        }

        return EE3Wrapper.ee3mod.getEnergyValueRegistry().getStacksInRange(start, finish);
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
