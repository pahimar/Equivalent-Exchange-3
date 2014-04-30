package com.pahimar.ee3.inventory;

import com.pahimar.ee3.tileentity.TileEntityGlassBell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGlassBell extends Container
{
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerGlassBell(InventoryPlayer inventoryPlayer, TileEntityGlassBell tileGlassBell)
    {
        this.addSlotToContainer(new Slot(tileGlassBell, TileEntityGlassBell.DISPLAY_SLOT_INVENTORY_INDEX, 80, 22));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 58 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 116));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            if (slotIndex < TileEntityGlassBell.INVENTORY_SIZE)
            {

                if (!this.mergeItemStack(slotItemStack, 1, inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else
            {
                if (!this.mergeItemStack(slotItemStack, 0, TileEntityGlassBell.INVENTORY_SIZE, false))
                {
                    return null;
                }
            }

            if (slotItemStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }
}
