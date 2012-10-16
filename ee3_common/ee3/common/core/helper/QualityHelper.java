package ee3.common.core.helper;

import net.minecraft.src.ItemStack;

public class QualityHelper {
	
	/*
	 * Legend for the dust table quality lookup, comparison is based off of quality tiers
	 * 
	 *                   Item Quality
	 *				|_0_|_1_|_2_|_3_|_4_|_5_|
	 *   Fuel     0 | 0 | 0 | 0 | 1 | 1 | 1 |
	 * 	Quality	  1 | 0 | 1 | 1 | 1 | 2 | 2 |
	 * 	          2 | 0 | 1 | 2 | 2 | 2 | 2 |
	 *            3 | 1 | 1 | 2 | 3 | 3 | 3 |
	 *            4 | 1 | 2 | 2 | 3 | 4 | 4 |
	 *            5 | 1 | 2 | 2 | 3 | 4 | 5 |
	 */
	private static int[][] dustTable = {
		{0, 0, 0, 1, 1, 1},
		{0, 1, 1, 1, 2, 2},
		{0, 1, 2, 2, 2, 2},
		{1, 1, 2, 3, 3, 3},
		{1, 2, 2, 3, 4, 4},
		{1, 2, 2, 3, 4, 5},
	};

	public static int getItemTierQuality(ItemStack item) {
		// TODO Return the 'Tier' level of the given ItemStack
		return -1;
	}
	
	public static int getFuelTierQuality(ItemStack fuel) {
		// TODO Return the 'Tier' level of the given ItemStack
		return -1;
	}
	
	public static int getDustTierQuality(ItemStack item, ItemStack fuel) {
		if ((getItemTierQuality(item) >= 0) && (getItemTierQuality(item) <= 5)) {
			if ((getFuelTierQuality(fuel) >= 0) && (getFuelTierQuality(fuel) <= 5)) {
				return dustTable[getItemTierQuality(item)][getFuelTierQuality(fuel)];
			}
		}
		
		return -1;
	}
	
}
