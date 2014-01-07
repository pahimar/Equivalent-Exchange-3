package com.pahimar.ee3.recipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.helper.LogHelper;
import com.pahimar.ee3.item.ItemAlchemicalDust;
import com.pahimar.ee3.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesAludel
{
    private static RecipesAludel aludelRegistry = null;

    // TODO Handle that the input stack could be an OreStack
    private Multimap<WrappedStack, WrappedStack[]> aludelRecipes;

    private RecipesAludel()
    {
        aludelRecipes = HashMultimap.create();
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


    public void addRecipe(ItemStack recipeOutput, Object recipeInputStack, ItemStack recipeInputDust)
    {
        WrappedStack wrappedInputStack = new WrappedStack(recipeInputStack);

        if (recipeOutput != null && (wrappedInputStack.getWrappedStack() instanceof ItemStack || wrappedInputStack.getWrappedStack() instanceof OreStack) && recipeInputDust.getItem() instanceof ItemAlchemicalDust)
        {
            WrappedStack wrappedRecipeOutput = new WrappedStack(recipeOutput);
            WrappedStack wrappedDustStack = new WrappedStack(recipeInputDust);

            if (!aludelRecipes.containsKey(wrappedRecipeOutput))
            {
                boolean recipeInputsExistAlready = false;

                for (WrappedStack outputStack : aludelRecipes.keySet())
                {
                    if (!recipeInputsExistAlready)
                    {
                        for (WrappedStack[] recipeInputs : aludelRecipes.get(outputStack))
                        {
                            if (recipeInputs.length == 2 && !recipeInputsExistAlready)
                            {
                                if (recipeInputs[0].equals(wrappedInputStack) && recipeInputs[1].equals(wrappedDustStack))
                                {
                                    recipeInputsExistAlready = true;
                                }
                            }
                        }
                    }
                }

                if (!recipeInputsExistAlready)
                {
                    aludelRecipes.put(wrappedRecipeOutput, new WrappedStack[]{wrappedInputStack, wrappedDustStack});
                }
                else
                {
                    LogHelper.warning(String.format("Failed to add - O: %s, I: %s, D: %s", wrappedRecipeOutput, wrappedInputStack, wrappedDustStack));
                }
            }
        }
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputDust)
    {
        OreStack possibleOreStack = OreStack.getOreStackFromList(recipeInputStack);
        WrappedStack wrappedInputStack;

        if (possibleOreStack != null)
        {
            wrappedInputStack = new WrappedStack(possibleOreStack);
        }
        else
        {
            wrappedInputStack = new WrappedStack(recipeInputStack);
        }

        WrappedStack wrappedDustStack = new WrappedStack(recipeInputDust);

        if (wrappedInputStack.getWrappedStack() != null && wrappedDustStack.getWrappedStack() != null)
        {
            for (WrappedStack recipeOutput : aludelRecipes.keySet())
            {
                for (WrappedStack[] recipeInputs : aludelRecipes.get(recipeOutput))
                {
                    if (recipeInputs.length == 2)
                    {
                        if (recipeInputs[0].equals(wrappedInputStack) && recipeInputs[1].equals(wrappedDustStack))
                        {
                            return (ItemStack) recipeOutput.getWrappedStack();
                        }
                    }
                }
            }
        }

        return null;
    }

    public Multimap<WrappedStack, WrappedStack[]> getRecipeMap()
    {
        return aludelRecipes;
    }

    public void debugDumpMap()
    {
        for (WrappedStack recipeOutput : aludelRecipes.keySet())
        {
            for (WrappedStack[] recipeInputs : aludelRecipes.get(recipeOutput))
            {
                LogHelper.debug(String.format("Output: %s, Input Stack: %s, Dust Stack: %s", recipeOutput, recipeInputs[0], recipeInputs[1]));
            }
        }
    }
}
