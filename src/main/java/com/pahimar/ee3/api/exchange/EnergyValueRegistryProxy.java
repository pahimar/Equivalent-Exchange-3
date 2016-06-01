package com.pahimar.ee3.api.exchange;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class EnergyValueRegistryProxy {

    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static Map<WrappedStack, EnergyValue> getPreCalculationEnergyValues() {
        return getEnergyValues(Phase.PRE_CALCULATION);
    }

    public static Map<WrappedStack, EnergyValue> getPostCalculationEnergyValues() {
        return getEnergyValues(Phase.POST_CALCULATION);
    }

    public static Map<WrappedStack, EnergyValue> getEnergyValues() {
        return getEnergyValues(Phase.ALL);
    }

    public static Map<WrappedStack, EnergyValue> getEnergyValues(Phase phase) {

        init();

        if (ee3Mod != null) {
            if (phase == Phase.PRE_CALCULATION) {
                EE3Wrapper.ee3mod.getEnergyValueRegistry().getPreCalculationStackValueMap();
            }
            else if (phase == Phase.POST_CALCULATION) {
                EE3Wrapper.ee3mod.getEnergyValueRegistry().getPostCalculationStackValueMap();
            }
            else if (phase == Phase.ALL) {
                EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValues();
            }
        }

        return null;
    }

    public static boolean hasEnergyValue(Object object)
    {
        return hasEnergyValue(object, false);
    }

    public static boolean hasEnergyValue(Object object, boolean strict) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().hasEnergyValue(object, strict);
        }

        return false;
    }

    public static EnergyValue getEnergyValue(Object object) {
        return getEnergyValue(object, false);
    }

    public static EnergyValue getEnergyValue(Object object, boolean strict) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValue(object, strict);
        }

        return null;
    }

    public static EnergyValue getEnergyValueForStack(Object object) {
        return getEnergyValueForStack(object, false);
    }

    public static EnergyValue getEnergyValueForStack(Object object, boolean strict) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValueForStack(object, strict);
        }

        return null;
    }

    // TODO List -> Collection
    public static List getStacksInRange(Number start, Number finish) {
        return getStacksInRange(start, finish);
    }

    // TODO List -> Collection
    public static List getStacksInRange(EnergyValue start, EnergyValue finish) {

        init();

        if (ee3Mod != null) {
            return new ArrayList<>(EE3Wrapper.ee3mod.getEnergyValueRegistry().getStacksInRange(start, finish));
        }

        return Collections.EMPTY_LIST;
    }

    /**
     *
     * @param object
     * @param energyValue
     */
    public static void addPreCalculationEnergyValue(Object object, Number energyValue) {
        setEnergyValue(object, energyValue, Phase.PRE_CALCULATION);
    }

    /**
     *
     * @param object
     * @param energyValue
     */
    public static void addPreCalculationEnergyValue(Object object, EnergyValue energyValue) {
        setEnergyValue(object, energyValue, Phase.PRE_CALCULATION);
    }

    /**
     *
     * @param object
     * @param energyValue
     */
    public static void addPostCalculationEnergyValue(Object object, Number energyValue) {
        setEnergyValue(object, energyValue);
    }

    /**
     *
     * @param object
     * @param energyValue
     */
    public static void addPostCalculationEnergyValue(Object object, EnergyValue energyValue) {
        setEnergyValue(object, energyValue);
    }

    public static void setEnergyValue(Object object, Number energyValue) {
        setEnergyValue(object, new EnergyValue(energyValue), Phase.POST_CALCULATION);
    }

    public static void setEnergyValue(Object object, EnergyValue energyValue) {
        setEnergyValue(object, energyValue, Phase.POST_CALCULATION);
    }

    public static void setEnergyValue(Object object, Number energyValue, Phase phase) {

        setEnergyValue(object, new EnergyValue(energyValue), phase);
    }

    public static void setEnergyValue(Object object, EnergyValue energyValue, Phase phase) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getEnergyValueRegistry().setEnergyValue(object, energyValue, phase);
        }
    }

    private static class EE3Wrapper {
        private static EquivalentExchange3 ee3mod;
    }

    private static void init() {

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }

    public enum Phase {
        PRE_CALCULATION,
        POST_CALCULATION,
        ALL
    }
}
