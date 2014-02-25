package com.pahimar.ee3.inventory;

import com.pahimar.ee3.item.ItemAlchemicalBag;
import com.pahimar.ee3.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryAlchemicalBag implements IInventory, INBTTaggable
{
    public ItemStack parentItemStack;
    protected ItemStack[] inventory;
    protected String customName;

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        if (inventory[slotIndex] != null)
        {
            ItemStack itemStack = inventory[slotIndex];
            inventory[slotIndex] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
    }

    @Override
    public String getInvName()
    {
        return this.hasCustomName() ? this.getCustomName() : Strings.ALCHEMICAL_BAG_NAME;
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return true;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void onInventoryChanged()
    {
        // NOOP
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return true;
    }

    @Override
    public void openChest()
    {
        // NOOP
    }

    @Override
    public void closeChest()
    {
        // NOOP
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        if (itemStack.getItem() instanceof ItemAlchemicalBag)
        {
            return false;
        }
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        // TODO
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        // TODO
    }

    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public String getCustomName()
    {

        return customName;
    }
}
