package com.pahimar.ee3.configuration;

import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * ConfigurationSettings
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ConfigurationSettings {

    /*
     * Version check related settings
     */
    public static boolean DISPLAY_VERSION_RESULT;
    public static final String DISPLAY_VERSION_RESULT_CONFIGNAME = "version_check.display_results";
    public static final boolean DISPLAY_VERSION_RESULT_DEFAULT = true;

    public static String LAST_DISCOVERED_VERSION;
    public static final String LAST_DISCOVERED_VERSION_CONFIGNAME = "version_check.last_discovered_version";
    public static final String LAST_DISCOVERED_VERSION_DEFAULT = "";

    public static String LAST_DISCOVERED_VERSION_TYPE;
    public static final String LAST_DISCOVERED_VERSION_TYPE_CONFIGNAME = "version_check.last_discovered_version_type";
    public static final String LAST_DISCOVERED_VERSION_TYPE_DEFAULT = "";

    /*
     * Audio config settings
     */
    public static String ENABLE_SOUNDS;
    public static final String ENABLE_SOUNDS_CONFIGNAME = "sounds.enabled";
    public static final String ENABLE_SOUNDS_DEFAULT = "all";

    /*
     * Graphic config settings
     */
    // Whether or not EE3 particle fx are enabled
    public static boolean ENABLE_PARTICLE_FX;
    public static final String ENABLE_PARTICLE_FX_CONFIGNAME = "particle_fx.enabled";
    public static final boolean ENABLE_PARTICLE_FX_DEFAULT = true;

    // Whether or not the in world transmutation overlays are enabled
    public static boolean ENABLE_OVERLAY_WORLD_TRANSMUTATION;
    public static final String ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME = "world_transmutation_overlay.enabled";
    public static final boolean ENABLE_OVERLAY_WORLD_TRANSMUTATION_DEFAULT = true;

    public static int TARGET_BLOCK_OVERLAY_POSITION;
    public static final String TARGET_BLOCK_OVERLAY_POSITION_CONFIGNAME = "block_overlay_position";
    public static final int TARGET_BLOCK_OVERLAY_POSITION_DEFAULT = 3;

    public static float TARGET_BLOCK_OVERLAY_OPACITY;
    public static final String TARGET_BLOCK_OVERLAY_OPACITY_CONFIGNAME = "block_overlay_opacity";
    public static final float TARGET_BLOCK_OVERLAY_OPACITY_DEFAULT = 0.75F;

    public static float TARGET_BLOCK_OVERLAY_SCALE;
    public static final String TARGET_BLOCK_OVERLAY_SCALE_CONFIGNAME = "block_overlay_scale";
    public static final float TARGET_BLOCK_OVERLAY_SCALE_DEFAULT = 2.5F;

    /*
     * Block related config settings
     */

    /*
     * Item related config settings
     */
    // The maximum durability for the Minium Stone 
    public static int MINIUM_STONE_MAX_DURABILITY;
    public static final String MINIUM_STONE_MAX_DURABILITY_CONFIGNAME = Strings.MINIUM_STONE_NAME;
    public static final int MINIUM_STONE_MAX_DURABILITY_DEFAULT = 1521;

    // The maximum durability for the Philosophers Stone 
    public static int PHILOSOPHERS_STONE_MAX_DURABILITY;
    public static final String PHILOSOPHERS_STONE_MAX_DURABILITY_CONFIGNAME = Strings.PHILOSOPHERS_STONE_NAME;
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
