package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityAludel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;

public class TileEntityAludel extends TileEntityEE implements ISidedInventory
{
    public static final int INVENTORY_SIZE = 4;
    public static final int FUEL_INVENTORY_INDEX = 0;
    public static final int INPUT_INVENTORY_INDEX = 1;
    public static final int DUST_INVENTORY_INDEX = 2;
    public static final int OUTPUT_INVENTORY_INDEX = 3;
    public int deviceCookTime;              // How much longer the Aludel will cook
    public int fuelBurnTime;                // The fuel value for the currently burning fuel
    public int itemCookTime;                // How long the current item has been "cooking"
    public ItemStack outputItemStack;
    public boolean hasGlassBell = false;
    /**
     * The ItemStacks that hold the items currently being used in the Aludel
     */
    private ItemStack[] inventory;

    public TileEntityAludel()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1)
    {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int var1, ItemStack var2, int var3)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int var1, ItemStack var2, int var3)
    {
        return false;
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

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityAludel(this, null));
    }
}
