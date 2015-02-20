package com.pahimar.repackage.cofh.lib.gui.slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Slot that will redirect inserts to another inventory slot (other than index), but not be visible.
 * <p/>
 * Used primarily for containers that have a larger internal inventory than external (e.g., DeepStorageUnit)
 */
public class SlotInvisible extends Slot
{

    protected final int slotIndex;

    public SlotInvisible(IInventory inventory, int index, int x, int y, int slot)
    {

        super(inventory, index, x, y);
        slotIndex = slot;
    }

    @Override
    public void putStack(ItemStack stack)
    {

        this.inventory.setInventorySlotContents(slotIndex, stack);
        this.onSlotChanged();
    }

    @Override
    public ItemStack getStack()
    {

        return null;
    }

    @Override
    public ItemStack decrStackSize(int par1)
    {

        return null;
    }

    @Override
    public boolean canTakeStack(EntityPlayer p)
    {

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean func_111238_b()
    {

        return false;
    }

}
