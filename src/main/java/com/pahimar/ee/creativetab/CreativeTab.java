package com.pahimar.ee.creativetab;

import com.pahimar.ee.EquivalentExchange;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * TODO Finish Javadoc
 *
 * @author      pahimar
 *
 * @since       3.0.0
 */
public class CreativeTab {

    private static final ItemStack TAB_ITEMSTACK = new ItemStack(Items.SLIME_BALL);

    /**
     * TODO Finish Javadoc
     */
    public static final CreativeTabs MOD_TAB = new CreativeTabs(EquivalentExchange.MOD_ID) {
        @Override
        public ItemStack getTabIconItem() {
            return TAB_ITEMSTACK;
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
