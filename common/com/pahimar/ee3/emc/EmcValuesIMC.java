package com.pahimar.ee3.emc;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
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

    private static Multimap<CustomWrappedStack, EmcValue> preAssignedValueMap = HashMultimap.create();
    private static Multimap<CustomWrappedStack, EmcValue> postAssignedValueMap = HashMultimap.create();
    
    public static Multimap<CustomWrappedStack, EmcValue> getPreAssignedValues() {
        return preAssignedValueMap;
    }
    
    public static Multimap<CustomWrappedStack, EmcValue> getPostAssignedValues() {
        return postAssignedValueMap;
    }
    
    public static void addPreAssignedValued(CustomWrappedStack wrappedStack, EmcValue emcValue) {
        
    }

    public static void addPostAssignedValued(CustomWrappedStack wrappedStack, EmcValue emcValue) {
        
    }
}
