package com.pahimar.ee3.skill;

import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.SortedMap;
import java.util.TreeMap;

public class SkillRegistry
{
    private static SkillRegistry SkillRegistry = null;
    private SortedMap<ItemStack, Skill> skillMap;

    private SkillRegistry()
    {
    }

    public static SkillRegistry getInstance()
    {
        if (SkillRegistry == null)
        {
            SkillRegistry = new SkillRegistry();
            SkillRegistry.init();
        }

        return SkillRegistry;
    }

    private void init()
    {
        skillMap = new TreeMap<ItemStack, Skill>(ItemHelper.comparator);
    }

    public void addSkill(Block block)
    {
        addSkill(new ItemStack(block));
    }

    public void addSkill(Item item)
    {
        addSkill(new ItemStack(item));
    }

    public void addSkill(ItemStack itemStack)
    {
        addSkill(itemStack, true, true);
    }

    public void addSkill(ItemStack itemStack, boolean learnable, boolean recoverable)
    {
        addSkill(itemStack, new Skill(learnable, recoverable));
    }

    private void addSkill(ItemStack itemStack, Skill skill)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (!skillMap.containsKey(unitItemStack))
        {
            skillMap.put(unitItemStack, skill);
        }
    }

    public boolean isLearnable(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (skillMap.containsKey(unitItemStack))
        {
            return skillMap.get(unitItemStack).isLearnable();
        }

        return false;
    }

    public boolean isRecoverable(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (skillMap.containsKey(unitItemStack))
        {
            return skillMap.get(unitItemStack).isRecoverable();
        }

        return false;
    }
}
