package ee3.common.core.handlers;

import java.io.File;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLLog;
import ee3.common.EquivalentExchange3;
import ee3.common.lib.ItemIds;
import net.minecraftforge.common.Configuration;
import static net.minecraftforge.common.Configuration.*;
import static ee3.common.lib.Reference.*;

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

    public static boolean AUTO_RESOLVE_IDS;
    public static boolean ENABLE_SOUNDS;
    public static boolean ENABLE_PARTICLES;

    public static void init(File configFile) {
        Configuration configuration = new Configuration(configFile);

        try {
            configuration.load();

            // TODO: Clean up property names

            /* General Configs */
            ENABLE_SOUNDS = configuration.getOrCreateBooleanProperty("enable_sounds", CATEGORY_GENERAL, true).getBoolean(false);
            ENABLE_PARTICLES = configuration.getOrCreateBooleanProperty("enable_particles", CATEGORY_GENERAL, true).getBoolean(false);

            /* Block Configs */
            AUTO_RESOLVE_IDS = configuration.getOrCreateBooleanProperty("auto_resolve_ids", CATEGORY_BLOCK, false).getBoolean(false);

            /* Item Configs */
            ItemIds.MINIUM_SHARD = configuration.getOrCreateIntProperty("miniumShard", CATEGORY_ITEM, 27269).getInt(27269);
            ItemIds.MINIUM_STONE = configuration.getOrCreateIntProperty("miniumStone", CATEGORY_ITEM, 27270).getInt(27270);
            ItemIds.PHIL_STONE = configuration.getOrCreateIntProperty("philStone", CATEGORY_ITEM, 27271).getInt(27271);

            /* KeyBinding Configs */
            EquivalentExchange3.proxy.setKeyBinding(KEYBINDING_EXTRA, configuration.getOrCreateIntProperty(KEYBINDING_EXTRA, CATEGORY_KEYBIND, KEYBINDING_EXTRA_DEFAULT).getInt(KEYBINDING_EXTRA_DEFAULT));
            EquivalentExchange3.proxy.setKeyBinding(KEYBINDING_CHARGE, configuration.getOrCreateIntProperty(KEYBINDING_CHARGE, CATEGORY_KEYBIND, KEYBINDING_CHARGE_DEFAULT).getInt(KEYBINDING_CHARGE_DEFAULT));
            EquivalentExchange3.proxy.setKeyBinding(KEYBINDING_TOGGLE, configuration.getOrCreateIntProperty(KEYBINDING_TOGGLE, CATEGORY_KEYBIND, KEYBINDING_TOGGLE_DEFAULT).getInt(KEYBINDING_TOGGLE_DEFAULT));
            EquivalentExchange3.proxy.setKeyBinding(KEYBINDING_RELEASE, configuration.getOrCreateIntProperty(KEYBINDING_RELEASE, CATEGORY_KEYBIND, KEYBINDING_RELEASE_DEFAULT).getInt(KEYBINDING_RELEASE_DEFAULT));
        }
        catch (Exception e) {
            // TODO: Clean up the logging message
            FMLLog.log(Level.SEVERE, e, "Equivalent Exchange 3 has had a problem loading its configuration");
        }
        finally {
            configuration.save();
        }
    }
}
