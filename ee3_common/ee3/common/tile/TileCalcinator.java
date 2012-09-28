package ee3.common.tile;

import ee3.common.block.ModBlocks;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;

public class TileCalcinator extends TileEE implements IInventory {
	
	/**
     * The ItemStacks that hold the items currently being used in the Calcinator
     */
	private ItemStack[] calcinatorItemStacks = new ItemStack[3];

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
	}
	
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
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
		return "container." + ModBlocks.CALCINATOR_NAME;
	}

	public int getInventoryStackLimit() {
		return 64;
	}


	public void openChest() { }
	public void closeChest() { }
	
}
