package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import com.pahimar.ee3.tileentity.TileCalcinator;

/**
 * Equivalent-Exchange-3
 * 
 * ContainerCalcinator
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ContainerCalcinator extends Container {

    public ContainerCalcinator(InventoryPlayer inventoryPlayer, TileCalcinator calcinator) {

        // Add the fuel slot to the container
        this.addSlotToContainer(new Slot(calcinator, TileCalcinator.FUEL_INVENTORY_INDEX, 56, 62));

        // Add the input slot to the container
        this.addSlotToContainer(new Slot(calcinator, TileCalcinator.INPUT_INVENTORY_INDEX, 56, 17));

        // Add the output results slot to the container
        this.addSlotToContainer(new SlotCalcinator(calcinator, TileCalcinator.OUTPUT_INVENTORY_INDEX, 116, 35));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 94 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 152));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {

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
            if (slotIndex < TileCalcinator.INVENTORY_SIZE) {

                if (!this.mergeItemStack(slotItemStack, TileCalcinator.INVENTORY_SIZE, inventorySlots.size(), false)) {
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
                    if (!this.mergeItemStack(slotItemStack, TileCalcinator.FUEL_INVENTORY_INDEX, TileCalcinator.OUTPUT_INVENTORY_INDEX, false)) {
                        return null;
                    }
                }

                /**
                 * Finally, attempt to put stack into the input slot
                 */
                else if (!this.mergeItemStack(slotItemStack, TileCalcinator.INPUT_INVENTORY_INDEX, TileCalcinator.OUTPUT_INVENTORY_INDEX, false)) {
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
