package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTabletOutput extends Slot
{
    public SlotTabletOutput(IInventory iInventory, int slotIndex, int x, int y)
    {
        super(iInventory, slotIndex, x, y);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
    {

    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return false;
    }
}
