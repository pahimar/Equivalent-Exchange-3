package com.pahimar.ee3.recipe;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.LoaderUtils;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.Loader;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class RecipeRegistry {

    public static final Marker RECIPE_MARKER = MarkerManager.getMarker("EE3_RECIPE", LogHelper.MOD_MARKER);
    public static final RecipeRegistry INSTANCE = new RecipeRegistry();

    private Multimap<WrappedStack, Set<WrappedStack>> recipeMap;
    private ImmutableMultimap<WrappedStack, Set<WrappedStack>> immutableRecipeMap;

    private RecipeRegistry() {
        recipeMap = TreeMultimap.create(WrappedStack.COMPARATOR, Comparators.WRAPPED_STACK_SET_COMPARATOR);
    }

    public void addRecipe(Object recipeOutput, Collection<?> recipeInputList) {

        // Wrap the recipe output
        WrappedStack wrappedRecipeOutput = WrappedStack.wrap(recipeOutput);
        if (wrappedRecipeOutput == null) {
            return;
        }

        Set<WrappedStack> wrappedRecipeInputList = new TreeSet<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (Object recipeInputObject : recipeInputList) {

            WrappedStack wrappedInputObject = WrappedStack.wrap(recipeInputObject);

            if (wrappedInputObject != null) {
                wrappedRecipeInputList.add(wrappedInputObject);
                stringBuilder.append(wrappedInputObject);
                stringBuilder.append(" ");
            }
            else {
                return;
            }
        }

        // Check to see if we already have this recipe in the map
        boolean existsAlready = false;
        for (Set<WrappedStack> recipeInputs : recipeMap.get(wrappedRecipeOutput)) {
            if (recipeInputs.containsAll(wrappedRecipeInputList) && wrappedRecipeInputList.containsAll(recipeInputs)) {
                existsAlready = true;
            }
        }

        // Add the recipe mapping only if we don't already have it
        if (!existsAlready) {
            LogHelper.trace(RECIPE_MARKER, "[{}] Mod with ID '{}' added recipe (Output: {}, Inputs: {})", LoaderUtils.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedRecipeOutput, stringBuilder.toString().trim());
            recipeMap.put(wrappedRecipeOutput, wrappedRecipeInputList);
        }
    }

    public void registerVanillaRecipes() {

        new RecipesVanilla().registerRecipes();
        new RecipesFluidContainers().registerRecipes();
        new RecipesPotions().registerRecipes();
    }

    public Multimap<WrappedStack, Set<WrappedStack>> getRecipeMappings() {

        if (immutableRecipeMap == null) {
            immutableRecipeMap = ImmutableMultimap.copyOf(INSTANCE.recipeMap);
        }

        return immutableRecipeMap;
    }

    public void dumpRecipeRegistryToLog() {

        for (WrappedStack wrappedStack : getRecipeMappings().keySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("Output: %s, Inputs: ", wrappedStack.toString()));
            for (Set<WrappedStack> listStacks : getRecipeMappings().get(wrappedStack)) {
                for (WrappedStack listStack : listStacks) {
                    stringBuilder.append(listStack.toString() + " ");
                }
            }
            LogHelper.info(RECIPE_MARKER, stringBuilder.toString());
        }
    }
}