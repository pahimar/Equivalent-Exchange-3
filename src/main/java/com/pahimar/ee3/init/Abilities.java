package com.pahimar.ee3.init;

import com.pahimar.ee3.api.AbilityRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
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
    }
}