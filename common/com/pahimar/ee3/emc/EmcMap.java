package com.pahimar.ee3.emc;

import java.util.HashMap;

import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcMap {

    private static EmcMap emcMap = null;

    private HashMap<CustomWrappedStack, EmcValue> emcMappings;

    private EmcMap() {

        emcMappings = new HashMap<CustomWrappedStack, EmcValue>();
    }

    public static EmcMap getInstance() {

        if (emcMap == null) {
            emcMap = new EmcMap();
        }

        return emcMap;
    }

    public EmcValue getEmcValue(Object object) {

        EmcValue emcValue = null;

        if (CustomWrappedStack.canBeWrapped(object)) {
            return emcMappings.get(new CustomWrappedStack(object));
        }

        return emcValue;
    }
}
