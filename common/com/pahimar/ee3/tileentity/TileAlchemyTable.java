package com.pahimar.ee3.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TileAlchemyTable extends TileEE implements IInventory {

    public TileAlchemyTable() {

    }

    @Override
    public int getSizeInventory() {

        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {

        // TODO Auto-generated method stub

    }

    @Override
    public String getInvName() {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isInvNameLocalized() {

        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getInventoryStackLimit() {

        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void openChest() {

        // TODO Auto-generated method stub

    }

    @Override
    public void closeChest() {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack) {

        // TODO Auto-generated method stub
        return false;
    }

}
