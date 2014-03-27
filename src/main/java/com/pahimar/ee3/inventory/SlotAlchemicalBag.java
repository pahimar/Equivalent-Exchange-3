package com.pahimar.ee3.inventory;

import com.pahimar.ee3.item.ItemAlchemicalBag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAlchemicalBag extends Slot
{
    private final EntityPlayer entityPlayer;
    private ContainerAlchemicalBag containerAlchemicalBag;

    public SlotAlchemicalBag(ContainerAlchemicalBag containerAlchemicalBag, IInventory inventory, EntityPlayer entityPlayer, int x, int y, int z)
    {
        super(inventory, x, y, z);
        this.entityPlayer = entityPlayer;
        this.containerAlchemicalBag = containerAlchemicalBag;
    }

    @Override
    public void onSlotChange(ItemStack itemStack1, ItemStack itemStack2)
    {
        super.onSlotChange(itemStack1, itemStack2);
        containerAlchemicalBag.saveInventory(entityPlayer);
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return itemStack.getItem() instanceof ItemAlchemicalBag ? false : true;
    }
}
