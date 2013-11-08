package com.pahimar.ee3.core.helper.nbt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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

    // Constants for encoding/decoding EmcValues as NBT
    public static final String TAG_NAME_EMCVALUE = "emcValue";
    public static final String TEMPLATE_EMCVALUE_COMPONENT_ENTRY = "componentOrdinal_%s";
    
    // Constants for encoding/decoding "stacks" as NBT
    public static final String TAG_NAME_STACK = "stack";
    
    // Constants for encoding/decoding recipes as NBT
    public static final String TAG_NAME_RECIPES = "recipes";
    public static final String TAG_NAME_RECIPE_OUTPUT = "recipeOutput";
    public static final String TAG_NAME_RECIPE_INPUTS = "recipeInputs";
    public static final String TEMPLATE_RECIPE_LIST_ENTRY = "recipeListEntry_%s";
    public static final String TEMPLATE_RECIPE_INPUT_ENTRY = "recipeInput_%s";
    
    // Constants for encoding/decoding Object:EmcValue mappings as NBT
    public static final String TAG_NAME_STACK_VALUE_MAP = "stackValueMap";
    public static final String TEMPLATE_STACK_VALUE_MAP_ENTRY = "stackValueMapEntry_%s";
    
    /*
     * Begin NBT encode/decode helper methods for EmcValue
     */
    
    public static NBTTagCompound encodeEmcValue(float[] subValueArray) {
        
        if (subValueArray.length == EmcType.TYPES.length) {
            return encodeEmcValue(new EmcValue(subValueArray));
        }
        
        return null;
    }

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

        if (emcValue.getValue() > 0f) {
            NBTTagCompound encodedEmcValue = new NBTTagCompound(tagCompoundName);
            
            for (EmcType emcType: EmcType.TYPES) {
                encodedEmcValue.setFloat(String.format(TEMPLATE_EMCVALUE_COMPONENT_ENTRY, emcType.ordinal()), emcValue.components[emcType.ordinal()]);
            }
    
            return encodedEmcValue;
        }
        
        return null;
    }

    /**
     * 
     * @param nbtEncodedEmcValue
     * @return
     */
    public static EmcValue decodeEmcValue(NBTTagCompound nbtEncodedEmcValue) {

        if (nbtEncodedEmcValue != null) {
            
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
        
        return null;
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
    // TODO Come back and move related NBT constants from Strings to this class
    public static NBTTagCompound encodeStack(String tagCompoundName, Object object) {
        
        if (CustomWrappedStack.canBeWrapped(object)) {
            
            NBTTagCompound encodedStack = new NBTTagCompound(tagCompoundName);
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
            
            return encodedStack;
        }
        else {
            return null;
        }
    }

    /**
     * 
     * @param nbtEncodedStack
     * @return
     */
    // TODO Come back and move NBT related constants from Strings to this class
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

        // Check each element of the provided list to see if it can be wrapped as a CustomWrappedStack
        boolean validList = true;

        if (recipeInputs != null && recipeInputs.size() > 0) {
            
            for (Object recipeInput : recipeInputs) {
                
                if (validList && !CustomWrappedStack.canBeWrapped(recipeInput)) {
                    validList = false;
                }
            }
        }
        else {
            
            validList = false;
        }
        
        // If all elements in the list can be wrapped, then proceed with encoding
        if (validList) {
            
            NBTTagCompound encodedRecipeInputs = new NBTTagCompound(tagCompoundName);
            
            for (int i = 0; i < recipeInputs.size(); i++) {
                encodedRecipeInputs.setCompoundTag(String.format(TEMPLATE_RECIPE_INPUT_ENTRY, i), encodeStack(String.format(TEMPLATE_RECIPE_INPUT_ENTRY, i), recipeInputs.get(i)));
            }
            
            return encodedRecipeInputs;
        }
        
        return null;
    }

    /**
     * 
     * @param encodedRecipeInputs
     * @return
     */
    public static List<CustomWrappedStack> decodeRecipeInputs(NBTTagCompound encodedRecipeInputs) {

        List<CustomWrappedStack> decodedRecipeInputs = new ArrayList<CustomWrappedStack>();

        if (encodedRecipeInputs.getName().equalsIgnoreCase(TAG_NAME_RECIPE_INPUTS)) {
            
            for (int i = 0; i < encodedRecipeInputs.getTags().size(); i++) {
                
                NBTTagCompound encodedRecipeInput = encodedRecipeInputs.getCompoundTag(String.format(TEMPLATE_RECIPE_INPUT_ENTRY, i));
                
                if (!encodedRecipeInput.hasNoTags()) {
                    CustomWrappedStack decodedRecipeInput = decodeStack(encodedRecipeInput);
                    
                    if (decodedRecipeInput.getWrappedStack() != null) {
                        decodedRecipeInputs.add(decodedRecipeInput);
                    }
                    else {
                        return null;
                    }
                }
            }
        }
        
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

        Map<Object, List<?>> recipeMap = new HashMap<Object, List<?>>();
        recipeMap.put(recipeOutput, recipeInputs);
        
        return encodeRecipe(TAG_NAME_RECIPES, recipeMap);
    }

    /**
     * 
     * @param tagCompoundName
     * @param recipeOutput
     * @param recipeInputs
     * @return
     */
    public static NBTTagCompound encodeRecipe(String tagCompoundName, Object recipeOutput, List<?> recipeInputs) {

        Map<Object, List<?>> recipeMap = new HashMap<Object, List<?>>();
        recipeMap.put(recipeOutput, recipeInputs);
        
        return encodeRecipe(tagCompoundName, recipeMap);
    }
    
    public static NBTTagCompound encodeRecipe(Map<Object, List<?>> recipes) {
        
        return encodeRecipe(TAG_NAME_RECIPES, recipes);
    }
    
    public static NBTTagCompound encodeRecipe(String tagCompoundName, Map<Object, List<?>> recipeMap) {
        
        NBTTagCompound encodedRecipes = new NBTTagCompound(tagCompoundName);
        
        int i = 0;
        for (Object recipeOutput : recipeMap.keySet()) {
            
            boolean recipeMapEntryValid = true;
            List<?> recipeInputs = recipeMap.get(recipeOutput);
            
            // Check to make sure that the given recipe map entry is valid (can be wrapped)
            if (CustomWrappedStack.canBeWrapped(recipeOutput)) {
                for (Object recipeInput : recipeInputs) {
                    if (recipeMapEntryValid && !CustomWrappedStack.canBeWrapped(recipeInput)) {
                        recipeMapEntryValid = false;
                    }
                }
            }
            else {
                recipeMapEntryValid = false;
            }
            
            // Add the proven valid recipe map entry to the encoding
            if (recipeMapEntryValid) {
                
                NBTTagCompound recipeListEntry = new NBTTagCompound(String.format(TEMPLATE_RECIPE_LIST_ENTRY, i));
                
                recipeListEntry.setCompoundTag(TAG_NAME_RECIPE_OUTPUT, encodeStack(TAG_NAME_RECIPE_OUTPUT, recipeOutput));
                recipeListEntry.setCompoundTag(TAG_NAME_RECIPE_INPUTS, encodeRecipeInputs(recipeInputs));
                
                encodedRecipes.setCompoundTag(String.format(TEMPLATE_RECIPE_LIST_ENTRY, i), recipeListEntry);
                i++;
            }
            else {
                // Log the failure
            }
        }
        
        if (encodedRecipes.hasNoTags()) {
            return null;
        }
        else {
            return encodedRecipes;
        }
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
