package com.pahimar.ee3.emc;

import java.util.Map;
import java.util.TreeMap;

import com.pahimar.ee3.item.CustomWrappedStack;

/**
 * Equivalent-Exchange-3
 * 
 * EmcIMCValues
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EmcValuesIMC {

    private static Map<CustomWrappedStack, EmcValue> preAssignedValueMap = new TreeMap<CustomWrappedStack, EmcValue>();
    private static Map<CustomWrappedStack, EmcValue> postAssignedValueMap = new TreeMap<CustomWrappedStack, EmcValue>();
    
    public static Map<CustomWrappedStack, EmcValue> getPreAssignedValues() {
        return preAssignedValueMap;
    }
    
    public static Map<CustomWrappedStack, EmcValue> getPostAssignedValues() {
        return postAssignedValueMap;
    }
    
    public static void addPreAssignedValued(CustomWrappedStack wrappedStack, EmcValue emcValue) {

        if (!preAssignedValueMap.containsKey(wrappedStack)) {
            preAssignedValueMap.put(wrappedStack, emcValue);
        }
    }

    public static void addPostAssignedValued(CustomWrappedStack wrappedStack, EmcValue emcValue) {

        if (!postAssignedValueMap.containsKey(wrappedStack)) {
            postAssignedValueMap.put(wrappedStack, emcValue);
        }
    }
}
