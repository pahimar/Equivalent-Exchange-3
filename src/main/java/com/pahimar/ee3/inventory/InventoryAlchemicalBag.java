package com.pahimar.ee3.inventory;

import com.pahimar.ee3.lib.Strings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryAlchemicalBag implements IInventory {
    public static final int INVENTORY_SIZE = 13 * 4;
    
    /**
     * The ItemStacks that hold the items currently being used in the Alchemical Bag
     */
    private ItemStack[] inventory;
    public ItemStack bag;
    private int slot;
    private String customName;

    public InventoryAlchemicalBag(ItemStack bag, int slot)
    {
    	this.bag = bag;
    	this.slot = slot;
        inventory = new ItemStack[INVENTORY_SIZE];
        if (bag.getTagCompound() != null)
            this.readFromNBT(bag.getTagCompound());
    }
    
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

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    @Override
    public String getInvName()
    {

        return this.bag.hasDisplayName() ? this.bag.getDisplayName() : Strings.CONTAINER_ALCHEMICAL_CHEST_NAME;
    }

    @Override
    public int getInventoryStackLimit()
    {

        return 64;
    }
    
    public boolean hasCustomName()
    {

    	return customName != null;
    }
    
    public int getSlot()
    {
        return slot;
    }

    @Override
    public boolean isInvNameLocalized() {
        return this.bag.hasDisplayName();
    }

    @Override
    public void onInventoryChanged() {
        
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public void openChest()
    {
        
    }

    @Override
    public void closeChest()
    {
        
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }
    
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);
    }
    
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }
}
