package com.pahimar.ee3.api;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class AbilityRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static boolean isLearnable(Block block)
    {
        return isLearnable(new ItemStack(block));
    }

    public static boolean isLearnable(Item item)
    {
        return isLearnable(new ItemStack(item));
    }

    public static boolean isLearnable(ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getAbilityRegistry().isLearnable(itemStack);
        }

        return false;
    }

    public static void setAsLearnable(Block block)
    {
        setAsLearnable(new ItemStack(block));
    }

    public static void setAsLearnable(Item item)
    {
        setAsLearnable(new ItemStack(item));
    }

    public static void setAsLearnable(ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAbilityRegistry().setAsLearnable(itemStack);
        }
    }

    public static void setAsNotLearnable(Block block)
    {
        setAsNotLearnable(new ItemStack(block));
    }

    public static void setAsNotLearnable(Item item)
    {
        setAsNotLearnable(new ItemStack(item));
    }

    public static void setAsNotLearnable(ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAbilityRegistry().setAsNotLearnable(itemStack);
        }
    }

    public static boolean isRecoverable(Block block)
    {
        return isRecoverable(new ItemStack(block));
    }

    public static boolean isRecoverable(Item item)
    {
        return isRecoverable(new ItemStack(item));
    }

    public static boolean isRecoverable(ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getAbilityRegistry().isRecoverable(itemStack);
        }

        return false;
    }

    public static void setAsRecoverable(Block block)
    {
        setAsRecoverable(new ItemStack(block));
    }

    public static void setAsRecoverable(Item item)
    {
        setAsRecoverable(new ItemStack(item));
    }

    public static void setAsRecoverable(ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAbilityRegistry().setAsRecoverable(itemStack);
        }
    }

    public static void setAsNotRecoverable(Block block)
    {
        setAsNotRecoverable(new ItemStack(block));
    }

    public static void setAsNotRecoverable(Item item)
    {
        setAsNotRecoverable(new ItemStack(item));
    }

    public static void setAsNotRecoverable(ItemStack itemStack)
    {
        init();

        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod.getAbilityRegistry().setAsNotRecoverable(itemStack);
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
}
