package com.pahimar.ee3.helper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Equivalent-Exchange-3
 * <p/>
 * GeneralHelper
 *
 * @author pahimar
 */
public class GeneralHelper
{

    public static ItemStack convertObjectToItemStack(Object obj)
    {

        if (obj instanceof Item)
        {
            return new ItemStack((Item) obj);
        }
        else if (obj instanceof Block)
        {
            return new ItemStack((Block) obj);
        }
        else if (obj instanceof ItemStack)
        {
            return (ItemStack) obj;
        }
        else
        {
            return null;
        }
    }

    public static Object[] convertSingleStackToPluralStacks(ItemStack stack)
    {

        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        ItemStack currentStack;

        for (int i = 0; i < stack.stackSize; i++)
        {
            currentStack = new ItemStack(stack.itemID, 1, stack.getItemDamage());
            list.add(currentStack);
        }

        return list.toArray();
    }
}
