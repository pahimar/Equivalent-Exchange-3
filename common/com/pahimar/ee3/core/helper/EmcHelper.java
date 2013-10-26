package com.pahimar.ee3.core.helper;

import java.util.ArrayList;
import java.util.List;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcType;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcHelper {

    public static List<CustomWrappedStack> filterStacksByEmc(List<CustomWrappedStack> unfilteredStacks, EmcValue filterValue) {

        List<CustomWrappedStack> filteredStacks = new ArrayList<CustomWrappedStack>();
        
        for (CustomWrappedStack stack : unfilteredStacks) {
            
            if (EmcRegistry.hasEmcValue(stack)) {
                
                EmcValue value = EmcRegistry.getEmcValue(stack);
                boolean satisfiesFilter = true;
                float[] valueSubValues = value.components;
                float[] filterValueSubValues = filterValue.components;
                
                for (int i = 0; i < valueSubValues.length; i++) {
                    if (Float.compare(valueSubValues[i], filterValueSubValues[i]) < 0) {
                        satisfiesFilter = false;
                    }
                }
                
                if (satisfiesFilter) {
                    filteredStacks.add(stack);
                }
            }
        }
        
        return filteredStacks;
    }
    
    public static List<CustomWrappedStack> filterStacksByEmcAndRange(float start, float end, EmcValue filterValue) {
        return filterStacksByEmc(EmcRegistry.getStacksInRange(start, end), filterValue);
    }
    
    public static EmcValue computeEmcValueFromList(List<CustomWrappedStack> wrappedStacks) {
        
        float[] computedSubValues = new float[EmcType.TYPES.length];
        
        for (CustomWrappedStack wrappedStack : wrappedStacks) {
            
            EmcValue wrappedStackValue = EmcRegistry.getEmcValue(wrappedStack);
            
            if (wrappedStackValue != null) {
                for (EmcType emcType : EmcType.TYPES) {
                    computedSubValues[emcType.ordinal()] += wrappedStackValue.components[emcType.ordinal()] * wrappedStack.getStackSize();
                }
            }
            else {
                return null;
            }
        }
        
        return new EmcValue(computedSubValues);
    }
    
    public static EmcValue factorEmcValue(EmcValue emcValue, int factor) {
        return factorEmcValue(emcValue, (float) factor);
    }
    
    public static EmcValue factorEmcValue(EmcValue emcValue, float factor) {
        
        if ((Float.compare(factor, 0f) != 0) && (emcValue instanceof EmcValue)) {
            float[] factorSubValues = emcValue.components;
            
            for (int i = 0; i < factorSubValues.length; i++) {
                factorSubValues[i] = factorSubValues[i] * 1f / factor; 
            }
            
            return new EmcValue(factorSubValues);
        }
        else {
            return emcValue;
        }
    }
} 
