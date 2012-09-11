package ee3.common.core.handlers;

import java.io.File;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLLog;
import ee3.common.EquivalentExchange3;
import ee3.common.block.ModBlocks;
import ee3.common.item.ModItems;
import ee3.common.lib.BlockIds;
import ee3.common.lib.ConfigurationSettings;
import ee3.common.lib.ItemIds;
import ee3.common.lib.Reference;
import net.minecraftforge.common.Configuration;
import static net.minecraftforge.common.Configuration.*;

/**
 * ConfigurationManager
 * 
 * Loads in configuration data from disk
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ConfigurationHandler {

    private static final String CATEGORY_KEYBIND = "keybinds";

    public static void init(File configFile) {
        Configuration configuration = new Configuration(configFile);

        try {
            configuration.load();

            /* General Configs */
            ConfigurationSettings.ENABLE_SOUNDS = configuration.getOrCreateBooleanProperty(Reference.ENABLE_SOUNDS, CATEGORY_GENERAL, ConfigurationSettings.ENABLE_SOUNDS_DEFAULT).getBoolean(ConfigurationSettings.ENABLE_SOUNDS_DEFAULT);
            ConfigurationSettings.ENABLE_PARTICLE_FX = configuration.getOrCreateBooleanProperty(Reference.ENABLE_PARTICLE_FX, CATEGORY_GENERAL, ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT).getBoolean(ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT);
            ConfigurationSettings.MINIUM_STONE_TRANSMUTE_COST = configuration.getOrCreateIntProperty(ConfigurationSettings.MINIUM_STONE_TRANSMUTE_COST_CONFIGNAME, CATEGORY_GENERAL, ConfigurationSettings.MINIUM_STONE_TRANSMUTE_COST_DEFAULT).getInt(ConfigurationSettings.MINIUM_STONE_TRANSMUTE_COST_DEFAULT);
            ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY = configuration.getOrCreateIntProperty(ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY_CONFIGNAME, CATEGORY_GENERAL, ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY_DEFAULT).getInt(ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY_DEFAULT);

            /* Block Configs */
            ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS = configuration.getOrCreateBooleanProperty(Reference.AUTO_RESOLVE_BLOCK_IDS, CATEGORY_BLOCK, ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT).getBoolean(ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT);
            BlockIds.CALCINATOR =  configuration.getOrCreateIntProperty(ModBlocks.CALCINATOR_NAME, CATEGORY_BLOCK, BlockIds.CALCINATOR_DEFAULT).getInt(BlockIds.CALCINATOR_DEFAULT);
            BlockIds.RED_WATER_STILL = configuration.getOrCreateIntProperty(ModBlocks.RED_WATER_STILL_NAME, CATEGORY_BLOCK, BlockIds.RED_WATER_STILL_DEFAULT).getInt(BlockIds.RED_WATER_STILL_DEFAULT);

            /* Item Configs */
            ItemIds.MINIUM_SHARD = configuration.getOrCreateIntProperty(ModItems.MINIUM_SHARD_NAME, CATEGORY_ITEM, ItemIds.MINIUM_SHARD_DEFAULT).getInt(ItemIds.MINIUM_SHARD_DEFAULT);
            ItemIds.MINIUM_STONE = configuration.getOrCreateIntProperty(ModItems.MINIUM_STONE_NAME, CATEGORY_ITEM, ItemIds.MINIUM_STONE_DEFAULT).getInt(ItemIds.MINIUM_STONE_DEFAULT);            
            ItemIds.PHILOSOPHER_STONE = configuration.getOrCreateIntProperty(ModItems.PHILOSOPHER_STONE_NAME, CATEGORY_ITEM, ItemIds.PHILOSOPHER_STONE_DEFAULT).getInt(ItemIds.PHILOSOPHER_STONE_DEFAULT);

            /* KeyBinding Configs */
            EquivalentExchange3.proxy.setKeyBinding(Reference.KEYBINDING_EXTRA, configuration.getOrCreateIntProperty(Reference.KEYBINDING_EXTRA, CATEGORY_KEYBIND, Reference.KEYBINDING_EXTRA_DEFAULT).getInt(Reference.KEYBINDING_EXTRA_DEFAULT));
            EquivalentExchange3.proxy.setKeyBinding(Reference.KEYBINDING_CHARGE, configuration.getOrCreateIntProperty(Reference.KEYBINDING_CHARGE, CATEGORY_KEYBIND, Reference.KEYBINDING_CHARGE_DEFAULT).getInt(Reference.KEYBINDING_CHARGE_DEFAULT));
            EquivalentExchange3.proxy.setKeyBinding(Reference.KEYBINDING_TOGGLE, configuration.getOrCreateIntProperty(Reference.KEYBINDING_TOGGLE, CATEGORY_KEYBIND, Reference.KEYBINDING_TOGGLE_DEFAULT).getInt(Reference.KEYBINDING_TOGGLE_DEFAULT));
            EquivalentExchange3.proxy.setKeyBinding(Reference.KEYBINDING_RELEASE, configuration.getOrCreateIntProperty(Reference.KEYBINDING_RELEASE, CATEGORY_KEYBIND, Reference.KEYBINDING_RELEASE_DEFAULT).getInt(Reference.KEYBINDING_RELEASE_DEFAULT));
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        }
        finally {
            configuration.save();
        }
    }
}
