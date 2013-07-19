package com.pahimar.ee3.inventory;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * WorldSavedDataEE
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class WorldSavedDataEE extends WorldSavedData implements IInventory {

    public UUID uuid;
    public ItemStack[] inventory;

    private final int INVENTORY_SIZE = 13 * 4;

    public WorldSavedDataEE(String filePath) {

        super(filePath);
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public void readFromNBT(NBTTagCompound var1) {

        // TODO Auto-generated method stub

    }

    @Override
    public void writeToNBT(NBTTagCompound var1) {

        // TODO Auto-generated method stub

    }

    @Override
    public int getSizeInventory() {

        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {

        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {

        // TODO Auto-generated method stub

    }

    @Override
    public String getInvName() {

        return "worldsaveddata" + "." + Strings.ALCHEMICAL_BAG_NAME;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public void onInventoryChanged() {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {

        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    @Override
    public boolean isInvNameLocalized() {

        return false;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {

        return false;
    }

}
