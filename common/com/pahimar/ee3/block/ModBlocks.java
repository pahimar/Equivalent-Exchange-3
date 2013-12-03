package com.pahimar.ee3.block;

import com.pahimar.ee3.fluid.BlockRedWater;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.lib.BlockIds;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Equivalent-Exchange-3
 * 
 * ModBlocks
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModBlocks {

    /* Mod block instances */
    public static Block calcinator;
    public static Block aludelBase;
    public static Block alchemicalChest;
    public static Block glassBell;
    public static Block blockRedWater;

    public static void init() {

        calcinator = new BlockCalcinator(BlockIds.CALCINATOR);
        aludelBase = new BlockAludelBase(BlockIds.ALUDEL_BASE);
        alchemicalChest = new BlockAlchemicalChest(BlockIds.ALCHEMICAL_CHEST);
        glassBell = new BlockGlassBell(BlockIds.GLASS_BELL);
        blockRedWater = new BlockRedWater(BlockIds.BLOCK_RED_WATER);

        GameRegistry.registerBlock(calcinator, Strings.CALCINATOR_NAME);
        GameRegistry.registerBlock(aludelBase, Strings.ALUDEL_NAME);
        GameRegistry.registerBlock(alchemicalChest, Strings.ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerBlock(glassBell, Strings.GLASS_BELL_NAME);
        GameRegistry.registerBlock(blockRedWater, Strings.BLOCK_RED_WATER_NAME);

        initBlockRecipes();
    }

    private static void initBlockRecipes() {

        GameRegistry.addRecipe(new ItemStack(glassBell), new Object[] { "iii", "i i", "i i", Character.valueOf('i'), Block.glass });
        GameRegistry.addRecipe(new ItemStack(aludelBase), new Object[] { "iii", "sis", "iii", Character.valueOf('i'), Item.ingotIron, Character.valueOf('s'), Block.stone });
    }
}
