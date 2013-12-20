package com.pahimar.ee3.recipe;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CalcinationManager
 *
 * @author pahimar
 */
public class CalcinationManager
{
    public static List<ItemStack> getCalcinationResult(ItemStack itemStack)
    {
        List<ItemStack> calcinationResults = new ArrayList<ItemStack>();

        EmcValue emcValue = EmcRegistry.getEmcValue(itemStack);

        if (emcValue != null)
        {
            // TODO The magic happens here
        }

        return calcinationResults;
    }
}
