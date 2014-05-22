package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Key;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound
{
    public abstract void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key);
}
