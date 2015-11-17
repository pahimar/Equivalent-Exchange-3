package com.pahimar.ee3.inventory;

import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.Set;
import java.util.TreeSet;

public class InventoryAlchemicalTome implements IInventory
{
    private ItemStack[] inventory;
    private Set<ItemStack> knownTransmutations;

    public InventoryAlchemicalTome(Set<ItemStack> knownTransmutations)
    {
        inventory = new ItemStack[80];
        if (knownTransmutations != null)
        {
            this.knownTransmutations = knownTransmutations;
        }
        else
        {
            this.knownTransmutations = new TreeSet<ItemStack>(Comparators.idComparator);
        }
        
        inventory = this.knownTransmutations.toArray(inventory);
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        if (slotIndex < getSizeInventory())
        {
            return inventory[slotIndex];
        }

        return null;
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
        if (getStackInSlot(slotIndex) != null)
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
        if (slotIndex < inventory.length)
        {
            inventory[slotIndex] = itemStack;
        }
    }

    @Override
    public String getInventoryName()
    {
        return Names.Containers.ALCHEMICAL_TOME;
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
        return false;
    }

    public Set<ItemStack> getKnownTransmutations()
    {
        return knownTransmutations;
    }
}
