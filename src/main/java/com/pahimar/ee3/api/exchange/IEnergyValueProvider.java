package com.pahimar.ee3.api.exchange;

import net.minecraft.item.ItemStack;

public interface IEnergyValueProvider
{
    public abstract EnergyValue getEnergyValue(ItemStack itemStack);
}
