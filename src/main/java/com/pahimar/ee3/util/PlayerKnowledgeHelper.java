package com.pahimar.ee3.util;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.handler.PlayerKnowledgeHandler;
import com.pahimar.ee3.item.ItemAlchemicalTome;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.skill.PlayerKnowledge;
import com.pahimar.ee3.skill.SkillRegistry;
import net.minecraft.item.ItemStack;

public class PlayerKnowledgeHelper
{
    public static boolean canLearnItemStack(ItemStack itemStack, ItemStack alchemicalTomeStack)
    {
        if (itemStack == null || itemStack.getItem() == null || alchemicalTomeStack == null || !(alchemicalTomeStack.getItem() instanceof ItemAlchemicalTome))
        {
            return false;
        }
        else
        {
            PlayerKnowledge playerKnowledge = ItemAlchemicalTome.readPlayerKnowledge(alchemicalTomeStack);
            return canLearnItemStack(itemStack, playerKnowledge);
        }
    }

    public static boolean canLearnItemStack(ItemStack itemStack, PlayerKnowledge playerKnowledge)
    {
        if (itemStack == null || itemStack.getItem() == null || playerKnowledge == null)
        {
            return false;
        }
        else
        {
            if (Settings.Transmutation.knowledgeMode.equalsIgnoreCase("All"))
            {
                return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack)
                        && !playerKnowledge.isItemStackKnown(itemStack);
            }
            else if (Settings.Transmutation.knowledgeMode.equalsIgnoreCase("Select"))
            {
                return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack)
                        && !playerKnowledge.isItemStackKnown(itemStack)
                        && SkillRegistry.getInstance().isLearnable(itemStack);
            }
            else if (Settings.Transmutation.knowledgeMode.equalsIgnoreCase("Tier"))
            {
                int itemStackKnowledgeTier = SkillRegistry.getInstance().getTier(itemStack);

                return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack)
                        && !playerKnowledge.isItemStackKnown(itemStack)
                        && SkillRegistry.getInstance().isLearnable(itemStack)
                        && itemStackKnowledgeTier >= 0 && itemStackKnowledgeTier <= Settings.Transmutation.maxKnowledgeTier;
            }
            else if (Settings.Transmutation.knowledgeMode.equalsIgnoreCase("Restricted"))
            {
                PlayerKnowledge allowedKnowledge = PlayerKnowledgeHandler.getAllowedPlayerKnowledge();

                return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack)
                        && !playerKnowledge.isItemStackKnown(itemStack)
                        && SkillRegistry.getInstance().isLearnable(itemStack)
                        && allowedKnowledge.isItemStackKnown(itemStack);
            }
        }

        return false;
    }
}
