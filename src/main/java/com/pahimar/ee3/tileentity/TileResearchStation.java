package com.pahimar.ee3.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TileResearchStation extends TileEE implements IInventory
{

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return 0;
    }

    /**
     * Returns the stack in slot i
     *
     * @param i
     */
    @Override
    public ItemStack getStackInSlot(int i)
    {
        return null;
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     *
     * @param i
     * @param j
     */
    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        return null;
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     *
     * @param i
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int i)
    {
        return null;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     *
     * @param i
     * @param itemstack
     */
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {

    }

    /**
     * Returns the name of the inventory.
     */
    @Override
    public String getInvName()
    {
        return null;
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    @Override
    public boolean isInvNameLocalized()
    {
        return false;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 0;
    }

    @Override
    public void openChest()
    {

    }

    @Override
    public void closeChest()
    {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     *
     * @param i
     * @param itemstack
     */
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return false;
    }
}
