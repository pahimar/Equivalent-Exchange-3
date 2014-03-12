package com.pahimar.ee3.api;

import com.pahimar.ee3.emc.EmcValue;
import net.minecraft.item.ItemStack;

public interface ICustomEmcValueProvider
{
    public abstract EmcValue getEmcValue(ItemStack itemStack);
}
