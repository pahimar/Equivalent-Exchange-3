package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

public class ContainerAlchemicalTome extends ContainerEE
{
    private InventoryAlchemicalTome inventoryAlchemicalTome;

    public ContainerAlchemicalTome(InventoryAlchemicalTome inventoryAlchemicalTome)
    {
        this.inventoryAlchemicalTome = inventoryAlchemicalTome;

        for (int x = 0; x < 9; ++x)
        {
            for (int y = 0; y < 9; ++y)
            {
                if (y + x * 9 < inventoryAlchemicalTome.getSizeInventory())
                {
                    this.addSlotToContainer(new Slot(inventoryAlchemicalTome, y + x * 9, 8 + y * 18, 18 + x * 18)
                    {
                        public boolean canTakeStack(EntityPlayer entityPlayer)
                        {
                            return false;
                        }
                    });
                }
            }
        }
    }
}
