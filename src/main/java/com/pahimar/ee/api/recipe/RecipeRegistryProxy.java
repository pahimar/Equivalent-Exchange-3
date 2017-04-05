package com.pahimar.ee.api.recipe;

import com.pahimar.ee.EquivalentExchange;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.Collection;

public final class RecipeRegistryProxy {

    /**
     * TODO Finish JavaDoc
     *
     * @param recipeOutput
     * @param recipeInputs
     */
    public static void addRecipe(Object recipeOutput, Object ... recipeInputs) {
        addRecipe(recipeOutput, Arrays.asList(recipeInputs));
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param recipeOutput
     * @param recipeInputs
     */
    public static void addRecipe(Object recipeOutput, Collection<?> recipeInputs) {

        init();

        if (mod != null) {
            ModWrapper.mod.getRecipeRegistry().addRecipe(recipeOutput, recipeInputs);
        }
    }

    @Mod.Instance("ee")
    private static Object mod;

    private static class ModWrapper {
        private static EquivalentExchange mod;
    }

    private static void init() {

        if (mod != null) {
            ModWrapper.mod = (EquivalentExchange) mod;
        }
    }
}
