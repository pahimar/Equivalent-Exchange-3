package ee3.common.core.helper;

import ee3.common.item.ModItems;
import net.minecraft.src.EntityAgeable;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;

/**
 * ItemDropHelper
 * 
 * Helper methods for dropping items in game
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemDropHelper {
	
	private static double rand;
	
	public static void dropMiniumShard(EntityPlayer player, EntityLiving entity) {
		if (!(entity instanceof EntityAgeable) && !(entity instanceof EntityPlayer)) {
			rand = Math.random();
			
			if (rand < 0.15d) {
				entity.dropItem(ModItems.miniumShard.shiftedIndex, 1);
			}
		}
	}

}
