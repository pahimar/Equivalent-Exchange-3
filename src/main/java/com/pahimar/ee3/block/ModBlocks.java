package com.pahimar.ee3.block;

import com.pahimar.ee3.lib.BlockIds;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ModBlocks
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModBlocks
{

    /* Mod block instances */
    public static Block calcinator;
    public static Block aludelBase;
    public static Block alchemicalChest;
    public static Block glassBell;

    public static void init()
    {

        calcinator = new BlockCalcinator(BlockIds.CALCINATOR);
        aludelBase = new BlockAludelBase(BlockIds.ALUDEL_BASE);
        alchemicalChest = new BlockAlchemicalChest(BlockIds.ALCHEMICAL_CHEST);
        glassBell = new BlockGlassBell(BlockIds.GLASS_BELL);

        GameRegistry.registerBlock(calcinator, Strings.CALCINATOR_NAME);
        GameRegistry.registerBlock(aludelBase, Strings.ALUDEL_NAME);
        GameRegistry.registerBlock(alchemicalChest, Strings.ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerBlock(glassBell, Strings.GLASS_BELL_NAME);

        initBlockRecipes();
    }

    private static void initBlockRecipes()
    {

        GameRegistry.addRecipe(new ItemStack(glassBell), new Object[] {"iii", "i i", "i i", 'i', Block.glass});
        GameRegistry.addRecipe(new ItemStack(aludelBase), new Object[] {"iii", "sis", "iii", 'i', Item.ingotIron, 's', Block.stone});
    }
}
