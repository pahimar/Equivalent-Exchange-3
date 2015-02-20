package com.pahimar.repackage.cofh.lib.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Slot that will only accept Potion Ingredients.
 */
public class SlotPotionIngredient extends Slot
{

    public SlotPotionIngredient(IInventory inventory, int index, int x, int y)
    {

        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {

        return stack != null && stack.getItem().isPotionIngredient(stack);
    }

}
