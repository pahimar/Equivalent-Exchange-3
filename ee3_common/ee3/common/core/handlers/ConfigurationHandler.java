package ee3.common.core.handlers;

import java.io.File;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLLog;
import ee3.common.EquivalentExchange3;
import net.minecraftforge.common.Configuration;
import static net.minecraftforge.common.Configuration.*;
import static ee3.common.lib.Reference.*;

/**
* TODO Class Description
* @author pahimar
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*
*/
public class ConfigurationHandler {
	
	private static final String CATEGORY_KEYBIND = "keybinds";
	
	public static boolean AUTO_RESOLVE_IDS;
	public static boolean ENABLE_SOUNDS;
	public static boolean ENABLE_PARTICLES;
	
	public static void init(File configFile) {
		Configuration configuration = new Configuration(configFile);
		
		try {
			configuration.load();
			
			// TODO: Clean up property names
			
			/* General Configs */
			ENABLE_SOUNDS = configuration.getOrCreateBooleanProperty("enable_sounds", CATEGORY_GENERAL, true).getBoolean(false);
			ENABLE_PARTICLES = configuration.getOrCreateBooleanProperty("enable_particles", CATEGORY_GENERAL, true).getBoolean(false);
			
			/* Block Configs */
			AUTO_RESOLVE_IDS = configuration.getOrCreateBooleanProperty("auto_resolve_ids", CATEGORY_BLOCK, false).getBoolean(false);
			
			/* Item Configs */
			
			/* KeyBinding Configs */
			EquivalentExchange3.proxy.setKeyBinding(KEYBINDING_EXTRA_NAME, configuration.getOrCreateIntProperty(KEYBINDING_EXTRA_NAME, CATEGORY_KEYBIND, KEYBINDING_EXTRA_DEFAULT).getInt(KEYBINDING_EXTRA_DEFAULT));
			EquivalentExchange3.proxy.setKeyBinding(KEYBINDING_CHARGE_NAME, configuration.getOrCreateIntProperty(KEYBINDING_CHARGE_NAME, CATEGORY_KEYBIND, KEYBINDING_CHARGE_DEFAULT).getInt(KEYBINDING_CHARGE_DEFAULT));
			EquivalentExchange3.proxy.setKeyBinding(KEYBINDING_TOGGLE_NAME, configuration.getOrCreateIntProperty(KEYBINDING_TOGGLE_NAME, CATEGORY_KEYBIND, KEYBINDING_TOGGLE_DEFAULT).getInt(KEYBINDING_TOGGLE_DEFAULT));
			EquivalentExchange3.proxy.setKeyBinding(KEYBINDING_RELEASE_NAME, configuration.getOrCreateIntProperty(KEYBINDING_RELEASE_NAME, CATEGORY_KEYBIND, KEYBINDING_RELEASE_DEFAULT).getInt(KEYBINDING_RELEASE_DEFAULT));
		}
		catch (Exception e) {
			// TODO: Clean up the logging message 
			FMLLog.log(Level.SEVERE, e, "Equivalent Exchange 3 has had a problem loading its configuration");
		}
		finally {
			configuration.save();
		}
	}
}
