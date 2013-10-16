package com.pahimar.ee3.emc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcRegistry {

    private static EmcRegistry emcRegistry = null;

    private Map<CustomWrappedStack, EmcValue> stackMappings;
    private SortedMap<EmcValue, List<CustomWrappedStack>> valueMappings;

    private EmcRegistry() {

        stackMappings = new HashMap<CustomWrappedStack, EmcValue>();
        valueMappings = new TreeMap<EmcValue, List<CustomWrappedStack>>();
    }

    private static void lazyInit() {

        if (emcRegistry == null) {
            emcRegistry = new EmcRegistry();
            emcRegistry.init();
        }
    }

    private void init() {
        
        stackMappings.putAll(EmcDefaultValues.getInstance().getDefaultValueMap());
    }
    
    public static boolean hasEmcValue(CustomWrappedStack stack) {
        
        lazyInit();
        return emcRegistry.stackMappings.containsKey(new CustomWrappedStack(stack.getWrappedStack()));
    }
    
    public static EmcValue getEmcValue(CustomWrappedStack stack) {
        
        lazyInit();
        return emcRegistry.stackMappings.get(new CustomWrappedStack(stack.getWrappedStack()));
    }
}
