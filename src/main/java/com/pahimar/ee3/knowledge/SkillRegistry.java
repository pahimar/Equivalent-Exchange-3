package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.handler.TransmutationKnowledgeHandler;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.item.ItemStack;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

// TODO Change up so that we only track what is not learnable, and what is not recoverable
// Everything that has an EnergyValue is learnable and recoverable unless there is an entry for it in either of these lists
public class SkillRegistry
{
    private static SkillRegistry SkillRegistry = null;
    private SortedMap<ItemStack, Skill> transmutationSkills;
    private Set<WrappedStack> notLearnableSet;
    private Set<WrappedStack> notRecoverableSet;

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
        transmutationSkills = new TreeMap<ItemStack, Skill>(ItemHelper.comparator);
        notLearnableSet = new TreeSet<WrappedStack>();
        notRecoverableSet = new TreeSet<WrappedStack>();
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

        if (!transmutationSkills.containsKey(unitItemStack))
        {
            transmutationSkills.put(unitItemStack, skill);
        }
    }

    public boolean hasSkillMapping(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        return transmutationSkills.containsKey(unitItemStack);
    }

    public boolean canBeLearned(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (transmutationSkills.containsKey(unitItemStack))
        {
            return transmutationSkills.get(unitItemStack).isLearnable();
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
                TransmutationKnowledge allowedKnowledge = TransmutationKnowledgeHandler.getAllowedTransmutations();

                return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && SkillRegistry.getInstance().canBeLearned(itemStack) && allowedKnowledge.isKnown(itemStack);
            }
        }

        return false;
    }

    public boolean isRecoverable(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (transmutationSkills.containsKey(unitItemStack))
        {
            return transmutationSkills.get(unitItemStack).isRecoverable();
        }

        return false;
    }

    public int getKnowledgeTier(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (transmutationSkills.containsKey(unitItemStack))
        {
            return transmutationSkills.get(unitItemStack).getKnowledgeTier();
        }

        return -1;
    }
}
