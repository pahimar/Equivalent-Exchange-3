package ee3.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.lib.ItemIds;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

/**
* ModItems
* 
* Contains all relevant mod item instances
* 
* @author pahimar
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*
*/
public class ModItems {
	
	public static Item miniumShard;
	public static Item miniumStone;
	public static Item philStone;
	
	public static void init() {
		
		/* Initialize each mod item individually */
		miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD).setIconCoord(0, 0).setItemName("miniumShard").setTabToDisplayOn(CreativeTabs.tabMisc);
		miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE).setIconCoord(1, 0).setItemName("miniumStone").setTabToDisplayOn(CreativeTabs.tabMisc);
		philStone = new ItemMiniumStone(ItemIds.PHIL_STONE).setIconCoord(2, 0).setItemName("philStone").setTabToDisplayOn(CreativeTabs.tabMisc);
		
		LanguageRegistry.addName(miniumShard, "Shard of Minium");
		LanguageRegistry.addName(miniumStone, "Minium Stone");
		LanguageRegistry.addName(philStone, "Philosophers Stone");
		
	}
	
}
