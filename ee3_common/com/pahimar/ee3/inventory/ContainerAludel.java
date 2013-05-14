package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.tileentity.TileAludel;

/**
 * Equivalent-Exchange-3
 * 
 * ContainerAludel
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ContainerAludel extends Container {

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerAludel(InventoryPlayer inventoryPlayer, TileAludel tileAludel) {

        this.addSlotToContainer(new Slot(tileAludel, TileAludel.INPUT_INVENTORY_INDEX, 44, 18));
        this.addSlotToContainer(new Slot(tileAludel, TileAludel.DUST_INVENTORY_INDEX, 44, 39));
        this.addSlotToContainer(new Slot(tileAludel, TileAludel.FUEL_INVENTORY_INDEX, 44, 74));
        this.addSlotToContainer(new Slot(tileAludel, TileAludel.OUTPUT_INVENTORY_INDEX, 120, 39));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 106 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 164));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {

        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {

        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            if (slotIndex < TileAludel.INVENTORY_SIZE) {
                
                if (!this.mergeItemStack(slotItemStack, TileAludel.INVENTORY_SIZE + 1, inventorySlots.size(), true)) {
                    return null;
                }
            }
            else {
                /*
                 * TODO: Depending on the slowItemStack, attempt to merge it into acceptable slots
                 */
                if (!this.mergeItemStack(slotItemStack, 0, TileAludel.INVENTORY_SIZE - 1, false)) {
                    return null;
                }
            }

            if (slotItemStack.stackSize == 0) {
                slot.putStack((ItemStack) null);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }
}
