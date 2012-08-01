package ee3.core;

import java.io.File;
import java.util.HashMap;

import static ee3.lib.ItemIds.*;
import static ee3.lib.BlockIds.*;
import ee3.mod_EE3;
import ee3.lib.Reference;

import static net.minecraft.src.forge.Configuration.*;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.Property;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ConfigurationManager {

	private String cfgFile;
	private static final String CATEGORY_KEYBIND = "keybinds";
	
	public static boolean AUTO_RESOLVE_IDS;
	public static boolean ENABLE_SOUNDS;
	public static boolean ENABLE_PARTICLES;
	
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
        	e.printStackTrace();
        	config = null;
        }
        
        if (config != null) {
        	Property prop;
        	
        	/* General Configs */
        	prop = config.getOrCreateBooleanProperty("autoResolve", CATEGORY_GENERAL, false);
        	AUTO_RESOLVE_IDS = prop.getBoolean(false);
        	prop = config.getOrCreateBooleanProperty("enableSounds", CATEGORY_GENERAL, true);
        	ENABLE_SOUNDS = prop.getBoolean(false);
        	prop = config.getOrCreateBooleanProperty("enableParticles", CATEGORY_GENERAL, true);
        	ENABLE_PARTICLES = prop.getBoolean(false);
        	
        	/* Block Ids */
	    	prop = config.getOrCreateBlockIdProperty("redWater", 176);
	    	RED_WATER_STILL = prop.getInt();
	    	
	    	/* Item Ids */	
	    	prop = config.getOrCreateIntProperty("miniumShard", CATEGORY_ITEM, 27269);
	    	MINIUM_SHARD = prop.getInt();
	    	prop = config.getOrCreateIntProperty("miniumStone", CATEGORY_ITEM, 27270);
	    	MINIUM_STONE = prop.getInt();
	    	prop = config.getOrCreateIntProperty("philStone", CATEGORY_ITEM, 27271);
	    	PHIL_STONE = prop.getInt();
	    	prop = config.getOrCreateIntProperty("djinnRing", CATEGORY_ITEM, 27272);
	    	DJINN_RING = prop.getInt();
	    	prop = config.getOrCreateIntProperty("eyeOfTheVoid", CATEGORY_ITEM, 27273);
	    	EYE_OF_THE_VOID = prop.getInt();
	    	prop = config.getOrCreateIntProperty("idolOfGaia", CATEGORY_ITEM, 27274);
	    	IDOL_OF_GAIA = prop.getInt();
	    	prop = config.getOrCreateIntProperty("umbralDisplacer", CATEGORY_ITEM, 27275);
	    	UMBRAL_DISPLACER = prop.getInt();
	    	prop = config.getOrCreateIntProperty("wikidsTempest", CATEGORY_ITEM, 27276);
	    	WIKIDS_TEMPEST = prop.getInt();
	    	prop = config.getOrCreateIntProperty("hornOfEverflowing", CATEGORY_ITEM, 27277);
	    	HORN_OF_EVERFLOWING = prop.getInt();
	    	prop = config.getOrCreateIntProperty("timelordsPocketWatch", CATEGORY_ITEM, 27278);
	    	TIMELORDS_POCKET_WATCH = prop.getInt();
	    	prop = config.getOrCreateIntProperty("calcinedAsh", CATEGORY_ITEM, 27279);
	    	CALCINED_ASH = prop.getInt();
	    	prop = config.getOrCreateIntProperty("alchemicalCoal", CATEGORY_ITEM, 27280);
	    	ALCHEMICAL_COAL = prop.getInt();
	    	prop = config.getOrCreateIntProperty("mobiusFuel", CATEGORY_ITEM, 27281);
	    	MOBIUS_FUEL = prop.getInt();
	    	prop = config.getOrCreateIntProperty("diviningRod", CATEGORY_ITEM, 27282);
	    	DIVINING_ROD = prop.getInt();
	    	prop = config.getOrCreateIntProperty("panacea", CATEGORY_ITEM, 27283);
	    	PANACEA = prop.getInt();
	    	prop = config.getOrCreateIntProperty("autoMail", CATEGORY_ITEM, 27284);
	    	AUTO_MAIL = prop.getInt();
	    	prop = config.getOrCreateIntProperty("caster", CATEGORY_ITEM, 27285);
	    	CASTER = prop.getInt();
	    	prop = config.getOrCreateIntProperty("projector", CATEGORY_ITEM, 27286);
	    	PROJECTOR = prop.getInt();
	    	prop = config.getOrCreateIntProperty("algot", CATEGORY_ITEM, 27287);
	    	ALGOT = prop.getInt();
	    	prop = config.getOrCreateIntProperty("wolfBoots", CATEGORY_ITEM, 27288);
	    	WOLF_BOOTS = prop.getInt();
	    	prop = config.getOrCreateIntProperty("darkMatter", CATEGORY_ITEM, 27289);
	    	DARK_MATTER = prop.getInt();
	    	prop = config.getOrCreateIntProperty("dmPickaxe", CATEGORY_ITEM, 27290);
	    	DARK_MATTER_PICKAXE = prop.getInt();
	    	prop = config.getOrCreateIntProperty("dmShovel", CATEGORY_ITEM, 27291);
	    	DARK_MATTER_SHOVEL = prop.getInt();
	    	prop = config.getOrCreateIntProperty("dmHoe", CATEGORY_ITEM, 27292);
	    	DARK_MATTER_HOE = prop.getInt();
	    	prop = config.getOrCreateIntProperty("dmShears", CATEGORY_ITEM, 27292);
	    	DARK_MATTER_SHEARS = prop.getInt();
	    	prop = config.getOrCreateIntProperty("dmHammer", CATEGORY_ITEM, 27293);
	    	DARK_MATTER_HAMMER = prop.getInt();
	    	prop = config.getOrCreateIntProperty("dmSword", CATEGORY_ITEM, 27294);
	    	DARK_MATTER_SWORD = prop.getInt();
	    	prop = config.getOrCreateIntProperty("dmBow", CATEGORY_ITEM, 27295);
	    	DARK_MATTER_BOW = prop.getInt();
	    	prop = config.getOrCreateIntProperty("dmFishingRod", CATEGORY_ITEM, 27296);
	    	DARK_MATTER_FISHING_ROD = prop.getInt();
	    	prop = config.getOrCreateIntProperty("dmArmor", CATEGORY_ITEM, 27297);
	    	DARK_MATTER_ARMOR = prop.getInt();
	    	prop = config.getOrCreateIntProperty("wardOfXeno", CATEGORY_ITEM, 27297);
	    	WARD_OF_XENOPHOBIA = prop.getInt();
	    	
	    	/* Keybinds */
        	prop = config.getOrCreateIntProperty("extra", CATEGORY_KEYBIND, 46);
        	prop = config.getOrCreateIntProperty("charge", CATEGORY_KEYBIND, 47);
        	prop = config.getOrCreateIntProperty("toggle", CATEGORY_KEYBIND, 34);
        	prop = config.getOrCreateIntProperty("release", CATEGORY_KEYBIND, 19);
        	
        	/* Write */
        	config.save();
        }
	}
}
