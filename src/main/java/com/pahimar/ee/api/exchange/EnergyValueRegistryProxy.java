package com.pahimar.ee.api.exchange;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.exchange.WrappedStack;
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

        if (mod != null) {
            if (phase == Phase.PRE_CALCULATION) {
                ModWrapper.mod.getEnergyValueRegistry().getPreCalculationStackValueMap();
            }
            else if (phase == Phase.POST_CALCULATION) {
                ModWrapper.mod.getEnergyValueRegistry().getPostCalculationStackValueMap();
            }
            else if (phase == Phase.ALL) {
                ModWrapper.mod.getEnergyValueRegistry().getEnergyValues();
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

        if (mod != null) {
            return ModWrapper.mod.getEnergyValueRegistry().hasEnergyValue(object, strict);
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

        if (mod != null) {
            return ModWrapper.mod.getEnergyValueRegistry().getEnergyValue(object, strict);
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

        if (mod != null) {
            return ModWrapper.mod.getEnergyValueRegistry().getEnergyValueForStack(object, strict);
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

        if (mod != null) {
            return ModWrapper.mod.getEnergyValueRegistry().getStacksInRange(minimum, maximum);
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

        if (mod != null) {
            ModWrapper.mod.getEnergyValueRegistry().setEnergyValue(object, energyValue, phase);
        }
    }

    @Mod.Instance("ee")
    private static Object mod;

    private static class ModWrapper {
        private static EquivalentExchange mod;
    }

    private static void init() {

        if (mod != null) {
            ModWrapper.mod = (EquivalentExchange) mod;
        }
    }

    public enum Phase {
        PRE_CALCULATION,
        POST_CALCULATION,
        ALL
    }
}
