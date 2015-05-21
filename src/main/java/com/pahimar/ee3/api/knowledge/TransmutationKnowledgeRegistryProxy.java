package com.pahimar.ee3.api.knowledge;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Set;
import java.util.UUID;

public class TransmutationKnowledgeRegistryProxy
{
    public static boolean doesPlayerKnow(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().doesPlayerKnow(entityPlayer, itemStack);
        }

        return false;
    }

    public static boolean doesPlayerKnow(UUID playerUUID, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().doesPlayerKnow(playerUUID, itemStack);
        }

        return false;
    }

    public static boolean canPlayerLearn(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().canPlayerLearn(entityPlayer, itemStack);
        }

        return false;
    }

    public static boolean canPlayerLearn(UUID playerUUID, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().canPlayerLearn(playerUUID, itemStack);
        }

        return false;
    }

    public static Set<ItemStack> getPlayerKnownTransmutations(EntityPlayer entityPlayer)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getPlayersKnownTransmutations(entityPlayer);
        }

        return null;
    }

    public static Set<ItemStack> getPlayerKnownTransmutations(UUID playerUUID)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getPlayersKnownTransmutations(playerUUID);
        }

        return null;
    }

    public static Set<ItemStack> getPlayerKnownTransmutationsFilteredStartsWith(EntityPlayer entityPlayer, String filterString)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getPlayersKnownTransmutationsFilteredStartsWith(entityPlayer, filterString);
        }

        return null;
    }

    public static Set<ItemStack> getPlayerKnownTransmutationsFilteredStartsWith(UUID playerUUID, String filterString)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getPlayersKnownTransmutationsFilteredStartsWith(playerUUID, filterString);
        }

        return null;
    }

    public static Set<ItemStack> getPlayerKnownTransmutationsFilteredContains(EntityPlayer entityPlayer, String filterString)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getPlayersKnownTransmutationsFilteredContains(entityPlayer, filterString);
        }

        return null;
    }

    public static Set<ItemStack> getPlayerKnownTransmutationsFilteredContains(UUID playerUUID, String filterString)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getPlayersKnownTransmutationsFilteredContains(playerUUID, filterString);
        }

        return null;
    }

    public static void teachPlayer(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().teachPlayer(entityPlayer, itemStack);
        }
    }

    public static void teachPlayer(UUID playerUUID, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().teachPlayer(playerUUID, itemStack);
        }
    }

    public static void makePlayerForget(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().makePlayerForget(entityPlayer, itemStack);
        }
    }

    public static void makePlayerForget(UUID playerUUID, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().makePlayerForget(playerUUID, itemStack);
        }
    }

    public static void makePlayerForgetEverything(EntityPlayer entityPlayer)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().makePlayerForgetEverything(entityPlayer);
        }
    }

    public static void makePlayerForgetEverything(UUID playerUUID)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().makePlayerForgetEverything(playerUUID);
        }
    }

    public static boolean doesTemplateKnow(ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().doesTemplateKnow(itemStack);
        }

        return false;
    }

    public static Set<ItemStack> getTemplateKnownTransmutations()
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getTemplatesKnownTransmutations();
        }

        return null;
    }

    public static Set<ItemStack> getTemplateKnownTransmutationsFilteredStartsWith(String filterString)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getTemplatesKnownTransmutationsFilteredStartsWith(filterString);
        }

        return null;
    }

    public static Set<ItemStack> getTemplateKnownTransmutationsFilteredContains(String filterString)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getTemplatesKnownTransmutationsFilteredContains(filterString);
        }

        return null;
    }

    public static void teachTemplate(ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().teachTemplate(itemStack);
        }
    }

    public static void makeTemplateForget(ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().makeTemplateForget(itemStack);
        }
    }

    public static void makeTemplateForgetEverything()
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().makeTemplateForgetEverything();
        }
    }

    @Mod.Instance("EE3")
    private static Object ee3Mod;

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
}
