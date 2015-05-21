package com.pahimar.ee3.api.exchange;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;

import java.util.List;

public final class EnergyValueRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static void addPreAssignedEnergyValue(Object object, float energyValue)
    {
        addPreAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public static void addPreAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getEnergyValueRegistry().addPreAssignedEnergyValue(object, energyValue);
        }
    }

    public static void addPostAssignedEnergyValue(Object object, float energyValue)
    {
        addPostAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public static void addPostAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getEnergyValueRegistry().addPostAssignedExactEnergyValue(object, energyValue);
        }
    }

    public static boolean hasEnergyValue(Object object)
    {
        return hasEnergyValue(object, false);
    }

    public static boolean hasEnergyValue(Object object, boolean strict)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().hasEnergyValue(object, strict);
        }

        return false;
    }

    public static EnergyValue getEnergyValue(Object object)
    {
        return getEnergyValue(object, false);
    }

    public static EnergyValue getEnergyValue(Object object, boolean strict)
    {
        return getEnergyValue(Phase.ALL, object, strict);
    }

    public static EnergyValue getEnergyValue(Phase phase, Object object, boolean strict)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValue(phase, object, strict);
        }

        return null;
    }

    public static EnergyValue getEnergyValueForStack(Object object)
    {
        return getEnergyValueForStack(object, false);
    }

    public static EnergyValue getEnergyValueForStack(Object object, boolean strict)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValueForStack(object, strict);
        }

        return null;
    }

    public static List getStacksInRange(float start, float finish)
    {
        return getStacksInRange(new EnergyValue(start), new EnergyValue(finish));
    }

    public static List getStacksInRange(EnergyValue start, EnergyValue finish)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().getStacksInRange(start, finish);
        }

        return null;
    }

    public static void dumpEnergyValueRegistryToLog()
    {
        dumpEnergyValueRegistryToLog(Phase.ALL);
    }

    public static void dumpEnergyValueRegistryToLog(Phase phase)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getEnergyValueRegistry().dumpEnergyValueRegistryToLog(phase);
        }
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

    public enum Phase
    {
        PRE_ASSIGNMENT,
        POST_ASSIGNMENT,
        RUNTIME,
        ALL
    }
}
