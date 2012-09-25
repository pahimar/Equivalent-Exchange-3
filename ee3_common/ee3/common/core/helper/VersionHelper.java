package ee3.common.core.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
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

	// Var to hold the result of the remote version check
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
			result = CONNECTION_ERROR;
		}
		
		logResult();
	}
	
	public static void logResult() {
		if ((result == CURRENT) || (result == OUTDATED)) {
			FMLCommonHandler.instance().getFMLLogger().fine(getResultMessage());
		}
		else {
			FMLCommonHandler.instance().getFMLLogger().warning(getResultMessage());
		}
	}
	
	public static String getResultMessage() {
		if (result == UNINITIALIZED) {
			return "Uninitialized";
		}
		else if (result == CURRENT) {
			return "Current";
		}
		else if (result == OUTDATED) {
			return "Outdated";
		}
		else if (result == CONNECTION_ERROR) {
			return "Connection Error";
		}
		else {
			return null;
		}
	}

}
