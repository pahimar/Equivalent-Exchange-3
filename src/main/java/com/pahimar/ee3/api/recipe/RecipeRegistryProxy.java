package com.pahimar.ee3.api.recipe;

import com.pahimar.ee3.EquivalentExchange3;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.Collection;

public final class RecipeRegistryProxy {

    public static void addRecipe(Object recipeOutput, Object ... recipeInputs) {
        addRecipe(recipeOutput, Arrays.asList(recipeInputs));
    }

    public static void addRecipe(Object recipeOutput, Collection<?> recipeInputList) {

        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getRecipeRegistry().addRecipe(recipeOutput, recipeInputList);
        }
    }

    public static void dumpRecipeRegistryToLog() {

        init();

        // NOOP if EquivalentExchange3 is not present
        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getRecipeRegistry().dumpRecipeRegistryToLog();
        }
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
