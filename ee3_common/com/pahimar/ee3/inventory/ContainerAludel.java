package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import com.pahimar.ee3.item.ItemAlchemicalDust;
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

        this.addSlotToContainer(new Slot(tileAludel, TileAludel.FUEL_INVENTORY_INDEX, 44, 74));
        this.addSlotToContainer(new Slot(tileAludel, TileAludel.INPUT_INVENTORY_INDEX, 44, 18));
        this.addSlotToContainer(new Slot(tileAludel, TileAludel.DUST_INVENTORY_INDEX, 44, 39));
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

        if (slot != null && slot.getHasStack()) {

            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            /**
             * If we are shift-clicking an item out of the Aludel's container,
             * attempt to put it in the first available slot in the player's
             * inventory
             */
            if (slotIndex < TileAludel.INVENTORY_SIZE) {

                if (!this.mergeItemStack(slotItemStack, TileAludel.INVENTORY_SIZE, inventorySlots.size(), false)) {
                    return null;
                }
            }
            else {

                /**
                 * If the stack being shift-clicked into the Aludel's container
                 * is a fuel, first try to put it in the fuel slot. If it cannot
                 * be merged into the fuel slot, try to put it in the input
                 * slot.
                 */
                if (TileEntityFurnace.isItemFuel(slotItemStack)) {
                    if (!this.mergeItemStack(slotItemStack, TileAludel.FUEL_INVENTORY_INDEX, TileAludel.INPUT_INVENTORY_INDEX, false)) {
                        return null;
                    }
                }

                /**
                 * If the stack being shift-clicked into the Aludel's container
                 * is a dust, first try to put it in the dust slot. If it cannot
                 * be merged into the dust slot, try to put it in the input
                 * slot.
                 */
                else if (slotItemStack.getItem() instanceof ItemAlchemicalDust) {
                    if (!this.mergeItemStack(slotItemStack, TileAludel.DUST_INVENTORY_INDEX, TileAludel.OUTPUT_INVENTORY_INDEX, false)) {
                        return null;
                    }
                }

                /**
                 * Finally, attempt to put stack into the input slot
                 */
                else if (!this.mergeItemStack(slotItemStack, TileAludel.INPUT_INVENTORY_INDEX, TileAludel.DUST_INVENTORY_INDEX, false)) {
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
