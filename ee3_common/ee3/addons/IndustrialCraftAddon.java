package ee3.addons;

import net.minecraft.src.ModLoader;
import ee3.core.helper.Helper;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class IndustrialCraftAddon {

	public static void init() {
		try {
			ModLoader.getLogger().finer(Helper.getLogMessage("IndustrialCraft2 detected; attempting to initialize IndustrialCraft2 addon"));
			
			ModLoader.getLogger().finer(Helper.getLogMessage("IndustrialCraft2 addon initialized"));
		}
		catch (Exception e) {
			ModLoader.getLogger().fine(Helper.getLogMessage("Failed to initialize IndustrialCraft2 addon"));
			e.printStackTrace(System.err);
		}
	}
}
