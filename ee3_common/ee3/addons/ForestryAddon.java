package ee3.addons;

import ee3.lib.Reference;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class ForestryAddon extends EEAddon {

	private static final int APATITE = 0;
	private static final int COPPER = 1;
	private static final int TIN = 2;
	
	public static Item beeComb = null;
	public static Item honeyDrop = null;
	public static Item ingotCopper = null;
	public static Item ingotTin = null;
	public static Item ingotBronze = null;
	
	public static Block resources = null;
	
	public static void init() {
		try {
			ModLoader.getLogger().fine(Reference.LOGGER_PREFIX + "Loaded Forestry Addon");
		}
		catch (Exception e) {
			ModLoader.getLogger().warning(Reference.LOGGER_PREFIX + "Could not load Forestry Addon");
			e.printStackTrace(System.err);
		}
	}
}
