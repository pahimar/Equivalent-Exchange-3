package com.pahimar.ee3.recipe;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.helper.LogHelper;
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
    private static ItemStack VERDANT_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 1);
    private static ItemStack AZURE_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 2);
    private static ItemStack MINIUM_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 3);
    private static ItemStack AMARANTHINE_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 4);
    private static ItemStack IRIDESCENT_DUST_STACK = new ItemStack(ModItems.alchemicalDust.itemID, 1, 5);

    private static Random random = new Random();

    public static List<ItemStack> getCalcinationResult(ItemStack itemStack)
    {
        ItemStack stack = itemStack.copy();
        List<ItemStack> calcinationResults = new ArrayList<ItemStack>();
        EmcValue emcValue = EmcRegistry.getInstance().getEmcValue(itemStack);

        EmcValue ashEmcValue = EmcRegistry.getInstance().getEmcValue(ASH_DUST_STACK);
        EmcValue verdantEmcValue = EmcRegistry.getInstance().getEmcValue(VERDANT_DUST_STACK);
        EmcValue azureEmcValue = EmcRegistry.getInstance().getEmcValue(AZURE_DUST_STACK);
        EmcValue miniumEmcValue = EmcRegistry.getInstance().getEmcValue(MINIUM_DUST_STACK);
        EmcValue amarathineEmcValue = EmcRegistry.getInstance().getEmcValue(AMARANTHINE_DUST_STACK);
        EmcValue iridescentEmcValue = EmcRegistry.getInstance().getEmcValue(IRIDESCENT_DUST_STACK);

        if (emcValue != null)
        {
            if (emcValue.compareTo(ashEmcValue) < Compare.EQUALS)
            {
                LogHelper.debug("1");
            }
            else if (emcValue.equals(ashEmcValue))
            {
                LogHelper.debug("1");
                calcinationResults.add(ASH_DUST_STACK.copy());
            }
            else if (emcValue.compareTo(ashEmcValue) == Compare.GREATER_THAN && emcValue.compareTo(verdantEmcValue) == Compare.LESSER_THAN)
            {
                LogHelper.debug("1,2");
            }
            else if (emcValue.equals(verdantEmcValue))
            {
                LogHelper.debug("2");
                calcinationResults.add(VERDANT_DUST_STACK.copy());
            }
            else if (emcValue.compareTo(verdantEmcValue) == Compare.GREATER_THAN && emcValue.compareTo(azureEmcValue) == Compare.LESSER_THAN)
            {
                LogHelper.debug("2,3");
            }
            else if (emcValue.equals(azureEmcValue))
            {
                LogHelper.debug("3");
                calcinationResults.add(AZURE_DUST_STACK.copy());
            }
            else if (emcValue.compareTo(azureEmcValue) == Compare.GREATER_THAN && emcValue.compareTo(miniumEmcValue) == Compare.LESSER_THAN)
            {
                LogHelper.debug("3,4");
            }
            else if (emcValue.equals(miniumEmcValue))
            {
                LogHelper.debug("4");
                calcinationResults.add(MINIUM_DUST_STACK.copy());
            }
            else if (emcValue.compareTo(miniumEmcValue) == Compare.GREATER_THAN && emcValue.compareTo(amarathineEmcValue) == Compare.LESSER_THAN)
            {
                LogHelper.debug("4,5");
            }
            else if (emcValue.equals(amarathineEmcValue))
            {
                LogHelper.debug("5");
                calcinationResults.add(AMARANTHINE_DUST_STACK.copy());
            }
            else if (emcValue.compareTo(amarathineEmcValue) == Compare.GREATER_THAN && emcValue.compareTo(iridescentEmcValue) == Compare.LESSER_THAN)
            {
                LogHelper.debug("5,6");
            }
            else if (emcValue.equals(iridescentEmcValue))
            {
                LogHelper.debug("6");
                calcinationResults.add(IRIDESCENT_DUST_STACK.copy());
            }
            else if (emcValue.compareTo(iridescentEmcValue) == Compare.GREATER_THAN)
            {
                LogHelper.debug("6");
                calcinationResults.add(IRIDESCENT_DUST_STACK.copy());
            }
        }
        else
        {
            calcinationResults.add(ASH_DUST_STACK.copy());
        }

        return calcinationResults;
    }
}
