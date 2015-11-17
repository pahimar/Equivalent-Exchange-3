package com.pahimar.ee3.api.exchange;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import cpw.mods.fml.common.Mod;

import java.util.List;

public final class EnergyValueRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static void addPreCalculationEnergyValue(Object object, float energyValue)
    {
        addPreCalculationEnergyValue(object, new EnergyValue(energyValue));
    }

    @Deprecated
    public static void addPreAssignedEnergyValue(Object object, float energyValue)
    {
        addPreAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public static void addPreCalculationEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        if (ee3Mod != null)
        {
            EnergyValueRegistry.Factory.addPreCalculationEnergyValue(object, energyValue);
        }
    }

    @Deprecated
    public static void addPreAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        if (ee3Mod != null)
        {
            EnergyValueRegistry.Factory.addPreCalculationEnergyValue(object, energyValue);
        }
    }

    public static void addPostCalculationEnergyValue(Object object, float energyValue)
    {
        addPostCalculationEnergyValue(object, new EnergyValue(energyValue));
    }

    @Deprecated
    public static void addPostAssignedEnergyValue(Object object, float energyValue)
    {
        addPostAssignedEnergyValue(object, new EnergyValue(energyValue));
    }

    public static void addPostCalculationEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        if (ee3Mod != null)
        {
            EnergyValueRegistry.Factory.addPostCalculationExactEnergyValue(object, energyValue);
        }
    }

    @Deprecated
    public static void addPostAssignedEnergyValue(Object object, EnergyValue energyValue)
    {
        init();

        if (ee3Mod != null)
        {
            EnergyValueRegistry.Factory.addPostCalculationExactEnergyValue(object, energyValue);
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
        /**
         * @Deprecated Use PRE_CALCULATION instead
         */
        @Deprecated PRE_ASSIGNMENT,

        PRE_CALCULATION,

        /**
         * @Deprecated Use POST_CALCULATION instead
         */
        @Deprecated POST_ASSIGNMENT,

        POST_CALCULATION,

        RUNTIME,

        ALL
    }
}
