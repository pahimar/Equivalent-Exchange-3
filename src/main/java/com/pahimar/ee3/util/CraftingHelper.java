package com.pahimar.ee3.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingHelper
{
    public static void addShapedOreRecipe(ItemStack outputItemStack, Object... objectInputs)
    {
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(outputItemStack, objectInputs));
    }

    public static void addShapelessOreRecipe(ItemStack outputItemStack, Object... objectInputs)
    {
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(outputItemStack, objectInputs));
    }
}
