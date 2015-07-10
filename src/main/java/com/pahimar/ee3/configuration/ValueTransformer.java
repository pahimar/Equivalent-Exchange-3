package com.pahimar.ee3.configuration;

import com.ibm.icu.impl.IllegalIcuArgumentException;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ValueTransformer<T>
{
    private final T fallbackValue;
    private final boolean useFallback;
    private final Map<String, T> transformations;

    public ValueTransformer()
    {
        this(false, null);
    }

    public ValueTransformer(T fallback)
    {
        this(true, fallback);
    }

    private ValueTransformer(boolean useFallback, T fallbackValue) {
        this.useFallback = useFallback;
        this.fallbackValue = fallbackValue;
        this.transformations = new TreeMap<String, T>();
    }

    public final ValueTransformer<T> addTransform(String key, T value)
    {
        if(key == null || key.length() == 0)
            return this;

        key = key.toLowerCase();
        if(this.transformations.containsKey(key))
            return this;

        this.transformations.put(key, value);
        return this;
    }

    public final T getValue(String key)
            throws IllegalIcuArgumentException
    {
        key = key.toLowerCase();
        if(this.transformations.containsKey(key))
            return this.transformations.get(key);

        if(useFallback)
            return fallbackValue;

        throw new IllegalIcuArgumentException("Could not find transform for key");
    }

    public final String[] getKeys()
    {
        Set<String> keySet = this.transformations.keySet();
        return keySet.toArray(new String[keySet.size()]);
    }

    public final T getFallbackValue()
    {
        return this.fallbackValue;
    }
}
