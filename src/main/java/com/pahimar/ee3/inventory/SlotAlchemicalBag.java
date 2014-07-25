package com.pahimar.ee3.inventory;

import com.pahimar.ee3.item.ItemAlchemicalBag;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAlchemicalBag extends Slot
{
    private final EntityPlayer entityPlayer;
    private ContainerAlchemicalBag containerAlchemicalBag;

    public SlotAlchemicalBag(ContainerAlchemicalBag containerAlchemicalBag, IInventory inventory, EntityPlayer entityPlayer, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x, y);
        this.entityPlayer = entityPlayer;
        this.containerAlchemicalBag = containerAlchemicalBag;
    }

    @Override
    public void onSlotChanged()
    {
        super.onSlotChanged();

        //Only save on server side to prevent a huge amount of client-side saving when the bag is opened
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            containerAlchemicalBag.saveInventory(entityPlayer);
        }
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return !(itemStack.getItem() instanceof ItemAlchemicalBag);
    }
}
