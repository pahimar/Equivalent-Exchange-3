package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityGlassBell;
import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;

public class TileEntityGlassBell extends TileEntityEE implements IInventory
{
    public static final int INVENTORY_SIZE = 1;
    public static final int DISPLAY_SLOT_INVENTORY_INDEX = 0;
    public ItemStack outputItemStack;

    /**
     * The ItemStacks that hold the items currently being used in the Glass Bell
     */
    private ItemStack[] inventory;

    public TileEntityGlassBell()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
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
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }

        if (!this.worldObj.isRemote)
        {
            ItemStack displayStack = this.inventory[DISPLAY_SLOT_INVENTORY_INDEX];

            if (displayStack != null)
            {
                this.state = (byte) Block.getBlockFromItem(displayStack.getItem()).getLightValue();
            }
            else
            {
                this.state = 0;
            }

            PacketHandler.INSTANCE.sendToAllAround(new MessageTileEntityGlassBell(this, displayStack), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 128d));
        }

        this.markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.GLASS_BELL;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return true;
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
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items", 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

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

    @Override
    public Packet getDescriptionPacket()
    {
        if (getStackInSlot(DISPLAY_SLOT_INVENTORY_INDEX) != null && getStackInSlot(DISPLAY_SLOT_INVENTORY_INDEX).stackSize > 0)
        {
            return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityGlassBell(this, getStackInSlot(DISPLAY_SLOT_INVENTORY_INDEX)));
        }

        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityGlassBell(this, null));
    }
}
