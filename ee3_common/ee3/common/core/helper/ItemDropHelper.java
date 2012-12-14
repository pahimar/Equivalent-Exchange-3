package ee3.common.core.helper;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import ee3.common.item.ModItems;

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
		if (GeneralHelper.isHostileEntity(entity)) {
			rand = Math.random();
			
			if (rand < 0.15d) {
				entity.dropItem(ModItems.miniumShard.shiftedIndex, 1);
			}
		}
	}

}
