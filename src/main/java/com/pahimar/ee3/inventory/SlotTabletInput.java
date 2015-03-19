package com.pahimar.ee3.inventory;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.knowledge.AbilityRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTabletInput extends Slot
{
    public SlotTabletInput(IInventory iInventory, int slotIndex, int x, int y)
    {
        super(iInventory, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && AbilityRegistry.getInstance().isRecoverable(itemStack);
    }
}
