package com.pahimar.ee3.api.exchange;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class EnergyValueRegistryProxy {

    /**
     * TODO Finish JavaDoc
     *
     * @return
     */
    public static Map<WrappedStack, EnergyValue> getPreCalculationEnergyValues() {
        return getEnergyValues(Phase.PRE_CALCULATION);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @return
     */
    public static Map<WrappedStack, EnergyValue> getPostCalculationEnergyValues() {
        return getEnergyValues(Phase.POST_CALCULATION);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @return
     */
    public static Map<WrappedStack, EnergyValue> getEnergyValues() {
        return getEnergyValues(Phase.ALL);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param phase
     * @return
     */
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

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @return
     */
    public static boolean hasEnergyValue(Object object)
    {
        return hasEnergyValue(object, false);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param strict
     * @return
     */
    public static boolean hasEnergyValue(Object object, boolean strict) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().hasEnergyValue(object, strict);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @return
     */
    public static EnergyValue getEnergyValue(Object object) {
        return getEnergyValue(object, false);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param strict
     * @return
     */
    public static EnergyValue getEnergyValue(Object object, boolean strict) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValue(object, strict);
        }

        return null;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @return
     */
    public static EnergyValue getEnergyValueForStack(Object object) {
        return getEnergyValueForStack(object, false);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param strict
     * @return
     */
    public static EnergyValue getEnergyValueForStack(Object object, boolean strict) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().getEnergyValueForStack(object, strict);
        }

        return null;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param minimum
     * @param maximum
     * @return
     */
    public static Set<ItemStack> getStacksInRange(Number minimum, Number maximum) {
        return getStacksInRange(minimum, maximum);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param minimum
     * @param maximum
     * @return
     */
    public static Set<ItemStack> getStacksInRange(EnergyValue minimum, EnergyValue maximum) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().getStacksInRange(minimum, maximum);
        }

        return Collections.emptySet();
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param energyValue
     */
    public static void addPreCalculationEnergyValue(Object object, Number energyValue) {
        setEnergyValue(object, energyValue, Phase.PRE_CALCULATION);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param energyValue
     */
    public static void addPreCalculationEnergyValue(Object object, EnergyValue energyValue) {
        setEnergyValue(object, energyValue, Phase.PRE_CALCULATION);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param energyValue
     */
    public static void addPostCalculationEnergyValue(Object object, Number energyValue) {
        setEnergyValue(object, energyValue);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param energyValue
     */
    public static void addPostCalculationEnergyValue(Object object, EnergyValue energyValue) {
        setEnergyValue(object, energyValue);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param energyValue
     */
    public static void setEnergyValue(Object object, Number energyValue) {
        setEnergyValue(object, new EnergyValue(energyValue), Phase.POST_CALCULATION);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param energyValue
     */
    public static void setEnergyValue(Object object, EnergyValue energyValue) {
        setEnergyValue(object, energyValue, Phase.POST_CALCULATION);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param energyValue
     * @param phase
     */
    public static void setEnergyValue(Object object, Number energyValue, Phase phase) {

        setEnergyValue(object, new EnergyValue(energyValue), phase);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param energyValue
     * @param phase
     */
    public static void setEnergyValue(Object object, EnergyValue energyValue, Phase phase) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getEnergyValueRegistry().setEnergyValue(object, energyValue, phase);
        }
    }

    @Mod.Instance("EE3")
    private static Object ee3Mod;

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
