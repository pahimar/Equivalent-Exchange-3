package com.pahimar.ee3.util;

import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.Comparator;
import java.util.UUID;

public class ItemHelper
{
    public static ItemStack cloneItemStack(ItemStack itemStack, int stackSize)
    {
        ItemStack clonedItemStack = itemStack.copy();
        clonedItemStack.stackSize = stackSize;
        return clonedItemStack;
    }

    public static Comparator<ItemStack> comparator = new Comparator<ItemStack>()
    {
        public int compare(ItemStack itemStack1, ItemStack itemStack2)
        {
            if (itemStack1 != null && itemStack2 != null)
            {
                // Sort on itemID
                if (Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem()) == 0)
                {
                    // Then sort on meta
                    if (itemStack1.getItemDamage() == itemStack2.getItemDamage())
                    {
                        // Then sort on NBT
                        if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
                        {
                            // Then sort on stack size
                            if (itemStack1.getTagCompound().equals(itemStack2.getTagCompound()))
                            {
                                return (itemStack1.stackSize - itemStack2.stackSize);
                            }
                            else
                            {
                                return (itemStack1.getTagCompound().hashCode() - itemStack2.getTagCompound().hashCode());
                            }
                        }
                        else if (!(itemStack1.hasTagCompound()) && itemStack2.hasTagCompound())
                        {
                            return -1;
                        }
                        else if (itemStack1.hasTagCompound() && !(itemStack2.hasTagCompound()))
                        {
                            return 1;
                        }
                        else
                        {
                            return (itemStack1.stackSize - itemStack2.stackSize);
                        }
                    }
                    else
                    {
                        return (itemStack1.getItemDamage() - itemStack2.getItemDamage());
                    }
                }
                else
                {
                    return Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem());
                }
            }
            else if (itemStack1 != null)
            {
                return -1;
            }
            else if (itemStack2 != null)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    };

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
        return (comparator.compare(first, second) == 0);
    }

    public static boolean equalsIgnoreStackSize(ItemStack itemStack1, ItemStack itemStack2)
    {
        if (itemStack1 != null && itemStack2 != null)
        {
            // Sort on itemID
            if (Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem()) == 0)
            {
                // Then sort on meta
                if (itemStack1.getItemDamage() == itemStack2.getItemDamage())
                {
                    // Then sort on NBT
                    if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
                    {
                        // Then sort on stack size
                        if (itemStack1.getTagCompound().equals(itemStack2.getTagCompound()))
                        {
                            return true;
                        }
                    }
                    else
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static int compare(ItemStack itemStack1, ItemStack itemStack2)
    {
        return comparator.compare(itemStack1, itemStack2);
    }

    public static String toString(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            return String.format("%sxitemStack[%s@%s]", itemStack.stackSize, itemStack.getUnlocalizedName(), itemStack.getItemDamage());
        }

        return "null";
    }

    public static boolean hasOwner(ItemStack itemStack)
    {
        return (NBTHelper.hasTag(itemStack, Names.NBT.OWNER_UUID_MOST_SIG) && NBTHelper.hasTag(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG)) || NBTHelper.hasTag(itemStack, Names.NBT.OWNER);
    }

    public static String getOwnerName(ItemStack itemStack)
    {
        if (NBTHelper.hasTag(itemStack, Names.NBT.OWNER))
        {
            return NBTHelper.getString(itemStack, Names.NBT.OWNER);
        }

        return StatCollector.translateToLocal(Messages.NO_OWNER);
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
        NBTHelper.setString(itemStack, Names.NBT.OWNER, entityPlayer.getDisplayName());
        NBTHelper.setLong(itemStack, Names.NBT.OWNER_UUID_MOST_SIG, entityPlayer.getUniqueID().getMostSignificantBits());
        NBTHelper.setLong(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG, entityPlayer.getUniqueID().getLeastSignificantBits());
    }
}
