package com.pahimar.ee3.item;

import net.minecraft.item.ItemStack;

public interface IChargeable
{
    public abstract short getChargeLevel(ItemStack itemStack);

    public abstract void setChargeLevel(ItemStack itemStack, short chargeLevel);

    public abstract void increaseChargeLevel(ItemStack itemStack);

    public abstract void decreaseChargeLevel(ItemStack itemStack);
}
