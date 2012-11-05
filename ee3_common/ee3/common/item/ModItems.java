package ee3.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.EquivalentExchange3;
import ee3.common.lib.ItemIds;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

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
    public static final String INERT_STONE_NAME = "inertStone";
    public static final String MINIUM_STONE_NAME = "miniumStone";
    public static final String PHILOSOPHER_STONE_NAME = "philStone";
    public static final String ALCHEMY_DUST_NAME = "alchemyDust";

    /* Mod item instances */
    public static Item miniumShard;
    public static Item inertStone;
    public static Item miniumStone;
    public static Item philStone;
    public static Item alchemyDust;

    public static void init() {
        /* Initialize each mod item individually */
        miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD).setIconCoord(0, 0).setItemName(MINIUM_SHARD_NAME).setCreativeTab(EquivalentExchange3.tabsEE3);
        inertStone = new ItemInertStone(ItemIds.INERT_STONE).setIconCoord(1, 0).setItemName(INERT_STONE_NAME).setCreativeTab(EquivalentExchange3.tabsEE3);
        miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE).setIconCoord(2, 0).setItemName(MINIUM_STONE_NAME).setCreativeTab(EquivalentExchange3.tabsEE3);
        philStone = new ItemPhilosopherStone(ItemIds.PHILOSOPHER_STONE).setIconCoord(3, 0).setItemName(PHILOSOPHER_STONE_NAME).setCreativeTab(EquivalentExchange3.tabsEE3);
        alchemyDust = new ItemAlchemyDust(ItemIds.PHILOSOPHER_STONE + 1).setIconCoord(0, 3).setItemName(ALCHEMY_DUST_NAME).setCreativeTab(EquivalentExchange3.tabsEE3);

        miniumStone.setContainerItem(miniumStone);
        philStone.setContainerItem(philStone);

        GameRegistry.addRecipe(new ItemStack(inertStone), 
                new Object[] {"sis","igi","sis", 
            Character.valueOf('s'), Block.stone,
            Character.valueOf('i'), Item.ingotIron,
            Character.valueOf('g'), Item.ingotGold
        });
        
        GameRegistry.addRecipe(new ItemStack(miniumStone), 
        		new Object[] {"sss","sis","sss", 
	        Character.valueOf('s'), miniumShard, 
	        Character.valueOf('i'), inertStone
        });

    }
}
