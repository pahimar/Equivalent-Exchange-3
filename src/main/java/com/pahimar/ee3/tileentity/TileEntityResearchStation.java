package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.api.knowledge.TransmutationKnowledgeRegistryProxy;
import com.pahimar.ee3.knowledge.AbilityRegistry;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityResearchStation;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.ItemHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;

import java.util.UUID;

public class TileEntityResearchStation extends TileEntityEE implements IInventory
{
    public static final int INVENTORY_SIZE = 2;
    public static final int ITEM_SLOT_INVENTORY_INDEX = 0;
    public static final int ALCHENOMICON_SLOT_INVENTORY_INDEX = 1;

    public int itemLearnTime;
    public boolean isItemKnown;
    private ItemStack[] inventory;

    public TileEntityResearchStation()
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
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.RESEARCH_STATION;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && entityplayer.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
    }

    @Override
    public void openInventory()
    {
        // NOOP
    }

    @Override
    public void closeInventory()
    {
        // NOOP
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return slotIndex == ITEM_SLOT_INVENTORY_INDEX && AbilityRegistry.getInstance().isLearnable(itemStack);
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
        nbtTagCompound.setTag(Names.NBT.ITEMS, tagList);
        nbtTagCompound.setInteger("itemLearnTime", itemLearnTime);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEMS, 10);
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
        itemLearnTime = nbtTagCompound.getInteger("itemLearnTime");
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityResearchStation(this));
    }

    @SideOnly(Side.CLIENT)
    public int getLearnProgressScaled(int scale)
    {
        return this.itemLearnTime * scale / 200;
    }

    @Override
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            // Continue "cooking" the same item, if we can
            if (this.canLearnItemStack())
            {
                this.itemLearnTime++;

                if (this.itemLearnTime == 200)
                {
                    this.itemLearnTime = 0;
                    this.learnItemStack();
                }
            }
            else
            {
                this.itemLearnTime = 0;
            }

            isItemKnown = isItemStackKnown();
        }
    }

    private boolean canLearnItemStack()
    {
        ItemStack alchenomicon = inventory[ALCHENOMICON_SLOT_INVENTORY_INDEX];
        UUID playerUUID = ItemHelper.getOwnerUUID(alchenomicon);

        if (alchenomicon != null && playerUUID != null)
        {
            return TransmutationKnowledgeRegistryProxy.canPlayerLearn(playerUUID, inventory[ITEM_SLOT_INVENTORY_INDEX]);
        }

        return false;
    }

    private boolean isItemStackKnown()
    {
        ItemStack alchenomicon = inventory[ALCHENOMICON_SLOT_INVENTORY_INDEX];
        UUID playerUUID = ItemHelper.getOwnerUUID(alchenomicon);

        if (alchenomicon != null && playerUUID != null)
        {
            return TransmutationKnowledgeRegistryProxy.doesPlayerKnow(playerUUID, inventory[ITEM_SLOT_INVENTORY_INDEX]);
        }

        return false;
    }

    private void learnItemStack()
    {
        if (this.canLearnItemStack())
        {
            TransmutationKnowledgeRegistryProxy.teachPlayer(ItemHelper.getOwnerUUID(inventory[ALCHENOMICON_SLOT_INVENTORY_INDEX]), inventory[ITEM_SLOT_INVENTORY_INDEX]);

            this.inventory[ITEM_SLOT_INVENTORY_INDEX].stackSize--;

            if (this.inventory[ITEM_SLOT_INVENTORY_INDEX].stackSize <= 0)
            {
                this.inventory[ITEM_SLOT_INVENTORY_INDEX] = null;
            }
        }
    }
}
