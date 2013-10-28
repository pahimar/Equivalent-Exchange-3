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
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.item.crafting.RecipeRegistry;

public class EmcRegistry {

    private int MAX_ATTEMPTED_ASSIGNMENT_PASSES = 16;
    
    private static EmcRegistry emcRegistry = null;

    private ImmutableSortedMap<CustomWrappedStack, EmcValue> stackMappings;
    private ImmutableSortedMap<EmcValue, List<CustomWrappedStack>> valueMappings;

    public static void lazyInit() {

        if (emcRegistry == null) {
            emcRegistry = new EmcRegistry();
            emcRegistry.init();
        }
    }

    private void init() {

        ImmutableSortedMap.Builder<CustomWrappedStack, EmcValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
        ImmutableSortedMap.Builder<EmcValue, List<CustomWrappedStack>> valueMappingsBuilder = ImmutableSortedMap.naturalOrder();

        Map<CustomWrappedStack, EmcValue> defaultValues = EmcDefaultValues.getDefaultValueMap();

        // Handle the stack mappings
        stackMappingsBuilder.putAll(defaultValues);
        stackMappings = stackMappingsBuilder.build();
        
        // Attempt auto-assignment
        int passNumber = 0;
        Map<CustomWrappedStack, EmcValue> computedStackValues = computeStackMappings();

        while ((computedStackValues.size() > 0) && (passNumber < MAX_ATTEMPTED_ASSIGNMENT_PASSES)) {
            
            passNumber++;
            computedStackValues = computeStackMappings();
            
            stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(stackMappings);                
            stackMappingsBuilder.putAll(computedStackValues);
            stackMappings = stackMappingsBuilder.build();
        }
        
        // Handle the value mappings
        SortedMap<EmcValue, ArrayList<CustomWrappedStack>> tempValueMappings = new TreeMap<EmcValue, ArrayList<CustomWrappedStack>>();

        for (CustomWrappedStack stack : defaultValues.keySet()) {
            EmcValue value = defaultValues.get(stack);

            if (tempValueMappings.containsKey(value)) {
                if (!(tempValueMappings.get(value).contains(stack))) {
                    tempValueMappings.get(value).add(stack);
                }
            }
            else {
                tempValueMappings.put(value, new ArrayList<CustomWrappedStack>(Arrays.asList(stack)));
            }
        }
        
        valueMappingsBuilder.putAll(tempValueMappings);
        valueMappings = valueMappingsBuilder.build();
    }
    
    private static Map<CustomWrappedStack, EmcValue> computeStackMappings() {
        
        Map<CustomWrappedStack, EmcValue> computedStackMap = new HashMap<CustomWrappedStack, EmcValue>();
        
        for (CustomWrappedStack recipeOutput : RecipeRegistry.getRecipeMappings().keySet()) {
            
            if (!hasEmcValue(recipeOutput.getWrappedStack(), false) && !computedStackMap.containsKey(recipeOutput.getWrappedStack())) {
                
                EmcValue lowestValue = null;
                
                for (List<CustomWrappedStack> recipeInputs : RecipeRegistry.getRecipeMappings().get(recipeOutput)) {
                    
                    EmcValue computedValue = EmcHelper.computeEmcValueFromList(recipeInputs);
                    computedValue = EmcHelper.factorEmcValue(computedValue, recipeOutput.getStackSize());
                    
                    if (computedValue != null) {
                        if (computedValue.compareTo(lowestValue) < 0) {
                            lowestValue = computedValue;
                        }
                    }
                }
                
                if ((lowestValue != null) && (lowestValue.getValue() > 0f)) {
                    computedStackMap.put(new CustomWrappedStack(recipeOutput.getWrappedStack()), lowestValue);
                }
            }
        }
        
        return computedStackMap;
    }

    public static boolean hasEmcValue(Object object, boolean strict) {

        lazyInit();

        if (CustomWrappedStack.canBeWrapped(object)) {
            
            CustomWrappedStack stack = new CustomWrappedStack(object);
            
            if (emcRegistry.stackMappings.containsKey(new CustomWrappedStack(stack.getWrappedStack()))) {
                return emcRegistry.stackMappings.containsKey(new CustomWrappedStack(stack.getWrappedStack()));
            }
            else {
                
                if (!strict) {
                    if (stack.getWrappedStack() instanceof ItemStack) {                  
                        
                        ItemStack wrappedItemStack = (ItemStack) stack.getWrappedStack();
                        
                        // If its an OreDictionary item, scan its siblings for values
                        if (OreDictionary.getOreID(wrappedItemStack) != -1) {
                            
                            OreStack oreStack = new OreStack(wrappedItemStack);
                            
                            if (emcRegistry.stackMappings.containsKey(new CustomWrappedStack(oreStack))) {
                                return emcRegistry.stackMappings.containsKey(new CustomWrappedStack(oreStack));
                            }
                            else {
                                for (ItemStack itemStack : OreDictionary.getOres(OreDictionary.getOreID(wrappedItemStack))) {
                                    if (emcRegistry.stackMappings.containsKey(new CustomWrappedStack(itemStack))) {
                                        return emcRegistry.stackMappings.containsKey(new CustomWrappedStack(itemStack));
                                    }
                                }
                            }
                        }
                        // Else, scan for if there is a wildcard value for it
                        else {
                            
                            for (CustomWrappedStack valuedStack : emcRegistry.stackMappings.keySet()) {
                                
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
            
            CustomWrappedStack stack = new CustomWrappedStack(object);
            
            if (emcRegistry.stackMappings.containsKey(new CustomWrappedStack(stack.getWrappedStack()))) {
                return emcRegistry.stackMappings.get(new CustomWrappedStack(stack.getWrappedStack()));
            }
            else {
                
                if (stack.getWrappedStack() instanceof ItemStack) {
                    
                    ItemStack wrappedItemStack = (ItemStack) stack.getWrappedStack();
                    EmcValue lowestValue = null;
                    
                    if (OreDictionary.getOreID(wrappedItemStack) != -1) {
                        
                        OreStack oreStack = new OreStack(wrappedItemStack);
                        
                        if (emcRegistry.stackMappings.containsKey(new CustomWrappedStack(oreStack))) {
                            return emcRegistry.stackMappings.get(new CustomWrappedStack(oreStack));
                        }
                        else {
                            
                            for (ItemStack itemStack : OreDictionary.getOres(OreDictionary.getOreID(wrappedItemStack))) {
                                
                                if (emcRegistry.stackMappings.containsKey(new CustomWrappedStack(itemStack))) {
                                    if (lowestValue == null) {
                                        lowestValue = emcRegistry.stackMappings.get(new CustomWrappedStack(itemStack));
                                    }
                                    else {
                                        EmcValue itemValue = emcRegistry.stackMappings.get(new CustomWrappedStack(itemStack));
                                        
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
                        
                        for (CustomWrappedStack valuedStack : emcRegistry.stackMappings.keySet()) {
                            
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

    public static List<CustomWrappedStack> getStacksInRange(int start, int finish) {

        return getStacksInRange(new EmcValue(start), new EmcValue(finish));
    }

    public static List<CustomWrappedStack> getStacksInRange(float start, float finish) {

        return getStacksInRange(new EmcValue(start), new EmcValue(finish));
    }

    public static List<CustomWrappedStack> getStacksInRange(EmcValue start, EmcValue finish) {

        lazyInit();

        List<CustomWrappedStack> stacksInRange = new ArrayList<CustomWrappedStack>();

        SortedMap<EmcValue, List<CustomWrappedStack>> tailMap = emcRegistry.valueMappings.tailMap(start);
        SortedMap<EmcValue, List<CustomWrappedStack>> headMap = emcRegistry.valueMappings.headMap(finish);

        SortedMap<EmcValue, List<CustomWrappedStack>> smallerMap;
        SortedMap<EmcValue, List<CustomWrappedStack>> biggerMap;

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
        
        for (CustomWrappedStack stack : emcRegistry.stackMappings.keySet()) {
            LogHelper.debug("Stack: " + stack + ", Value: " + emcRegistry.stackMappings.get(stack));
        }
    }
    
    public static void printUnmappedStacks() {
        
        lazyInit();
        List<CustomWrappedStack> discoveredStacks = new ArrayList<CustomWrappedStack>(RecipeRegistry.getDiscoveredStacks());
        Collections.sort(discoveredStacks);
        for (CustomWrappedStack stack : discoveredStacks) {
            if (!hasEmcValue(stack)) {
                if (RecipeRegistry.getRecipeMappings().get(stack).size() > 0) {
                    for (List<CustomWrappedStack> recipeInputs : RecipeRegistry.getRecipeMappings().get(stack)) {
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
        
        List<CustomWrappedStack> unmappedStacks = new ArrayList<CustomWrappedStack>();
        
        for (CustomWrappedStack recipeOutput : RecipeRegistry.getRecipeMappings().keySet()) {
            
            CustomWrappedStack unitStack = new CustomWrappedStack(recipeOutput.getWrappedStack());
            
            if (!hasEmcValue(unitStack)) {
                unmappedStacks.add(recipeOutput);
            }
        }
        
        Collections.sort(unmappedStacks);
        
        for (CustomWrappedStack recipeOutput : unmappedStacks) {
            
            for (List<CustomWrappedStack> recipeInputs : RecipeRegistry.getRecipeMappings().get(recipeOutput)) {
                LogHelper.debug(String.format("Recipe Output: %s, Recipe Inputs: %s", recipeOutput, RecipeHelper.collateInputStacks(recipeInputs)));
            }
        }
        
    }
    
}
