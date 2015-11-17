package com.pahimar.ee3.util;

import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class ItemHelper
{
    public static ItemStack cloneItemStack(ItemStack itemStack, int stackSize)
    {
        ItemStack clonedItemStack = itemStack.copy();
        clonedItemStack.stackSize = stackSize;
        return clonedItemStack;
    }

    /**
     * Compares two ItemStacks for equality, testing itemID, metaData, stackSize, and their NBTTagCompounds (if they are
     * present)
     *
     * @param first  The first ItemStack being tested for equality
     * @param second The second ItemStack being tested for equality
     * @return true if the two ItemStacks are equivalent, false otherwise
     */
    public static boolean equals(ItemStack first, ItemStack second)
    {
        return (Comparators.idComparator.compare(first, second) == 0);
    }

    public static boolean equalsIgnoreStackSize(ItemStack itemStack1, ItemStack itemStack2)
    {
        if (itemStack1 != null && itemStack2 != null)
        {
            // Sort on itemID
            if (Item.itemRegistry.getNameForObject(itemStack1.getItem()).compareToIgnoreCase(Item.itemRegistry.getNameForObject(itemStack2.getItem())) == 0)
            {
                // Sort on item
                if (itemStack1.getItem() == itemStack2.getItem())
                {
                    // Then sort on meta
                    if (itemStack1.getItemDamage() == itemStack2.getItemDamage())
                    {
                        // Then sort on NBT
                        if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
                        {
                            // Then sort on stack size
                            if (ItemStack.areItemStackTagsEqual(itemStack1, itemStack2))
                            {
                                return true;
                            }
                        }
                        else if (!itemStack1.hasTagCompound() && !itemStack2.hasTagCompound())
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static int compare(ItemStack itemStack1, ItemStack itemStack2)
    {
        return Comparators.idComparator.compare(itemStack1, itemStack2);
    }

    public static String toString(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            if (itemStack.hasTagCompound())
            {
                return String.format("%sxitemStack[%s@%s:%s]", itemStack.stackSize, itemStack.getUnlocalizedName(), itemStack.getItemDamage(), itemStack.getTagCompound());
            }
            else
            {
                return String.format("%sxitemStack[%s@%s]", itemStack.stackSize, itemStack.getUnlocalizedName(), itemStack.getItemDamage());
            }
        }

        return "null";
    }

    public static boolean hasOwner(ItemStack itemStack)
    {
        return (NBTHelper.hasTag(itemStack, Names.NBT.OWNER_UUID_MOST_SIG) && NBTHelper.hasTag(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG)) || NBTHelper.hasTag(itemStack, Names.NBT.OWNER);
    }

    public static boolean hasOwnerUUID(ItemStack itemStack)
    {
        return NBTHelper.hasTag(itemStack, Names.NBT.OWNER_UUID_MOST_SIG) && NBTHelper.hasTag(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG);
    }

    public static boolean hasOwnerName(ItemStack itemStack)
    {
        return NBTHelper.hasTag(itemStack, Names.NBT.OWNER);
    }

    public static String getOwnerName(ItemStack itemStack)
    {
        if (NBTHelper.hasTag(itemStack, Names.NBT.OWNER))
        {
            return NBTHelper.getString(itemStack, Names.NBT.OWNER);
        }

        return null;
    }

    public static UUID getOwnerUUID(ItemStack itemStack)
    {
        if (NBTHelper.hasTag(itemStack, Names.NBT.OWNER_UUID_MOST_SIG) && NBTHelper.hasTag(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG))
        {
            return new UUID(NBTHelper.getLong(itemStack, Names.NBT.OWNER_UUID_MOST_SIG), NBTHelper.getLong(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG));
        }

        return null;
    }

    public static void setOwner(ItemStack itemStack, EntityPlayer entityPlayer)
    {
        setOwnerName(itemStack, entityPlayer);
        setOwnerUUID(itemStack, entityPlayer);
    }

    public static void setOwnerUUID(ItemStack itemStack, EntityPlayer entityPlayer)
    {
        NBTHelper.setLong(itemStack, Names.NBT.OWNER_UUID_MOST_SIG, entityPlayer.getUniqueID().getMostSignificantBits());
        NBTHelper.setLong(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG, entityPlayer.getUniqueID().getLeastSignificantBits());
    }

    public static void setOwnerName(ItemStack itemStack, EntityPlayer entityPlayer)
    {
        NBTHelper.setString(itemStack, Names.NBT.OWNER, entityPlayer.getDisplayName());
    }
}
