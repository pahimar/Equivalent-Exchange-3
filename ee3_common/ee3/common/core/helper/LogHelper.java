package ee3.common.core.helper;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import ee3.common.lib.Reference;

public class LogHelper {

	public static void log(Level logLevel, String message) {
		System.out.println(Reference.LOGGER_PREFIX + message);
		FMLCommonHandler.instance().getFMLLogger().log(logLevel, Reference.LOGGER_PREFIX + message);
	}
	
}
