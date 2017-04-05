package com.pahimar.ee.util;

import com.pahimar.ee.reference.Comparators;
import com.pahimar.ee.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.UsernameCache;

import javax.annotation.Nullable;
import java.util.UUID;

public class ItemStackUtils {

    /**
     * TODO Documentation
     *
     * @param itemStack
     * @param stackSize
     * @return
     */
    @Nullable
    public static ItemStack clone(ItemStack itemStack, int stackSize) {

        if (itemStack != null) {
            ItemStack clonedItemStack = itemStack.copy();
            clonedItemStack.setCount(stackSize);
            return clonedItemStack;
        }
        else {
            return null;
        }
    }

    /**
     * Compares two ItemStacks for equality, testing itemID, metaData, stackSize, and their NBTTagCompounds (if they are
     * present)
     *
     * @param itemStack1 The first ItemStack being tested for equality
     * @param itemStack2 The second ItemStack being tested for equality
     * @return true if the two ItemStacks are equivalent, false otherwise
     */
    public static boolean equals(ItemStack itemStack1, ItemStack itemStack2) {
        return (Comparators.ID_COMPARATOR.compare(itemStack1, itemStack2) == 0);
    }

    /**
     * TODO Documentation
     *
     * @param itemStack1
     * @param itemStack2
     * @return
     */
    public static boolean equalsIgnoreStackSize(@Nullable ItemStack itemStack1, @Nullable ItemStack itemStack2) {
        return equals(clone(itemStack1, 1), clone(itemStack2, 1));
    }

    /**
     * TODO Documentation
     *
     * @param itemStack1
     * @param itemStack2
     * @return
     */
    public static int compare(ItemStack itemStack1, ItemStack itemStack2) {
        return Comparators.ID_COMPARATOR.compare(itemStack1, itemStack2);
    }

    /**
     * TODO Documentation
     *
     * @param itemStack
     * @return
     */
    public static String toString(ItemStack itemStack) {

        if (itemStack != null) {
            if (itemStack.hasTagCompound()) {
                return String.format("%sxitemStack[%s@%s:%s]", itemStack.getCount(), itemStack.getUnlocalizedName(), itemStack.getItemDamage(), itemStack.getTagCompound());
            }
            else {
                return String.format("%sxitemStack[%s@%s]", itemStack.getCount(), itemStack.getUnlocalizedName(), itemStack.getItemDamage());
            }
        }

        return "null";
    }

    /**
     * TODO Documentation
     *
     * @param itemStack
     * @return
     */
    public static boolean hasOwner(ItemStack itemStack) {
        return NBTUtils.getString(itemStack, Names.NBT.OWNER) != null || NBTUtils.getUUID(itemStack, Names.NBT.OWNER) != null;
    }

    /**
     * TODO Documentation
     *
     * @param itemStack
     * @return
     */
    @Nullable
    public static String getOwner(ItemStack itemStack) {

        if (itemStack.getItem() instanceof IOwnable) {
            UUID playerUUID = ItemStackUtils.getOwnerUUID(itemStack);
            if (playerUUID != null && UsernameCache.containsUUID(playerUUID)) {
                UsernameCache.getLastKnownUsername(playerUUID);
            }
            else if (ItemStackUtils.getOwnerName(itemStack) != null) {
                ItemStackUtils.getOwnerName(itemStack);
            }
        }

        return null;
    }

    /**
     * TODO Documentation
     *
     * @param itemStack
     * @return
     */
    @Nullable
    public static String getOwnerName(ItemStack itemStack) {
        if (itemStack != null) {
            UUID playerUUID = ItemStackUtils.getOwnerUUID(itemStack);
            if (playerUUID != null && UsernameCache.containsUUID(playerUUID)) {
               return UsernameCache.getLastKnownUsername(playerUUID);
            }
            else if (NBTUtils.getString(itemStack, Names.NBT.OWNER) != null) {
                return NBTUtils.getString(itemStack, Names.NBT.OWNER);
            }
        }

        return null;
    }

    /**
     * TODO Documentation
     *
     * @param itemStack
     * @return
     */
    @Nullable
    public static UUID getOwnerUUID(ItemStack itemStack) {
        return NBTUtils.getUUID(itemStack, Names.NBT.OWNER);
    }

    /**
     * TODO Documentation
     *
     * @param itemStack
     * @param entityPlayer
     */
    public static void setOwner(ItemStack itemStack, EntityPlayer entityPlayer) {
        setOwnerName(itemStack, entityPlayer);
        setOwnerUUID(itemStack, entityPlayer);
    }

    /**
     * TODO Documentation
     *
     * @param itemStack
     * @param entityPlayer
     */
    public static void setOwnerUUID(ItemStack itemStack, EntityPlayer entityPlayer) {
        NBTUtils.setUUID(itemStack, Names.NBT.OWNER, entityPlayer.getUniqueID());
    }

    /**
     * TODO Documentation
     *
     * @param itemStack
     * @param entityPlayer
     */
    public static void setOwnerName(ItemStack itemStack, EntityPlayer entityPlayer) {
        NBTUtils.setString(itemStack, Names.NBT.OWNER, entityPlayer.getName());
    }
}
