package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAludelOutput extends Slot
{
    public SlotAludelOutput(IInventory inventory, int x, int y, int z)
    {
        super(inventory, x, y, z);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        super.onPickupFromSlot(entityPlayer, itemStack);
    }
}
