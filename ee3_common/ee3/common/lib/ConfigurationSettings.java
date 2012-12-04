package ee3.common.lib;

import ee3.common.item.ModItems;

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
    // Whether or not automatic block id resolution for EE3 is turned on or off
    public static boolean AUTO_RESOLVE_BLOCK_IDS;
    public static final String ENABLE_VERSION_CHECK_CONFIGNAME = "enable_version_check";
    public static final boolean AUTO_RESOLVE_BLOCK_IDS_DEFAULT = false;

    // Whether or not EE3 sounds are enabled
    public static boolean ENABLE_SOUNDS;
    public static final String ENABLE_SOUNDS_CONFIGNAME = "enable_sounds";
    public static final boolean ENABLE_SOUNDS_DEFAULT = true;

    // Whether or not EE3 will do a version check when loaded
    public static boolean ENABLE_VERSION_CHECK;
    public static final String AUTO_RESOLVE_BLOCK_IDS_CONFIGNAME = "auto_resolve_block_ids";
    public static final boolean ENABLE_VERSION_CHECK_DEFAULT = true;

    /*
     * Render config settings
     */
    // Whether or not EE3 particle fx are enabled
    public static boolean ENABLE_PARTICLE_FX;
    public static final String ENABLE_PARTICLE_FX_CONFIGNAME = "enable_particle_fx";
    public static final boolean ENABLE_PARTICLE_FX_DEFAULT = true;

    // Whether or not the in world transmutation overlay is enabled
    public static boolean ENABLE_OVERLAY_WORLD_TRANSMUTATION;
    public static final String ENABLE_OVERLAY_WORLD_TRANSMUTATION_CONFIGNAME = "enable_world_transmutation_overlay";
    public static final boolean ENABLE_OVERLAY_WORLD_TRANSMUTATION_DEFAULT = true;

    /*
     * Minium stone config settings
     */
    // The durability cost for each transmute with the Minium Stone 
    public static int MINIUM_STONE_TRANSMUTE_COST;
    public static final String MINIUM_STONE_TRANSMUTE_COST_CONFIGNAME = Strings.MINIUM_STONE_NAME + ".transmuteCost";
    public static final int MINIUM_STONE_TRANSMUTE_COST_DEFAULT = 1;

    // The maximum durability for the Minium Stone 
    public static int MINIUM_STONE_MAX_DURABILITY;
    public static final String MINIUM_STONE_MAX_DURABILITY_CONFIGNAME = Strings.MINIUM_STONE_NAME + ".maxDurability";
    public static final int MINIUM_STONE_MAX_DURABILITY_DEFAULT = 1521;
    
    public static int PHILOSOPHERS_STONE_MAX_DURABILITY;
    public static final String PHILOSOPHERS_STONE_MAX_DURABILITY_CONFIGNAME = Strings.PHILOSOPHER_STONE_NAME + ".maxDurability";
    public static final int PHILOSOPHERS_STONE_MAX_DURABILITY_DEFAULT = 10001;

}
