package com.pahimar.ee3.api;

import net.minecraft.item.ItemStack;

public interface ICustomEmcValueProvider
{
    public abstract EmcValue getEmcValue(ItemStack itemStack);
}
