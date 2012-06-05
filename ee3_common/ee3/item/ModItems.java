package ee3.item;

import ee3.lib.ItemIds;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class ModItems {
	
	private static boolean initialized; 
	
	public static Item miniumStone;
	public static Item philStone;
	
	public static void init() {
		if (initialized)
			initialized = true;
		
		/* Initialise each mod item individually */
		miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE).setIconCoord(0, 0).setItemName("miniumStone");
		philStone = new ItemPhilosopherStone(ItemIds.PHIL_STONE).setIconCoord(1, 0).setItemName("philStone");
		
		/* Set the Container items for our mod items */
		miniumStone.setContainerItem(miniumStone);
		philStone.setContainerItem(philStone);
		
		/* Add the item names to the mod items */
		ModLoader.addName(miniumStone, "Minium Stone");
		ModLoader.addName(philStone, "Philosopher's Stone");
		
		ModLoader.addShapelessRecipe(new ItemStack(miniumStone), Block.dirt);
		ModLoader.addShapelessRecipe(new ItemStack(philStone), Block.dirt, Block.dirt);
	}
}
