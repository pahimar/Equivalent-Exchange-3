package com.pahimar.ee3.api.blacklist;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.exchange.WrappedStack;
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

        if (ee3Mod != null) {
            if (blacklist == Blacklist.KNOWLEDGE) {
                return EE3Wrapper.ee3mod.getBlacklistRegistry().getKnowledgeBlacklist();
            }
            else if (blacklist == Blacklist.EXCHANGE) {
                return EE3Wrapper.ee3mod.getBlacklistRegistry().getExchangeBlacklist();
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

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getBlacklistRegistry().isLearnable(object);
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

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getBlacklistRegistry().isExchangeable(object);
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

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getBlacklistRegistry().addToBlacklist(object, blacklist);
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

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getBlacklistRegistry().removeFromBlacklist(object, blacklist);
        }
    }

    @Mod.Instance("ee3")
    private static Object ee3Mod;

    private static class EE3Wrapper {
        private static EquivalentExchange3 ee3mod;
    }

    private static void init() {

        if (ee3Mod != null) {
            BlacklistRegistryProxy.EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }

    public enum Blacklist {
        KNOWLEDGE,
        EXCHANGE
    }
}
