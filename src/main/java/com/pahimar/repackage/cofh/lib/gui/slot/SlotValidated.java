package com.pahimar.repackage.cofh.lib.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * A slot where the input can be validated based on any arbitrary criteria by using a passthrough method to an {@link ISlotValidator}.
 *
 * @author King Lemming
 */
public class SlotValidated extends Slot
{

    ISlotValidator validator;

    public SlotValidated(ISlotValidator validator, IInventory inventory, int index, int x, int y)
    {

        super(inventory, index, x, y);
        this.validator = validator;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {

        return validator.isItemValid(stack);
    }

}
