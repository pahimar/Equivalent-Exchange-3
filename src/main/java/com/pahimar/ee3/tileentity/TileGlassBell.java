package com.pahimar.ee3.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TileGlassBell extends TileEntityEE implements IInventory
{
    public static final int INVENTORY_SIZE = 1;
    public static final int DISPLAY_SLOT_INVENTORY_INDEX = 0;
    public ItemStack outputItemStack;
    /**
     * Server sync counter (once per 20 ticks)
     */
    private int ticksSinceSync;
    /**
     * The ItemStacks that hold the items currently being used in the Glass Bell
     */
    private ItemStack[] inventory;

    public TileGlassBell()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public int getSizeInventory()
    {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int var1)
    {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2)
    {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2)
    {

    }

    @Override
    public String getInventoryName()
    {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return false;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2)
    {
        return false;
    }
}
