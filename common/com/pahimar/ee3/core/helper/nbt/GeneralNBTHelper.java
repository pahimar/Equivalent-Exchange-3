package com.pahimar.ee3.core.helper.nbt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.pahimar.ee3.emc.EmcType;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.EnergyStack;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.lib.InterModComms;
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
public class GeneralNBTHelper {

    public static NBTTagCompound encodeEmcValue(EmcValue emcValue) {
        
        return encodeEmcValue(InterModComms.EMC_VALUE_TAG_NAME, emcValue);
    }
    
    public static NBTTagCompound encodeEmcValue(String compoundName, EmcValue emcValue) {
        
        NBTTagCompound encodedValue = new NBTTagCompound(compoundName);
        
        for (EmcType emcType: EmcType.TYPES) {
            encodedValue.setFloat(String.format(InterModComms.EMC_VALUE_COMPONENT_ORDINAL_TEMPLATE, emcType.ordinal()), emcValue.components[emcType.ordinal()]);
        }
        
        return encodedValue;
    }
    
    public static EmcValue decodeEmcValue(NBTTagCompound encodedValue) {
        
        float[] subValues = new float[EmcType.TYPES.length];
        
        for (EmcType emcType : EmcType.TYPES) {
            if (encodedValue.hasKey(String.format(InterModComms.EMC_VALUE_COMPONENT_ORDINAL_TEMPLATE, emcType.ordinal())) && emcType.ordinal() < subValues.length) {
                subValues[emcType.ordinal()] = encodedValue.getFloat(String.format(InterModComms.EMC_VALUE_COMPONENT_ORDINAL_TEMPLATE, emcType.ordinal()));
            }
        }
        
        return new EmcValue(subValues);
    }
    
    public static NBTTagCompound encodeEmcValueMapping(Object object, EmcValue emcValue) {
        
        return encodeEmcValueMapping(InterModComms.STACK_VALUE_MAPPING_TAG_NAME, object, emcValue);
    }
    
    private static NBTTagCompound encodeEmcValueMapping(String compoundName, Object object, EmcValue emcValue) {
        
        NBTTagCompound tagCompound = new NBTTagCompound(compoundName);
        
        tagCompound.setCompoundTag(InterModComms.STACK_TAG_NAME, encodeStackAsNBT(InterModComms.STACK_TAG_NAME, object));
        tagCompound.setCompoundTag(InterModComms.EMC_VALUE_TAG_NAME, encodeEmcValue(InterModComms.EMC_VALUE_TAG_NAME, emcValue));
        
        return tagCompound;
    }
    
    public static NBTTagCompound encodeEmcValueMappings(String compoundName, Map<CustomWrappedStack, EmcValue> stackValueMap) {
        
        NBTTagCompound tagCompound = new NBTTagCompound(compoundName);
        
        tagCompound.setInteger(InterModComms.COUNT_TAG_NAME, stackValueMap.keySet().size());
        int i = 0;
        for (CustomWrappedStack stack : stackValueMap.keySet()) {
            tagCompound.setCompoundTag(String.format(InterModComms.STACK_VALUE_MAPPING_TEMPLATE, i), encodeEmcValueMapping(stack, stackValueMap.get(stack)));
        }
        
        return tagCompound;
    }
    
    public static NBTTagCompound encodeEmcValueMappings(Map<CustomWrappedStack, EmcValue> stackValueMap) {
        return encodeEmcValueMappings(InterModComms.STACK_VALUE_MAPPING_TAG_NAME, stackValueMap);
    }
    
    public static NBTTagCompound encodeEmcValueMappings(String compoundName, Object object, EmcValue emcValue) {
        
        HashMap<CustomWrappedStack, EmcValue> stackValueMap = new HashMap<CustomWrappedStack, EmcValue>();
        
        if (CustomWrappedStack.canBeWrapped(object)) {
            CustomWrappedStack wrappedObject = new CustomWrappedStack(object);
            stackValueMap.put(wrappedObject, emcValue);
            return encodeEmcValueMappings(compoundName, stackValueMap);
        }
        else {
            return null;
        }
    }
    
    public static Map<CustomWrappedStack, EmcValue> decodeEmcValueMappings(NBTTagCompound encodedEmcValueMap) {
        
        Map<CustomWrappedStack, EmcValue> decodedEmcValueMapping = new HashMap<CustomWrappedStack, EmcValue>();
        
        if (encodedEmcValueMap.getName().equals(InterModComms.STACK_VALUE_MAPPING_TAG_NAME)) {
            int count = encodedEmcValueMap.getInteger(InterModComms.COUNT_TAG_NAME);
            
            for (int i = 0; i < count; i++) {
                if (encodedEmcValueMap.hasKey(String.format(InterModComms.STACK_VALUE_MAPPING_TEMPLATE, i))) {
                    
                    Map<CustomWrappedStack, EmcValue> decodedMapping = decodeEmcValueMapping(encodedEmcValueMap.getCompoundTag(String.format(InterModComms.STACK_VALUE_MAPPING_TEMPLATE, i)));
                    
                    if (decodedMapping != null && decodedMapping.size() > 0) {
                        decodedEmcValueMapping.putAll(decodedMapping);
                    }
                }
            }
        }
        
        return decodedEmcValueMapping;
    }
    
    public static Map<CustomWrappedStack, EmcValue> decodeEmcValueMapping(NBTTagCompound encodedEmcValueMapping) {

        Map<CustomWrappedStack, EmcValue> decodedEmcValueMapping = new HashMap<CustomWrappedStack, EmcValue>();
        
        if (encodedEmcValueMapping.hasKey(InterModComms.STACK_TAG_NAME) && encodedEmcValueMapping.hasKey(InterModComms.EMC_VALUE_TAG_NAME)) {
            CustomWrappedStack stack = GeneralNBTHelper.decodeStackFromNBT(encodedEmcValueMapping.getCompoundTag(InterModComms.STACK_TAG_NAME));
            EmcValue emcValue = GeneralNBTHelper.decodeEmcValue(encodedEmcValueMapping.getCompoundTag(InterModComms.EMC_VALUE_TAG_NAME));
            
            if (stack != null && emcValue != null) {
                if (stack.getWrappedStack() != null && emcValue.getValue() > 0) {
                    decodedEmcValueMapping.put(stack, emcValue);
                    return decodedEmcValueMapping;
                }
            }
        }
        
        return null;
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
