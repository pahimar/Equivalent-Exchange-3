package com.pahimar.repackage.cofh.lib.gui.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Slot which copies an ItemStack when clicked on, does not decrement the ItemStack on the cursor.
 *
 * @author King Lemming
 */
public class SlotFalseCopy extends Slot {

    public int slotIndex = 0;

    public SlotFalseCopy(IInventory inventory, int index, int x, int y) {

        super(inventory, index, x, y);
        slotIndex = index;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {

        return false;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return true;
    }

    @Override
    public void putStack(ItemStack stack) {

        if (stack != null) {
            stack.stackSize = 1;
        }
        this.inventory.setInventorySlotContents(this.slotIndex, stack);
        this.onSlotChanged();
    }

}
