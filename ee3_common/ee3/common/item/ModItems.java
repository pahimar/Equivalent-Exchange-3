package ee3.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.lib.ItemIds;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

/**
 * ModItems
 * 
 * Contains all relevant mod item instances
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModItems {
    
    /* Item name constants */
    public static final String MINIUM_SHARD_NAME = "miniumShard";
    public static final String MINIUM_STONE_NAME = "miniumStone";
    public static final String PHILOSOPHER_STONE_NAME = "philStone";

    /* Mod item instances */
    public static Item miniumShard;
    public static Item miniumStone;
    public static Item philStone;

    public static void init() {
        /* Initialize each mod item individually */
        miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD).setIconCoord(0, 0).setItemName(MINIUM_SHARD_NAME).setCreativeTab(CreativeTabs.tabMisc);
        miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE).setIconCoord(1, 0).setItemName(MINIUM_STONE_NAME).setCreativeTab(CreativeTabs.tabMisc);
        philStone = new ItemPhilosopherStone(ItemIds.PHILOSOPHER_STONE).setIconCoord(2, 0).setItemName(PHILOSOPHER_STONE_NAME).setCreativeTab(CreativeTabs.tabMisc);

        miniumStone.setContainerItem(miniumStone);
        philStone.setContainerItem(philStone);

    }
}
