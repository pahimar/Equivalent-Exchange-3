package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.lib.Strings;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * TileCalcinator
 * 
 * Calcinator tile entity, and all the logic associated with it
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TileCalcinator extends TileEE implements IInventory {
	
	/**
     * The ItemStacks that hold the items currently being used in the Calcinator
     */
	private ItemStack[] calcinatorItemStacks = new ItemStack[3];

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		
		// Read in the ItemStacks in the inventory from NBT
		NBTTagList tagList = nbtTagCompound.getTagList("Items");
        this.calcinatorItemStacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = (NBTTagCompound)tagList.tagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < this.calcinatorItemStacks.length) {
                this.calcinatorItemStacks[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
		
	}
	
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		// Write the ItemStacks in the inventory to NBT
		NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < this.calcinatorItemStacks.length; ++currentIndex) {
            if (this.calcinatorItemStacks[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte)currentIndex);
                this.calcinatorItemStacks[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);
        
	}

	/**
     * Returns the number of slots in the inventory.
     */
	public int getSizeInventory() {
		return this.calcinatorItemStacks.length;
	}

	/**
     * Returns the stack in slot i
     */
	public ItemStack getStackInSlot(int i) {
		return this.calcinatorItemStacks[i];
	}

	public ItemStack decrStackSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		// TODO Auto-generated method stub
		
	}

	public String getInvName() {
		return "container." + Strings.CALCINATOR_NAME;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void openChest() { }
	public void closeChest() { }
	
}
