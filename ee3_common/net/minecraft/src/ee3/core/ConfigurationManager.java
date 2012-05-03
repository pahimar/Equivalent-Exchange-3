package net.minecraft.src.ee3.core;

import java.io.File;
import java.util.HashMap;

import net.minecraft.src.mod_EE3;
import net.minecraft.src.ee3.lib.Reference;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.Property;

public class ConfigurationManager {

	private String cfgFile;
	private static final String CATEGORY_KEYBIND = "keybinds";
	
	public ConfigurationManager(String cfgFile) {
		this.cfgFile = Reference.CONFIG_DIR + cfgFile;
	}
	
	public void init() {
		Configuration config;
        try {
            File configFile = new File(mod_EE3.proxy.getMinecraftDir(), cfgFile);
            config = new Configuration(configFile);
            config.load();
        }
        catch(Exception e) {
        	config = null;
        }
        
        if (config != null) {
        	Property prop;
        	
        	/* General Configs */
        	prop = config.getOrCreateBooleanProperty("autoResolve", Configuration.CATEGORY_GENERAL, false);
        	prop = config.getOrCreateBooleanProperty("enableSounds", Configuration.CATEGORY_GENERAL, true);
        	
        	/* Block Ids */
        	prop = config.getOrCreateBlockIdProperty("stone", 175);
	    	prop = config.getOrCreateBlockIdProperty("pedestal", 176);
	    	prop = config.getOrCreateBlockIdProperty("chest", 177);
	    	prop = config.getOrCreateBlockIdProperty("torch", 178);
	    	prop = config.getOrCreateBlockIdProperty("device", 179);
	    	
	    	/* Keybinds */
        	prop = config.getOrCreateIntProperty("key.extra", CATEGORY_KEYBIND, 46);
        	prop = config.getOrCreateIntProperty("key.charge", CATEGORY_KEYBIND, 47);
        	prop = config.getOrCreateIntProperty("key.toggle", CATEGORY_KEYBIND, 34);
        	prop = config.getOrCreateIntProperty("key.release", CATEGORY_KEYBIND, 19);
        	
        	/* Write */
        	config.save();
        }
	}
}
