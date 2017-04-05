package com.pahimar.ee.api.exchange;

import net.minecraft.item.ItemStack;

public interface IEnergyValueProvider {

    EnergyValue getEnergyValueFor(ItemStack itemStack);
}
