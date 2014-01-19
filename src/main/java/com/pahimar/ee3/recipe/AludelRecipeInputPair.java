package com.pahimar.ee3.recipe;

import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.helper.LogHelper;
import com.pahimar.ee3.item.ItemAlchemicalDust;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class AludelRecipeInputPair
{
    public final ItemStack inputStack;
    public final ItemStack dustStack;

    public AludelRecipeInputPair(ItemStack inputStack, ItemStack dustStack)
    {
        this.inputStack = inputStack.copy();
        this.dustStack = dustStack.copy();
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof AludelRecipeInputPair)
        {
            AludelRecipeInputPair recipeInputPair = (AludelRecipeInputPair) object;

            LogHelper.debug(String.format("input - this.itemId: %s, object.inputStack.itemId: %s", this.inputStack.itemID, recipeInputPair.inputStack.itemID));
            LogHelper.debug(String.format("input - this.meta: %s, object.inputStack.meta: %s", this.inputStack.getItemDamage(), recipeInputPair.inputStack.getItemDamage()));
            LogHelper.debug(String.format("input - this.stackSize: %s, object.inputStack.stackSize: %s", this.inputStack.stackSize, recipeInputPair.inputStack.stackSize));

            LogHelper.debug(String.format("dust - this.itemId: %s, object.inputStack.itemId: %s", this.dustStack.itemID, recipeInputPair.dustStack.itemID));
            LogHelper.debug(String.format("dust - this.meta: %s, object.inputStack.meta: %s", this.dustStack.getItemDamage(), recipeInputPair.dustStack.getItemDamage()));
            LogHelper.debug(String.format("dust - this.stackSize: %s, object.inputStack.stackSize: %s", this.dustStack.stackSize, recipeInputPair.dustStack.stackSize));

            if (this.inputStack.itemID == recipeInputPair.inputStack.itemID && (this.inputStack.getItemDamage() == recipeInputPair.inputStack.getItemDamage() || this.inputStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || recipeInputPair.inputStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) && this.inputStack.stackSize <= recipeInputPair.inputStack.stackSize)
            {
                if (this.dustStack.itemID == recipeInputPair.dustStack.itemID && this.dustStack.getItemDamage() == recipeInputPair.dustStack.getItemDamage() && this.dustStack.stackSize <= recipeInputPair.dustStack.stackSize)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isValid()
    {
        return inputStack != null && dustStack != null && dustStack.getItem() instanceof ItemAlchemicalDust;
    }

    @Override
    public String toString()
    {
        return String.format("Input stack: %s, Dust stack: %s", ItemHelper.toString(this.inputStack), ItemHelper.toString(this.dustStack));
    }
}
