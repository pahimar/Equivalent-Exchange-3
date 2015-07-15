package com.pahimar.ee3.serialization;

import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.EnergyValueStackMapping;
import com.pahimar.ee3.exchange.WrappedStack;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class EnergyValueRegistrySerializer implements JsonSerializer<EnergyValueRegistry>
{
    public static String toJson(EnergyValueRegistry registry)
    {
        return JsonSerialization.jsonSerializer.toJson(registry, EnergyValueRegistry.class);
    }

    @Override
    public JsonElement serialize(EnergyValueRegistry energyValueRegistry, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonArray jsonEnergyValueRegistry = new JsonArray();

        Map<WrappedStack, EnergyValue> stackMapping = energyValueRegistry.getStackValueMap();
        for (WrappedStack wrappedStack : stackMapping.keySet())
        {
            EnergyValue value = stackMapping.get(wrappedStack);
            EnergyValueStackMapping mapping = new EnergyValueStackMapping(wrappedStack, value);
            jsonEnergyValueRegistry.add(JsonSerialization.jsonSerializer.toJsonTree(mapping));
        }

        return jsonEnergyValueRegistry;
    }
}