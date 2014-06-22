package com.pahimar.ee3.skill;

import com.pahimar.ee3.init.Skills;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.ee3.util.LogHelper;
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
        Skills.addDefaultSkills();
    }

    public boolean addSkill(Item item)
    {
        return addSkill(new ItemStack(item));
    }

    public boolean addSkill(Block block)
    {
        return addSkill(new ItemStack(block));
    }

    public boolean addSkill(ItemStack itemStack)
    {
        return addSkill(itemStack, true, true);
    }

    public boolean addSkill(ItemStack itemStack, boolean learnable, boolean recoverable)
    {
        return addSkill(itemStack, new Skill(learnable, recoverable));
    }

    public boolean addSkill(ItemStack itemStack, Skill skill)
    {
        if (!skillMap.containsKey(itemStack))
        {
            skillMap.put(itemStack, skill);
            return true;
        }

        return false;
    }

    public void dumpSkillSet()
    {
        for (ItemStack itemStack : skillMap.keySet())
        {
            LogHelper.info(String.format("%s: %s", ItemHelper.toString(itemStack), skillMap.get(itemStack)));
        }
    }
}
