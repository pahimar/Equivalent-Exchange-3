package com.pahimar.ee3.recipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.api.WrappedStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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

        // Add recipes gathered via IMC
        for (WrappedStack outputStack : RecipesIMC.getIMCRecipes().keySet())
        {
            for (List<WrappedStack> inputStacks : RecipesIMC.getIMCRecipes().get(outputStack))
            {
                if (!recipeMap.get(outputStack).contains(inputStacks))
                {
                    recipeMap.put(outputStack, inputStacks);
                }
            }
        }

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

        // Scan stacks from vanilla item array
        for (int i = 0; i < Item.itemsList.length; i++)
        {
            if (Item.itemsList[i] != null)
            {
                if (Item.itemsList[i].getHasSubtypes())
                {
                    for (int meta = 0; meta < 16; meta++)
                    {
                        WrappedStack wrappedItemStack = new WrappedStack(new ItemStack(Item.itemsList[i].itemID, 1, meta));

                        if (!discoveredStacks.contains(wrappedItemStack))
                        {
                            discoveredStacks.add(wrappedItemStack);
                        }
                    }
                }
                else
                {
                    WrappedStack wrappedItemStack = new WrappedStack(Item.itemsList[i]);

                    if (!discoveredStacks.contains(wrappedItemStack))
                    {
                        discoveredStacks.add(wrappedItemStack);
                    }
                }
            }
        }
    }

    @Override
    public String toString()
    {

        StringBuilder stringBuilder = new StringBuilder();

        // Sort the keys for output to console
        SortedSet<WrappedStack> set = new TreeSet<WrappedStack>();
        set.addAll(recipeMap.keySet());

        for (WrappedStack key : set)
        {

            Collection<List<WrappedStack>> recipeMappings = recipeMap.get(key);

            for (List<WrappedStack> recipeList : recipeMappings)
            {
                stringBuilder.append(String.format("Recipe Output: %s, Recipe Input: %s\n", key.toString(), recipeList.toString()));
            }
        }

        return stringBuilder.toString();
    }
}
