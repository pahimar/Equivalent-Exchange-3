package com.pahimar.ee3.api.knowledge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

/**
 * @deprecated as of API 0.4.0; use {@link PlayerKnowledgeRegistryProxy} instead
 * @see PlayerKnowledgeRegistryProxy
 */
@Deprecated
public class TransmutationKnowledgeRegistryProxy {

    @Deprecated
    public static boolean doesPlayerKnow(EntityPlayer entityPlayer, ItemStack itemStack) {
        return PlayerKnowledgeRegistryProxy.doesPlayerKnow(entityPlayer, itemStack);
    }

    @Deprecated
    public static boolean doesPlayerKnow(UUID playerUUID, ItemStack itemStack) {
        return false;
    }

    @Deprecated
    public static boolean canPlayerLearn(EntityPlayer entityPlayer, ItemStack itemStack) {
        return PlayerKnowledgeRegistryProxy.canPlayerLearn(entityPlayer, itemStack);
    }

    @Deprecated
    public static boolean canPlayerLearn(UUID playerUUID, ItemStack itemStack) {
        return false;
    }

    @Deprecated
    public static Set<ItemStack> getPlayerKnownTransmutations(EntityPlayer entityPlayer) {
        return PlayerKnowledgeRegistryProxy.getKnownItemStacks(entityPlayer);
    }

    @Deprecated
    public static Set<ItemStack> getPlayerKnownTransmutations(UUID playerUUID) {
        return Collections.EMPTY_SET;
    }

    @Deprecated
    public static Set<ItemStack> getPlayerKnownTransmutationsFilteredStartsWith(EntityPlayer entityPlayer, String filterString) {
        return PlayerKnowledgeRegistryProxy.getKnownItemStacks(entityPlayer);
    }

    @Deprecated
    public static Set<ItemStack> getPlayerKnownTransmutationsFilteredStartsWith(UUID playerUUID, String filterString) {
        return Collections.EMPTY_SET;
    }

    @Deprecated
    public static Set<ItemStack> getPlayerKnownTransmutationsFilteredContains(EntityPlayer entityPlayer, String filterString) {
        return PlayerKnowledgeRegistryProxy.getKnownItemStacks(entityPlayer);
    }

    @Deprecated
    public static Set<ItemStack> getPlayerKnownTransmutationsFilteredContains(UUID playerUUID, String filterString) {
        return Collections.EMPTY_SET;
    }

    @Deprecated
    public static void teachPlayer(EntityPlayer entityPlayer, ItemStack itemStack) {
        PlayerKnowledgeRegistryProxy.teachPlayer(entityPlayer, itemStack);
    }

    @Deprecated
    public static void teachPlayer(UUID playerUUID, ItemStack itemStack) {
        // NOOP
    }

    @Deprecated
    public static void makePlayerForget(EntityPlayer entityPlayer, ItemStack itemStack) {
        PlayerKnowledgeRegistryProxy.makePlayerForget(entityPlayer, itemStack);
    }

    @Deprecated
    public static void makePlayerForget(UUID playerUUID, ItemStack itemStack) {
        // NOOP
    }

    @Deprecated
    public static void makePlayerForgetEverything(EntityPlayer entityPlayer) {
        PlayerKnowledgeRegistryProxy.makePlayerForgetAll(entityPlayer);
    }

    @Deprecated
    public static void makePlayerForgetEverything(UUID playerUUID) {
        // NOOP
    }

    @Deprecated
    public static boolean doesTemplateKnow(ItemStack itemStack) {
        return false;
    }

    @Deprecated
    public static Set<ItemStack> getTemplateKnownTransmutations() {
        return Collections.EMPTY_SET;
    }

    @Deprecated
    public static Set<ItemStack> getTemplateKnownTransmutationsFilteredStartsWith(String filterString) {
        return Collections.EMPTY_SET;
    }

    @Deprecated
    public static Set<ItemStack> getTemplateKnownTransmutationsFilteredContains(String filterString) {
        return Collections.EMPTY_SET;
    }

    @Deprecated
    public static void teachTemplate(ItemStack itemStack) {
        // NOOP
    }

    @Deprecated
    public static void makeTemplateForget(ItemStack itemStack) {
        // NOOP
    }

    @Deprecated
    public static void makeTemplateForgetEverything() {
        // NOOP
    }
}
