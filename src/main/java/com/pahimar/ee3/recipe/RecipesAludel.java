package com.pahimar.ee3.recipe;

import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.helper.LogHelper;
import com.pahimar.ee3.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RecipesAludel
{
    private static RecipesAludel aludelRegistry = null;

    private Map<AludelRecipeInputPair, ItemStack> aludelRecipes;

    private RecipesAludel()
    {
        aludelRecipes = new HashMap<AludelRecipeInputPair, ItemStack>();
    }

    public static RecipesAludel getInstance()
    {
        if (aludelRegistry == null)
        {
            aludelRegistry = new RecipesAludel();
            aludelRegistry.init();
        }

        return aludelRegistry;
    }

    private void init()
    {
        // TODO Trim recipes
        // Alchemical Coal
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 0), new ItemStack(Item.coal, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 32, 1));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 0), new ItemStack(Item.coal, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 1, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 4, 0), new ItemStack(Item.coal, 4, 0), new ItemStack(ModItems.alchemicalDust.itemID, 1, 3));

        // Mobius Fuel
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 1), new ItemStack(Item.coal, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 7, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 1), new ItemStack(Item.coal, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 2, 3));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 1), new ItemStack(ModItems.alchemicalFuel.itemID, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 7, 2));

        // Aeternalis Fuel
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 2), new ItemStack(Item.coal, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 64, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 2), new ItemStack(Item.coal, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 16, 3));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 2), new ItemStack(ModItems.alchemicalFuel.itemID, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 63, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 2), new ItemStack(ModItems.alchemicalFuel.itemID, 1, 0), new ItemStack(ModItems.alchemicalDust.itemID, 16, 3));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 2), new ItemStack(ModItems.alchemicalFuel.itemID, 1, 1), new ItemStack(ModItems.alchemicalDust.itemID, 56, 2));
        aludelRegistry.addRecipe(new ItemStack(ModItems.alchemicalFuel.itemID, 1, 2), new ItemStack(ModItems.alchemicalFuel.itemID, 1, 1), new ItemStack(ModItems.alchemicalDust.itemID, 14, 3));

        // Infused Cloth

        // Infused Wood

        // Infused Planks
    }


    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        AludelRecipeInputPair recipeInputPair = new AludelRecipeInputPair(recipeInputStack, recipeInputDust);

        if (recipeInputPair.isValid() && recipeOutput != null)
        {
            boolean similiarRecipeExists = false;

            for (AludelRecipeInputPair existingRecipeInput : aludelRecipes.keySet())
            {
                if (existingRecipeInput.equalsIgnoreStackSize(recipeInputPair))
                {
                    similiarRecipeExists = true;
                }
            }

            if (!similiarRecipeExists)
            {
                aludelRecipes.put(recipeInputPair, recipeOutput);
            }
        }
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        AludelRecipeInputPair recipeInputPair = new AludelRecipeInputPair(recipeInputStack, recipeInputDust);

        if (recipeInputPair.isValid())
        {
            // TODO Check for keys that are similiar but smaller in size than the provided inputs
            if (aludelRecipes.containsKey(recipeInputPair))
            {
                return aludelRecipes.get(recipeInputPair);
            }
            else
            {

            }
        }

        return null;
    }

    public AludelRecipeInputPair getRecipeInputs(ItemStack itemStack)
    {
        for (AludelRecipeInputPair recipeInputPair : aludelRecipes.keySet())
        {
            if (ItemHelper.equals(aludelRecipes.get(recipeInputPair), itemStack))
            {
                return recipeInputPair;
            }
        }

        return null;
    }

    public Map<AludelRecipeInputPair, ItemStack> getRecipeMap()
    {
        return aludelRecipes;
    }

    public void debugDumpMap()
    {
        for (AludelRecipeInputPair recipeInputPair : aludelRecipes.keySet())
        {
            LogHelper.debug(String.format("Output: %s, Input Stack: %s, Dust Stack: %s", ItemHelper.toString(aludelRecipes.get(recipeInputPair)), ItemHelper.toString(recipeInputPair.inputStack), ItemHelper.toString(recipeInputPair.dustStack)));
        }
    }
}
