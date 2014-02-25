package com.pahimar.ee3.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TileAlchemySquare extends TileEE implements IInventory
{
    private ItemStack[] inventory;

    public static final int INVENTORY_SIZE = 8;

    public TileAlchemySquare()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return INVENTORY_SIZE;
    }

    /**
     * Returns the stack in slot i
     *
     * @param slotIndex
     */
    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory[slotIndex];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     *
     * @param slotIndex
     * @param amount
     */
    @Override
    public ItemStack decrStackSize(int slotIndex, int amount)
    {
        return null;
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     *
     * @param slotIndex
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        return null;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     *
     * @param slotIndex
     * @param itemStack
     */
    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {

    }

    /**
     * Returns the name of the inventory.
     */
    @Override
    public String getInventoryName()
    {
        return null;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     *
     * @param entityplayer
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     *
     * @param slotIndex
     * @param itemStack
     */
    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return false;
    }

	@Override
	public boolean hasCustomInventoryName() 
	{
		// TODO Auto-generated method stub
		return false;
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
}
