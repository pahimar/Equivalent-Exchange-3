package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAlchemicalBag extends Slot
{
    private final EntityPlayer entityPlayer;
    private ContainerAlchemicalBag containerAlchemicalBag;

    public SlotAlchemicalBag(EntityPlayer entityPlayer, ContainerAlchemicalBag containerAlchemicalBag, IInventory inventory, int x, int y, int z)
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
}
