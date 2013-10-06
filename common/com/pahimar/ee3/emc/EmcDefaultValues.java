package com.pahimar.ee3.emc;

import java.util.HashMap;
import java.util.Map;

import com.pahimar.ee3.core.util.EnergyStack;
import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcDefaultValues {
    
    private static EmcDefaultValues defaultValues = null;
    private Map<CustomWrappedStack, EmcValue> defaultValueMap;

    private EmcDefaultValues() {
        
        defaultValueMap = new HashMap<CustomWrappedStack, EmcValue>();
    }
    
    public static EmcDefaultValues getInstance() {
        
        if (defaultValues == null) {
            defaultValues = new EmcDefaultValues();
            defaultValues.init();
        }
        
        return defaultValues;
    }
    
    private void init() {
        
        defaultValueMap.put(
            new CustomWrappedStack(new EnergyStack(EnergyStack.VANILLA_SMELTING_ENERGY_NAME, 1)), 
            new EmcValue(1, EmcComponent.KINETIC_UNIT_COMPONENT)
        );
    }
    
    public Map<CustomWrappedStack, EmcValue> getDefaultValueMap() {
        
        return defaultValueMap;
    }
}
