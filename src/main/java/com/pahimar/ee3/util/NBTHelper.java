package com.pahimar.ee3.util;

import com.pahimar.ee3.reference.Names;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;

import java.util.UUID;

public class NBTHelper {

    public static void clearStatefulNBTTags(ItemStack itemStack) {

        if (NBTHelper.hasKey(itemStack, Names.NBT.CRAFTING_GUI_OPEN)) {
            NBTHelper.removeTag(itemStack, Names.NBT.CRAFTING_GUI_OPEN);
        }
        else if (NBTHelper.hasKey(itemStack, Names.NBT.TRANSMUTATION_GUI_OPEN)) {
            NBTHelper.removeTag(itemStack, Names.NBT.TRANSMUTATION_GUI_OPEN);
        }
        else if (NBTHelper.hasKey(itemStack, Names.NBT.ALCHEMICAL_BAG_GUI_OPEN)) {
            NBTHelper.removeTag(itemStack, Names.NBT.ALCHEMICAL_BAG_GUI_OPEN);
        }
    }

    public static boolean hasKey(ItemStack itemStack, String keyName) {
        return itemStack != null && itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey(keyName);
    }

    public static void removeTag(ItemStack itemStack, String keyName) {

        if (itemStack != null && itemStack.getTagCompound() != null && keyName != null && !keyName.isEmpty()) {
            itemStack.getTagCompound().removeTag(keyName);
        }
    }

    public static boolean hasUUID(ItemStack itemStack) {
        return getLong(itemStack, Names.NBT.UUID_MOST_SIG) != null && getLong(itemStack, Names.NBT.UUID_LEAST_SIG) != null;
    }

    public static UUID getUUID(ItemStack itemStack) {

        if (hasUUID(itemStack)) {
            return new UUID(getLong(itemStack, Names.NBT.UUID_MOST_SIG), getLong(itemStack, Names.NBT.UUID_LEAST_SIG));
        }

        return null;
    }

    public static void setUUID(ItemStack itemStack, UUID uuid) {

        if (itemStack != null) {

            initNBTTagCompound(itemStack);

            if (uuid == null) {
                uuid = UUID.randomUUID();
            }

            setLong(itemStack, Names.NBT.UUID_MOST_SIG, uuid.getMostSignificantBits());
            setLong(itemStack, Names.NBT.UUID_LEAST_SIG, uuid.getLeastSignificantBits());
        }
    }

    /**
     * Initializes the NBT Tag Compound for the given ItemStack
     *
     * @param itemStack The ItemStack for which its NBT Tag Compound is being checked for initialization
     */
    private static void initNBTTagCompound(ItemStack itemStack) {

        if (itemStack != null && itemStack.getTagCompound() == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    // String
    public static String getString(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagString) {
                return itemStack.getTagCompound().getString(keyName);
            }
        }

        return null;
    }

    public static void setString(ItemStack itemStack, String keyName, String keyValue) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setString(keyName, keyValue);
        }
    }

    // boolean
    public static Boolean getBoolean(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagByte) {
                itemStack.getTagCompound().getBoolean(keyName);
            }
        }

        return null;
    }

    public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setBoolean(keyName, keyValue);
        }
    }

    // byte
    public static Byte getByte(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagByte) {
                return itemStack.getTagCompound().getByte(keyName);
            }
        }

        return null;
    }

    public static void setByte(ItemStack itemStack, String keyName, byte keyValue) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setByte(keyName, keyValue);
        }
    }

    // short
    public static Short getShort(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagShort) {
                return itemStack.getTagCompound().getShort(keyName);
            }
        }

        return null;
    }

    public static void setShort(ItemStack itemStack, String keyName, short keyValue) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setShort(keyName, keyValue);
        }
    }

    // int
    public static Integer getInteger(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagInt) {
                return itemStack.getTagCompound().getInteger(keyName);
            }
        }

        return null;
    }

    public static void setInteger(ItemStack itemStack, String keyName, int keyValue) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setInteger(keyName, keyValue);
        }
    }

    // long
    public static Long getLong(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagLong) {
                return itemStack.getTagCompound().getLong(keyName);
            }
        }

        return null;
    }

    public static void setLong(ItemStack itemStack, String keyName, long keyValue) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {

            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setLong(keyName, keyValue);
        }
    }

    // float
    public static Float getFloat(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagFloat) {
                return itemStack.getTagCompound().getFloat(keyName);
            }
        }

        return null;
    }

    public static void setFloat(ItemStack itemStack, String keyName, float keyValue) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setFloat(keyName, keyValue);
        }
    }

    // double
    public static Double getDouble(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagDouble) {
                return itemStack.getTagCompound().getDouble(keyName);
            }
        }

        return null;
    }

    public static void setDouble(ItemStack itemStack, String keyName, double keyValue) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setDouble(keyName, keyValue);
        }
    }

    // tag list
    public static NBTTagList getTagList(ItemStack itemStack, String keyName, int nbtBaseType) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagList) {
                return itemStack.getTagCompound().getTagList(keyName, nbtBaseType);
            }
        }

        return null;
    }

    public static void setTagList(ItemStack itemStack, String keyName, NBTTagList nbtTagList) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setTag(keyName, nbtTagList);
        }
    }

    // tag compound
    public static NBTTagCompound getTagCompound(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName)) {
            if (itemStack.getTagCompound().getTag(keyName) instanceof NBTTagCompound) {
                return itemStack.getTagCompound().getCompoundTag(keyName);
            }
        }

        return null;
    }

    public static void setTagCompound(ItemStack itemStack, String keyName, NBTTagCompound nbtTagCompound) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setTag(keyName, nbtTagCompound);
        }
    }
}