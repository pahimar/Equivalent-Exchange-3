package com.pahimar.ee3.api.knowledge;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;

public final class AbilityRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static boolean isLearnable(Object object)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getAbilityRegistry().isLearnable(object);
        }

        return false;
    }

    public static void setAsLearnable(Object object)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAbilityRegistry().setAsLearnable(object);
        }
    }

    public static void setAsNotLearnable(Object object)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAbilityRegistry().setAsNotLearnable(object);
        }
    }

    public static boolean isRecoverable(Object object)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getAbilityRegistry().isRecoverable(object);
        }

        return false;
    }

    public static void setAsRecoverable(Object object)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAbilityRegistry().setAsRecoverable(object);
        }
    }

    public static void setAsNotRecoverable(Object object)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAbilityRegistry().setAsNotRecoverable(object);
        }
    }

    public static void dumpAbilityRegistryToLog()
    {
        dumpAbilityRegistryToLog(Abilities.ALL);
    }

    public static void dumpAbilityRegistryToLog(Abilities ability)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAbilityRegistry().dumpAbilityRegistryToLog(ability);
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

    public enum Abilities
    {
        NOT_LEARNABLE,
        NOT_RECOVERABLE,
        ALL
    }
}
