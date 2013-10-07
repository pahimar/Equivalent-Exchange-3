package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcRegistry {

    private static EmcRegistry emcRegistry = null;

    private Map<CustomWrappedStack, EmcValue> stackMappings;
    private TreeMap<EmcValue, List<CustomWrappedStack>> valueMappings;

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
        
        stackMappings.putAll(defaultValueMap);
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
    
    public void printDebugDump() {
        
        List<CustomWrappedStack> keyList = new ArrayList<CustomWrappedStack>();
        keyList.addAll(stackMappings.keySet());
        Collections.sort(keyList);
        
        for (CustomWrappedStack stack : keyList) {
            LogHelper.debug(stack + " == " + stackMappings.get(stack));
        }
    }
}
