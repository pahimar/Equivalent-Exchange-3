package com.pahimar.ee3.api.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class InfusionRecipe implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inventory, World world) {
        return false;
    }

    @Nullable
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventory) {
        return null;
    }

    @Override
    public int getRecipeSize() {
        return 0;
    }

    @Nullable
    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inventory) {
        return new ItemStack[0];
    }
}
