package com.pahimar.ee3.util;

import com.pahimar.ee3.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.item.ItemAlchemicalDust;
import net.minecraft.item.ItemStack;

import java.util.TreeMap;

public class CalcinationHelper {
    public static ItemStack getCalcinationResult(ItemStack calcinedStack) {
        ItemStack itemStack = calcinedStack.copy();
        itemStack.stackSize = 1;

        TreeMap<EnergyValue, ItemStack> sortedItems = new TreeMap<EnergyValue, ItemStack>();

        for (ItemStack dustStack : ItemAlchemicalDust.getAlchemicalDusts()) {
            // If the item to be calcined is an alchemical dust, return null (you cannot calcine what's already been calcined)
            if (ItemHelper.equals(itemStack, dustStack)) {
                return null;
            }

            if (EnergyValueRegistry.getInstance().hasEnergyValue(dustStack)) {
                sortedItems.put(EnergyValueRegistry.getInstance().getEnergyValue(dustStack), dustStack);
            }
        }

        if (EnergyValueRegistry.getInstance().hasEnergyValue(itemStack)) {
            if (sortedItems.containsKey(EnergyValueRegistry.getInstance().getEnergyValue(itemStack))) {
                return sortedItems.get(EnergyValueRegistry.getInstance().getEnergyValue(itemStack));
            } else {
                sortedItems.put(EnergyValueRegistry.getInstance().getEnergyValue(itemStack), itemStack);

                if (sortedItems.lowerEntry(EnergyValueRegistry.getInstance().getEnergyValue(itemStack)) == null) {
                    return new ItemStack(ModItems.alchemicalDust, 1, 0);
                } else {
                    return sortedItems.lowerEntry(EnergyValueRegistry.getInstance().getEnergyValue(itemStack)).getValue();
                }
            }
        } else {
            return new ItemStack(ModItems.alchemicalDust, 1, 0);
        }
    }
}
