package com.pahimar.ee3.core.helper.nbt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.emc.EmcType;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.EnergyStack;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * EncodedNBTHelper
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EncodedNBTHelper {

    public static final String TAG_NAME_EMCVALUE = "emcValue";
    public static final String TAG_NAME_STACK = "stack";
    public static final String TAG_NAME_RECIPE_INPUTS = "recipeInputs";
    public static final String TAG_NAME_RECIPE_INPUTS_LIST = "recipeInputsList";
    public static final String TAG_NAME_RECIPE_OUTPUT = "recipeOutput";
    public static final String TAG_NAME_RECIPE = "recipe";
    public static final String TAG_NAME_STACK_VALUE_MAP = "stackValueMap";
    
    public static final String TEMPLATE_EMCVALUE_COMPONENT_ENTRY = "componentOrdinal_%s";
    public static final String TEMPLATE_RECIPE_INPUT_ENTRY = "recipeInput_%s";
    public static final String TEMPLATE_STACK_VALUE_MAPPING_ENTRY = "stackValueMapping_%s";
    
    /*
     * Begin NBT encode/decode helper methods for EmcValue
     */

    /**
     * 
     * @param emcValue
     * @return
     */
    public static NBTTagCompound encodeEmcValue(EmcValue emcValue) {

        return encodeEmcValue(TAG_NAME_EMCVALUE, emcValue);
    }

    /**
     * 
     * @param tagCompoundName
     * @param emcValue
     * @return
     */
    public static NBTTagCompound encodeEmcValue(String tagCompoundName, EmcValue emcValue) {

        NBTTagCompound encodedEmcValue = new NBTTagCompound(tagCompoundName);
        
        for (EmcType emcType: EmcType.TYPES) {
            encodedEmcValue.setFloat(String.format(TEMPLATE_EMCVALUE_COMPONENT_ENTRY, emcType.ordinal()), emcValue.components[emcType.ordinal()]);
        }

        return encodedEmcValue;
    }

    /**
     * 
     * @param nbtEncodedEmcValue
     * @return
     */
    public static EmcValue decodeEmcValue(NBTTagCompound nbtEncodedEmcValue) {

        float[] components = new float[EmcType.TYPES.length];

        for (EmcType emcType : EmcType.TYPES) {
            if (nbtEncodedEmcValue.hasKey(String.format(TEMPLATE_EMCVALUE_COMPONENT_ENTRY, emcType.ordinal())) && emcType.ordinal() < components.length) {
                components[emcType.ordinal()] = nbtEncodedEmcValue.getFloat(String.format(TEMPLATE_EMCVALUE_COMPONENT_ENTRY, emcType.ordinal()));
            }
        }
        
        EmcValue decodedEmcValue = new EmcValue(components);
        
        if (decodedEmcValue.getValue() > 0f) {
            return decodedEmcValue;
        }
        else {
            return null;
        }
    }

    
    /*
     * Begin NBT encode/decode helpers for CustomWrappedStacks
     */

    /**
     * 
     * @param object
     * @return
     */
    public static NBTTagCompound encodeStack(Object object) {

        return encodeStack(TAG_NAME_STACK, object);
    }

    /**
     * 
     * @param tagCompoundName
     * @param object
     * @return
     */
    public static NBTTagCompound encodeStack(String tagCompoundName, Object object) {

        NBTTagCompound encodedStack = new NBTTagCompound(tagCompoundName);
        
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

    /**
     * 
     * @param nbtEncodedStack
     * @return
     */
    public static CustomWrappedStack decodeStack(NBTTagCompound nbtEncodedStack) {

        CustomWrappedStack decodedStack = null;
        
        // If the encoded tag compound is of type ItemStack, parse out the ItemStack info
        if (nbtEncodedStack.hasKey(Strings.NBT_ENCODED_ATTR_TYPE)) {
            if (nbtEncodedStack.getString(Strings.NBT_ENCODED_ATTR_TYPE).equalsIgnoreCase(Strings.NBT_ENCODED_ATTR_TYPE_ITEM)) {

                ItemStack itemStack = new ItemStack(0, 0, 0);
                itemStack.readFromNBT(nbtEncodedStack);
                decodedStack = new CustomWrappedStack(itemStack);
            }
            // Else if the encoded tag compound is of type OreStack, parse out the OreStack info
            else if (nbtEncodedStack.getString(Strings.NBT_ENCODED_ATTR_TYPE).equalsIgnoreCase(Strings.NBT_ENCODED_ATTR_TYPE_ORE)) {

                if (nbtEncodedStack.hasKey(Strings.NBT_ENCODED_ATTR_ORE_NAME) && nbtEncodedStack.hasKey(Strings.NBT_ENCODED_ATTR_SIZE)) {
                    if ((nbtEncodedStack.getString(Strings.NBT_ENCODED_ATTR_ORE_NAME).length() > 0) && (nbtEncodedStack.getShort(Strings.NBT_ENCODED_ATTR_SIZE) >= 0)) {
                        decodedStack = new CustomWrappedStack(new OreStack(nbtEncodedStack.getString(Strings.NBT_ENCODED_ATTR_ORE_NAME), nbtEncodedStack.getShort(Strings.NBT_ENCODED_ATTR_SIZE)));
                    }
                }
            }
            // Else if the encoded tag compound is of type EnergyStack, parse out the EnergyStack info
            else if (nbtEncodedStack.getString(Strings.NBT_ENCODED_ATTR_TYPE).equalsIgnoreCase(Strings.NBT_ENCODED_ATTR_TYPE_ENERGY)) {

                if (nbtEncodedStack.hasKey(Strings.NBT_ENCODED_ATTR_ENERGY_NAME) && nbtEncodedStack.hasKey(Strings.NBT_ENCODED_ATTR_SIZE)) {
                    if ((nbtEncodedStack.getString(Strings.NBT_ENCODED_ATTR_ENERGY_NAME).length() > 0) && (nbtEncodedStack.getShort(Strings.NBT_ENCODED_ATTR_SIZE) >= 0)) {
                        decodedStack = new CustomWrappedStack(new EnergyStack(nbtEncodedStack.getString(Strings.NBT_ENCODED_ATTR_ENERGY_NAME), nbtEncodedStack.getShort(Strings.NBT_ENCODED_ATTR_SIZE)));
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

    
    /*
     * Begin NBT encode/decode helpers for Recipe inputs
     */

    /**
     * 
     * @param recipeInputs
     * @return
     */
    public static NBTTagCompound encodeRecipeInputs(List<?> recipeInputs) {

        return encodeRecipeInputs(TAG_NAME_RECIPE_INPUTS, recipeInputs);
    }

    /**
     * 
     * @param tagCompoundName
     * @param recipeInputs
     * @return
     */
    public static NBTTagCompound encodeRecipeInputs(String tagCompoundName, List<?> recipeInputs) {

        NBTTagCompound encodedRecipeInputs = new NBTTagCompound(tagCompoundName);
        NBTTagList encodedRecipeInputsList = new NBTTagList(TAG_NAME_RECIPE_INPUTS_LIST);
        
        for (int i = 0; i < recipeInputs.size(); i++) {
            encodedRecipeInputsList.appendTag(encodeStack(String.format(TEMPLATE_RECIPE_INPUT_ENTRY, i), recipeInputs.get(i)));
        }
        
        encodedRecipeInputs.setTag(TAG_NAME_RECIPE_INPUTS_LIST, encodedRecipeInputsList);

        return encodedRecipeInputs;
    }

    /**
     * 
     * @param encodedRecipeInputs
     * @return
     */
    public static List<CustomWrappedStack> decodeRecipeInputs(NBTTagCompound encodedRecipeInputs) {

        List<CustomWrappedStack> decodedRecipeInputs = new ArrayList<CustomWrappedStack>();

        // TODO Pick up here
        
        return decodedRecipeInputs;
    }

    
    /*
     * Begin NBT encode/decode helpers for Recipes
     */
    
    /**
     * 
     * @param recipeOutput
     * @param recipeInputs
     * @return
     */
    public static NBTTagCompound encodeRecipe(Object recipeOutput, List<?> recipeInputs) {

        return encodeRecipe(TAG_NAME_RECIPE, recipeOutput, recipeInputs);
    }

    /**
     * 
     * @param tagCompoundName
     * @param recipeOutput
     * @param recipeInputs
     * @return
     */
    public static NBTTagCompound encodeRecipe(String tagCompoundName, Object recipeOutput, List<?> recipeInputs) {

        NBTTagCompound encodedRecipe = new NBTTagCompound(tagCompoundName);

        // TODO Work this into encodeRecipe(String, Map<Object, List<?>>)
        
        return encodedRecipe;
    }
    
    public static NBTTagCompound encodeRecipe(Map<Object, List<?>> recipes) {
        
        return encodeRecipe(TAG_NAME_RECIPE, recipes);
    }
    
    public static NBTTagCompound encodeRecipe(String tagCompoundName, Map<Object, List<?>> recipes) {
        
        NBTTagCompound encodedRecipes = new NBTTagCompound(tagCompoundName);

        // TODO Support for encoding multiple recipes into a single NBTTagCompound
        
        return encodedRecipes;
    }

    /**
     * 
     * @param nbtEncodedRecipe
     * @return
     */
    public static Multimap<CustomWrappedStack, List<CustomWrappedStack>> decodeRecipe(NBTTagCompound nbtEncodedRecipe) {

        Multimap<CustomWrappedStack, List<CustomWrappedStack>> decodedRecipes = HashMultimap.create();

        return decodedRecipes;
    }

    
    /*
     * Begin NBT encode/decode helpers for CustomWrappedStack:EmcValue mappings
     */
    
    /**
     * 
     * @param object
     * @param emcValue
     * @return
     */
    public static NBTTagCompound encodeStackValueMap(Object object, EmcValue emcValue) {

        return encodeStackValueMap(TAG_NAME_STACK_VALUE_MAP, object, emcValue);
    }

    /**
     * 
     * @param tagCompoundName
     * @param object
     * @param emcValue
     * @return
     */
    public static NBTTagCompound encodeStackValueMap(String tagCompoundName, Object object, EmcValue emcValue) {

        NBTTagCompound encodedStackValueMap = new NBTTagCompound(tagCompoundName);

        if (CustomWrappedStack.canBeWrapped(object) && (emcValue != null && emcValue.getValue() > 0f)) {

            Map<CustomWrappedStack, EmcValue> stackValueMap = new HashMap<CustomWrappedStack, EmcValue>();
            stackValueMap.put(new CustomWrappedStack(object), emcValue);
            encodedStackValueMap = encodeStackValueMap(tagCompoundName, stackValueMap);
        }

        return encodedStackValueMap;
    }

    /**
     * 
     * @param stackValueMap
     * @return
     */
    public static NBTTagCompound encodeStackValueMap(Map<CustomWrappedStack, EmcValue> stackValueMap) {

        return encodeStackValueMap(TAG_NAME_STACK_VALUE_MAP, stackValueMap);
    }

    /**
     * 
     * @param tagCompoundName
     * @param stackValueMap
     * @return
     */
    public static NBTTagCompound encodeStackValueMap(String tagCompoundName, Map<CustomWrappedStack, EmcValue> stackValueMap) {

        NBTTagCompound encodedStackValueMap = new NBTTagCompound(tagCompoundName);

        return encodedStackValueMap;
    }
    
    /**
     * 
     * @param encodedStackValueMap
     * @return
     */
    public static Map<CustomWrappedStack, EmcValue> decodeStackValueMap(NBTTagCompound encodedStackValueMap) {
        
        Map<CustomWrappedStack, EmcValue> decodedStackValueMap = new HashMap<CustomWrappedStack, EmcValue>();
        
        return decodedStackValueMap;
    }
}
