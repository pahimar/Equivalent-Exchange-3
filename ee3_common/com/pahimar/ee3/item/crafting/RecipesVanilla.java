package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipesVanilla {

    private static Multimap<CustomWrappedStack, List<CustomWrappedStack>> vanillaRecipes = null;

    ArrayList<CustomWrappedStack> discoveredItems = new ArrayList<CustomWrappedStack>();

    public static Multimap<CustomWrappedStack, List<CustomWrappedStack>> getVanillaRecipes() {

        if (vanillaRecipes == null) {
            init();
        }

        return vanillaRecipes;
    }

    private static void init() {

        vanillaRecipes = HashMultimap.create();
    }

    private static void discoverItems() {

    }
}
