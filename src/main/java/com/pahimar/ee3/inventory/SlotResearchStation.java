package com.pahimar.ee3.inventory;

import com.pahimar.ee3.item.ItemAlchemicalTome;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotResearchStation extends Slot
{
    public SlotResearchStation(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            return itemStack.getItem() instanceof ItemAlchemicalTome;
        }

        return false;
    }
}
