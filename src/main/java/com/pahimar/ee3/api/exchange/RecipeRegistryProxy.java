package com.pahimar.ee3.api.exchange;

import java.util.List;

/**
 * @deprecated
 * @see com.pahimar.ee3.api.recipe.RecipeRegistryProxy
 */
@Deprecated
public final class RecipeRegistryProxy {

    /**
     *
     * @deprecated
     * @param recipeOutput
     * @param recipeInputList
     * @see com.pahimar.ee3.api.recipe.RecipeRegistryProxy#addRecipe(Object, List)
     */
    @Deprecated
    public static void addRecipe(Object recipeOutput, List<?> recipeInputList) {
        com.pahimar.ee3.api.recipe.RecipeRegistryProxy.addRecipe(recipeOutput, recipeInputList);
    }

    /**
     * @deprecated
     * @see com.pahimar.ee3.api.recipe.RecipeRegistryProxy#dumpRecipeRegistryToLog()
     */
    @Deprecated
    public static void dumpRecipeRegistryToLog() {
        com.pahimar.ee3.api.recipe.RecipeRegistryProxy.dumpRecipeRegistryToLog();
    }
}
