package com.pahimar.ee3.item.crafting;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.helper.RecipeHelper;
import com.pahimar.ee3.item.WrappedStack;

/**
 * Equivalent-Exchange-3
 * 
 * RecipesIMC
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class RecipesIMC {
    
    private static Multimap<WrappedStack, List<WrappedStack>> imcRecipes = HashMultimap.create();
    
    public static Multimap<WrappedStack, List<WrappedStack>> getIMCRecipes() {
        return imcRecipes;
    }
    
    public static void addRecipe(WrappedStack recipeOutput, List<?> recipeInputs) {
        
        imcRecipes.put(recipeOutput, RecipeHelper.collateInputStacks(recipeInputs));
    }
}
