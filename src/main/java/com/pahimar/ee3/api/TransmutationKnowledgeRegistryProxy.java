package com.pahimar.ee3.api;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Set;

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

    public static boolean canPlayerLearn(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().canPlayerLearn(entityPlayer, itemStack);
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

    public static Set<ItemStack> getPlayerKnownTransmutationsFilteredStartsWith(EntityPlayer entityPlayer, String filterString)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().getPlayersKnownTransmutationsFilteredStartsWith(entityPlayer, filterString);
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

    public static void teachPlayer(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().teachPlayer(entityPlayer, itemStack);
        }
    }

    public static void teachPlayerEverything(EntityPlayer entityPlayer)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().teachPlayerEverything(entityPlayer);
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

    public static void makePlayerForgetEverything(EntityPlayer entityPlayer)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().makePlayerForgetEverything(entityPlayer);
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

    public static void teachTemplateEverything()
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getTransmutationKnowledgeRegistry().teachTemplateEverything();
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
