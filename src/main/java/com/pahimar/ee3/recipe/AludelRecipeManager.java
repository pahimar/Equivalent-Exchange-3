package com.pahimar.ee3.recipe;

import com.google.common.collect.ImmutableList;
import com.pahimar.ee3.api.exchange.RecipeRegistryProxy;
import com.pahimar.ee3.item.crafting.RecipeAludel;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AludelRecipeManager
{
    private static AludelRecipeManager aludelRegistry = null;

    private List<RecipeAludel> aludelRecipes;

    private AludelRecipeManager()
    {
        aludelRecipes = new ArrayList<RecipeAludel>();
    }

    public static AludelRecipeManager getInstance()
    {
        if (aludelRegistry == null)
        {
            aludelRegistry = new AludelRecipeManager();
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
        return ImmutableList.copyOf(aludelRecipes);
    }

    public static void registerRecipes()
    {
        for (RecipeAludel recipeAludel : AludelRecipeManager.getInstance().getRecipes())
        {
            RecipeRegistryProxy.addRecipe(recipeAludel.getRecipeOutput(), recipeAludel.getRecipeInputsAsWrappedStacks());
        }
    }
}
