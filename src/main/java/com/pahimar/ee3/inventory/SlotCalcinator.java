package com.pahimar.ee3.inventory;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCalcinator extends Slot
{
    public SlotCalcinator(IInventory inventory, int x, int y, int z)
    {
        super(inventory, x, y, z);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        super.onPickupFromSlot(entityPlayer, itemStack);
        FMLCommonHandler.instance().firePlayerCraftingEvent(entityPlayer, itemStack, inventory);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }
}
