package com.pahimar.ee3.emc;

import com.pahimar.ee3.api.EmcValue;
import com.pahimar.ee3.api.WrappedStack;

import java.util.Map;
import java.util.TreeMap;

/**
 * Equivalent-Exchange-3
 * <p/>
 * EmcIMCValues
 *
 * @author pahimar
 */
public class EmcValuesIMC
{

    private static Map<WrappedStack, EmcValue> preAssignedValueMap = new TreeMap<WrappedStack, EmcValue>();
    private static Map<WrappedStack, EmcValue> postAssignedValueMap = new TreeMap<WrappedStack, EmcValue>();

    public static Map<WrappedStack, EmcValue> getPreAssignedValues()
    {
        return preAssignedValueMap;
    }

    public static Map<WrappedStack, EmcValue> getPostAssignedValues()
    {
        return postAssignedValueMap;
    }

    public static void addPreAssignedValued(WrappedStack wrappedStack, EmcValue emcValue)
    {
        if (wrappedStack != null)
        {
            if (!preAssignedValueMap.containsKey(wrappedStack))
            {
                preAssignedValueMap.put(wrappedStack, emcValue);
            }
            else
            {
                // TODO Log that we already have a value for that
            }
        }
        else
        {
            // TODO Logging
        }
    }

    public static void addPostAssignedValued(WrappedStack wrappedStack, EmcValue emcValue)
    {

        if (wrappedStack != null)
        {
            if (!postAssignedValueMap.containsKey(wrappedStack))
            {
                postAssignedValueMap.put(wrappedStack, emcValue);
            }
            else
            {
                // TODO Log that we already have a value for that
            }
        }
        else
        {
            // TODO Logging
        }
    }
}
