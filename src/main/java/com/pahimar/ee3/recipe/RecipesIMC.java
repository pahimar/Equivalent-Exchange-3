package com.pahimar.ee3.recipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.helper.RecipeHelper;

import java.util.List;

/**
 * Equivalent-Exchange-3
 * <p/>
 * RecipesIMC
 *
 * @author pahimar
 */
public class RecipesIMC
{

    private static Multimap<WrappedStack, List<WrappedStack>> imcRecipes = HashMultimap.create();

    public static Multimap<WrappedStack, List<WrappedStack>> getIMCRecipes()
    {
        return imcRecipes;
    }

    public static void addRecipe(WrappedStack recipeOutput, List<?> recipeInputs)
    {
        imcRecipes.put(recipeOutput, RecipeHelper.collateInputStacks(recipeInputs));
    }
}
