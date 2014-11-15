package com.pahimar.repackage.cofh.lib.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Slot that will only accept ItemStacks whose items are a subclass of the given class.
 */
public class SlotAcceptAssignable extends Slot {

    protected Class<? extends Item> clazz;

    public SlotAcceptAssignable(IInventory inventory, int index, int x, int y, Class<? extends Item> c) {

        super(inventory, index, x, y);
        clazz = c;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return stack != null && clazz.isInstance(stack.getItem());
    }

}
