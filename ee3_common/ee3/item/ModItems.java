package ee3.item;

import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;

public class ModItems {
	
	private static boolean initialized; 
	
	public static Item philStone;
	
	public static void init() {
		if (initialized)
			initialized = true;
		
		philStone = new ItemPhilosopherStone(27270).setIconCoord(0, 0).setItemName("philStone");
		philStone.setContainerItem(philStone);
		
		ModLoader.addName(philStone, "Philosopher's Stone");
	}
}
