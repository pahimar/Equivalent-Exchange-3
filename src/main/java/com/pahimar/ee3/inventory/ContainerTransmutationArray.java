package com.pahimar.ee3.inventory;

import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ContainerTransmutationArray extends ContainerEE
{
    private TileEntityAlchemyArray tileEntityAlchemyArray;

    public ContainerTransmutationArray(InventoryPlayer inventoryPlayer, TileEntityAlchemyArray tileEntityAlchemyArray)
    {
        this.tileEntityAlchemyArray = tileEntityAlchemyArray;

        int maxArrayRowCount = (2 * (tileEntityAlchemyArray.getSize() - 1)) + 1;
        int maxArrayColumnCount = maxArrayRowCount;

        for (int rowIndex = 0; rowIndex < maxArrayRowCount; rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < maxArrayColumnCount; columnIndex++)
            {
                if (tileEntityAlchemyArray.getSize() == 1)
                {
                    this.addSlotToContainer(new Slot(tileEntityAlchemyArray, columnIndex + rowIndex * maxArrayRowCount, 120 + columnIndex * 18, 69 + rowIndex * 18)
                    {
                        @Override
                        public boolean isItemValid(ItemStack itemStack)
                        {
                            return itemStack.getItem() instanceof ItemBlock;
                        }
                    });
                }
                else if (tileEntityAlchemyArray.getSize() == 2)
                {
                    this.addSlotToContainer(new Slot(tileEntityAlchemyArray, columnIndex + rowIndex * maxArrayRowCount, 102 + columnIndex * 18, 51 + rowIndex * 18)
                    {
                        @Override
                        public boolean isItemValid(ItemStack itemStack)
                        {
                            return itemStack.getItem() instanceof ItemBlock;
                        }
                    });
                }
                else if (tileEntityAlchemyArray.getSize() == 3)
                {
                    this.addSlotToContainer(new Slot(tileEntityAlchemyArray, columnIndex + rowIndex * maxArrayRowCount, 84 + columnIndex * 18, 33 + rowIndex * 18)
                    {
                        @Override
                        public boolean isItemValid(ItemStack itemStack)
                        {
                            return itemStack.getItem() instanceof ItemBlock;
                        }
                    });
                }
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 47 + inventoryColumnIndex * 18, 173 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 47 + actionBarSlotIndex * 18, 231));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        int inventorySize = ((2 * (this.tileEntityAlchemyArray.getSize() - 1)) + 1);
        inventorySize *= inventorySize;

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            /**
             * If we are shift-clicking an item out of the container,
             * attempt to put it in the first available slot in the entityPlayer's
             * inventory
             */
            if (slotIndex < inventorySize)
            {
                if (!this.mergeItemStack(slotItemStack, inventorySize, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                if (!this.mergeItemStack(slotItemStack, 0, inventorySize, false))
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
        return this.tileEntityAlchemyArray.isUseableByPlayer(entityPlayer);
    }
}
