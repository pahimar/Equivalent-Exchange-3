package com.pahimar.ee3.api.exchange;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.Mod;

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

    @Mod.Instance("EE3")
    private static Object ee3Mod;

    private static class EE3Wrapper {
        private static EquivalentExchange3 ee3mod;
    }

    private static void init() {

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }
}
