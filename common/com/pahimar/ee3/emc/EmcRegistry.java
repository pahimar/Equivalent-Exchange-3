package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.common.collect.ImmutableSortedMap;
import com.pahimar.ee3.item.CustomWrappedStack;

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
        SortedMap<EmcValue, List<CustomWrappedStack>> tempValueMappings = new TreeMap<EmcValue, List<CustomWrappedStack>>();

        for (CustomWrappedStack stack : defaultValues.keySet()) {
            EmcValue value = defaultValues.get(stack);

            if (tempValueMappings.containsKey(value)) {
                if (!(tempValueMappings.get(value).contains(stack))) {
                    tempValueMappings.get(value).add(stack);
                }
            }
            else {
                tempValueMappings.put(value, Arrays.asList(stack));
            }
        }

        valueMappingsBuilder.putAll(tempValueMappings);
        valueMappings = valueMappingsBuilder.build();
    }

    public static boolean hasEmcValue(CustomWrappedStack stack) {

        lazyInit();
        return emcRegistry.stackMappings.containsKey(new CustomWrappedStack(stack.getWrappedStack()));
    }

    public static EmcValue getEmcValue(CustomWrappedStack stack) {

        lazyInit();
        return emcRegistry.stackMappings.get(new CustomWrappedStack(stack.getWrappedStack()));
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
}
