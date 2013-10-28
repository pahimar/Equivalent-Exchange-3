package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipeRegistry {

    private static RecipeRegistry recipeRegistry = null;

    private Multimap<CustomWrappedStack, List<CustomWrappedStack>> recipeMap;
    private List<CustomWrappedStack> discoveredStacks;

    public static Multimap<CustomWrappedStack, List<CustomWrappedStack>> getRecipeMappings() {

        lazyInit();
        return recipeRegistry.recipeMap;
    }

    public static List<CustomWrappedStack> getDiscoveredStacks() {

        lazyInit();
        return Collections.unmodifiableList(recipeRegistry.discoveredStacks);
    }

    private static void lazyInit() {

        if (recipeRegistry == null) {
            recipeRegistry = new RecipeRegistry();
            recipeRegistry.init();
        }
    }

    private void init() {

        recipeMap = HashMultimap.create();

        // Add potion recipes
        recipeMap.putAll(RecipesPotions.getPotionRecipes());

        // Add recipes in the vanilla crafting manager
        recipeMap.putAll(RecipesVanilla.getVanillaRecipes());

        // Add recipes gathered via IMC
        recipeMap.putAll(RecipesIMC.getIMCRecipes());

        // Discover all stacks that we can
        discoverStacks();
    }

    private void discoverStacks() {

        discoveredStacks = new ArrayList<CustomWrappedStack>();

        // Scan stacks from known recipes
        for (CustomWrappedStack recipeOutput : recipeMap.keySet()) {
            if (!discoveredStacks.contains(new CustomWrappedStack(recipeOutput.getWrappedStack()))) {
                discoveredStacks.add(new CustomWrappedStack(recipeOutput.getWrappedStack()));
            }
            
            for (List<CustomWrappedStack> recipeInputList : recipeMap.get(recipeOutput)) {
                for (CustomWrappedStack recipeInput : recipeInputList) {
                    if (!discoveredStacks.contains(new CustomWrappedStack(recipeInput.getWrappedStack()))) {
                        discoveredStacks.add(new CustomWrappedStack(recipeInput.getWrappedStack()));
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

                        if (!discoveredStacks.contains(wrappedItemStack)) {
                            discoveredStacks.add(wrappedItemStack);
                        }
                    }
                }
                else {
                    CustomWrappedStack wrappedItemStack = new CustomWrappedStack(Item.itemsList[i]);

                    if (!discoveredStacks.contains(wrappedItemStack)) {
                        discoveredStacks.add(wrappedItemStack);
                    }
                }
            }
        }
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
