package com.pahimar.ee3.creativetab;

import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab {

    public static final CreativeTabs EE3_TAB = new CreativeTabs(Reference.LOWERCASE_MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return ModItems.stonePhilosophers;
        }
    };
}
