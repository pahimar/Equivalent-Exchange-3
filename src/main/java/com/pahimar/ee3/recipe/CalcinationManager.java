package com.pahimar.ee3.recipe;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.item.ItemAlchemicalDust;
import com.pahimar.ee3.item.ModItems;
import net.minecraft.item.ItemStack;

import java.util.TreeMap;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CalcinationManager
 *
 * @author pahimar
 */
public class CalcinationManager
{
    // TODO Random chance to get an extra item in the stack
    public static ItemStack getCalcinationResult(ItemStack calcinedStack)
    {
        ItemStack itemStack = calcinedStack.copy();
        itemStack.stackSize = 1;

        TreeMap<EmcValue, ItemStack> sortedItems = new TreeMap<EmcValue, ItemStack>();

        for (ItemStack dustStack : ItemAlchemicalDust.getAlchemicalDusts())
        {
            // If the item to be calcined is an alchemical dust, return null (you cannot calcine what's already been calcined)
            if (ItemHelper.equals(itemStack, dustStack))
            {
                return null;
            }

            if (EmcRegistry.getInstance().hasEmcValue(dustStack))
            {
                sortedItems.put(EmcRegistry.getInstance().getEmcValue(dustStack), dustStack);
            }
        }

        if (EmcRegistry.getInstance().hasEmcValue(itemStack))
        {
            if (sortedItems.containsKey(EmcRegistry.getInstance().getEmcValue(itemStack)))
            {
                return sortedItems.get(EmcRegistry.getInstance().getEmcValue(itemStack));
            }
            else
            {
                sortedItems.put(EmcRegistry.getInstance().getEmcValue(itemStack), itemStack);

                if (sortedItems.lowerEntry(EmcRegistry.getInstance().getEmcValue(itemStack)) == null)
                {
                    return new ItemStack(ModItems.alchemicalDust, 1, 0);
                }
                else
                {
                    return sortedItems.lowerEntry(EmcRegistry.getInstance().getEmcValue(itemStack)).getValue();
                }
            }
        }
        else
        {
            return new ItemStack(ModItems.alchemicalDust, 1, 0);
        }
    }
}
