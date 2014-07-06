package com.pahimar.ee3.recipe;

import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.item.crafting.RecipeAludel;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipesAludel
{
    private static RecipesAludel aludelRegistry = null;

    private List<RecipeAludel> aludelRecipes;

    private RecipesAludel()
    {
        aludelRecipes = new ArrayList<RecipeAludel>();
    }

    public static RecipesAludel getInstance()
    {
        if (aludelRegistry == null)
        {
            aludelRegistry = new RecipesAludel();
        }

        return aludelRegistry;
    }

    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        addRecipe(new RecipeAludel(recipeOutput, recipeInputStack, recipeInputDust));
    }

    public void addRecipe(RecipeAludel recipeAludel)
    {
        if (!aludelRecipes.contains(recipeAludel))
        {
            aludelRecipes.add(recipeAludel);
        }
        else
        {
            LogHelper.debug(String.format("Attempted to add RecipeAludel '%s' but already exists in the recipe list", recipeAludel));
        }
    }

    public void addRecipe(ItemStack recipeOutput, OreStack recipeInputStack, ItemStack recipeInputDust)
    {
        addRecipe(new RecipeAludel(recipeOutput, recipeInputStack, recipeInputDust));
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        for (RecipeAludel recipeAludel : aludelRecipes)
        {
            if (recipeAludel.matches(recipeInputStack, recipeInputDust))
            {
                return recipeAludel.getRecipeOutput();
            }
        }

        return null;
    }

    public RecipeAludel getRecipe(ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        for (RecipeAludel recipeAludel : aludelRecipes)
        {
            if (recipeAludel.matches(recipeInputStack, recipeInputDust))
            {
                return recipeAludel;
            }
        }

        return null;
    }

    public List<RecipeAludel> getRecipes()
    {
        return aludelRecipes;
    }

    public void debugDumpMap()
    {
        for (RecipeAludel recipeAludel : aludelRecipes)
        {
            LogHelper.debug(String.format("Output: %s, Input Stack: %s, Dust Stack: %s", recipeAludel.getRecipeOutput(), recipeAludel.getRecipeInputs()[0], recipeAludel.getRecipeInputs()[1]));
        }
    }
}
