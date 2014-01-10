package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.item.ItemAlchemicalDust;
import net.minecraft.item.ItemStack;

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
        return object instanceof AludelRecipeInputPair && ItemHelper.equals(((AludelRecipeInputPair) object).inputStack, this.inputStack) && ItemHelper.equals(((AludelRecipeInputPair) object).dustStack, this.dustStack);
    }

    public boolean isValid()
    {
        return inputStack != null && dustStack != null && dustStack.getItem() instanceof ItemAlchemicalDust;
    }

    public boolean equalsIgnoreStackSize(AludelRecipeInputPair inputPair)
    {
        if (this.isValid() && inputPair.isValid())
        {
            return equalsIgnoreStackSize(this.inputStack, inputPair.inputStack) && equalsIgnoreStackSize(this.dustStack, inputPair.dustStack);
        }

        return false;
    }

    private static boolean equalsIgnoreStackSize(ItemStack itemStack1, ItemStack itemStack2)
    {
        if (itemStack1 != null && itemStack2 != null)
        {
            if (itemStack1.itemID == itemStack2.itemID && itemStack1.getItemDamage() == itemStack2.getItemDamage())
            {
                if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
                {
                    return itemStack1.getTagCompound().equals(itemStack2.getTagCompound());
                }
                else
                {
                    return true;
                }
            }
            else
            {
                OreStack oreStack1 = OreStack.getOreStackFromList(itemStack1);
                OreStack oreStack2 = OreStack.getOreStackFromList(itemStack2);

                if (oreStack1 != null && oreStack2 != null)
                {
                    return OreStack.compareOreNames(oreStack1, oreStack2);
                }
            }
        }

        return false;
    }
}
