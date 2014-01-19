package com.pahimar.ee3.item.crafting;

import net.minecraft.item.ItemStack;

import java.util.List;

public class RecipeAludel
{
    private ItemStack recipeOutput;
    private List<ItemStack> recipeInputs;

    public RecipeAludel(ItemStack recipeOutput, List<ItemStack> recipeInputs)
    {
        this.recipeOutput = recipeOutput;
        this.recipeInputs = recipeInputs;
    }

    public boolean matches(RecipeAludel recipeAludel)
    {
        return false;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    public List<ItemStack> getRecipeInputs()
    {
        return this.recipeInputs;
    }
}
