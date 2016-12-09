package com.pahimar.ee3.creativetab;

import com.pahimar.ee3.EquivalentExchange3;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTab {

    public static final CreativeTabs EE3_TAB = new CreativeTabs(EquivalentExchange3.MOD_ID) {

        @Override
        public ItemStack getTabIconItem() {
            // TODO Uncomment after reimplemented
//            return new ItemStack(ModItems.PHILOSOPHERS_STONE);
            return new ItemStack(Items.SLIME_BALL);
        }
    };
}
