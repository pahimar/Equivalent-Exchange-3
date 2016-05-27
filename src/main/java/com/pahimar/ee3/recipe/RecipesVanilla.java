package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.RecipeHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Arrays;
import java.util.List;

public class RecipesVanilla {

    public void registerRecipes() {

        for (Object recipeObject : CraftingManager.getInstance().getRecipeList()) {

            // Vanilla
            if (recipeObject instanceof ShapedRecipes || recipeObject instanceof ShapelessRecipes || recipeObject instanceof ShapedOreRecipe || recipeObject instanceof ShapelessOreRecipe) {
                IRecipe recipe = (IRecipe) recipeObject;
                ItemStack recipeOutput = recipe.getRecipeOutput();

                if (recipeOutput != null) {

                    List<WrappedStack> recipeInputs = RecipeHelper.getRecipeInputs(recipe);

                    if (!recipeInputs.isEmpty()) {
                        RecipeRegistryProxy.addRecipe(recipeOutput, recipeInputs);
                    }
                }
            }
        }

        // Fixes for OreDictionary entries that may not exist (because the OreDictionary entry has nothing in it)
        RecipeRegistryProxy.addRecipe(new ItemStack(Blocks.daylight_detector), Arrays.asList(new ItemStack(Blocks.glass, 3, OreDictionary.WILDCARD_VALUE), new OreStack("slabWood", 3), new OreStack("gemQuartz", 3)));
    }
}
