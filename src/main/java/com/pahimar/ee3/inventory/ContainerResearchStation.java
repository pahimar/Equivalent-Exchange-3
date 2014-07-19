package com.pahimar.ee3.inventory;

import com.pahimar.ee3.item.ItemAlchemicalTome;
import com.pahimar.ee3.tileentity.TileEntityResearchStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerResearchStation extends ContainerEE
{
    private TileEntityResearchStation tileEntityResearchStation;

    public ContainerResearchStation(InventoryPlayer inventoryPlayer, TileEntityResearchStation tileEntityResearchStation)
    {
        this.tileEntityResearchStation = tileEntityResearchStation;

        this.addSlotToContainer(new Slot(tileEntityResearchStation, TileEntityResearchStation.ITEM_SLOT_INVENTORY_INDEX, 35, 41));
        this.addSlotToContainer(new SlotResearchStation(tileEntityResearchStation, TileEntityResearchStation.TOME_SLOT_INVENTORY_INDEX, 125, 41));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 94 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 152));
        }
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

            /**
             * If we are shift-clicking an item out of the Research Table's container,
             * attempt to put it in the first available slot in the player's
             * inventory
             */
            if (slotIndex < TileEntityResearchStation.INVENTORY_SIZE)
            {
                if (!this.mergeItemStack(slotItemStack, TileEntityResearchStation.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                /**
                 * If the stack being shift-clicked into the Research Table's container
                 * is a fuel, first try to put it in the fuel slot. If it cannot
                 * be merged into the fuel slot, try to put it in the input
                 * slot.
                 */
                if (slotItemStack.getItem() instanceof ItemAlchemicalTome)
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityResearchStation.TOME_SLOT_INVENTORY_INDEX, TileEntityResearchStation.INVENTORY_SIZE, false))
                    {
                        return null;
                    }
                }
                else
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityResearchStation.ITEM_SLOT_INVENTORY_INDEX, TileEntityResearchStation.TOME_SLOT_INVENTORY_INDEX, false))
                    {
                        return null;
                    }
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
