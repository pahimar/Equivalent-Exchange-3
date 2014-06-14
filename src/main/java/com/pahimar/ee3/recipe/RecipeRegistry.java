package com.pahimar.ee3.recipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.LogHelper;

import java.util.*;

public class RecipeRegistry
{

    private static RecipeRegistry recipeRegistry = null;

    private Multimap<WrappedStack, List<WrappedStack>> recipeMap;
    private List<WrappedStack> discoveredStacks;

    private RecipeRegistry()
    {
        recipeMap = HashMultimap.create();
        discoveredStacks = new ArrayList<WrappedStack>();

        init();
    }

    public static RecipeRegistry getInstance()
    {
        if (recipeRegistry == null)
        {
            recipeRegistry = new RecipeRegistry();
        }

        return recipeRegistry;
    }

    private void init()
    {
        // Add recipes in the vanilla crafting manager
        for (WrappedStack outputStack : RecipesVanilla.getVanillaRecipes().keySet())
        {
            for (List<WrappedStack> inputStacks : RecipesVanilla.getVanillaRecipes().get(outputStack))
            {
                if (!recipeMap.get(outputStack).contains(inputStacks))
                {
                    recipeMap.put(outputStack, inputStacks);
                }
            }
        }

        // Add fluid container recipes
        for (WrappedStack outputStack : RecipesFluidContainers.getFluidContainerRecipes().keySet())
        {
            for (List<WrappedStack> inputStacks : RecipesFluidContainers.getFluidContainerRecipes().get(outputStack))
            {
                if (!recipeMap.get(outputStack).contains(inputStacks))
                {
                    recipeMap.put(outputStack, inputStacks);
                }
            }
        }

        // Add potion recipes
        for (WrappedStack outputStack : RecipesPotions.getPotionRecipes().keySet())
        {
            for (List<WrappedStack> inputStacks : RecipesPotions.getPotionRecipes().get(outputStack))
            {
                if (!recipeMap.get(outputStack).contains(inputStacks))
                {
                    recipeMap.put(outputStack, inputStacks);
                }
            }
        }

        // Add Aludel recipes
//        for (RecipeAludel recipeAludel : RecipesAludel.getInstance().getRecipes())
//        {
//            WrappedStack recipeOutput = new WrappedStack(recipeAludel.getRecipeOutput());
//            List<WrappedStack> recipeInputs = recipeAludel.getRecipeInputsAsWrappedStacks();
//
//            if (!recipeMap.get(recipeOutput).contains(recipeInputs))
//            {
//                recipeMap.put(recipeOutput, recipeInputs);
//            }
//        }

        // Discover all stacks that we can
        discoverStacks();
    }

    public Multimap<WrappedStack, List<WrappedStack>> getRecipeMappings()
    {

        return recipeRegistry.recipeMap;
    }

    public List<WrappedStack> getDiscoveredStacks()
    {

        return Collections.unmodifiableList(recipeRegistry.discoveredStacks);
    }

    private void discoverStacks()
    {
        discoveredStacks = new ArrayList<WrappedStack>();

        // Scan stacks from known recipes
        for (WrappedStack recipeOutput : recipeMap.keySet())
        {
            if (!discoveredStacks.contains(new WrappedStack(recipeOutput.getWrappedStack())))
            {
                discoveredStacks.add(new WrappedStack(recipeOutput.getWrappedStack()));
            }

            for (List<WrappedStack> recipeInputList : recipeMap.get(recipeOutput))
            {
                for (WrappedStack recipeInput : recipeInputList)
                {
                    if (!discoveredStacks.contains(new WrappedStack(recipeInput.getWrappedStack())))
                    {
                        discoveredStacks.add(new WrappedStack(recipeInput.getWrappedStack()));
                    }
                }
            }
        }
    }

    public void dumpRegistry()
    {
        // Sort the keys for output to console
        SortedSet<WrappedStack> set = new TreeSet<WrappedStack>();
        set.addAll(recipeMap.keySet());

        for (WrappedStack key : set)
        {
            Collection<List<WrappedStack>> recipeMappings = recipeMap.get(key);

            for (List<WrappedStack> recipeList : recipeMappings)
            {
                LogHelper.info(String.format("Recipe Output: %s, Recipe Input: %s", key.toString(), recipeList.toString()));
            }
        }
    }
}