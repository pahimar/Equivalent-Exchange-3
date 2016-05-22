package com.pahimar.ee3.api.knowledge;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class PlayerKnowledgeRegistryProxy {

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param object
     * @return
     */
    public static boolean doesPlayerKnow(EntityPlayer entityPlayer, Object object) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().doesPlayerKnow(entityPlayer, object);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param object
     * @return
     */
    public static boolean doesPlayerKnow(String playerName, Object object) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().doesPlayerKnow(playerName, object);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param object
     * @return
     */
    public static boolean canPlayerLearn(EntityPlayer entityPlayer, Object object) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().canPlayerLearn(entityPlayer, object);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param object
     * @return
     */
    public static boolean canPlayerLearn(String playerName, Object object) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().canPlayerLearn(playerName, object);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @return
     */
    public static Set<ItemStack> getKnownItemStacks(EntityPlayer entityPlayer) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().getKnownItemStacks(entityPlayer);
        }

        return Collections.EMPTY_SET;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @return
     */
    public static Set<ItemStack> getKnownItemStacks(String playerName) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().getKnownItemStacks(playerName);
        }

        return Collections.EMPTY_SET;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param object
     */
    public static void teachPlayer(EntityPlayer entityPlayer, Object object) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().teachPlayer(entityPlayer, object);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param object
     */
    public static void teachPlayer(String playerName, Object object) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().teachPlayer(playerName, object);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param objects
     */
    public static void teachPlayer(EntityPlayer entityPlayer, Collection<?> objects) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().teachPlayer(entityPlayer, objects);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param objects
     */
    public static void teachPlayer(String playerName, Collection<?> objects) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().teachPlayer(playerName, objects);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param object
     */
    public static void makePlayerForget(EntityPlayer entityPlayer, Object object) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().makePlayerForget(entityPlayer, object);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param object
     */
    public static void makePlayerForget(String playerName, Object object) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().makePlayerForget(playerName, object);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param objects
     */
    public static void makePlayerForget(EntityPlayer entityPlayer, Collection<?> objects) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().makePlayerForget(entityPlayer, objects);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param objects
     */
    public static void makePlayerForget(String playerName, Collection<?> objects) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().makePlayerForget(playerName, objects);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     */
    public static void makePlayerForgetAll(EntityPlayer entityPlayer) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().makePlayerForgetAll(entityPlayer);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     */
    public static void makePlayerForgetAll(String playerName) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getPlayerKnowledgeRegistry().makePlayerForgetAll(playerName);
        }
    }

    @Mod.Instance("EE3")
    private static Object ee3Mod;

    private static class EE3Wrapper {
        private static EquivalentExchange3 ee3mod;
    }

    private static void init() {

        if (ee3Mod != null) {
            PlayerKnowledgeRegistryProxy.EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }
}
