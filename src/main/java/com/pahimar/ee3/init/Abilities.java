package com.pahimar.ee3.init;

import com.pahimar.ee3.api.knowledge.AbilityRegistryProxy;
import com.pahimar.ee3.exchange.CachedOreDictionary;
import com.pahimar.ee3.exchange.OreStack;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Abilities
{
    public static void setOresNotLearnable()
    {
        for (String oreName : OreDictionary.getOreNames())
        {
            if (oreName.startsWith("ore"))
            {
                for (ItemStack itemStack : CachedOreDictionary.getInstance().getItemStacksForOreName(oreName))
                {
                    AbilityRegistryProxy.setAsNotLearnable(itemStack);
                }
                AbilityRegistryProxy.setAsNotLearnable(new OreStack(oreName));
            }
        }

        AbilityRegistryProxy.setAsNotLearnable(new ItemStack(Blocks.coal_ore));
    }
}