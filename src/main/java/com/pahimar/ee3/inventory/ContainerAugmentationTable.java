package com.pahimar.ee3.inventory;

import com.pahimar.ee3.item.ItemGem;
import com.pahimar.ee3.item.ItemToolEE;
import com.pahimar.ee3.tileentity.TileEntityAugmentationTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAugmentationTable extends ContainerEE
{
    private TileEntityAugmentationTable tileEntityAugmentationTable;

    public ContainerAugmentationTable(InventoryPlayer inventoryPlayer, TileEntityAugmentationTable tileEntityAugmentationTable)
    {
        this.tileEntityAugmentationTable = tileEntityAugmentationTable;

        this.addSlotToContainer(new Slot(tileEntityAugmentationTable, TileEntityAugmentationTable.INPUT_SLOT_INVENTORY_INDEX, 36, 50));
        this.addSlotToContainer(new Slot(tileEntityAugmentationTable, TileEntityAugmentationTable.AUGMENT_SLOT_INVENTORY_INDEX, 72, 50));
        this.addSlotToContainer(new Slot(tileEntityAugmentationTable, TileEntityAugmentationTable.OUTPUT_SLOT_INVENTORY_INDEX, 136, 50));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 14 + inventoryColumnIndex * 18, 106 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 14 + actionBarSlotIndex * 18, 164));
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
             * If we are shift-clicking an item out of the AugmentationTable's container,
             * attempt to put it in the first available slot in the player's
             * inventory
             */
            if (slotIndex < TileEntityAugmentationTable.INVENTORY_SIZE)
            {
                if (!this.mergeItemStack(slotItemStack, TileEntityAugmentationTable.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                /**
                 * If the stack being shift-clicked into the AugmentationTable's container
                 * is a tool(?), first try to put it in the tool slot.
                 */
                //TODO: create IAugmentable(?) interface
                if (slotItemStack.getItem() instanceof ItemToolEE)
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityAugmentationTable.INPUT_SLOT_INVENTORY_INDEX, TileEntityAugmentationTable.AUGMENT_SLOT_INVENTORY_INDEX, false))
                    {
                        return null;
                    }
                }

                /**
                 * If the stack being shift-clicked into the AugmentationTable's container
                 * is an augment(?), try to put it in the augment slot.
                 */
                else if (slotItemStack.getItem() instanceof ItemGem)
                {
                    if (!this.mergeItemStack(slotItemStack, TileEntityAugmentationTable.AUGMENT_SLOT_INVENTORY_INDEX, TileEntityAugmentationTable.OUTPUT_SLOT_INVENTORY_INDEX, false))
                    {
                        return null;
                    }
                }

                /**
                 * If the stack is not augmentable or an augment don't add it in a slot
                 */
                else
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

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.tileEntityAugmentationTable.isUseableByPlayer(entityPlayer);
    }
}
