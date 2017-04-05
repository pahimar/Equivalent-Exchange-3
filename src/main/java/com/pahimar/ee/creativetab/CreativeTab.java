package com.pahimar.ee.creativetab;

import com.pahimar.ee.EquivalentExchange;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTab {

    public static final CreativeTabs EE_TAB = new CreativeTabs(EquivalentExchange.MOD_ID) {

        @Override
        public ItemStack getTabIconItem() {
            // TODO Uncomment after reimplemented
//            return new ItemStack(ModItems.PHILOSOPHERS_STONE);
            return new ItemStack(Items.SLIME_BALL);
        }
    };
}
