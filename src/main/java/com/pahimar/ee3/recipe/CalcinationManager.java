package com.pahimar.ee3.recipe;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.ModItems;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private static Random random = new Random();

    public static List<ItemStack> getCalcinationResult(ItemStack calcinedStack)
    {
        ItemStack itemStack = calcinedStack.copy();
        List<ItemStack> calcinationResults = new ArrayList<ItemStack>();
        TreeMap<EmcValue, ItemStack> sortedItems = new TreeMap<EmcValue, ItemStack>();

        for (ItemStack dustStack : ModItems.alchemicalDust.getSubTypes())
        {
            if (EmcRegistry.getInstance().hasEmcValue(dustStack))
            {
                sortedItems.put(EmcRegistry.getInstance().getEmcValue(dustStack), dustStack);
            }
        }

        if (EmcRegistry.getInstance().hasEmcValue(itemStack))
        {
            if (sortedItems.containsKey(EmcRegistry.getInstance().getEmcValue(itemStack)))
            {
                calcinationResults.add(sortedItems.get(EmcRegistry.getInstance().getEmcValue(itemStack)));
            }
            else
            {
                sortedItems.put(EmcRegistry.getInstance().getEmcValue(itemStack), itemStack);

                TreeMap<EmcValue, ItemStack> lessThanMap = new TreeMap<EmcValue, ItemStack>(sortedItems.headMap(EmcRegistry.getInstance().getEmcValue(itemStack)));

                if (lessThanMap.size() == 0)
                {
                    calcinationResults.add(new ItemStack(ModItems.alchemicalDust, 1, 0));
                }
                else
                {
                    calcinationResults.add(lessThanMap.lastEntry().getValue());
                }
            }
        }
        else
        {
            calcinationResults.add(new ItemStack(ModItems.alchemicalDust, 1, 0));
        }

        return calcinationResults;
    }
}
