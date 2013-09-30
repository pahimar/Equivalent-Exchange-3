package com.pahimar.ee3.emc;

import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcRegistry {

    private static EmcRegistry emcRegistry = null;
    
    private ImmutableMap<CustomWrappedStack, EmcValue> stackMappings;
    private ImmutableSortedMap<EmcValue, List<CustomWrappedStack>> valueMappings;

    private EmcRegistry() {

    }

    public static EmcRegistry getInstance() {

        if (emcRegistry == null) {
            emcRegistry = new EmcRegistry();
            emcRegistry.init();
        }

        return emcRegistry;
    }

    private void init() {

        // Grab the default value map
        ImmutableMap.Builder<CustomWrappedStack, EmcValue> stackMappingsBuilder = ImmutableMap.builder();
        stackMappingsBuilder.putAll(EmcDefaultValues.getInstance().getDefaultValueMap());
        stackMappings = stackMappingsBuilder.build();
    }
    
    public boolean hasEmcValue(CustomWrappedStack wrappedStack) {
        
        return stackMappings.containsKey(wrappedStack) ? stackMappings.get(wrappedStack) instanceof EmcValue : false;
    }
    
    public boolean hasStacksForValue(EmcValue emcValue) {
        
        return valueMappings.containsKey(emcValue) ? valueMappings.get(emcValue).size() > 0 : false;
    }
    
    public EmcValue getEmcValue(CustomWrappedStack wrappedStack) {
        
        return stackMappings.get(wrappedStack);
    }
    
    public List<CustomWrappedStack> getStacksFromValue(EmcValue emcValue) {
        
        return valueMappings.get(emcValue);
    }
    
    public List<CustomWrappedStack> getStacksInEmcRange(EmcValue fromValue, EmcValue toValue) {
        
        return null;
    }
    
    public void addEmcValueMapping(CustomWrappedStack wrappedStack, EmcValue emcValue) {
        
        
    }
    
    private void printDebugDump() {
        
    }
}
