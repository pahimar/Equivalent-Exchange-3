package com.pahimar.ee3.item;

import net.minecraft.item.ItemStack;

/**
 * Equivalent-Exchange-3
 * 
 * IChargeable
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public interface IChargeable {

    public abstract short getCharge(ItemStack stack);

    public abstract void setCharge(ItemStack stack, short charge);

    public abstract void increaseCharge(ItemStack stack);

    public abstract void decreaseCharge(ItemStack stack);

}
