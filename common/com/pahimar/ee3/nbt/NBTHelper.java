package com.pahimar.ee3.nbt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.EnergyStack;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * NBTHelper
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class NBTHelper {

    /**
     * Encodes the given NBT object as a String
     * 
     * @param nbtBase
     * @return String encoding of the given NBT object
     */
    public static String encodeNBTAsString(NBTBase nbtBase) {

        StringBuilder stringBuilder = new StringBuilder();

        if (nbtBase != null) {
            // Encode the name of the tag, and the type of the tag
            stringBuilder.append(String.format("'%s':%s:", nbtBase.getName(), NBTBase.getTagName(nbtBase.getId())));

            // Encode the value of the tag, depending on the type of the tag
            switch (nbtBase.getId()) {
                case 0: {
                    stringBuilder.append(((NBTTagEnd) nbtBase).toString());
                    break;
                }
                case 1: {
                    stringBuilder.append(String.format("%s", ((NBTTagByte) nbtBase).data));
                    break;
                }
                case 2: {
                    stringBuilder.append(String.format("%s", ((NBTTagShort) nbtBase).data));
                    break;
                }
                case 3: {
                    stringBuilder.append(String.format("%s", ((NBTTagInt) nbtBase).data));
                    break;
                }
                case 4: {
                    stringBuilder.append(String.format("%s", ((NBTTagLong) nbtBase).data));
                    break;
                }
                case 5: {
                    stringBuilder.append(String.format("%s", ((NBTTagFloat) nbtBase).data));
                    break;
                }
                case 6: {
                    stringBuilder.append(String.format("%s", ((NBTTagDouble) nbtBase).data));
                    break;
                }
                case 7: {
                    NBTTagByteArray byteArray = (NBTTagByteArray) nbtBase;

                    stringBuilder.append("[");

                    for (int i = 0; i < byteArray.byteArray.length; i++) {
                        stringBuilder.append(byteArray.byteArray[i]);

                        if (i < byteArray.byteArray.length - 1) {
                            stringBuilder.append("|");
                        }
                    }

                    stringBuilder.append("]");

                    break;
                }
                case 8: {
                    stringBuilder.append(String.format("%s", ((NBTTagString) nbtBase).data));
                    break;
                }
                case 9: {
                    NBTTagList tagList = (NBTTagList) nbtBase;

                    stringBuilder.append("[");

                    for (int i = 0; i < tagList.tagCount(); i++) {
                        Object tagObject = tagList.tagAt(i);

                        if (tagObject instanceof NBTBase) {
                            stringBuilder.append(encodeNBTAsString((NBTBase) tagObject));
                        }

                        if (i < tagList.tagCount() - 1) {
                            stringBuilder.append("|");
                        }
                    }

                    stringBuilder.append("]");

                    break;
                }
                case 10: {
                    NBTTagCompound tagCompound = (NBTTagCompound) nbtBase;

                    stringBuilder.append("[");

                    Iterator<?> tagIterator = tagCompound.getTags().iterator();

                    while (tagIterator.hasNext()) {
                        Object tagObject = tagIterator.next();

                        if (tagObject instanceof NBTBase) {
                            stringBuilder.append(encodeNBTAsString((NBTBase) tagObject));
                        }

                        if (tagIterator.hasNext()) {
                            stringBuilder.append("|");
                        }
                    }

                    stringBuilder.append("]");

                    break;
                }
                case 11: {
                    NBTTagIntArray intArray = (NBTTagIntArray) nbtBase;

                    stringBuilder.append("[");

                    for (int i = 0; i < intArray.intArray.length; i++) {
                        stringBuilder.append(intArray.intArray[i]);

                        if (i < intArray.intArray.length - 1) {
                            stringBuilder.append("|");
                        }
                    }

                    stringBuilder.append("]");

                    break;
                }
                default: {
                    stringBuilder.append("UNKNOWN");
                    break;
                }
            }
        }

        return stringBuilder.toString();
    }

    // TODO Link this method to some API stuffs
    public static NBTTagCompound encodeStackAsNBT(Object stackObject) {

        return encodeStackAsNBT("", stackObject);
    }

    public static NBTTagCompound encodeStackAsNBT(String name, Object object) {

        NBTTagCompound encodedStack = new NBTTagCompound(name);

        if (CustomWrappedStack.canBeWrapped(object)) {

            CustomWrappedStack wrappedStack = new CustomWrappedStack(object);

            if (wrappedStack.getWrappedStack() instanceof ItemStack) {

                ItemStack itemStack = (ItemStack) wrappedStack.getWrappedStack();
                itemStack.stackSize = wrappedStack.getStackSize();
                encodedStack.setString(Strings.NBT_ENCODED_ATTR_TYPE, Strings.NBT_ENCODED_ATTR_TYPE_ITEM);
                itemStack.writeToNBT(encodedStack);
            }
            else if (wrappedStack.getWrappedStack() instanceof OreStack) {
                OreStack oreStack = (OreStack) wrappedStack.getWrappedStack();

                encodedStack.setString(Strings.NBT_ENCODED_ATTR_TYPE, Strings.NBT_ENCODED_ATTR_TYPE_ORE);
                encodedStack.setString(Strings.NBT_ENCODED_ATTR_ORE_NAME, oreStack.oreName);
                encodedStack.setShort(Strings.NBT_ENCODED_ATTR_SIZE, (short) wrappedStack.getStackSize());
            }
            else if (wrappedStack.getWrappedStack() instanceof EnergyStack) {
                EnergyStack energyStack = (EnergyStack) wrappedStack.getWrappedStack();

                encodedStack.setString(Strings.NBT_ENCODED_ATTR_TYPE, Strings.NBT_ENCODED_ATTR_TYPE_ENERGY);
                encodedStack.setString(Strings.NBT_ENCODED_ATTR_ENERGY_NAME, energyStack.energyName);
                encodedStack.setShort(Strings.NBT_ENCODED_ATTR_SIZE, (short) wrappedStack.getStackSize());
            }
        }

        return encodedStack;
    }

    public static NBTTagCompound encodeRecipeAsNBT(Object recipeOutput, List<?> recipeInputs) {

        NBTTagCompound encodedRecipe = new NBTTagCompound();

        NBTTagCompound recipeOutputNBTCompound = encodeStackAsNBT(Strings.NBT_ENCODED_RECIPE_OUTPUT, recipeOutput);
        NBTTagList recipeInputsNBTList = new NBTTagList(Strings.NBT_ENCODED_RECIPE_INPUTS);

        for (int i = 0; i < recipeInputs.size(); i++) {
            recipeInputsNBTList.appendTag(encodeStackAsNBT(Strings.NBT_ENCODED_RECIPE_INPUT_PREFIX.concat(Integer.toString(i)), recipeInputs.get(i)));
        }

        encodedRecipe.setCompoundTag(Strings.NBT_ENCODED_RECIPE_OUTPUT, recipeOutputNBTCompound);
        encodedRecipe.setTag(Strings.NBT_ENCODED_RECIPE_INPUTS, recipeInputsNBTList);

        return encodedRecipe;
    }

    /**
     * 
     * @param encodedStack
     *            A NBTTagCompound containing the encoded information of either
     *            a ItemStack, OreStack, or EnergyStack
     * @return A CustomWrappedStack containing the encoded stack provided in the
     *         NBTTagCompound (could be ItemStack, OreStack, or EnergyStack).
     *         Returns null in the event that no valid stack was able to be
     *         parsed from the encoded NBTTagCompound.
     */
    public static CustomWrappedStack decodeStackFromNBT(NBTTagCompound encodedStack) {

        CustomWrappedStack decodedStack = null;

        // If the encoded tag compound is of type ItemStack, parse out the ItemStack info
        if (encodedStack.hasKey(Strings.NBT_ENCODED_ATTR_TYPE)) {
            if (encodedStack.getString(Strings.NBT_ENCODED_ATTR_TYPE).equalsIgnoreCase(Strings.NBT_ENCODED_ATTR_TYPE_ITEM)) {

                ItemStack itemStack = new ItemStack(0, 0, 0);
                itemStack.readFromNBT(encodedStack);
                decodedStack = new CustomWrappedStack(itemStack);
            }
            // Else if the encoded tag compound is of type OreStack, parse out the OreStack info
            else if (encodedStack.getString(Strings.NBT_ENCODED_ATTR_TYPE).equalsIgnoreCase(Strings.NBT_ENCODED_ATTR_TYPE_ORE)) {

                if (encodedStack.hasKey(Strings.NBT_ENCODED_ATTR_ORE_NAME) && encodedStack.hasKey(Strings.NBT_ENCODED_ATTR_SIZE)) {
                    if ((encodedStack.getString(Strings.NBT_ENCODED_ATTR_ORE_NAME).length() > 0) && (encodedStack.getShort(Strings.NBT_ENCODED_ATTR_SIZE) >= 0)) {
                        decodedStack = new CustomWrappedStack(new OreStack(encodedStack.getString(Strings.NBT_ENCODED_ATTR_ORE_NAME), encodedStack.getShort(Strings.NBT_ENCODED_ATTR_SIZE)));
                    }
                }
            }
            // Else if the encoded tag compound is of type EnergyStack, parse out the EnergyStack info
            else if (encodedStack.getString(Strings.NBT_ENCODED_ATTR_TYPE).equalsIgnoreCase(Strings.NBT_ENCODED_ATTR_TYPE_ENERGY)) {

                if (encodedStack.hasKey(Strings.NBT_ENCODED_ATTR_ENERGY_NAME) && encodedStack.hasKey(Strings.NBT_ENCODED_ATTR_SIZE)) {
                    if ((encodedStack.getString(Strings.NBT_ENCODED_ATTR_ENERGY_NAME).length() > 0) && (encodedStack.getShort(Strings.NBT_ENCODED_ATTR_SIZE) >= 0)) {
                        decodedStack = new CustomWrappedStack(new EnergyStack(encodedStack.getString(Strings.NBT_ENCODED_ATTR_ENERGY_NAME), encodedStack.getShort(Strings.NBT_ENCODED_ATTR_SIZE)));
                    }
                }
            }
        }

        /*
         * This will only return non-null in the event that a proper
         * ItemStack|OreStack|EnergyStack was decoded from the encoded
         * NBTTagCompound
         */
        return decodedStack;
    }

    public static Map<CustomWrappedStack, List<CustomWrappedStack>> decodeRecipeFromNBT(NBTTagCompound encodedRecipe) {

        HashMap<CustomWrappedStack, List<CustomWrappedStack>> decodedRecipe = new HashMap<CustomWrappedStack, List<CustomWrappedStack>>();

        CustomWrappedStack recipeOutput = null;
        ArrayList<CustomWrappedStack> recipeInputs = new ArrayList<CustomWrappedStack>();

        CustomWrappedStack decodedStack = null;
        NBTTagCompound encodedStack = null;

        // Decode the recipe output
        if (encodedRecipe.hasKey(Strings.NBT_ENCODED_RECIPE_OUTPUT)) {

            decodedStack = decodeStackFromNBT(encodedRecipe.getCompoundTag(Strings.NBT_ENCODED_RECIPE_OUTPUT));

            if (decodedStack != null) {
                recipeOutput = decodedStack;
            }
        }

        // Decode the recipe inputs
        if (encodedRecipe.hasKey("recipeInputs")) {
            NBTTagList recipeInputsTagList = encodedRecipe.getTagList(Strings.NBT_ENCODED_RECIPE_INPUTS);

            for (int i = 0; i < recipeInputsTagList.tagCount(); i++) {
                if (recipeInputsTagList.tagAt(i) instanceof NBTTagCompound) {

                    encodedStack = (NBTTagCompound) recipeInputsTagList.tagAt(i);
                    decodedStack = decodeStackFromNBT(encodedStack);

                    if (decodedStack != null) {
                        recipeInputs.add(decodedStack);
                    }
                }
            }
        }

        // If we decoded both a recipe output and some inputs for it, add it to the map
        if (recipeOutput != null && recipeInputs.size() > 0) {
            decodedRecipe.put(recipeOutput, recipeInputs);
        }

        return decodedRecipe;
    }

    /**
     * Initializes the NBT Tag Compound for the given ItemStack if it is null
     * 
     * @param itemStack
     *            The ItemStack for which its NBT Tag Compound is being checked
     *            for initialization
     */
    private static void initNBTTagCompound(ItemStack itemStack) {

        if (itemStack.stackTagCompound == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    public static boolean hasTag(ItemStack itemStack, String keyName) {

        if (itemStack.stackTagCompound != null)
            return itemStack.stackTagCompound.hasKey(keyName);

        return false;
    }

    public static void removeTag(ItemStack itemStack, String keyName) {

        if (itemStack.stackTagCompound != null) {
            itemStack.stackTagCompound.removeTag(keyName);
        }
    }

    // String
    public static String getString(ItemStack itemStack, String keyName) {

        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            setString(itemStack, keyName, "");
        }

        return itemStack.stackTagCompound.getString(keyName);
    }

    public static void setString(ItemStack itemStack, String keyName, String keyValue) {

        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setString(keyName, keyValue);
    }

    // boolean
    public static boolean getBoolean(ItemStack itemStack, String keyName) {

        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            setBoolean(itemStack, keyName, false);
        }

        return itemStack.stackTagCompound.getBoolean(keyName);
    }

    public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue) {

        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setBoolean(keyName, keyValue);
    }

    // byte
    public static byte getByte(ItemStack itemStack, String keyName) {

        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            setByte(itemStack, keyName, (byte) 0);
        }

        return itemStack.stackTagCompound.getByte(keyName);
    }

    public static void setByte(ItemStack itemStack, String keyName, byte keyValue) {

        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setByte(keyName, keyValue);
    }

    // short
    public static short getShort(ItemStack itemStack, String keyName) {

        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            setShort(itemStack, keyName, (short) 0);
        }

        return itemStack.stackTagCompound.getShort(keyName);
    }

    public static void setShort(ItemStack itemStack, String keyName, short keyValue) {

        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setShort(keyName, keyValue);
    }

    // int
    public static int getInt(ItemStack itemStack, String keyName) {

        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            setInteger(itemStack, keyName, 0);
        }

        return itemStack.stackTagCompound.getInteger(keyName);
    }

    public static void setInteger(ItemStack itemStack, String keyName, int keyValue) {

        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setInteger(keyName, keyValue);
    }

    // long
    public static long getLong(ItemStack itemStack, String keyName) {

        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            setLong(itemStack, keyName, 0);
        }

        return itemStack.stackTagCompound.getLong(keyName);
    }

    public static void setLong(ItemStack itemStack, String keyName, long keyValue) {

        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setLong(keyName, keyValue);
    }

    // float
    public static float getFloat(ItemStack itemStack, String keyName) {

        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            setFloat(itemStack, keyName, 0);
        }

        return itemStack.stackTagCompound.getFloat(keyName);
    }

    public static void setFloat(ItemStack itemStack, String keyName, float keyValue) {

        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setFloat(keyName, keyValue);
    }

    // double
    public static double getDouble(ItemStack itemStack, String keyName) {

        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            setDouble(itemStack, keyName, 0);
        }

        return itemStack.stackTagCompound.getDouble(keyName);
    }

    public static void setDouble(ItemStack itemStack, String keyName, double keyValue) {

        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setDouble(keyName, keyValue);
    }

}
