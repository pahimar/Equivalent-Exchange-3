package com.pahimar.ee3.recipe;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.helper.LogHelper;
import com.pahimar.ee3.item.ModItems;
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
            // TODO Get EmcValue of itemStack, add it list of EmcValues of the different dusts, sort it, determine position, and determine result from that
            for (ItemStack alchemicalDustStack : ModItems.alchemicalDust.getSubTypes())
            {
                LogHelper.debug(ItemHelper.toString(alchemicalDustStack));
            }
        }

        return calcinationResults;
    }
}
