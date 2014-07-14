package com.pahimar.ee3.api;

import net.minecraft.item.ItemStack;

public interface EnergyValueProvider
{
    public abstract float getEnergyValue(ItemStack itemStack);
}
