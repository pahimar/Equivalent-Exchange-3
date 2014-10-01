package com.pahimar.ee3.api;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class SkillRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static void addSkill(Block block)
    {
        addSkill(new ItemStack(block));
    }

    public static void addSkill(Block block, boolean isLearnable, boolean isRecoverable)
    {
        addSkill(new ItemStack(block), isLearnable, isRecoverable);
    }

    public static void addSkill(Item item)
    {
        addSkill(new ItemStack(item));
    }

    public static void addSkill(Item item, boolean isLearnable, boolean isRecoverable)
    {
        addSkill(new ItemStack(item), isLearnable, isRecoverable);
    }

    public static void addSkill(ItemStack itemStack)
    {
        addSkill(itemStack, true, true);
    }

    public static void addSkill(ItemStack itemStack, boolean isLearnable, boolean isRecovereable)
    {
        addSkill(itemStack, isLearnable, isRecovereable, 0);
    }

    public static void addSkill(ItemStack itemStack, boolean isLearnable, boolean isRecovereable, int knowledgeTier)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return;
        }

        EE3Wrapper.ee3mod.getSkillRegistry().addSkill(itemStack, isLearnable, isRecovereable, knowledgeTier);
    }

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

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return false;
        }

        return EE3Wrapper.ee3mod.getSkillRegistry().canBeLearned(itemStack);
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

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return false;
        }

        return EE3Wrapper.ee3mod.getSkillRegistry().isRecoverable(itemStack);
    }

    public static int getKnowledgeTier(Block block)
    {
        return getKnowledgeTier(new ItemStack(block));
    }

    public static int getKnowledgeTier(Item item)
    {
        return getKnowledgeTier(new ItemStack(item));
    }

    public static int getKnowledgeTier(ItemStack itemStack)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return -1;
        }

        return EE3Wrapper.ee3mod.getSkillRegistry().getKnowledgeTier(itemStack);
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
