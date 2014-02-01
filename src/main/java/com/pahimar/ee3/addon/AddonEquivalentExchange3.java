package com.pahimar.ee3.addon;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class AddonEquivalentExchange3
{
    public static void init()
    {
        addRecipes();
        addPreAssignmentEmcValues();
        addPostAssignmentEmcValues();
    }

    public static void addRecipes()
    {
        /**
         * Chalk
         */
        AddonHandler.sendAddRecipe(new ItemStack(ModItems.chalk, 4), new ItemStack(ModBlocks.chalk));

        /**
         * Infused Cloth
         */
        AddonHandler.sendAddRecipe(new ItemStack(ModBlocks.infusedCloth.blockID, 1, 0), new ItemStack(Block.cloth), new ItemStack(ModItems.alchemicalDust.itemID, 1, 1));
        AddonHandler.sendAddRecipe(new ItemStack(ModBlocks.infusedCloth.blockID, 1, 1), new ItemStack(Block.cloth), new ItemStack(ModItems.alchemicalDust.itemID, 1, 2));
        AddonHandler.sendAddRecipe(new ItemStack(ModBlocks.infusedCloth.blockID, 1, 2), new ItemStack(Block.cloth), new ItemStack(ModItems.alchemicalDust.itemID, 1, 3));

        /**
         * Infused Wood
         */
        AddonHandler.sendAddRecipe(new ItemStack(ModBlocks.infusedWood.blockID, 1, 0), new ItemStack(Block.wood), new ItemStack(ModItems.alchemicalDust.itemID, 4, 1));
        AddonHandler.sendAddRecipe(new ItemStack(ModBlocks.infusedWood.blockID, 1, 1), new ItemStack(Block.wood), new ItemStack(ModItems.alchemicalDust.itemID, 4, 2));
        AddonHandler.sendAddRecipe(new ItemStack(ModBlocks.infusedWood.blockID, 1, 2), new ItemStack(Block.wood), new ItemStack(ModItems.alchemicalDust.itemID, 4, 3));
    }

    private static void addPreAssignmentEmcValues()
    {
        // NOOP
    }

    private static void addPostAssignmentEmcValues()
    {
        // NOOP
    }
}
