package com.pahimar.ee3.item.crafting;


import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class RecipeAludel
{
    private ItemStack recipeOutput;
    private WrappedStack inputStack;
    private ItemStack dustStack;

    public RecipeAludel(ItemStack recipeOutput, ItemStack inputStack, ItemStack dustStack)
    {
        this.recipeOutput = recipeOutput.copy();
        this.inputStack = new WrappedStack(inputStack);
        this.dustStack = dustStack.copy();
    }

    public boolean matches(ItemStack inputStack, ItemStack dustStack)
    {
        return matches(new WrappedStack(inputStack), dustStack);
    }

    public boolean matches(WrappedStack inputStack, ItemStack dustStack)
    {
        return compareStacks(this.inputStack, inputStack) && compareItemStacks(this.dustStack, dustStack);
    }

    private static boolean compareStacks(WrappedStack wrappedStack1, WrappedStack wrappedStack2)
    {
        if (wrappedStack1 != null && wrappedStack1.getWrappedObject() != null && wrappedStack2 != null && wrappedStack2.getWrappedObject() != null)
        {
            if (wrappedStack1.getWrappedObject() instanceof ItemStack && wrappedStack2.getWrappedObject() instanceof ItemStack)
            {
                ItemStack itemStack1 = (ItemStack) wrappedStack1.getWrappedObject();
                itemStack1.stackSize = wrappedStack1.getStackSize();
                ItemStack itemStack2 = (ItemStack) wrappedStack2.getWrappedObject();
                itemStack2.stackSize = wrappedStack2.getStackSize();

                return compareItemStacks(itemStack1, itemStack2);
            }
        }

        return false;
    }

    private static boolean compareItemStacks(ItemStack itemStack1, ItemStack itemStack2)
    {
        if (itemStack1 != null && itemStack2 != null)
        {
            if (itemStack1.getItem().getIdFromItem(itemStack1.getItem()) == itemStack2.getItem().getIdFromItem(itemStack2.getItem()))
            {
                if (itemStack1.getItemDamage() == itemStack2.getItemDamage() || itemStack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || itemStack2.getItemDamage() == OreDictionary.WILDCARD_VALUE)
                {
                    if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
                    {
                        if (itemStack1.getTagCompound().hashCode() == itemStack2.getTagCompound().hashCode())
                        {
                            return itemStack2.stackSize >= itemStack1.stackSize;
                        }
                    }
                    else if (!itemStack1.hasTagCompound() && !itemStack2.hasTagCompound())
                    {
                        return itemStack2.stackSize >= itemStack1.stackSize;
                    }
                }
            }
        }

        return false;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    public WrappedStack[] getRecipeInputs()
    {
        return new WrappedStack[]{inputStack, new WrappedStack(dustStack)};
    }

    public List<WrappedStack> getRecipeInputsAsWrappedStacks()
    {
        List<WrappedStack> recipeInputs = new ArrayList<WrappedStack>();
        recipeInputs.add(new WrappedStack(inputStack));
        recipeInputs.add(new WrappedStack(dustStack));
        return recipeInputs;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof RecipeAludel)
        {
            return matches((RecipeAludel) object);
        }

        return false;
    }

    public boolean matches(RecipeAludel recipeAludel)
    {
        return compareItemStacks(this.recipeOutput, recipeAludel.recipeOutput) && matches(recipeAludel.inputStack, recipeAludel.dustStack);
    }

    @Override
    public String toString()
    {
        return String.format("Output: %s, Input: %s, Dust: %s", recipeOutput, inputStack, dustStack);
    }
}
