package com.pahimar.ee3.configuration;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class GeneralConfiguration {

    private static Configuration generalConfiguration;

    public static final String CATEGORY_KEYBIND = "keybindings";
    public static final String CATEGORY_GRAPHICS = "graphics";
    public static final String CATEGORY_AUDIO = "audio";

    protected static void init(File configFile) {

        generalConfiguration = new Configuration(configFile);

        try {
            generalConfiguration.load();
            
            /* Version check */
            ConfigurationSettings.DISPLAY_VERSION_RESULT = generalConfiguration.get(CATEGORY_GENERAL, ConfigurationSettings.DISPLAY_VERSION_RESULT_CONFIGNAME, ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT).getBoolean(ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT);
            ConfigurationSettings.LAST_DISCOVERED_VERSION = generalConfiguration.get(CATEGORY_GENERAL, ConfigurationSettings.LAST_DISCOVERED_VERSION_CONFIGNAME, ConfigurationSettings.LAST_DISCOVERED_VERSION_DEFAULT).getString();
            ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE = generalConfiguration.get(CATEGORY_GENERAL, ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE_CONFIGNAME, ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE_DEFAULT).getString();

            /* Graphics */
            ConfigurationSettings.ENABLE_PARTICLE_FX = generalConfiguration.get(CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_PARTICLE_FX_CONFIGNAME, ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT).getBoolean(ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT);
            ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION = generalConfiguration.get(CATEGORY_GRAPHICS, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME, ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_DEFAULT).getBoolean(ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION_DEFAULT);
            ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION = generalConfiguration.get(CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME, ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_DEFAULT).getInt(ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION_DEFAULT);
            try {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE = Float.parseFloat(generalConfiguration.get(CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE_CONFIGNAME, ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE_DEFAULT).getString());

                if (ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE <= 0F) {
                    ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE = ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE_DEFAULT;
                }
            }
            catch (Exception e) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE = ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE_DEFAULT;
            }
            try {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY = Float.parseFloat(generalConfiguration.get(CATEGORY_GRAPHICS, ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY_CONFIGNAME, ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY_DEFAULT).getString());

                if (ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY < 0F || ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY > 1F) {
                    ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY = ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY_DEFAULT;
                }
            }
            catch (Exception e) {
                ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY = ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY_DEFAULT;
            }

            /* Audio */
            ConfigurationSettings.ENABLE_SOUNDS = generalConfiguration.get(CATEGORY_AUDIO, ConfigurationSettings.ENABLE_SOUNDS_CONFIGNAME, ConfigurationSettings.ENABLE_SOUNDS_DEFAULT).getString();

            /* KeyBindings */
            generalConfiguration.addCustomCategoryComment(CATEGORY_KEYBIND, "Keybindings for Equivalent Exchange 3. See http://www.minecraftwiki.net/wiki/Key_codes for mapping of key codes to keyboard keys");
            EquivalentExchange3.proxy.setKeyBinding(ConfigurationSettings.KEYBINDING_EXTRA, generalConfiguration.get(CATEGORY_KEYBIND, ConfigurationSettings.KEYBINDING_EXTRA, ConfigurationSettings.KEYBINDING_EXTRA_DEFAULT).getInt(ConfigurationSettings.KEYBINDING_EXTRA_DEFAULT));
            EquivalentExchange3.proxy.setKeyBinding(ConfigurationSettings.KEYBINDING_CHARGE, generalConfiguration.get(CATEGORY_KEYBIND, ConfigurationSettings.KEYBINDING_CHARGE, ConfigurationSettings.KEYBINDING_CHARGE_DEFAULT).getInt(ConfigurationSettings.KEYBINDING_CHARGE_DEFAULT));
            EquivalentExchange3.proxy.setKeyBinding(ConfigurationSettings.KEYBINDING_TOGGLE, generalConfiguration.get(CATEGORY_KEYBIND, ConfigurationSettings.KEYBINDING_TOGGLE, ConfigurationSettings.KEYBINDING_TOGGLE_DEFAULT).getInt(ConfigurationSettings.KEYBINDING_TOGGLE_DEFAULT));
            EquivalentExchange3.proxy.setKeyBinding(ConfigurationSettings.KEYBINDING_RELEASE, generalConfiguration.get(CATEGORY_KEYBIND, ConfigurationSettings.KEYBINDING_RELEASE, ConfigurationSettings.KEYBINDING_RELEASE_DEFAULT).getInt(ConfigurationSettings.KEYBINDING_RELEASE_DEFAULT));
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its general configuration");
        }
        finally {
            generalConfiguration.save();
        }
    }
    
    public static void set(String categoryName, String propertyName, String newValue) {

        generalConfiguration.load();
        if (generalConfiguration.getCategoryNames().contains(categoryName)) {
            if (generalConfiguration.getCategory(categoryName).containsKey(propertyName)) {
                generalConfiguration.getCategory(categoryName).get(propertyName).set(newValue);
            }
        }
        generalConfiguration.save();
    }
}
