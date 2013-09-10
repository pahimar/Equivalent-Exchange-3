package com.pahimar.ee3.emc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.block.Block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcRegistry {

    private static EmcRegistry emcRegistry = null;

    private Map<CustomWrappedStack, EmcValue> stackMappings;
    private TreeMap<EmcValue, List<CustomWrappedStack>> valueMappings;
    
    private ImmutableMap<CustomWrappedStack, EmcValue> immutableStackMappings;
    private ImmutableSortedMap<EmcValue, List<CustomWrappedStack>> immutableValueMappings;

    private EmcRegistry() {

        stackMappings = new HashMap<CustomWrappedStack, EmcValue>();
        valueMappings = new TreeMap<EmcValue, List<CustomWrappedStack>>();
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
        Map<CustomWrappedStack, EmcValue> defaultValueMap = EmcDefaultValues.getInstance().getDefaultValueMap();
        
        stackMappings.put(new CustomWrappedStack(Block.cobblestone), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        
        immutableStackMappings = ImmutableMap.copyOf(stackMappings);
        immutableValueMappings = ImmutableSortedMap.copyOf(valueMappings);
        stackMappings.clear();
        valueMappings.clear();
    }
    
    public boolean hasEmcValue(CustomWrappedStack wrappedStack) {
        
        return immutableStackMappings.containsKey(wrappedStack) ? immutableStackMappings.get(wrappedStack) instanceof EmcValue : false;
    }
    
    public boolean hasStacksForValue(EmcValue emcValue) {
        
        return immutableValueMappings.containsKey(emcValue) ? immutableValueMappings.get(emcValue).size() > 0 : false;
    }
    
    public EmcValue getEmcValue(CustomWrappedStack wrappedStack) {
        
        return immutableStackMappings.get(wrappedStack);
    }
    
    public List<CustomWrappedStack> getStacksFromValue(EmcValue emcValue) {
        
        return immutableValueMappings.get(emcValue);
    }
    
    public List<CustomWrappedStack> getStacksInEmcRange(EmcValue fromValue, EmcValue toValue) {
        
        return null;
    }
    
    public void addEmcValueMapping(CustomWrappedStack wrappedStack, EmcValue emcValue) {
        
        
    }
}
