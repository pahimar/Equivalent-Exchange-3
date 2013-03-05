package com.pahimar.ee3.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.lib.ItemIds;

import cpw.mods.fml.common.registry.GameRegistry;

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
    public static Item alchemyBag;

    public static void init() {

        /* Initialize each mod item individually */
        //miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD);
        inertStone = new ItemInertStone(ItemIds.INERT_STONE);
        //miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE);
        //philStone = new ItemPhilosopherStone(ItemIds.PHILOSOPHER_STONE);
        //alchemyDust = new ItemAlchemyDust(ItemIds.ALCHEMY_DUST);
        //alchemyBag = new ItemAlchemyBag(ItemIds.ALCHEMY_BAG);

        //miniumStone.setContainerItem(miniumStone);
        //philStone.setContainerItem(philStone);

        //GameRegistry.addRecipe(new ItemStack(inertStone), new Object[] { "sis", "igi", "sis", Character.valueOf('s'), Block.stone, Character.valueOf('i'), Item.ingotIron, Character.valueOf('g'), Item.ingotGold });

        //GameRegistry.addRecipe(new ItemStack(miniumStone), new Object[] { "sss", "sis", "sss", Character.valueOf('s'), miniumShard, Character.valueOf('i'), inertStone });

    }
}
