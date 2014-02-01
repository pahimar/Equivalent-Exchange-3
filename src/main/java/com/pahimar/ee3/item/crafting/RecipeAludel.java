package com.pahimar.ee3.item.crafting;

import com.pahimar.ee3.api.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class RecipeAludel
{
    private ItemStack recipeOutput;
    private ItemStack inputStack;       // TODO Allow this to match on OreDictionary items (so all wood works, rather than just vanilla wood)
    private ItemStack dustStack;

    public RecipeAludel(ItemStack recipeOutput, ItemStack inputStack, ItemStack dustStack)
    {
        this.recipeOutput = recipeOutput;
        this.inputStack = inputStack;
        this.dustStack = dustStack;
    }

    public boolean matches(RecipeAludel recipeAludel)
    {
        return compareItemStacks(this.recipeOutput, recipeAludel.recipeOutput) && compareItemStacks(this.inputStack, recipeAludel.inputStack) && compareItemStacks(this.dustStack, recipeAludel.dustStack);
    }

    public boolean matches(ItemStack inputStack, ItemStack dustStack)
    {
        return compareItemStacks(this.inputStack, inputStack) && compareItemStacks(this.dustStack, dustStack);
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    public ItemStack[] getRecipeInputs()
    {
        return new ItemStack[]{inputStack, dustStack};
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

    private static boolean compareItemStacks(ItemStack itemStack1, ItemStack itemStack2)
    {
        if (itemStack1 != null && itemStack2 != null)
        {
            if (itemStack1.itemID == itemStack2.itemID)
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