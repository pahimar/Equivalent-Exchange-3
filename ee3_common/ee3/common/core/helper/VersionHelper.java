package ee3.common.core.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.lib.ConfigurationSettings;
import ee3.common.lib.Reference;

/**
 * VersionHelper
 * 
 * Contains methods for checking the version of the currently running instance of the mod against a remote version number authority.
 * Meant to help users by notifying them if they are behind the latest published version of the mod
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class VersionHelper {
	
	// The (publicly available) remote version number authority file
	private static final String REMOTE_VERSION_FILE = "https://dl.dropbox.com/u/25591134/EE3/version.txt";
	
	// All possible results of the remote version number check
	public static final byte UNINITIALIZED = 0;
	public static final byte CURRENT = 1;
	public static final byte OUTDATED = 2;
	public static final byte CONNECTION_ERROR = 3;
	
	// Localization keys
	private static final String VERSION_CHECK_DISABLED = "version.check_disabled";
	private static final String VERSION_CHECK_INIT_LOG_MESSAGE = "version.init_log_message";
	private static final String UNINITIALIZED_MESSAGE = "version.uninitialized";
	private static final String CURRENT_MESSAGE = "version.current";
	private static final String OUTDATED_MESSAGE = "version.outdated";
	private static final String CONNECTION_ERROR_MESSAGE = "version.connection_error";

	// Var to hold the result of the remote version check, initially set to uninitialized
	public static byte result = UNINITIALIZED;
	
	/***
	 * Checks the version of the currently running instance of the mod against the remote version authority, and sets the result of the check appropriately
	 */
	public static void checkVersion() {
		try {
			URL url = new URL(REMOTE_VERSION_FILE);
			
			InputStreamReader isr = new InputStreamReader(url.openStream());
			BufferedReader reader = new BufferedReader(isr);
			
			String line = null;
			
			// While we are not at the end of the file, fetch the next line in the file
			while ((line = reader.readLine()) != null) {
				// If the current line is for this version of Minecraft, read further
				if (line.startsWith(Loader.instance().getMCVersionString())) {
					// If the current line is also for this mod, read further
					if (line.contains(Reference.CHANNEL_NAME)) {
						// If the current line is also the same as the running version of the mod, set the result appropriately
						if (line.endsWith(Reference.VERSION)) {
							// Set the version check result to CURRENT
							result = CURRENT;
							
							// Close the associated network resources
							reader.close();
							isr.close();
							
							return;
						}
					}
				}
			}
			
			// Since this mod version is not the current version, set the version check appropriately
			result = OUTDATED;
			
			// Close the associated network resources
			reader.close();
			isr.close();
		} catch (Exception e) {
			// If we cannot connect to the remote version number authority, set the version check appropriately
			e.printStackTrace(System.err);
			result = CONNECTION_ERROR;
		}
		
	}
	
	public static void logResult() {
		if (ConfigurationSettings.ENABLE_VERSION_CHECK) {
			LogHelper.log(Level.INFO, LanguageRegistry.instance().getStringLocalization(VERSION_CHECK_INIT_LOG_MESSAGE) + " " + REMOTE_VERSION_FILE);
			if ((result == CURRENT) || (result == OUTDATED)) {
				LogHelper.log(Level.INFO, getResultMessage());
			}
			else {
				LogHelper.log(Level.WARNING, getResultMessage());
			}
		}
		else {
			LogHelper.log(Level.INFO, getResultMessage());
		}
	}
	
	public static String getResultMessage() {
		if (ConfigurationSettings.ENABLE_VERSION_CHECK) {
			if (result == UNINITIALIZED) {
				return LanguageRegistry.instance().getStringLocalization(UNINITIALIZED_MESSAGE);
			}
			else if (result == CURRENT) {
				return LanguageRegistry.instance().getStringLocalization(CURRENT_MESSAGE);
			}
			else if (result == OUTDATED) {
				return LanguageRegistry.instance().getStringLocalization(OUTDATED_MESSAGE);
			}
			else if (result == CONNECTION_ERROR) {
				return LanguageRegistry.instance().getStringLocalization(CONNECTION_ERROR_MESSAGE);
			}
			else {
				return null;
			}
		}
		else {
			return LanguageRegistry.instance().getStringLocalization(VERSION_CHECK_DISABLED);
		}
	}

}
