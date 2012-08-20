package ee3.common.core;

import java.io.File;
import java.util.logging.Level;

import org.lwjgl.input.Keyboard;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;

import static net.minecraftforge.common.Configuration.*;

/**
* TODO Class Description
* @author pahimar
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*
*/
public class ConfigurationManager {
	
	private static final String CATEGORY_KEYBIND = "keybinds";
	
	public static boolean AUTO_RESOLVE_IDS;
	public static boolean ENABLE_SOUNDS;
	public static boolean ENABLE_PARTICLES;
	
	public static void init(File configFile) {
		Configuration configuration = new Configuration(configFile);
		
		try {
			configuration.load();
			
			/* General Configs */
			ENABLE_SOUNDS = configuration.getOrCreateBooleanProperty("enable_sounds", CATEGORY_GENERAL, true).getBoolean(false);
			ENABLE_PARTICLES = configuration.getOrCreateBooleanProperty("enable_particles", CATEGORY_GENERAL, true).getBoolean(false);
			
			/* Block Configs */
			AUTO_RESOLVE_IDS = configuration.getOrCreateBooleanProperty("auto_resolve_ids", CATEGORY_BLOCK, false).getBoolean(false);
			
			/* Item Configs */
			
			/* Keybinding Configs */
			configuration.getOrCreateIntProperty("extra", CATEGORY_KEYBIND, Keyboard.KEY_C);
			configuration.getOrCreateIntProperty("charge", CATEGORY_KEYBIND, Keyboard.KEY_V);
			configuration.getOrCreateIntProperty("toggle", CATEGORY_KEYBIND, Keyboard.KEY_G);
			configuration.getOrCreateIntProperty("release", CATEGORY_KEYBIND, Keyboard.KEY_R);
		}
		catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "Equivalent Exchange 3 has had a problem loading its configuration");
		}
		finally {
			configuration.save();
		}
	}
}
