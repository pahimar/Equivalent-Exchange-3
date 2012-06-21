package ee3.addons;

import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;
import ee3.lib.Helper;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class BuildCraftAddon extends EEAddon {
	
	public static void init() {
		try {
			ModLoader.getLogger().finer(Helper.getLogMessage("BuildCraft detected; attempting to initialize BuildCraft addon"));
			
			ModLoader.getLogger().finer(Helper.getLogMessage("BuildCraft addon initialized"));
		}
		catch (Exception e) {
			ModLoader.getLogger().fine(Helper.getLogMessage("Failed to initialize BuildCraft Addon"));
			e.printStackTrace(System.err);
		}
	}
}
