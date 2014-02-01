package com.pahimar.ee3.addon;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.item.ModItems;
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
