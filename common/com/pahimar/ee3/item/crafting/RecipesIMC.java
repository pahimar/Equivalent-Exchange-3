package com.pahimar.ee3.item.crafting;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.helper.RecipeHelper;
import com.pahimar.ee3.item.CustomWrappedStack;

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
    
    private static Multimap<CustomWrappedStack, List<CustomWrappedStack>> imcRecipes = HashMultimap.create();
    
    public static Multimap<CustomWrappedStack, List<CustomWrappedStack>> getIMCRecipes() {
        return imcRecipes;
    }
    
    public static void addRecipe(CustomWrappedStack recipeOutput, List<?> recipeInputs) {
        
        imcRecipes.put(recipeOutput, RecipeHelper.collateInputStacks(recipeInputs));
    }

}
