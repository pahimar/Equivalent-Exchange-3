package com.pahimar.ee3.recipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.item.crafting.RecipeAludel;

import java.util.ArrayList;
import java.util.List;

public class RecipeRegistry
{
    private static RecipeRegistry recipeRegistry = null;

    private Multimap<WrappedStack, List<WrappedStack>> recipeMap;
    private Multimap<WrappedStack, List<WrappedStack>> tempRecipeMap;

    private RecipeRegistry()
    {
        recipeMap = HashMultimap.create();
        tempRecipeMap = HashMultimap.create();

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

    public void addRecipe(Object recipeOutput, List<?> recipeInputList)
    {
        // Verify that the recipe output object can be wrapped
        if (!WrappedStack.canBeWrapped(recipeOutput))
        {
            return;
        }

        // Verify that every recipe input object can be wrapped
        for (Object recipeInputObject : recipeInputList)
        {
            if (!WrappedStack.canBeWrapped(recipeInputObject))
            {
                return;
            }
        }

        // Wrap the recipe output
        WrappedStack wrappedRecipeOutput = new WrappedStack(recipeOutput);
        List<WrappedStack> wrappedRecipeInputList = new ArrayList<WrappedStack>();
        for (Object recipeInputObject : recipeInputList)
        {
            wrappedRecipeInputList.add(new WrappedStack(recipeInputObject));
        }
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
        for (RecipeAludel recipeAludel : RecipesAludel.getInstance().getRecipes())
        {
            WrappedStack recipeOutput = new WrappedStack(recipeAludel.getRecipeOutput());
            List<WrappedStack> recipeInputs = recipeAludel.getRecipeInputsAsWrappedStacks();

            if (!recipeMap.get(recipeOutput).contains(recipeInputs))
            {
                recipeMap.put(recipeOutput, recipeInputs);
            }
        }
    }

    public Multimap<WrappedStack, List<WrappedStack>> getRecipeMappings()
    {
        return recipeRegistry.recipeMap;
    }
}