package com.pahimar.ee3.creativetab;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab {

    public static final CreativeTabs EE3_TAB = new CreativeTabs(EquivalentExchange3.MOD_ID) {

        @Override
        public Item getTabIconItem() {
            return ModItems.stonePhilosophers;
        }
    };
}
