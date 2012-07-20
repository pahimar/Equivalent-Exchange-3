package ee3.item;

import ee3.lib.ItemIds;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ModItems {
	
	public static Item miniumShard;
	public static Item miniumStone;
	public static Item philStone;
	
	public static void init() {
		
		/* Initialise each mod item individually */
		miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD).setIconCoord(0, 0).setItemName("miniumShard");
		miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE).setIconCoord(1, 0).setItemName("miniumStone");
		philStone = new ItemPhilosopherStone(ItemIds.PHIL_STONE).setIconCoord(2, 0).setItemName("philosophersStone");
		
		ModLoader.addShapelessRecipe(new ItemStack(miniumStone), Block.dirt);
		ModLoader.addShapelessRecipe(new ItemStack(philStone), Block.dirt, Block.dirt);
	}
}
