package com.pahimar.ee3.api.knowledge;

import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;

@Deprecated
public final class AbilityRegistryProxy {

    /**
     * @deprecated See {@link BlacklistRegistryProxy#isLearnable(Object)}
     */
    public static boolean isLearnable(Object object) {
        return BlacklistRegistryProxy.isLearnable(object);
    }

    /**
     * @deprecated See {@link BlacklistRegistryProxy#setAsLearnable(Object)}
     */
    public static void setAsLearnable(Object object) {
        BlacklistRegistryProxy.setAsLearnable(object);
    }

    /**
     * @deprecated See {@link BlacklistRegistryProxy#setAsNotLearnable(Object)}
     */
    public static void setAsNotLearnable(Object object) {
        BlacklistRegistryProxy.setAsNotLearnable(object);
    }

    /**
     * @deprecated See {@link BlacklistRegistryProxy#isExchangeable(Object)}
     */
    public static boolean isRecoverable(Object object) {
        return BlacklistRegistryProxy.isExchangeable(object);
    }

    /**
     * @deprecated See {@link BlacklistRegistryProxy#setAsExchangeable(Object)}
     */
    public static void setAsRecoverable(Object object) {
        BlacklistRegistryProxy.setAsExchangeable(object);
    }

    /**
     * @deprecated See {@link BlacklistRegistryProxy#setAsNotExchangeable(Object)}
     */
    public static void setAsNotRecoverable(Object object) {
        BlacklistRegistryProxy.setAsNotExchangeable(object);
    }

    /**
     * @deprecated will be removed from EE3 in newer versions of Minecraft
     */
    public static void dumpAbilityRegistryToLog() {
        // NOOP
    }

    /**
     * @deprecated will be removed from EE3 in newer versions of Minecraft
     */
    public static void dumpAbilityRegistryToLog(Abilities ability) {
        // NOOP
    }

    public enum Abilities {
        NOT_LEARNABLE,
        NOT_RECOVERABLE,
        ALL
    }
}
