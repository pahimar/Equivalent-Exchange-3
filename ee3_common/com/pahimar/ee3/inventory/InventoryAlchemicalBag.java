/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pahimar.ee3.inventory;

import com.pahimar.ee3.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 *
 * @author Michael DeRuscio<https://github.com/Michael-073>
 */
public class InventoryAlchemicalBag implements IInventory{

    private ItemStack[] inventory;
    private final int INVENTORY_SIZE = 13 * 4;

    public InventoryAlchemicalBag(ItemStack stack) {
        if(stack.getItem() != ModItems.alchemicalBag) return;
        boolean success = readFromNBT(stack.getTagCompound());
        if(!success)
            inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public int getSizeInventory() {
        return INVENTORY_SIZE;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int num) {
        ItemStack stack = getStackInSlot(slot), s;
        if(stack == null) return null;
        if(stack.stackSize > num) {
            s = stack.splitStack(num);
            if(stack.stackSize <= 0)
                setInventorySlotContents(slot, null);
        } else {
            s = stack;
            setInventorySlotContents(slot, null);

        }
        onInventoryChanged();
        return s;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot ){
        if (inventory[slot] != null) {
            ItemStack itemstack = inventory[slot];
            this.inventory[slot] = null;
            return itemstack;
        } else
            return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
    }

    @Override
    public String getInvName() {
        return "I need a name";
    }

    @Override
    public boolean isInvNameLocalized() {
        return false; //
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {}

    @Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    private static final String NBT_ALCHEMICAL_BAG_INVENTORY = "alchemicalBagInventory";

    private boolean readFromNBT(NBTTagCompound tag) {
        if(tag == null || !tag.hasKey(NBT_ALCHEMICAL_BAG_INVENTORY)) return false;
        NBTTagList tagList = tag.getTagList(NBT_ALCHEMICAL_BAG_INVENTORY);
        inventory = new ItemStack[getSizeInventory()];

        for(int i = 0; i < tagList.tagCount(); i++){
            NBTTagCompound t = (NBTTagCompound) tagList.tagAt(i);
            int slot = t.getByte("Slot");
            if(slot >= 0 && slot < inventory.length)
                inventory[slot] = ItemStack.loadItemStackFromNBT(t);
        }
        return true;
    }

    public void writeBagContents(ItemStack item) {
        NBTTagCompound tag = item.getTagCompound();
        if(tag == null){
            tag = new NBTTagCompound();
            item.setTagCompound(tag);
        }
        NBTTagList list = new NBTTagList();
        NBTTagCompound tagComp;
        ItemStack stack;
        for(int i = 0; i < inventory.length; i++) {
            stack = inventory[i];
            if(stack == null) continue;
            tagComp = new NBTTagCompound();
            tagComp.setByte("Slot", (byte)i);
            stack.writeToNBT(tagComp);
            list.appendTag( tagComp );
        }
        tag.setTag(NBT_ALCHEMICAL_BAG_INVENTORY, list);
//        tag.setTag(NBT_ALCHEMICAL_BAG_INVENTORY, list);
    }

}
