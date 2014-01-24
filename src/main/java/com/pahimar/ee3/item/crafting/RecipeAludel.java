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
        if (recipeOutput.itemID != recipeAludel.recipeOutput.itemID
                || recipeOutput.getItemDamage() != recipeAludel.recipeOutput.getItemDamage()
                || !recipeOutput.getTagCompound().equals(recipeAludel.recipeOutput.stackTagCompound)
                || recipeOutput.stackSize > recipeAludel.recipeOutput.stackSize)
        {
            return false;
        }

        boolean foundMatchingStack;
        for (ItemStack stack1 : recipeInputs)
        {
            foundMatchingStack = false;
            for (ItemStack stack2 : recipeAludel.recipeInputs)
            {
                if (stack1.itemID == stack2.itemID && stack1.getItemDamage() == stack2.getItemDamage()
                        && stack1.getTagCompound().equals(stack2.stackTagCompound)
                        && stack1.stackSize <= stack2.stackSize)
                {
                    foundMatchingStack = true;
                    break;
                }
            }
            if (!foundMatchingStack)
            {
                return false;
            }
        }
        return true;
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
