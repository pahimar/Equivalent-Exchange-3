package ee3.common.lib;

/**
 * Reference
 * 
 * General purpose library to contain mod related constants
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Reference {

    /* Debug Mode On-Off */
    public static final boolean DEBUG_MODE = false;

    /* General Mod related constants */
    public static final String MOD_ID = "EE3";
    public static final String MOD_NAME = "Equivalent Exchange 3";
    public static final String VERSION = "@VERSION@";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final int SECOND_IN_TICKS = 20;
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
    public static final String SERVER_PROXY_CLASS = "ee3.common.core.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "ee3.client.core.ClientProxy";

    /* Configuration related constants */
    public static final String ENABLE_VERSION_CHECK = "enable_version_check";
    public static final String ENABLE_SOUNDS = "enable_sounds";
    public static final String ENABLE_PARTICLE_FX = "enable_particle_fx";
    public static final String AUTO_RESOLVE_BLOCK_IDS = "auto_resolve_block_ids";

    /* KeyBinding related constants */
    public static final String KEYBINDING_EXTRA = "key.extra";
    public static final int KEYBINDING_EXTRA_DEFAULT = 46;
    public static final String KEYBINDING_RELEASE = "key.release";
    public static final int KEYBINDING_RELEASE_DEFAULT = 19;
    public static final String KEYBINDING_TOGGLE = "key.toggle";
    public static final int KEYBINDING_TOGGLE_DEFAULT = 34;
    public static final String KEYBINDING_CHARGE = "key.charge";
    public static final int KEYBINDING_CHARGE_DEFAULT = 47;

    /* Texture related constants */
    public static final String SPRITE_SHEET_LOCATION = "/ee3/art/sprites/";
    public static final String ARMOR_SHEET_LOCATION = "/ee3/art/armor/";
    public static final String GUI_SHEET_LOCATION = "/ee3/art/gui/";
    public static final String ITEM_SPRITE_SHEET = "ee3_items.png";
    public static final String BLOCK_SPRITE_SHEET = "ee3_blocks.png";
    public static final String CALCINATOR_TEXTURE_SHEET = "calcinator.png";

    /* General Tile Entity related constants */
    public static final String TE_GEN_OWNER_NBT_TAG_LABEL = "owner";
    public static final String TE_GEN_STATE_NBT_TAG_LABEL = "state";
    public static final String TE_GEN_DIRECTION_NBT_TAG_LABEL = "direction";

    // TODO: Find a better spot for these
    public static final int BLOCK_RED_WATER_EFFECT_DURATION_BASE = 5;
    public static final int BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER = 2;
    public static final int BLOCK_RED_WATER_RANGE_BASE = 1;
    public static final int BLOCK_RED_WATER_RANGE_MODIFIER = 3;

}
