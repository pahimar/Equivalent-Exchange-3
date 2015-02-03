package com.pahimar.ee3.init;

import com.pahimar.ee3.api.AbilityRegistryProxy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Skills
{
    public static void addDefaultSkills()
    {
        for (String oreName : OreDictionary.getOreNames())
        {
            if (oreName.startsWith("ore"))
            {
                for (ItemStack oreStack : OreDictionary.getOres(oreName))
                {
                    AbilityRegistryProxy.setAsNotLearnable(oreStack);
                }
            }
        }
    }
}