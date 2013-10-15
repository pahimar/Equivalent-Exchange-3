package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipeRegistry {

    private static RecipeRegistry recipeRegistry = null;

    private ImmutableMultimap<CustomWrappedStack, List<CustomWrappedStack>> recipeMap;
    public ImmutableList<CustomWrappedStack> discoveredStacks;

    private RecipeRegistry() {

    }

    public static RecipeRegistry getInstance() {

        if (recipeRegistry == null) {
            recipeRegistry = new RecipeRegistry();
            recipeRegistry.init();
        }

        return recipeRegistry;
    }

    private void init() {

        ImmutableMultimap.Builder<CustomWrappedStack, List<CustomWrappedStack>> immutableRecipeMap = ImmutableMultimap.builder();
        
        // Add potion recipes
        immutableRecipeMap.putAll(RecipesPotions.getPotionRecipes());

        // Add smelting recipes in the vanilla smelting manager
        immutableRecipeMap.putAll(RecipesSmelting.getSmeltingRecipes());

        // Add recipes in the vanilla crafting manager
        immutableRecipeMap.putAll(RecipesVanilla.getVanillaRecipes());

        // Add recipes gathered via IMC
        immutableRecipeMap.putAll(RecipesIMC.getIMCRecipes());
        
        recipeMap = immutableRecipeMap.build();
        
        discoverStacks();
    }

    private void discoverStacks() {
        
       List<CustomWrappedStack> foundStacks = new ArrayList<CustomWrappedStack>();
        
        // Scan stacks from known recipes
        SortedSet<CustomWrappedStack> recipeOutputSet = new TreeSet<CustomWrappedStack>();
        recipeOutputSet.addAll(recipeMap.keySet());
        
        Iterator<CustomWrappedStack> recipeOutputIterator = recipeOutputSet.iterator();
        
        while (recipeOutputIterator.hasNext()) {
            CustomWrappedStack recipeOutput = recipeOutputIterator.next();
            
            if (recipeOutput.getWrappedStack() != null) {
                if (!foundStacks.contains(recipeOutput.getWrappedStack())) {
                    foundStacks.add(new CustomWrappedStack(recipeOutput.getWrappedStack()));
                }
            }
        }
        
        // Scan stacks from vanilla item array
        
        // Add all the discovered stacks to the immutable list of discovered stacks
        ImmutableList.Builder<CustomWrappedStack> discoveredStacksBuilder = ImmutableList.builder();
        discoveredStacksBuilder.addAll(foundStacks.iterator());
        discoveredStacks = discoveredStacksBuilder.build();
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        // Sort the keys for output to console
        SortedSet<CustomWrappedStack> set = new TreeSet<CustomWrappedStack>(); 
        set.addAll(recipeMap.keySet());
        
        for (CustomWrappedStack key : set) {

            Collection<List<CustomWrappedStack>> recipeMappings = recipeMap.get(key);

            for (List<CustomWrappedStack> recipeList : recipeMappings) {
                stringBuilder.append(String.format("Recipe Output: %s, Recipe Input: %s\n", key.toString(), recipeList.toString()));
            }
        }

        return stringBuilder.toString();
    }
}
