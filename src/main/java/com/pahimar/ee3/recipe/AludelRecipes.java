package com.pahimar.ee3.recipe;

import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.item.ItemAlchemicalDust;
import net.minecraft.item.ItemStack;

import java.util.SortedMap;
import java.util.TreeMap;

public class AludelRecipes
{
    private static AludelRecipes aludelRegistry = null;

    // TODO Handle that the input stack could be an OreStack
    private SortedMap<ItemStack, ItemStack[]> aludelRecipes;

    private AludelRecipes()
    {
        init();
    }

    public AludelRecipes getInstance()
    {
        if (aludelRegistry == null)
        {
            aludelRegistry = new AludelRecipes();
        }

        return aludelRegistry;
    }

    private void init()
    {
        aludelRecipes = new TreeMap<ItemStack, ItemStack[]>(ItemHelper.comparator);
    }

    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        if (!aludelRecipes.containsKey(recipeOutput) && recipeInputDust.getItem() instanceof ItemAlchemicalDust)
        {
            aludelRecipes.put(recipeOutput, new ItemStack[]{recipeInputStack, recipeInputDust});
        }
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        for (ItemStack recipeOutput : aludelRecipes.keySet())
        {
            if (ItemHelper.equals(aludelRecipes.get(recipeOutput)[0], recipeInputStack) && ItemHelper.equals(aludelRecipes.get(recipeOutput)[1], recipeInputDust))
            {
                return recipeOutput;
            }
        }

        return null;
    }

    public SortedMap<ItemStack, ItemStack[]> getRecipeMap()
    {
        return aludelRecipes;
    }
}
