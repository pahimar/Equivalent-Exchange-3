package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.tileentity.TileAlchemicalChest;

/**
 * Equivalent-Exchange-3
 * 
 * ContainerAlchemicalChest
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ContainerAlchemicalChest extends Container {

    private TileAlchemicalChest tileAlchemicalChest;

    private final int CHEST_INVENTORY_ROWS = 4;
    private final int CHEST_INVENTORY_COLUMNS = 13;

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerAlchemicalChest(InventoryPlayer inventoryPlayer, TileAlchemicalChest tileAlchemicalChest) {

        this.tileAlchemicalChest = tileAlchemicalChest;

        tileAlchemicalChest.openChest();

        // Add the Alchemical Chest slots to the container
        for (int chestRowIndex = 0; chestRowIndex < CHEST_INVENTORY_ROWS; ++chestRowIndex) {
            for (int chestColumnIndex = 0; chestColumnIndex < CHEST_INVENTORY_COLUMNS; ++chestColumnIndex) {
                this.addSlotToContainer(new Slot(tileAlchemicalChest, chestColumnIndex + chestRowIndex * 13, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18));
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 44 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 162));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {

        return true;
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer entityPlayer) {

        super.onContainerClosed(entityPlayer);
        tileAlchemicalChest.closeChest();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {

        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();

            if (slotIndex < CHEST_INVENTORY_ROWS * CHEST_INVENTORY_COLUMNS) {
                if (!this.mergeItemStack(itemStack, CHEST_INVENTORY_ROWS * CHEST_INVENTORY_COLUMNS, inventorySlots.size(), false))
                    return null;
            }
            else if (!this.mergeItemStack(itemStack, 0, CHEST_INVENTORY_ROWS * CHEST_INVENTORY_COLUMNS, false))
                return null;

            if (itemStack.stackSize == 0) {
                slot.putStack((ItemStack) null);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return newItemStack;
    }
}
