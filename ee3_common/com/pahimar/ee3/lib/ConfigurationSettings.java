package com.pahimar.ee3.lib;

/**
 * ConfigurationSettings
 * 
 * Stores the various configuration settings read in from configuration files
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ConfigurationSettings {

    /*
     * General configuration settings
     */
    // Whether or not EE3 sounds are enabled
    public static boolean ENABLE_SOUNDS;
    public static final String ENABLE_SOUNDS_CONFIGNAME = "sounds.enabled";
    public static final boolean ENABLE_SOUNDS_DEFAULT = true;

    // Whether or not EE3 will do a version check when loaded
    public static boolean ENABLE_VERSION_CHECK;
    public static final String ENABLE_VERSION_CHECK_CONFIGNAME = "version_check.enabled";
    public static final boolean ENABLE_VERSION_CHECK_DEFAULT = true;

    /*
     * Graphic config settings
     */
    // Whether or not EE3 particle fx are enabled
    public static boolean ENABLE_PARTICLE_FX;
    public static final String ENABLE_PARTICLE_FX_CONFIGNAME = "particle_fx.enabled";
    public static final boolean ENABLE_PARTICLE_FX_DEFAULT = true;

    // Whether or not the in world transmutation overlay is enabled
    public static boolean ENABLE_OVERLAY_WORLD_TRANSMUTATION;
    public static final String ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME = "world_transmutation_overlay.enabled";
    public static final boolean ENABLE_OVERLAY_WORLD_TRANSMUTATION_DEFAULT = true;

    /*
     * Block related config settings
     */
    public static int RED_WATER_DURATION_BASE;
    public static String RED_WATER_DURATION_BASE_CONFIGNAME = "duration.base";
    public static final int RED_WATER_DURATION_BASE_DEFAULT = 5;

    public static int RED_WATER_DURATION_MODIFIER;
    public static String RED_WATER_DURATION_MODIFIER_CONFIGNAME = "duration.modifier";
    public static final int RED_WATER_DURATION_MODIFIER_DEFAULT = 2;

    public static int RED_WATER_RANGE_BASE;
    public static String RED_WATER_RANGE_BASE_CONFIGNAME = "range.base";
    public static final int RED_WATER_RANGE_BASE_DEFAULT = 1;

    public static int RED_WATER_RANGE_MODIFIER;
    public static String RED_WATER_RANGE_MODIFIER_CONFIGNAME = "range.modifier";
    public static final int RED_WATER_RANGE_MODIFIER_DEFAULT = 3;

    /*
     * Item related config settings
     */
    // The maximum durability for the Minium Stone 
    public static int MINIUM_STONE_MAX_DURABILITY;
    public static final String MINIUM_STONE_MAX_DURABILITY_CONFIGNAME = Strings.MINIUM_STONE_NAME;
    public static final int MINIUM_STONE_MAX_DURABILITY_DEFAULT = 1521;

    // The maximum durability for the Philosophers Stone 
    public static int PHILOSOPHERS_STONE_MAX_DURABILITY;
    public static final String PHILOSOPHERS_STONE_MAX_DURABILITY_CONFIGNAME = Strings.PHILOSOPHER_STONE_NAME;
    public static final int PHILOSOPHERS_STONE_MAX_DURABILITY_DEFAULT = 10001;

    /*
     * Keybinding related config settings
     */
    // Extra key
    public static final String KEYBINDING_EXTRA = "key.extra";
    public static final int KEYBINDING_EXTRA_DEFAULT = 46;

    // Release key
    public static final String KEYBINDING_RELEASE = "key.release";
    public static final int KEYBINDING_RELEASE_DEFAULT = 19;

    // Toggle key
    public static final String KEYBINDING_TOGGLE = "key.toggle";
    public static final int KEYBINDING_TOGGLE_DEFAULT = 34;

    // Charge key
    public static final String KEYBINDING_CHARGE = "key.charge";
    public static final int KEYBINDING_CHARGE_DEFAULT = 47;

    /*
     * Transmutation related config settings
     */
    // The durability cost for each item transmutation 
    public static int TRANSMUTE_COST_ITEM;
    public static final String TRANSMUTE_COST_ITEM_CONFIGNAME = Strings.TRANSMUTATION_COST_ITEM;
    public static final int TRANSMUTE_COST_ITEM_DEFAULT = 1;

    // The durability cost for each block transmutation 
    public static int TRANSMUTE_COST_BLOCK;
    public static final String TRANSMUTE_COST_BLOCK_CONFIGNAME = Strings.TRANSMUTATION_COST_BLOCK;
    public static final int TRANSMUTE_COST_BLOCK_DEFAULT = 1;

    // The durability cost for each block transmutation 
    public static int TRANSMUTE_COST_MOB;
    public static final String TRANSMUTE_COST_MOB_CONFIGNAME = Strings.TRANSMUTATION_COST_MOB;
    public static final int TRANSMUTE_COST_MOB_DEFAULT = 1;

}
