package com.pahimar.ee3.item;

import com.pahimar.ee3.lib.ItemIds;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ModItems
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModItems
{

    // Mod item instances
    public static Item miniumShard;
    public static Item inertStone;
    public static Item miniumStone;
    public static Item philStone;
    public static Item alchemicalDust;
    public static Item alchemicalBag;
    public static Item alchemicalChalk;

    public static void init()
    {

        // Initialize each mod item individually
        miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD);
        inertStone = new ItemInertStone(ItemIds.INERT_STONE);
        miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE);
        philStone = new ItemPhilosophersStone(ItemIds.PHILOSOPHERS_STONE);
        alchemicalDust = new ItemAlchemicalDust(ItemIds.ALCHEMICAL_DUST);
        alchemicalBag = new ItemAlchemicalBag(ItemIds.ALCHEMICAL_BAG);
        alchemicalChalk = new ItemAlchemicalChalk(ItemIds.ALCHEMICAL_CHALK);

        // Set container items
        miniumStone.setContainerItem(miniumStone);
        philStone.setContainerItem(philStone);

        // Register items with the GameRegistry
        GameRegistry.registerItem(miniumShard, Strings.MINIUM_SHARD_NAME);
        GameRegistry.registerItem(inertStone, Strings.INERT_STONE_NAME);
        GameRegistry.registerItem(miniumStone, Strings.MINIUM_STONE_NAME);
        GameRegistry.registerItem(philStone, Strings.PHILOSOPHERS_STONE_NAME);
        GameRegistry.registerItem(alchemicalDust, Strings.ALCHEMICAL_DUST_NAME);
        GameRegistry.registerItem(alchemicalBag, Strings.ALCHEMICAL_BAG_NAME);
        GameRegistry.registerItem(alchemicalChalk, Strings.ALCHEMICAL_CHALK_NAME);

        // Add recipes for items
        GameRegistry.addRecipe(new ItemStack(inertStone), new Object[] {"sis", "igi", "sis", 's', Block.stone, 'i', Item.ingotIron, 'g', Item.ingotGold});
        GameRegistry.addRecipe(new ItemStack(miniumStone), new Object[] {"sss", "sis", "sss", 's', miniumShard, 'i', inertStone});
        GameRegistry.addShapelessRecipe(new ItemStack(alchemicalChalk), new ItemStack(Item.clay), new ItemStack(Item.dyePowder.itemID, 1, 15), new ItemStack(Item.dyePowder.itemID, 1, 15), new ItemStack(Item.dyePowder.itemID, 1, 15), new ItemStack(Item.dyePowder.itemID, 1, 15));
    }
}
