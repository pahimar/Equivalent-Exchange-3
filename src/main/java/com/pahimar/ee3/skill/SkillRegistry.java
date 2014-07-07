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
        if (!skillMap.containsKey(itemStack))
        {
            skillMap.put(itemStack, skill);
        }
    }

    public void addSkill(Block block)
    {
        addSkill(new ItemStack(block));
    }

    public boolean isLearnable(ItemStack itemStack)
    {
        if (skillMap.containsKey(itemStack))
        {
            return skillMap.get(itemStack).isLearnable();
        }

        return false;
    }

    public boolean isRecoverable(ItemStack itemStack)
    {
        if (skillMap.containsKey(itemStack))
        {
            return skillMap.get(itemStack).isRecoverable();
        }

        return false;
    }
}
