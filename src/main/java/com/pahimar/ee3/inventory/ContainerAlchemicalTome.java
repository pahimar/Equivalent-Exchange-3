package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerAlchemicalTome extends Container
{
    public ContainerAlchemicalTome(InventoryPlayer inventoryPlayer)
    {
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return true;
    }
}
