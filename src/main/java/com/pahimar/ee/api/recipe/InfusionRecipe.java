package com.pahimar.ee.api.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
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
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inventory) {
        return NonNullList.withSize(inventory.getSizeInventory(), ItemStack.EMPTY);
    }
}
