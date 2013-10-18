package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.helper.RecipeHelper;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipesVanilla {

    private static Multimap<CustomWrappedStack, List<CustomWrappedStack>> vanillaRecipes = null;

    public static Multimap<CustomWrappedStack, List<CustomWrappedStack>> getVanillaRecipes() {

        if (vanillaRecipes == null) {
            init();
        }

        return vanillaRecipes;
    }

    private static void init() {

        vanillaRecipes = HashMultimap.create();

        for (Object recipeObject : CraftingManager.getInstance().getRecipeList()) {

            if (recipeObject instanceof IRecipe) {

                IRecipe recipe = (IRecipe) recipeObject;
                ItemStack recipeOutput = recipe.getRecipeOutput();

                if (recipeOutput != null) {

                    ArrayList<CustomWrappedStack> recipeInputs = RecipeHelper.getRecipeInputs(recipe);
                    vanillaRecipes.put(new CustomWrappedStack(recipeOutput), recipeInputs);
                }
            }
        }
    }
}
