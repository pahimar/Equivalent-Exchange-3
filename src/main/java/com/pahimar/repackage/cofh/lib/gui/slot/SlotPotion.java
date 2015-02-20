package com.pahimar.repackage.cofh.lib.gui.slot;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Slot that will only accept Potions.
 */
public class SlotPotion extends Slot
{

    public SlotPotion(IInventory inventory, int index, int x, int y)
    {

        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {

        return stack != null && stack.getItem().equals(Items.potionitem);
    }

}
