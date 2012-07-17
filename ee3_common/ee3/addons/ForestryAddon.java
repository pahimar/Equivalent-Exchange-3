package ee3.addons;

import ee3.core.helper.Helper;
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
public class ForestryAddon extends EEAddon {

	private static final String FORESTRY_ITEM_CLASS = "forestry.core.config.ForestryItem";
	private static final String FORESTRY_BLOCK_CLASS = "forestry.api.core.ForestryBlock";
	
	private static final int APATITE = 0;
	private static final int COPPER = 1;
	private static final int TIN = 2;
	
	public static Item beeComb = null;
	public static Item honeyDrop = null;
	public static Item apatite = null;
	public static Item ingotCopper = null;
	public static Item ingotTin = null;
	public static Item ingotBronze = null;
	
	public static Block resources = null;
	
	public static void init() {
		try {
			ModLoader.getLogger().finer(Helper.getLogMessage("Forestry detected; attempting to initialize Forestry addon"));
			
			beeComb = getModItem("beeComb", FORESTRY_ITEM_CLASS);
			honeyDrop = getModItem("honeyDrop", FORESTRY_ITEM_CLASS);
			apatite = getModItem("apatite", FORESTRY_ITEM_CLASS);
			ingotCopper = getModItem("ingotCopper", FORESTRY_ITEM_CLASS);
			ingotTin = getModItem("ingotTin", FORESTRY_ITEM_CLASS);
			ingotBronze = getModItem("ingotBronze", FORESTRY_ITEM_CLASS);
			
			resources = getModBlock("resources", FORESTRY_BLOCK_CLASS);
			
			ModLoader.getLogger().finer(Helper.getLogMessage("Forestry addon initialized"));
		}
		catch (Exception e) {
			ModLoader.getLogger().fine(Helper.getLogMessage("Failed to initialize Forestry Addon"));
			e.printStackTrace(System.err);
		}
	}
	
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
