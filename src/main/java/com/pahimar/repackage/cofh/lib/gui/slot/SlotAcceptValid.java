package com.pahimar.repackage.cofh.lib.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Slot that will only accept ItemStacks when the IInventory returns true from isItemValidForSlot.
 */
public class SlotAcceptValid extends Slot {

    public SlotAcceptValid(IInventory inventory, int index, int x, int y) {

        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return stack != null && this.inventory.isItemValidForSlot(this.slotNumber, stack);
    }

}
