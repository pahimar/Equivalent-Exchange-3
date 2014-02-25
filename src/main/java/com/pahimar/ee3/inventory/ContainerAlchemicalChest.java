package com.pahimar.ee3.inventory;

import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ContainerAlchemicalChest
 *
 * @author pahimar
 */
public class ContainerAlchemicalChest extends Container
{
    private TileAlchemicalChest tileAlchemicalChest;

    private int chestInventoryRows;
    private int chestInventoryColumns;

    // Small Chest
    public static final int SMALL_CHEST_INVENTORY_ROWS = 4;
    public static final int SMALL_CHEST_INVENTORY_COLUMNS = 12;
    public static final int SMALL_INVENTORY_SIZE = SMALL_CHEST_INVENTORY_ROWS * SMALL_CHEST_INVENTORY_COLUMNS;

    // Medium Chest
    public static final int MEDIUM_CHEST_INVENTORY_ROWS = 7;
    public static final int MEDIUM_CHEST_INVENTORY_COLUMNS = 12;
    public static final int MEDIUM_INVENTORY_SIZE = MEDIUM_CHEST_INVENTORY_ROWS * MEDIUM_CHEST_INVENTORY_COLUMNS;

    // Large Chest
    public static final int LARGE_CHEST_INVENTORY_ROWS = 9;
    public static final int LARGE_CHEST_INVENTORY_COLUMNS = 13;
    public static final int LARGE_INVENTORY_SIZE = LARGE_CHEST_INVENTORY_ROWS * LARGE_CHEST_INVENTORY_COLUMNS;

    // Player Inventory
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerAlchemicalChest(InventoryPlayer inventoryPlayer, TileAlchemicalChest tileAlchemicalChest)
    {
        this.tileAlchemicalChest = tileAlchemicalChest;
        tileAlchemicalChest.openInventory();

        if (this.tileAlchemicalChest.getState() == 0)
        {
            chestInventoryRows = SMALL_CHEST_INVENTORY_ROWS;
            chestInventoryColumns = SMALL_CHEST_INVENTORY_COLUMNS;
        }
        else if (this.tileAlchemicalChest.getState() == 1)
        {
            chestInventoryRows = MEDIUM_CHEST_INVENTORY_ROWS;
            chestInventoryColumns = MEDIUM_CHEST_INVENTORY_COLUMNS;
        }
        else if (this.tileAlchemicalChest.getState() == 2)
        {
            chestInventoryRows = LARGE_CHEST_INVENTORY_ROWS;
            chestInventoryColumns = LARGE_CHEST_INVENTORY_COLUMNS;
        }

        // Add the Alchemical Chest slots to the container
        for (int chestRowIndex = 0; chestRowIndex < chestInventoryRows; ++chestRowIndex)
        {
            for (int chestColumnIndex = 0; chestColumnIndex < chestInventoryColumns; ++chestColumnIndex)
            {
                if (this.tileAlchemicalChest.getState() == 0)
                {
                    this.addSlotToContainer(new Slot(tileAlchemicalChest, chestColumnIndex + chestRowIndex * chestInventoryColumns, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18));
                }
                else if (this.tileAlchemicalChest.getState() == 1)
                {
                    this.addSlotToContainer(new Slot(tileAlchemicalChest, chestColumnIndex + chestRowIndex * chestInventoryColumns, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18));
                }
                else if (this.tileAlchemicalChest.getState() == 2)
                {
                    this.addSlotToContainer(new Slot(tileAlchemicalChest, chestColumnIndex + chestRowIndex * chestInventoryColumns, 8 + chestColumnIndex * 18, 8 + chestRowIndex * 18));
                }
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                if (this.tileAlchemicalChest.getState() == 0)
                {
                    this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 35 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
                }
                else if (this.tileAlchemicalChest.getState() == 1)
                {
                    this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 35 + inventoryColumnIndex * 18, 158 + inventoryRowIndex * 18));
                }
                else if (this.tileAlchemicalChest.getState() == 2)
                {
                    this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 44 + inventoryColumnIndex * 18, 174 + inventoryRowIndex * 18));
                }
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            if (this.tileAlchemicalChest.getState() == 0)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 35 + actionBarSlotIndex * 18, 162));
            }
            else if (this.tileAlchemicalChest.getState() == 1)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 35 + actionBarSlotIndex * 18, 216));
            }
            else if (this.tileAlchemicalChest.getState() == 2)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 232));
            }
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
        tileAlchemicalChest.closeInventory();
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

            if (slotIndex < chestInventoryRows * chestInventoryColumns)
            {
                if (!this.mergeItemStack(itemStack, chestInventoryRows * chestInventoryColumns, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemStack, 0, chestInventoryRows * chestInventoryColumns, false))
            {
                return null;
            }

            if (itemStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return newItemStack;
    }
}
