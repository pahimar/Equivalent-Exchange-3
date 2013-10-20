package com.pahimar.ee3.core.helper;

import java.util.ArrayList;
import java.util.List;

import com.pahimar.ee3.emc.EmcRegistry;
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
} 
