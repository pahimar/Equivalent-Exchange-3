package com.pahimar.ee3.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.network.packet.IPacket;
import com.pahimar.ee3.network.packet.PacketEETileWithItemUpdate;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public class TileGlassBell extends TileEE implements IInventory
{
    /**
     * Server sync counter (once per 20 ticks)
     */
    private int ticksSinceSync;

    /**
     * The ItemStacks that hold the items currently being used in the Glass Bell
     */
    private ItemStack[] inventory;

    public static final int INVENTORY_SIZE = 1;

    public static final int DISPLAY_SLOT_INVENTORY_INDEX = 0;

    public ItemStack outputItemStack;

    public TileGlassBell()
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
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Strings.CONTAINER_GLASS_BELL_NAME;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
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
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items", 0);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
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
    public boolean hasCustomInventoryName()
    {
        return this.hasCustomName();
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        ItemStack displayStack = this.inventory[DISPLAY_SLOT_INVENTORY_INDEX];

        if (!this.worldObj.isRemote)
        {
            if (++ticksSinceSync % 20 == 0)
            {
            	EquivalentExchange3.packetpipeline.sendToAllAround(getDescriptionPacket(), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 128d));
            }
        }
    }

    @Override
    public IPacket getDescriptionPacket()
    {
        ItemStack itemStack = this.inventory[DISPLAY_SLOT_INVENTORY_INDEX];

        if (itemStack != null && itemStack.stackSize > 0)
        {
            return new PacketEETileWithItemUpdate(xCoord, yCoord, zCoord, orientation, state, customName, itemStack.getUnlocalizedName(), itemStack.getItemDamage(), itemStack.stackSize, ItemHelper.getColor(itemStack));
        }
        else
        {
            return new PacketEETileWithItemUpdate(xCoord, yCoord, zCoord, orientation, state, customName, "", 0, 0, 0);
        }
    }

    
    public void onInventoryChanged()
    {
        worldObj.func_147451_t(xCoord, yCoord, zCoord);
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

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString());

        stringBuilder.append("TileGlassBell Data - ");
        for (int i = 0; i < inventory.length; i++)
        {
            if (i != 0)
            {
                stringBuilder.append(", ");
            }

            if (inventory[i] != null)
            {
                stringBuilder.append(String.format("inventory[%d]: %s", i, inventory[i].toString()));
            }
            else
            {
                stringBuilder.append(String.format("inventory[%d]: empty", i));
            }
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
