package com.pahimar.ee3.api.exchange;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;

import java.util.Collections;
import java.util.List;

public final class EnergyValueRegistryProxy {

    @Mod.Instance("EE3")
    private static Object ee3Mod;

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

    public static List getStacksInRange(Number start, Number finish) {
        return getStacksInRange(start, finish);
    }

    public static List getStacksInRange(EnergyValue start, EnergyValue finish) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getEnergyValueRegistry().getStacksInRange(start, finish);
        }

        return Collections.EMPTY_LIST;
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
        POST_CALCULATION
    }
}
