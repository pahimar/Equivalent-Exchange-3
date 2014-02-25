package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.google.common.base.Strings;
import com.pahimar.ee3.item.ItemAlchemicalBag;

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
    public String getInventoryName()
    {
    	return this.hasCustomInventoryName() ? this.getCustomName() : Strings.ALCHEMICAL_BAG_NAME;
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
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
	public boolean hasCustomInventoryName() 
	{
		// TODO Auto-generated method stub
		return customName != null && customName.length() > 0;
	}

	@Override
	public void markDirty() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openInventory() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		// TODO Auto-generated method stub
		
	}
	
	public String getCustomName()
    {
        return customName;
    }
}
