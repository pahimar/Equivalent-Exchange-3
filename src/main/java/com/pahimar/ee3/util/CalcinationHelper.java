package com.pahimar.ee3.util;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.init.ModItems;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class CalcinationHelper
{
    private static Random random = new Random();

    public static ItemStack getCalcinationResult(ItemStack itemStack) {
        EnergyValue dustEnergyValue = EnergyValueRegistryProxy.getEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 3));
        EnergyValue itemStackEnergyValue = EnergyValueRegistryProxy.getEnergyValue(itemStack);

        if (dustEnergyValue != null && itemStackEnergyValue != null) {
            int dustAmount = (int) Math.floor(itemStackEnergyValue.getValue() / dustEnergyValue.getValue());
            float residualEMC = itemStackEnergyValue.getValue() - (dustAmount * dustEnergyValue.getValue());

            double u = (double) residualEMC / dustEnergyValue.getValue(); // expected value (µ)
            double s = u / 2; // deviation (σ)
            u *= 1 - 0.0043451773677092; // negative cut-off correction factor
            dustAmount += (int) (Math.max(0, random.nextGaussian() * s + u) + random.nextDouble());

            if (dustAmount > 0) {
                return new ItemStack(ModItems.alchemicalDust, dustAmount, 3);
            }
        }

        return new ItemStack(ModItems.alchemicalDust, 1, 0);
    }
}
