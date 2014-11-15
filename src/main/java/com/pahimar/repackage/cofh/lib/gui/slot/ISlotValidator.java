package com.pahimar.repackage.cofh.lib.gui.slot;

import net.minecraft.item.ItemStack;

/**
 * Interface used in conjunction with {@link SlotValidated}.
 *
 * @author King Lemming
 */
public interface ISlotValidator {

    /**
     * Essentially a passthrough so an arbitrary criterion can be checked against.
     */
    boolean isItemValid(ItemStack stack);

}
