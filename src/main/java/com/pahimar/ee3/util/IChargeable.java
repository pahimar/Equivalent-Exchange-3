package com.pahimar.ee3.util;

import net.minecraft.item.ItemStack;

public interface IChargeable {

    short getMaxChargeLevel();

    short getChargeLevel(ItemStack itemStack);

    void setChargeLevel(ItemStack itemStack, short charge);

    void increaseChargeLevel(ItemStack itemStack);

    void decreaseChargeLevel(ItemStack itemStack);
}
