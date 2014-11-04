package com.pahimar.ee3.skill;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.handler.PlayerKnowledgeHandler;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.util.ItemHelper;
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

    public void addSkill(ItemStack itemStack, boolean learnable, boolean recoverable)
    {
        addSkill(itemStack, new Skill(learnable, recoverable, 0));
    }

    public void addSkill(ItemStack itemStack, boolean learnable, boolean recoverable, int tier)
    {
        addSkill(itemStack, new Skill(learnable, recoverable, tier));
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

    public boolean hasSkillMapping(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        return skillMap.containsKey(unitItemStack);
    }

    public boolean canBeLearned(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (skillMap.containsKey(unitItemStack))
        {
            return skillMap.get(unitItemStack).canBeLearned();
        }

        return false;
    }

    public static boolean canLearnItemStack(ItemStack itemStack)
    {
        if (itemStack == null || itemStack.getItem() == null)
        {
            return false;
        }
        else
        {
            if (Settings.Transmutation.knowledgeMode.equalsIgnoreCase("All"))
            {
                if (SkillRegistry.getInstance().hasSkillMapping(itemStack))
                {
                    return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && SkillRegistry.getInstance().canBeLearned(itemStack);
                }
                else
                {
                    return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack);
                }
            }
            else if (Settings.Transmutation.knowledgeMode.equalsIgnoreCase("Select"))
            {
                return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && SkillRegistry.getInstance().canBeLearned(itemStack);
            }
            else if (Settings.Transmutation.knowledgeMode.equalsIgnoreCase("Tier"))
            {
                int itemStackKnowledgeTier = SkillRegistry.getInstance().getKnowledgeTier(itemStack);

                return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && SkillRegistry.getInstance().canBeLearned(itemStack) && itemStackKnowledgeTier >= 0 && itemStackKnowledgeTier <= Settings.Transmutation.maxKnowledgeTier;
            }
            else if (Settings.Transmutation.knowledgeMode.equalsIgnoreCase("Restricted"))
            {
                PlayerKnowledge allowedKnowledge = PlayerKnowledgeHandler.getAllowedPlayerKnowledge();

                return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && SkillRegistry.getInstance().canBeLearned(itemStack) && allowedKnowledge.isItemStackKnown(itemStack);
            }
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

    public int getKnowledgeTier(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (skillMap.containsKey(unitItemStack))
        {
            return skillMap.get(unitItemStack).getKnowledgeTier();
        }

        return -1;
    }
}
