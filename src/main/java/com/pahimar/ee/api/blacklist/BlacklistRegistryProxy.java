package com.pahimar.ee.api.blacklist;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.exchange.WrappedStack;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

public final class BlacklistRegistryProxy {

    /**
     * TODO Finish JavaDoc
     *
     * @return
     */
    public static Set<WrappedStack> getKnowledgeBlacklist() {
        return getBlacklist(Blacklist.KNOWLEDGE);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @return
     */
    public static Set<WrappedStack> getExchangeBlacklist() {
        return getBlacklist(Blacklist.EXCHANGE);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param blacklist
     * @return
     */
    public static Set<WrappedStack> getBlacklist(Blacklist blacklist) {

        init();

        if (mod != null) {
            if (blacklist == Blacklist.KNOWLEDGE) {
                return ModWrappper.mod.getBlacklistRegistry().getKnowledgeBlacklist();
            }
            else if (blacklist == Blacklist.EXCHANGE) {
                return ModWrappper.mod.getBlacklistRegistry().getExchangeBlacklist();
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
    public static boolean isLearnable(Object object) {

        init();

        if (mod != null) {
            return ModWrappper.mod.getBlacklistRegistry().isLearnable(object);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @return
     */
    public static boolean isExchangeable(Object object) {

        init();

        if (mod != null) {
            return ModWrappper.mod.getBlacklistRegistry().isExchangeable(object);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     */
    public static void setAsLearnable(Object object) {
        removeFromBlacklist(object, Blacklist.KNOWLEDGE);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     */
    public static void setAsNotLearnable(Object object) {
        addToBlacklist(object, Blacklist.KNOWLEDGE);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     */
    public static void setAsExchangeable(Object object) {
        removeFromBlacklist(object, Blacklist.EXCHANGE);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     */
    public static void setAsNotExchangeable(Object object) {
        addToBlacklist(object, Blacklist.EXCHANGE);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param blacklist
     */
    public static void addToBlacklist(Object object, Blacklist blacklist) {

        init();

        if (mod != null) {
            ModWrappper.mod.getBlacklistRegistry().addToBlacklist(object, blacklist);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param blacklist
     */
    public static void removeFromBlacklist(Object object, Blacklist blacklist) {

        init();

        if (mod != null) {
            ModWrappper.mod.getBlacklistRegistry().removeFromBlacklist(object, blacklist);
        }
    }

    @Mod.Instance("ee")
    private static Object mod;

    private static class ModWrappper {
        private static EquivalentExchange mod;
    }

    private static void init() {

        if (mod != null) {
            ModWrappper.mod = (EquivalentExchange) mod;
        }
    }

    public enum Blacklist {
        KNOWLEDGE,
        EXCHANGE
    }
}
