package com.pahimar.ee3.serialization;

/*import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.EnergyValueStackMapping;
import com.pahimar.ee3.exchange.WrappedStack;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class EnergyValueRegistrySerializer implements JsonSerializer<EnergyValueRegistry>, JsonDeserializer<EnergyValueRegistry>
{
    @Override
    public EnergyValueRegistry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (json.isJsonArray())
        {

            JsonArray jsonArray = (JsonArray) json;
            Map<WrappedStack, EnergyValue> stackValueMap = new TreeMap<WrappedStack, EnergyValue>();
            Iterator<JsonElement> iterator = jsonArray.iterator();

            while (iterator.hasNext())
            {
                JsonElement jsonElement = iterator.next();
                EnergyValueStackMapping energyValueStackMapping = new EnergyValueStackMapping().deserialize(jsonElement, typeOfT, context);

                if (energyValueStackMapping != null)
                    stackValueMap.put(energyValueStackMapping.wrappedStack, energyValueStackMapping.energyValue);
            }

            ImmutableSortedMap.Builder<WrappedStack, EnergyValue> stackMappingsBuilder = ImmutableSortedMap.naturalOrder();
            stackMappingsBuilder.putAll(stackValueMap);
            stackMappings = stackMappingsBuilder.build();

            generateValueStackMappings();
        }

        return null;
    }

    @Override
    public JsonElement serialize(EnergyValueRegistry energyValueRegistry, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonArray jsonEnergyValueRegistry = new JsonArray();

        for (WrappedStack wrappedStack : energyValueRegistry.stackMappings.keySet())
            jsonEnergyValueRegistry.add(EnergyValueStackMapping.jsonSerializer.toJsonTree(new EnergyValueStackMapping(wrappedStack, energyValueRegistry.stackMappings.get(wrappedStack))));

        return jsonEnergyValueRegistry;
    }
}
*/