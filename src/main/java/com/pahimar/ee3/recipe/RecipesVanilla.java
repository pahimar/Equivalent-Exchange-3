package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.RecipeHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
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

        // Fixes for OreDictionary entries that may not exist (because the OreDictionary entry has nothing in it)
        RecipeRegistryProxy.addRecipe(new ItemStack(Blocks.DAYLIGHT_DETECTOR), new ItemStack(Blocks.GLASS, 3, OreDictionary.WILDCARD_VALUE), new OreStack("slabWood", 3), new OreStack("gemQuartz", 3));
    }
}
