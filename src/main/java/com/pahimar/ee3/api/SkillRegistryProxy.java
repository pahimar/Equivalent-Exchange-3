package com.pahimar.ee3.api;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SkillRegistryProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static void addSkill(Block block)
    {
        addSkill(new ItemStack(block));
    }

    public static void addSkill(Item item)
    {
        addSkill(new ItemStack(item));
    }

    public static void addSkill(ItemStack itemStack)
    {
        addSkill(itemStack, true, true);
    }

    public static void addSkill(ItemStack itemStack, boolean isLearnable, boolean isRecovereable)
    {
        addSkill(itemStack, isLearnable, isRecovereable, 0);
    }

    public static void addSkill(ItemStack itemStack, boolean isLearnable, boolean isRecovereable, int tier)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return;
        }

        EE3Wrapper.ee3mod.getSkillRegistry().addSkill(itemStack, isLearnable, isRecovereable, tier);
    }

    public static boolean isLearnable(ItemStack itemStack)
    {
        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod == null)
        {
            return false;
        }

        return EE3Wrapper.ee3mod.getSkillRegistry().isLearnable(itemStack);
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
