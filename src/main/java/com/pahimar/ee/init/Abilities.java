package com.pahimar.ee.init;

import com.pahimar.ee.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee.exchange.OreStack;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Abilities {

    public static void init() {

        for (String oreName : OreDictionary.getOreNames()) {
            if (oreName.startsWith("ore")) {
                OreDictionary.getOres(oreName).forEach(BlacklistRegistryProxy::setAsNotLearnable);
                BlacklistRegistryProxy.setAsNotLearnable(new OreStack(oreName));
            }
        }

        BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(Blocks.COAL_ORE));
        // TODO Uncomment after these items are implemented again
//        BlacklistRegistryProxy.setAsNotLearnable(ModItems.shardMinium);
//        BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(ModItems.alchemicalDust, 1, 1));
//        BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(ModItems.alchemicalDust, 1, 2));
    }
}