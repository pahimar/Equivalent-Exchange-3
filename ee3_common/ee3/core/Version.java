package ee3.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import ee3.lib.Reference;

import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_EE3;

public class Version {

	public static final int MAJOR = 1;
	public static final int MINOR = 0;
	public static final int REVISION = 0;
	public static final int BUILD = 0;
	
	public static final int REQ_FORGE_MAJOR = 3;
	public static final int REQ_FORGE_MINOR = 1;
	public static final int REQ_FORGE_REVISION = 3;
	
	private static final String REMOTE_VERSION_FILE = "http://dl.dropbox.com/u/25591134/EE2/version.txt";
	
	public static final byte CURRENT = 0;
	public static final byte OUTDATED = 1;
	public static final byte CONNECTION_ERROR = 2;
	
	public static byte currentVersion = 0;
	
	public static String getVersion() {
		return String.format("%d.%d.%d.%d", MAJOR, MINOR, REVISION, BUILD);
	}
	
	public static void versionCheck() {
		try {
			URL url = new URL(REMOTE_VERSION_FILE);
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
			String line = null;
		    while ((line = reader.readLine()) != null) {
		    	if (line.startsWith(mod_EE3.proxy.getMinecraftVersion())) {
		    		if (line.contains(Reference.CHANNEL_NAME)) {
			    		if (line.endsWith(getVersion())) {
							ModLoader.getLogger().info("[" + Reference.CHANNEL_NAME +  "] Version Check: Using the latest version");
			    			currentVersion = CURRENT;
			    			return;
			    		}
		    		}
		    	}
		    }
		    
			ModLoader.getLogger().info("[" + Reference.CHANNEL_NAME +  "] Version Check: Using outdated version");
			currentVersion = OUTDATED;
			
		} catch (Exception e) {
			ModLoader.getLogger().warning("[" + Reference.CHANNEL_NAME +  "] Version Check: Unable to read from remote version authority");
			currentVersion = CONNECTION_ERROR;
		}
	}
}
