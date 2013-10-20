package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipeRegistry {

    private static RecipeRegistry recipeRegistry = null;

    private ImmutableMultimap<CustomWrappedStack, List<CustomWrappedStack>> recipeMap;
    private ImmutableList<CustomWrappedStack> discoveredStacks;

    public static ImmutableMultimap<CustomWrappedStack, List<CustomWrappedStack>> getRecipeMappings() {

        lazyInit();
        return recipeRegistry.recipeMap;
    }

    public static ImmutableList<CustomWrappedStack> getDiscoveredStacks() {

        lazyInit();
        return recipeRegistry.discoveredStacks;
    }

    private static void lazyInit() {

        if (recipeRegistry == null) {
            recipeRegistry = new RecipeRegistry();
            recipeRegistry.init();
        }
    }

    private void init() {

        ImmutableMultimap.Builder<CustomWrappedStack, List<CustomWrappedStack>> immutableRecipeMap = ImmutableMultimap.builder();

        // Add potion recipes
        //immutableRecipeMap.putAll(RecipesPotions.getPotionRecipes());

        // Add smelting recipes in the vanilla smelting manager
        //immutableRecipeMap.putAll(RecipesSmelting.getSmeltingRecipes());

        // Add recipes in the vanilla crafting manager
        immutableRecipeMap.putAll(RecipesVanilla.getVanillaRecipes());

        // Add recipes gathered via IMC
        immutableRecipeMap.putAll(RecipesIMC.getIMCRecipes());

        // Finalize the Immutable Recipe Map
        recipeMap = immutableRecipeMap.build();

        // Discover all stacks that we can
        discoverStacks();
    }

    private void discoverStacks() {

        List<CustomWrappedStack> foundStacks = new ArrayList<CustomWrappedStack>();

        // Scan stacks from known recipes
        for (CustomWrappedStack recipeOutput : recipeMap.keySet()) {
            if (!foundStacks.contains(new CustomWrappedStack(recipeOutput.getWrappedStack()))) {
                foundStacks.add(new CustomWrappedStack(recipeOutput.getWrappedStack()));
            }
            
            for (List<CustomWrappedStack> recipeInputList : recipeMap.get(recipeOutput)) {
                for (CustomWrappedStack recipeInput : recipeInputList) {
                    if (!foundStacks.contains(new CustomWrappedStack(recipeInput.getWrappedStack()))) {
                        foundStacks.add(new CustomWrappedStack(recipeInput.getWrappedStack()));
                    }
                }
            }
        }

        // Scan stacks from vanilla item array
        for (int i = 0; i < Item.itemsList.length; i++) {
            if (Item.itemsList[i] != null) {
                if (Item.itemsList[i].getHasSubtypes()) {
                    for (int meta = 0; meta < 16; meta++) {
                        CustomWrappedStack wrappedItemStack = new CustomWrappedStack(new ItemStack(Item.itemsList[i].itemID, 1, meta));

                        if (!foundStacks.contains(wrappedItemStack)) {
                            foundStacks.add(wrappedItemStack);
                        }
                    }
                }
                else {
                    CustomWrappedStack wrappedItemStack = new CustomWrappedStack(Item.itemsList[i]);

                    if (!foundStacks.contains(wrappedItemStack)) {
                        foundStacks.add(wrappedItemStack);
                    }
                }
            }
        }

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
