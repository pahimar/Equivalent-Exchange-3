package com.pahimar.ee3.item.crafting;

import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
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

    public RecipeAludel(ItemStack recipeOutput, OreStack inputStack, ItemStack dustStack)
    {
        this.recipeOutput = recipeOutput.copy();
        this.inputStack = new WrappedStack(inputStack);
        this.dustStack = dustStack.copy();
    }

    public boolean matches(RecipeAludel recipeAludel)
    {
        return compareItemStacks(this.recipeOutput, recipeAludel.recipeOutput) && matches(recipeAludel.inputStack, recipeAludel.dustStack);
    }

    public boolean matches(ItemStack inputStack, ItemStack dustStack)
    {
        if (OreDictionary.getOreID(inputStack) != -1)
        {
            if (matches(new WrappedStack(new OreStack(inputStack)), dustStack))
            {
                return matches(new WrappedStack(new OreStack(inputStack)), dustStack);
            }
        }

        return matches(new WrappedStack(inputStack), dustStack);
    }

    public boolean matches(WrappedStack inputStack, ItemStack dustStack)
    {
        return compareStacks(this.inputStack, inputStack) && compareItemStacks(this.dustStack, dustStack);
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

    @Override
    public String toString()
    {
        return String.format("Output: %s, Input: %s, Dust: %s", recipeOutput, inputStack, dustStack);
    }

    private static boolean compareStacks(WrappedStack wrappedStack1, WrappedStack wrappedStack2)
    {
        if (wrappedStack1 != null && wrappedStack1.getWrappedStack() != null && wrappedStack2 != null && wrappedStack2.getWrappedStack() != null)
        {
            if (wrappedStack1.getWrappedStack() instanceof ItemStack && wrappedStack2.getWrappedStack() instanceof ItemStack)
            {
                ItemStack itemStack1 = (ItemStack) wrappedStack1.getWrappedStack();
                itemStack1.stackSize = wrappedStack1.getStackSize();
                ItemStack itemStack2 = (ItemStack) wrappedStack2.getWrappedStack();
                itemStack2.stackSize = wrappedStack2.getStackSize();

                return compareItemStacks(itemStack1, itemStack2);
            }
            else if (wrappedStack1.getWrappedStack() instanceof OreStack && wrappedStack2.getWrappedStack() instanceof OreStack)
            {
                if (((OreStack) wrappedStack1.getWrappedStack()).oreName.equalsIgnoreCase(((OreStack) wrappedStack2.getWrappedStack()).oreName))
                {
                    return wrappedStack2.getStackSize() >= wrappedStack1.getStackSize();
                }
            }
        }

        return false;
    }

    private static boolean compareItemStacks(ItemStack itemStack1, ItemStack itemStack2)
    {
        if (itemStack1 != null && itemStack2 != null)
        {
            if (itemStack1.getItem() == itemStack2.getItem())
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
}