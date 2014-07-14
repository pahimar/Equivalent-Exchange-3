package com.pahimar.ee3.api;

import net.minecraft.item.ItemStack;

public interface IEnergyValueProvider
{
    public abstract float getEnergyValue(ItemStack itemStack);
}
