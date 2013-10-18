package com.pahimar.ee3.core.helper;

import net.minecraft.item.ItemStack;

/**
 * Equivalent-Exchange-3
 * 
 * QualityHelper
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class QualityHelper {

    private static int[][] dustTable = { { 0, 0, 0, 1, 1, 1 }, { 0, 1, 1, 1, 2, 2 }, { 0, 1, 2, 2, 2, 2 }, { 1, 1, 2, 3, 3, 3 }, { 1, 2, 2, 3, 4, 4 }, { 1, 2, 2, 3, 4, 5 }, };

    public static int getItemTierQuality(ItemStack item) {

        // TODO Return the 'Tier' level of the given ItemStack
        return -1;
    }

    public static int getFuelTierQuality(ItemStack fuel) {

        // TODO Return the 'Tier' level of the given ItemStack
        return -1;
    }

    public static int getDustTierQuality(ItemStack item, ItemStack fuel) {

        if (getItemTierQuality(item) >= 0 && getItemTierQuality(item) <= 5) {
            if (getFuelTierQuality(fuel) >= 0 && getFuelTierQuality(fuel) <= 5)
                return dustTable[getItemTierQuality(item)][getFuelTierQuality(fuel)];
        }

        return -1;
    }

}
