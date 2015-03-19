package com.pahimar.ee3.init;

import com.pahimar.ee3.api.AbilityRegistryProxy;
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
                AbilityRegistryProxy.setAsNotLearnable(new OreStack(oreName));
            }
        }

        AbilityRegistryProxy.setAsNotLearnable(new ItemStack(Blocks.coal_ore));
    }
}