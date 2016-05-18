package com.pahimar.ee3.init;

import com.pahimar.ee3.api.knowledge.AbilityRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Abilities {

    public static void init() {

        for (String oreName : OreDictionary.getOreNames()) {
            if (oreName.startsWith("ore")) {
                OreDictionary.getOres(oreName).forEach(AbilityRegistryProxy::setAsNotLearnable);
                AbilityRegistryProxy.setAsNotLearnable(new OreStack(oreName));
            }
        }

        AbilityRegistryProxy.setAsNotLearnable(new ItemStack(Blocks.coal_ore));
        AbilityRegistryProxy.setAsNotLearnable(ModItems.shardMinium);
        AbilityRegistryProxy.setAsNotLearnable(new ItemStack(ModItems.alchemicalDust, 1, 1));
        AbilityRegistryProxy.setAsNotLearnable(new ItemStack(ModItems.alchemicalDust, 1, 2));
    }
}