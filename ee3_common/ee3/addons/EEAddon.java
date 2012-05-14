package ee3.addons;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class EEAddon {

	public static Item getModItem(String fieldItemName, String modClassName) throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
		return (Item)Class.forName(modClassName).getField(fieldItemName).get(null);
	}
	
	public static Block getModBlock(String fieldBlockName, String modClassName) throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
		return (Block)Class.forName(modClassName).getField(fieldBlockName).get(null);
	}
	
	public static ItemStack getModItemStack(String fieldItemName, String modClassName) throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
		return new ItemStack(getModItem(fieldItemName, modClassName));
	}
}
