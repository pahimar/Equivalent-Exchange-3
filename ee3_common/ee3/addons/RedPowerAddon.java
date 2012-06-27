package ee3.addons;

import net.minecraft.src.ModLoader;
import ee3.core.helper.Helper;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class RedPowerAddon {

	public static void init() {
		try {
			ModLoader.getLogger().finer(Helper.getLogMessage("RedPower2 detected; attempting to initialize RedPower2 addon"));
			
			ModLoader.getLogger().finer(Helper.getLogMessage("RedPower2 addon initialized"));
		}
		catch (Exception e) {
			ModLoader.getLogger().fine(Helper.getLogMessage("Failed to initialize RedPower2 addon"));
			e.printStackTrace(System.err);
		}
	}
}
