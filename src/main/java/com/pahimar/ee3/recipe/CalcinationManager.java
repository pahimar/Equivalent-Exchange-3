package com.pahimar.ee3.recipe;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.lib.Compare;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CalcinationManager
 *
 * @author pahimar
 */
public class CalcinationManager
{
    // Helper stacks
    private static ItemStack ASH_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 0);
    private static ItemStack MINIUM_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 1);
    private static ItemStack VERDANT_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 2);
    private static ItemStack AZURE_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 3);
    private static ItemStack AMARANTHINE_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 4);
    private static ItemStack IRIDESCENT_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 5);

    private static Random random = new Random();

    public static List<ItemStack> getCalcinationResult(ItemStack itemStack)
    {
        ItemStack stack = itemStack.copy();
        List<ItemStack> calcinationResults = new ArrayList<ItemStack>();
        EmcValue emcValue = EmcRegistry.getInstance().getEmcValue(itemStack);

        EmcValue ashEmcValue = EmcRegistry.getInstance().getEmcValue(ASH_DUST_STACK);
        EmcValue miniumEmcValue = EmcRegistry.getInstance().getEmcValue(MINIUM_DUST_STACK);
        EmcValue verdantEmcValue = EmcRegistry.getInstance().getEmcValue(VERDANT_DUST_STACK);
        EmcValue azureEmcValue = EmcRegistry.getInstance().getEmcValue(AZURE_DUST_STACK);
        EmcValue amarathineEmcValue = EmcRegistry.getInstance().getEmcValue(AMARANTHINE_DUST_STACK);
        EmcValue iridescentEmcValue = EmcRegistry.getInstance().getEmcValue(IRIDESCENT_DUST_STACK);

        if (emcValue != null)
        {
            if (Float.compare(emcValue.getValue(), ashEmcValue.getValue()) <= Compare.EQUALS)
            {

            }
            else if
        }
        else
        {
            calcinationResults.add(ASH_DUST_STACK.copy());
        }

        return calcinationResults;
    }
}
