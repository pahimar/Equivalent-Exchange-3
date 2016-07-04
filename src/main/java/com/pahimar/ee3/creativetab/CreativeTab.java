package com.pahimar.ee3.creativetab;

import com.pahimar.ee3.EquivalentExchange3;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTab {

    public static final CreativeTabs EE3_TAB = new CreativeTabs(EquivalentExchange3.MOD_ID) {

        @Override
        public Item getTabIconItem() {
            // TODO Uncomment after reimplemented
//            return ModItems.stonePhilosophers;
            return Items.SLIME_BALL;
        }
    };
}
