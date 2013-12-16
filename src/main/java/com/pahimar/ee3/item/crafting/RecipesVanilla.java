package com.pahimar.ee3.item.crafting;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.helper.RecipeHelper;
import com.pahimar.ee3.item.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.ArrayList;
import java.util.List;

public class RecipesVanilla
{

    private static Multimap<WrappedStack, List<WrappedStack>> vanillaRecipes = null;

    public static Multimap<WrappedStack, List<WrappedStack>> getVanillaRecipes()
    {

        if (vanillaRecipes == null)
        {
            init();
        }

        return vanillaRecipes;
    }

    private static void init()
    {

        vanillaRecipes = HashMultimap.create();

        for (Object recipeObject : CraftingManager.getInstance().getRecipeList())
        {

            if (recipeObject instanceof IRecipe)
            {

                IRecipe recipe = (IRecipe) recipeObject;
                ItemStack recipeOutput = recipe.getRecipeOutput();

                if (recipeOutput != null)
                {

                    ArrayList<WrappedStack> recipeInputs = RecipeHelper.getRecipeInputs(recipe);

                    if (!recipeInputs.isEmpty())
                    {
                        vanillaRecipes.put(new WrappedStack(recipeOutput), recipeInputs);
                    }
                }
            }
        }
    }
}
