package com.pahimar.ee.creativetab;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * TODO Finish Javadoc
 *
 * @author      pahimar
 *
 * @since       3.0.0
 */
public class CreativeTab {

    /**
     * TODO Finish Javadoc
     */
    public static final CreativeTabs MOD_TAB = new CreativeTabs(EquivalentExchange.MOD_ID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.ALCHENOMICON);
        }
    };

    /**
     * TODO Finish Javadoc
     *
     * @since   3.0.0
     */
    private CreativeTab() {
        // NO-OP
    }
}
