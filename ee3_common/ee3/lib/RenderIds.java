package ee3.lib;

import ee3.mod_EE3;
import net.minecraft.src.ModLoader;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class RenderIds {

	public static int RED_WATER_MODEL;
	
	
	public static void init() {
		RED_WATER_MODEL = ModLoader.getUniqueBlockModelID(mod_EE3.instance(), false);
	}
}
