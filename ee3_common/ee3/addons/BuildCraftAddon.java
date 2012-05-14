package ee3.addons;

import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;
import ee3.lib.Reference;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class BuildCraftAddon extends EEAddon {
	
	public static void init() {
		try {
			ModLoader.getLogger().fine(Reference.LOGGER_PREFIX + "Loaded BuildCraft Addon");
		}
		catch (Exception e) {
			ModLoader.getLogger().warning(Reference.LOGGER_PREFIX + "Could not load BuildCraft Addon");
			e.printStackTrace(System.err);
		}
	}
}
