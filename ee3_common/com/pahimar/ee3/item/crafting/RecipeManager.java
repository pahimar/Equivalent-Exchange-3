package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.core.util.RecipeHelper;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipeManager {

    private static RecipeManager recipeManager = null;

    private Multimap<CustomWrappedStack, List<CustomWrappedStack>> recipeMap;

    private List<CustomWrappedStack> wildCardStacks;

    private RecipeManager() {

        recipeMap = HashMultimap.create();
        wildCardStacks = RecipeHelper.discoverWildCards();
    }

    public static RecipeManager getInstance() {

        if (recipeManager == null) {
            recipeManager = new RecipeManager();
        }

        return recipeManager;
    }

    public boolean hasRecipe(CustomWrappedStack customWrappedStack) {

        return recipeMap.containsKey(customWrappedStack);
    }

    public boolean hasRecipe(ItemStack itemStack) {

        return hasRecipe(new CustomWrappedStack(itemStack));
    }

    public int countRecipes(CustomWrappedStack customWrappedStack) {

        Collection<List<CustomWrappedStack>> keys = recipeMap.get(customWrappedStack);

        return keys.size();
    }

    public int countRecipes(ItemStack itemStack) {

        return countRecipes(new CustomWrappedStack(itemStack));
    }

    public Collection<List<CustomWrappedStack>> getRecipes(CustomWrappedStack customWrappedStack) {

        return recipeMap.get(customWrappedStack);
    }

    public Collection<List<CustomWrappedStack>> getRecipes(ItemStack itemStack) {

        return getRecipes(new CustomWrappedStack(itemStack));
    }

    public void addRecipe(CustomWrappedStack recipeOutput, List<?> recipeInputs) {

        ArrayList<CustomWrappedStack> collatedStacks = new ArrayList<CustomWrappedStack>();

        CustomWrappedStack wrappedInput = null;

        /**
         * For every input in the input list, check to see if we have discovered
         * it already - If we have, add it to the one we already have - If we
         * have not, add it to the collection of discovered items
         */
        for (Object object : recipeInputs) {

        }
    }
}
