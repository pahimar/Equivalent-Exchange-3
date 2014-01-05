package com.pahimar.ee3.inventory;

import com.pahimar.ee3.helper.LogHelper;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ContainerAlchemicalChestLarge
 *
 * @author pahimar
 */
public class ContainerAlchemicalChestLarge extends Container
{
    private TileAlchemicalChest tileAlchemicalChest;

    private final int CHEST_INVENTORY_ROWS = 9;
    private final int CHEST_INVENTORY_COLUMNS = 13;

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerAlchemicalChestLarge(InventoryPlayer inventoryPlayer, TileAlchemicalChest tileAlchemicalChest)
    {
        this.tileAlchemicalChest = tileAlchemicalChest;
        LogHelper.debug("State: " + this.tileAlchemicalChest.getState() + ", Inventory size: " + this.tileAlchemicalChest.getSizeInventory());
        tileAlchemicalChest.openChest();

        // Add the Alchemical Chest slots to the container
        for (int chestRowIndex = 0; chestRowIndex < CHEST_INVENTORY_ROWS; ++chestRowIndex)
        {
            for (int chestColumnIndex = 0; chestColumnIndex < CHEST_INVENTORY_COLUMNS; ++chestColumnIndex)
            {
                this.addSlotToContainer(new Slot(tileAlchemicalChest, chestColumnIndex + chestRowIndex * CHEST_INVENTORY_COLUMNS, 8 + chestColumnIndex * 18, 8 + chestRowIndex * 18));
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 44 + inventoryColumnIndex * 18, 174 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 232));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer entityPlayer)
    {
        super.onContainerClosed(entityPlayer);
        tileAlchemicalChest.closeChest();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();

            if (slotIndex < CHEST_INVENTORY_ROWS * CHEST_INVENTORY_COLUMNS)
            {
                if (!this.mergeItemStack(itemStack, CHEST_INVENTORY_ROWS * CHEST_INVENTORY_COLUMNS, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemStack, 0, CHEST_INVENTORY_ROWS * CHEST_INVENTORY_COLUMNS, false))
            {
                return null;
            }

            if (itemStack.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return newItemStack;
    }
}
