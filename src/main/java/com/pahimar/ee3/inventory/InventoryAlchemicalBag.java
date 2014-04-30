package com.pahimar.ee3.inventory;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.INBTTaggable;
import com.pahimar.ee3.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.UUID;

public class InventoryAlchemicalBag implements IInventory, INBTTaggable
{
    public ItemStack parentItemStack;
    protected ItemStack[] inventory;
    protected String customName;

    public InventoryAlchemicalBag(ItemStack itemStack)
    {
        parentItemStack = itemStack;

        int size;
        if (itemStack.getItemDamage() == 1)
        {
            size = ContainerAlchemicalBag.MEDIUM_BAG_INVENTORY_ROWS * ContainerAlchemicalBag.MEDIUM_BAG_INVENTORY_COLUMNS;
        }
        else if (itemStack.getItemDamage() == 2)
        {
            size = ContainerAlchemicalBag.LARGE_BAG_INVENTORY_ROWS * ContainerAlchemicalBag.LARGE_BAG_INVENTORY_COLUMNS;
        }
        else
        {
            size = ContainerAlchemicalBag.SMALL_BAG_INVENTORY_ROWS * ContainerAlchemicalBag.SMALL_BAG_INVENTORY_COLUMNS;
        }

        inventory = new ItemStack[size];

        readFromNBT(itemStack.getTagCompound());
    }

    public void onGuiSaved(EntityPlayer entityPlayer)
    {
        parentItemStack = findParentItemStack(entityPlayer);

        if (parentItemStack != null)
        {
            save();
        }
    }

    public ItemStack findParentItemStack(EntityPlayer entityPlayer)
    {
        if (NBTHelper.hasUUID(parentItemStack))
        {
            UUID parentItemStackUUID = new UUID(parentItemStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG), parentItemStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG));
            for (int i = 0; i < entityPlayer.inventory.getSizeInventory(); i++)
            {
                ItemStack itemStack = entityPlayer.inventory.getStackInSlot(i);

                if (NBTHelper.hasUUID(itemStack))
                {
                    if (itemStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG) == parentItemStackUUID.getMostSignificantBits() && itemStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG) == parentItemStackUUID.getLeastSignificantBits())
                    {
                        return itemStack;
                    }
                }
            }
        }

        return null;
    }

    public boolean matchesUUID(UUID uuid)
    {
        return NBTHelper.hasUUID(parentItemStack) && parentItemStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG) == uuid.getLeastSignificantBits() && parentItemStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG) == uuid.getMostSignificantBits();
    }

    public void save()
    {
        NBTTagCompound nbtTagCompound = parentItemStack.getTagCompound();

        if (nbtTagCompound == null)
        {
            nbtTagCompound = new NBTTagCompound();

            UUID uuid = UUID.randomUUID();
            nbtTagCompound.setLong(Names.NBT.UUID_MOST_SIG, uuid.getMostSignificantBits());
            nbtTagCompound.setLong(Names.NBT.UUID_LEAST_SIG, uuid.getLeastSignificantBits());
        }

        writeToNBT(nbtTagCompound);
        parentItemStack.setTagCompound(nbtTagCompound);
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
        if (inventory[slotIndex] != null)
        {
            ItemStack itemStack = inventory[slotIndex];
            inventory[slotIndex] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.ALCHEMICAL_BAG;
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
    public void markDirty()
    {
        // NOOP
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return true;
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
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey("Items"))
        {
            // Read in the ItemStacks in the inventory from NBT
            if (nbtTagCompound.hasKey("Items"))
            {
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

            // Read in any custom name for the inventory
            if (nbtTagCompound.hasKey("display") && nbtTagCompound.getTag("display").getClass().equals(NBTTagCompound.class))
            {
                if (nbtTagCompound.getCompoundTag("display").hasKey("Name"))
                {
                    customName = nbtTagCompound.getCompoundTag("display").getString("Name");
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
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

    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public String getCustomName()
    {
        return customName;
    }
}
