package ee3.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.EquivalentExchange3;
import ee3.common.lib.ItemIds;
import ee3.common.lib.Strings;
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

    /* Mod item instances */
    public static Item miniumShard;
    public static Item inertStone;
    public static Item miniumStone;
    public static Item philStone;
    public static Item alchemyDust;

    public static void init() {
        /* Initialize each mod item individually */
        miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD);
        inertStone = new ItemInertStone(ItemIds.INERT_STONE);
        miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE);
        philStone = new ItemPhilosopherStone(ItemIds.PHILOSOPHER_STONE);
        alchemyDust = new ItemAlchemyDust(ItemIds.ALCHEMY_DUST);

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
