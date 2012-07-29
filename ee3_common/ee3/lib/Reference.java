package ee3.lib;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class Reference {
	public static final boolean DEBUG = false;
	
	public static final String MOD_NAME = "Equivalent Exchange 3";
	public static final String CHANNEL_NAME = "EE3";
	
	public static final String LOGGER_PREFIX = "[" + MOD_NAME + "] ";
	public static final String CONFIG_FILE = "EE3.cfg";
	public static final String CONFIG_DIR = "config/ee3/";
	
	public static final String SPRITE_SHEET_LOCATION = "/ee3/art/sprites/";
	public static final String ITEM_SPRITE_SHEET = "ee3_items.png";
	public static final String BLOCK_SPRITE_SHEET = "ee3_blocks.png";
	public static final String GUI_SHEET_LOCATION = "/ee3/art/gui/";
	public static final String ARMOR_SHEET_LOCATION = "/ee3/art/armor/";
	public static final String SOUND_RESOURCE_LOCATION = "ee3/sound/";
	public static final String SOUND_PREFIX = "ee3.sound.";
	public static final String LANG_RESOURCE_LOCATION = "/ee3/lang/";
	
	public static final int MINIUM_STONE_DURABILITY = 1531;
	public static final int MINIUM_STONE_TRANSMUTE_COST = 4;
	
	public static final String ITEM_NAME_MINIUM_SHARD = "item.name.minium_shard";
	public static final String ITEM_NAME_MINIUM_STONE = "item.name.minium_stone";
	public static final String ITEM_NAME_PHILOSOPHERS_STONE = "item.name.philosophers_stone";
	
	public static final int BLOCK_RED_WATER_EFFECT_DURATION = 30;
}
