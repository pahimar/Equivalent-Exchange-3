package com.pahimar.ee3.core.helper.nbt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.emc.EmcType;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.CustomWrappedStack;

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

    public static final String TAG_NAME_COUNT = "count";
    public static final String TAG_NAME_EMCVALUE = "encodedEmcValue";
    public static final String TAG_NAME_STACK = "encodedStack";
    public static final String TAG_NAME_STACK_VALUE_MAP = "stackValueMap";
    
    public static final String TEMPLATE_EMCVALUE_COMPONENT_ENTRY = "componentOrdinal_%s";
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

        return encodedEmcValue;
    }

    /**
     * 
     * @param nbtEncodedEmcValue
     * @return
     */
    public static EmcValue decodeEmcValue(NBTTagCompound nbtEncodedEmcValue) {

        float[] components = new float[EmcType.TYPES.length];

        return new EmcValue(components);
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

        return encodedStack;
    }

    /**
     * 
     * @param nbtEncodedStack
     * @return
     */
    public static CustomWrappedStack decodeStack(NBTTagCompound nbtEncodedStack) {

        CustomWrappedStack decodedStack = null;

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

        // TODO String constant for tag name
        return encodeRecipeInputs("", recipeInputs);
    }

    /**
     * 
     * @param tagCompoundName
     * @param recipeInputs
     * @return
     */
    public static NBTTagCompound encodeRecipeInputs(String tagCompoundName, List<?> recipeInputs) {

        NBTTagCompound encodedRecipeInputs = new NBTTagCompound(tagCompoundName);

        return encodedRecipeInputs;
    }

    /**
     * 
     * @param encodedRecipeInputs
     * @return
     */
    public static List<CustomWrappedStack> decodeRecipeInputs(NBTTagCompound encodedRecipeInputs) {

        List<CustomWrappedStack> decodedRecipeInputs = new ArrayList<CustomWrappedStack>();

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

        // TODO String constant for tag name
        return encodeRecipe("", recipeOutput, recipeInputs);
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

        return encodedRecipe;
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

        // TODO String constant for tag name
        return encodeStackValueMap("", object, emcValue);
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

        // TODO String constant for tag name
        return encodeStackValueMap("", stackValueMap);
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
