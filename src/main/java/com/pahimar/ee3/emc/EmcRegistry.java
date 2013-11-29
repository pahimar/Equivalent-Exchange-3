package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.ImmutableSortedMap;
import com.pahimar.ee3.core.helper.EmcHelper;
import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.core.helper.RecipeHelper;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.item.WrappedStack;
import com.pahimar.ee3.item.crafting.RecipeRegistry;

public class EmcRegistry {

    private int MAX_ATTEMPTED_ASSIGNMENT_PASSES = 16;
    
    private static EmcRegistry emcRegistry = null;

    private ImmutableSortedMap<WrappedStack, EmcValue> stackMappings;
    private ImmutableSortedMap<EmcValue, List<WrappedStack>> valueMappings;

    public static void lazyInit() {

        if (emcRegistry == null) {
            emcRegistry = new EmcRegistry();
            emcRegistry.init();
        }
    }

    private void init() {

        HashMap<WrappedStack, EmcValue> stackValueMap = new HashMap<WrappedStack, EmcValue>();
        
        /*
         *  Default values
         */
        Map<WrappedStack, EmcValue> defaultValuesMap = EmcValuesDefault.getDefaultValueMap();
        for (WrappedStack wrappedStack : defaultValuesMap.keySet()) {
            if (wrappedStack != null) {
                if (!stackValueMap.keySet().contains(wrappedStack)) {
                    EmcValue emcValue = defaultValuesMap.get(wrappedStack);
                    
                    if (emcValue != null && emcValue.getValue() > 0f) {
                        stackValueMap.put(wrappedStack, emcValue);
                    }
                }
            }
        }
        
        /*
         *  IMC Pre-assigned values
         */
        Map<WrappedStack, EmcValue> preAssignedValuesMap = EmcValuesIMC.getPreAssignedValues();
        for (WrappedStack wrappedStack : preAssignedValuesMap.keySet()) {
            if (wrappedStack != null) {
                if (!stackValueMap.keySet().contains(wrappedStack)) {
                    EmcValue emcValue = preAssignedValuesMap.get(wrappedStack);
                    
                    if (emcValue != null && emcValue.getValue() > 0f) {
                        stackValueMap.put(wrappedStack, emcValue);
                    }
                }
            }
        }
        
        /*
         *  Auto-assignment
         */
        ImmutableSortedMap.Builder<WrappedStack, EmcValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        stackValueMap.clear();
        stackMappings = stackMappingsBuilder.build();
        
        int passNumber = 0;
        Map<WrappedStack, EmcValue> computedStackValues = computeStackMappings();

        while ((computedStackValues.size() > 0) && (passNumber < MAX_ATTEMPTED_ASSIGNMENT_PASSES)) {
            
            passNumber++;
            computedStackValues = computeStackMappings();
            
            stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(stackMappings);                
            stackMappingsBuilder.putAll(computedStackValues);
            stackMappings = stackMappingsBuilder.build();
        }
        
        /*
         *  IMC Post-assigned values
         */
        stackValueMap.putAll(stackMappings);
        Map<WrappedStack, EmcValue> postAssignedValuesMap = EmcValuesIMC.getPostAssignedValues();
        for (WrappedStack wrappedStack : postAssignedValuesMap.keySet()) {
            if (wrappedStack != null) {
                
                EmcValue emcValue = postAssignedValuesMap.get(wrappedStack);
                
                if (emcValue != null && emcValue.getValue() > 0f) {
                    stackValueMap.put(wrappedStack, emcValue);
                }
            }
        }
        stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        stackMappingsBuilder.putAll(stackValueMap);
        stackMappings = stackMappingsBuilder.build();
        
        /*
         *  Value map resolution
         */
        SortedMap<EmcValue, List<WrappedStack>> tempValueMappings = new TreeMap<EmcValue, List<WrappedStack>>();

        for (WrappedStack stack : stackMappings.keySet()) {
            EmcValue value = stackMappings.get(stack);

            if (tempValueMappings.containsKey(value)) {
                if (!(tempValueMappings.get(value).contains(stack))) {
                    tempValueMappings.get(value).add(stack);
                }
            }
            else {
                tempValueMappings.put(value, new ArrayList<WrappedStack>(Arrays.asList(stack)));
            }
        }
        
        valueMappings = ImmutableSortedMap.copyOf(tempValueMappings);
    }
    
    private static Map<WrappedStack, EmcValue> computeStackMappings() {
        
        Map<WrappedStack, EmcValue> computedStackMap = new HashMap<WrappedStack, EmcValue>();
        
        for (WrappedStack recipeOutput : RecipeRegistry.getRecipeMappings().keySet()) {
            
            if (!hasEmcValue(recipeOutput.getWrappedStack(), false) && !computedStackMap.containsKey(recipeOutput.getWrappedStack())) {
                
                EmcValue lowestValue = null;
                
                for (List<WrappedStack> recipeInputs : RecipeRegistry.getRecipeMappings().get(recipeOutput)) {
                    
                    EmcValue computedValue = EmcHelper.computeEmcValueFromList(recipeInputs);
                    computedValue = EmcHelper.factorEmcValue(computedValue, recipeOutput.getStackSize());
                    
                    if (computedValue != null) {
                        if (computedValue.compareTo(lowestValue) < 0) {
                            lowestValue = computedValue;
                        }
                    }
                }
                
                if ((lowestValue != null) && (lowestValue.getValue() > 0f)) {
                    computedStackMap.put(new WrappedStack(recipeOutput.getWrappedStack()), lowestValue);
                }
            }
        }
        
        return computedStackMap;
    }

    public static boolean hasEmcValue(Object object, boolean strict) {

        lazyInit();

        if (WrappedStack.canBeWrapped(object)) {
            
            WrappedStack stack = new WrappedStack(object);
            
            if (emcRegistry.stackMappings.containsKey(new WrappedStack(stack.getWrappedStack()))) {
                return emcRegistry.stackMappings.containsKey(new WrappedStack(stack.getWrappedStack()));
            }
            else {
                
                if (!strict) {
                    if (stack.getWrappedStack() instanceof ItemStack) {                  
                        
                        ItemStack wrappedItemStack = (ItemStack) stack.getWrappedStack();
                        
                        // If its an OreDictionary item, scan its siblings for values
                        if (OreDictionary.getOreID(wrappedItemStack) != -1) {
                            
                            OreStack oreStack = new OreStack(wrappedItemStack);
                            
                            if (emcRegistry.stackMappings.containsKey(new WrappedStack(oreStack))) {
                                return emcRegistry.stackMappings.containsKey(new WrappedStack(oreStack));
                            }
                            else {
                                for (ItemStack itemStack : OreDictionary.getOres(OreDictionary.getOreID(wrappedItemStack))) {
                                    if (emcRegistry.stackMappings.containsKey(new WrappedStack(itemStack))) {
                                        return emcRegistry.stackMappings.containsKey(new WrappedStack(itemStack));
                                    }
                                }
                            }
                        }
                        // Else, scan for if there is a wildcard value for it
                        else {
                            
                            for (WrappedStack valuedStack : emcRegistry.stackMappings.keySet()) {
                                
                                if (valuedStack.getWrappedStack() instanceof ItemStack) {
                                    ItemStack valuedItemStack = (ItemStack) valuedStack.getWrappedStack();
                                    
                                    if ((valuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || wrappedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) && valuedItemStack.itemID == wrappedItemStack.itemID) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
    
    public static boolean hasEmcValue(Object object) {
        return hasEmcValue(object, false);
    }

    public static EmcValue getEmcValue(Object object, boolean strict) {

        lazyInit();

        if (hasEmcValue(object, strict)) {
            
            WrappedStack stack = new WrappedStack(object);
            
            if (emcRegistry.stackMappings.containsKey(new WrappedStack(stack.getWrappedStack()))) {
                return emcRegistry.stackMappings.get(new WrappedStack(stack.getWrappedStack()));
            }
            else {
                
                if (stack.getWrappedStack() instanceof ItemStack) {
                    
                    ItemStack wrappedItemStack = (ItemStack) stack.getWrappedStack();
                    EmcValue lowestValue = null;
                    
                    if (OreDictionary.getOreID(wrappedItemStack) != -1) {
                        
                        OreStack oreStack = new OreStack(wrappedItemStack);
                        
                        if (emcRegistry.stackMappings.containsKey(new WrappedStack(oreStack))) {
                            return emcRegistry.stackMappings.get(new WrappedStack(oreStack));
                        }
                        else {
                            
                            for (ItemStack itemStack : OreDictionary.getOres(OreDictionary.getOreID(wrappedItemStack))) {
                                
                                if (emcRegistry.stackMappings.containsKey(new WrappedStack(itemStack))) {
                                    if (lowestValue == null) {
                                        lowestValue = emcRegistry.stackMappings.get(new WrappedStack(itemStack));
                                    }
                                    else {
                                        EmcValue itemValue = emcRegistry.stackMappings.get(new WrappedStack(itemStack));
                                        
                                        if (itemValue.compareTo(lowestValue) < 0) {
                                            lowestValue = itemValue;
                                        }
                                    }
                                }
                            }
                            
                            return lowestValue;
                        }
                    }
                    else {
                        
                        for (WrappedStack valuedStack : emcRegistry.stackMappings.keySet()) {
                            
                            EmcValue stackValue = emcRegistry.stackMappings.get(valuedStack);
                            
                            if (valuedStack.getWrappedStack() instanceof ItemStack) {
                                
                                ItemStack valuedItemStack = (ItemStack) valuedStack.getWrappedStack();
                                
                                if ((valuedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || wrappedItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) && valuedItemStack.itemID == wrappedItemStack.itemID) {
                                    
                                    if (stackValue.compareTo(lowestValue) < 0) {
                                        lowestValue = stackValue;
                                    }
                                }
                            }
                        }
                        
                        return lowestValue;
                    }
                }
            }
        }

        return null;
    }
    
    public static EmcValue getEmcValue(Object object) {
        return getEmcValue(object, false);
    }

    public static List<WrappedStack> getStacksInRange(int start, int finish) {

        return getStacksInRange(new EmcValue(start), new EmcValue(finish));
    }

    public static List<WrappedStack> getStacksInRange(float start, float finish) {

        return getStacksInRange(new EmcValue(start), new EmcValue(finish));
    }

    public static List<WrappedStack> getStacksInRange(EmcValue start, EmcValue finish) {

        lazyInit();

        List<WrappedStack> stacksInRange = new ArrayList<WrappedStack>();

        SortedMap<EmcValue, List<WrappedStack>> tailMap = emcRegistry.valueMappings.tailMap(start);
        SortedMap<EmcValue, List<WrappedStack>> headMap = emcRegistry.valueMappings.headMap(finish);

        SortedMap<EmcValue, List<WrappedStack>> smallerMap;
        SortedMap<EmcValue, List<WrappedStack>> biggerMap;

        if (!tailMap.isEmpty() && !headMap.isEmpty()) {

            if (tailMap.size() <= headMap.size()) {
                smallerMap = tailMap;
                biggerMap = headMap;
            }
            else {
                smallerMap = headMap;
                biggerMap = tailMap;
            }

            for (EmcValue value : smallerMap.keySet()) {
                if (biggerMap.containsKey(value)) {
                    stacksInRange.addAll(emcRegistry.valueMappings.get(value));
                }
            }
        }

        return stacksInRange;
    }

    public static void printStackValueMappings() {

        lazyInit();
        
        for (WrappedStack stack : emcRegistry.stackMappings.keySet()) {
            LogHelper.debug("Stack: " + stack + ", Value: " + emcRegistry.stackMappings.get(stack));
        }
    }
    
    public static void printUnmappedStacks() {
        
        lazyInit();
        List<WrappedStack> discoveredStacks = new ArrayList<WrappedStack>(RecipeRegistry.getDiscoveredStacks());
        Collections.sort(discoveredStacks);
        for (WrappedStack stack : discoveredStacks) {
            if (!hasEmcValue(stack)) {
                if (RecipeRegistry.getRecipeMappings().get(stack).size() > 0) {
                    for (List<WrappedStack> recipeInputs : RecipeRegistry.getRecipeMappings().get(stack)) {
                        LogHelper.debug(stack + ": " + RecipeHelper.collateInputStacks(recipeInputs));
                    }
                }
                else {
                    LogHelper.debug(stack);
                }
            }
        }
    }
    
    public static void printUnmappedCompoundStacks() {
        
        lazyInit();
        
        List<WrappedStack> unmappedStacks = new ArrayList<WrappedStack>();
        
        for (WrappedStack recipeOutput : RecipeRegistry.getRecipeMappings().keySet()) {
            
            WrappedStack unitStack = new WrappedStack(recipeOutput.getWrappedStack());
            
            if (!hasEmcValue(unitStack)) {
                unmappedStacks.add(recipeOutput);
            }
        }
        
        Collections.sort(unmappedStacks);
        
        for (WrappedStack recipeOutput : unmappedStacks) {
            
            for (List<WrappedStack> recipeInputs : RecipeRegistry.getRecipeMappings().get(recipeOutput)) {
                LogHelper.debug(String.format("Recipe Output: %s, Recipe Inputs: %s", recipeOutput, RecipeHelper.collateInputStacks(recipeInputs)));
            }
        }
        
    }
    
}
