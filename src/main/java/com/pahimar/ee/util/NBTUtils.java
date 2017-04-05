package com.pahimar.ee.util;

import com.pahimar.ee.reference.Colors;
import com.pahimar.ee.reference.NBTType;
import com.pahimar.ee.reference.Names;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.UUID;

public class NBTUtils {

    private static final String[] STATEFUL_TAG_NAMES = { Names.NBT.GUI_OPEN };

    private static boolean validate(ItemStack itemStack, String keyName) {
        return itemStack != null && itemStack.getTagCompound() != null && keyName != null && !keyName.isEmpty();
    }

    public static boolean hasKey(ItemStack itemStack, String keyName) {
        return validate(itemStack, keyName) && itemStack.getTagCompound().hasKey(keyName);
    }

    public static boolean hasKey(ItemStack itemStack, String keyName, NBTType nbtType) {
        return validate(itemStack, keyName) && nbtType != null && itemStack.getTagCompound().hasKey(keyName, nbtType.ordinal());
    }

    public static void removeTag(ItemStack itemStack, String keyName) {

        if (validate(itemStack, keyName)) {
            itemStack.getTagCompound().removeTag(keyName);
        }
    }

    public static void clearStatefulTags(ItemStack itemStack) {

        for (String tagName : STATEFUL_TAG_NAMES) {
            removeTag(itemStack, tagName);
        }
    }

    public static boolean hasUUID(ItemStack itemStack, String keyName) {
        return validate(itemStack, keyName) && itemStack.getTagCompound().hasUniqueId(keyName);
    }

    public static UUID getUUID(ItemStack itemStack, String keyName) {
        return hasUUID(itemStack, keyName) ? itemStack.getTagCompound().getUniqueId(keyName) : null;
    }

    public static void setUUID(ItemStack itemStack, String keyName) {
        setUUID(itemStack, keyName, null);
    }

    public static void setUUID(ItemStack itemStack, String keyName, UUID uuid) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {

            if (uuid == null) {
                uuid = UUID.randomUUID();
            }

            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setUniqueId(keyName, uuid);
        }
    }

    public static boolean hasColor(ItemStack itemStack) {
        return hasKey(itemStack, Names.NBT.DISPLAY, NBTType.COMPOUND) && getTagCompound(itemStack, Names.NBT.DISPLAY).hasKey(Names.NBT.COLOR, NBTType.INT.ordinal());
    }

    public static int getColor(ItemStack itemStack) {

        if (hasColor(itemStack)) {
            return itemStack.getTagCompound().getCompoundTag(Names.NBT.DISPLAY).getInteger(Names.NBT.COLOR);
        }

        return Colors.PURE_WHITE;
    }

    public static void setColor(ItemStack itemStack, int color) {

        if (itemStack != null) {
            if (itemStack.getTagCompound() == null) {
                itemStack.setTagCompound(new NBTTagCompound());
            }

            if (itemStack.getTagCompound().hasKey(Names.NBT.DISPLAY, NBTType.COMPOUND.ordinal())) {
                itemStack.getTagCompound().getCompoundTag(Names.NBT.DISPLAY).setInteger(Names.NBT.COLOR, color);
            }
            else {
                NBTTagCompound displayTagCompound = new NBTTagCompound();
                displayTagCompound.setInteger(Names.NBT.COLOR, color);
                itemStack.getTagCompound().setTag(Names.NBT.DISPLAY, displayTagCompound);
            }
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

        if (hasKey(itemStack, keyName, NBTType.STRING)) {
            return itemStack.getTagCompound().getString(keyName);
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
    public static boolean getBoolean(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName, NBTType.BYTE)) {
            return itemStack.getTagCompound().getBoolean(keyName);
        }

        return false;
    }

    public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue) {

        if (itemStack != null && keyName != null && !keyName.isEmpty()) {
            initNBTTagCompound(itemStack);
            itemStack.getTagCompound().setBoolean(keyName, keyValue);
        }
    }

    // byte
    public static Byte getByte(ItemStack itemStack, String keyName) {

        if (hasKey(itemStack, keyName, NBTType.BYTE)) {
            return itemStack.getTagCompound().getByte(keyName);
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

        if (hasKey(itemStack, keyName, NBTType.SHORT)) {
            return itemStack.getTagCompound().getShort(keyName);
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

        if (hasKey(itemStack, keyName, NBTType.INT)) {
            return itemStack.getTagCompound().getInteger(keyName);
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

        if (hasKey(itemStack, keyName, NBTType.LONG)) {
            return itemStack.getTagCompound().getLong(keyName);
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

        if (hasKey(itemStack, keyName, NBTType.FLOAT)) {
            return itemStack.getTagCompound().getFloat(keyName);
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

        if (hasKey(itemStack, keyName, NBTType.DOUBLE)) {
            return itemStack.getTagCompound().getDouble(keyName);
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

        if (hasKey(itemStack, keyName, NBTType.LIST)) {
            return itemStack.getTagCompound().getTagList(keyName, nbtBaseType);
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

        if (hasKey(itemStack, keyName, NBTType.COMPOUND)) {
            return itemStack.getTagCompound().getCompoundTag(keyName);
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