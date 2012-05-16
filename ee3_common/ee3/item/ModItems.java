package ee3.item;

import ee3.lib.ItemIds;
import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class ModItems {
	
	private static boolean initialized; 
	
	public static Item philStone;
	public static Item lootBall;
	
	public static void init() {
		if (initialized)
			initialized = true;
		
		System.out.println("philStone: " + ItemIds.PHIL_STONE);
		System.out.println("lootBall: " + ItemIds.LOOT_BALL);
		
		/* Initialise each mod item individually */
		philStone = new ItemPhilosopherStone(ItemIds.PHIL_STONE).setIconCoord(0, 0).setItemName("philStone");
		lootBall = new ItemLootBall(ItemIds.LOOT_BALL).setIconCoord(11, 6).setItemName("lootBall");
		
		/* Set the Container items for our mod items */
		philStone.setContainerItem(philStone);
		
		/* Add the item names to the mod items */
		ModLoader.addName(philStone, "Philosopher's Stone");
		ModLoader.addName(lootBall, "Loot Ball");
	}
}
