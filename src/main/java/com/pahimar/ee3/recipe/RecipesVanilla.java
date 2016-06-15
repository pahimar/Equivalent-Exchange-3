package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.RecipeHelper;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Set;

public class RecipesVanilla {

    public void registerRecipes() {

        CraftingManager.getInstance().getRecipeList().stream()
                .filter(iRecipe -> iRecipe instanceof ShapedRecipes || iRecipe instanceof ShapelessRecipes || iRecipe instanceof ShapedOreRecipe || iRecipe instanceof ShapelessOreRecipe)
                .filter(iRecipe -> iRecipe.getRecipeOutput() != null)
                .forEach(iRecipe -> {
                    Set<WrappedStack> recipeInputs = RecipeHelper.getRecipeInputs(iRecipe);

                    if (!recipeInputs.isEmpty()) {
                        RecipeRegistryProxy.addRecipe(iRecipe.getRecipeOutput(), recipeInputs);
                    }
        });
    }
}
