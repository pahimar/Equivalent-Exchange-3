package com.pahimar.ee3.addon;

import com.pahimar.ee3.item.ItemAlchemicalDust;
import com.pahimar.ee3.item.ModItems;
import net.minecraft.item.Item;
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
        AddonHandler.sendAddRecipe(new ItemStack(ModItems.chalk, 4), new ItemStack(Item.clay, 4), new ItemStack(Item.dyePowder.itemID, 1, 15));
    }

    private static void addPreAssignmentEmcValues()
    {
        /**
         * Alchemical Dust
         */
        for (int meta = 0; meta < ItemAlchemicalDust.DEFAULT_EMC_VALUES.length; meta++)
        {
            AddonHandler.sendPreValueAssignment(new ItemStack(ModItems.alchemicalDust, 1, meta), ItemAlchemicalDust.DEFAULT_EMC_VALUES[meta]);
        }
    }

    private static void addPostAssignmentEmcValues()
    {

    }
}
