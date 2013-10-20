package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.common.collect.ImmutableSortedMap;
import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.core.helper.RecipeHelper;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.crafting.RecipeRegistry;

public class EmcRegistry {

    private static EmcRegistry emcRegistry = null;

    private ImmutableSortedMap<CustomWrappedStack, EmcValue> stackMappings;
    private ImmutableSortedMap<EmcValue, List<CustomWrappedStack>> valueMappings;

    private static void lazyInit() {

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

    public static boolean hasEmcValue(Object object) {

        lazyInit();

        if (CustomWrappedStack.canBeWrapped(object)) {
            CustomWrappedStack stack = new CustomWrappedStack(object);
            return emcRegistry.stackMappings.containsKey(new CustomWrappedStack(stack.getWrappedStack()));
        }

        return false;
    }

    public static EmcValue getEmcValue(Object object) {

        lazyInit();

        if (CustomWrappedStack.canBeWrapped(object)) {
            CustomWrappedStack stack = new CustomWrappedStack(object);
            return emcRegistry.stackMappings.get(new CustomWrappedStack(stack.getWrappedStack()));
        }

        return null;
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
    
}
