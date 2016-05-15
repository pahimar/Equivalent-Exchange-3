package com.pahimar.ee3.api.exchange;

import net.minecraft.item.ItemStack;

public interface IEnergyValueProvider {

    EnergyValue getEnergyValue(ItemStack itemStack);
}
