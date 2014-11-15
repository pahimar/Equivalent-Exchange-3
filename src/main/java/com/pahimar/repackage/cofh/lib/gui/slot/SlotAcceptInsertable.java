package com.pahimar.repackage.cofh.lib.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

/**
 * Slot that will only accept ItemStacks when the IInventory returns true from isItemValidForSlot.
 * <p/>
 * If an ISidedInventory, canInsertItem (from side 6 (UNKNOWN)) must also return true.
 */
public class SlotAcceptInsertable extends SlotAcceptValid {

    protected ISidedInventory sidedInv;

    public SlotAcceptInsertable(IInventory inventory, int index, int x, int y) {

        super(inventory, index, x, y);

        if (inventory instanceof ISidedInventory) {
            sidedInv = (ISidedInventory) inventory;
        } else {
            sidedInv = null;
        }
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        boolean valid = super.isItemValid(stack);

        return valid && sidedInv != null ? sidedInv.canInsertItem(slotNumber, stack, 6) : valid;
    }

}
