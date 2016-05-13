package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.NewEnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;

import java.lang.reflect.Type;
import java.util.Map;

public class NewEnergyValueRegistrySerializer implements JsonSerializer<NewEnergyValueRegistry>, JsonDeserializer<NewEnergyValueRegistry> {

    private static final String PRE_CALCULATION_ASSIGNMENTS = "pre_calculation_assignments";
    private static final String POST_CALCULATION_ASSIGNMENTS = "post_calculation_assignments";
    private static final String ENERGY_VALUE = "energyValue";

    @Override
    public NewEnergyValueRegistry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // TODO This
        return null;
    }

    @Override
    public JsonElement serialize(NewEnergyValueRegistry src, Type typeOfSrc, JsonSerializationContext context) {

        if (src != null) {
            JsonObject registryObject = new JsonObject();

            registryObject.add(PRE_CALCULATION_ASSIGNMENTS, test(src.preCalculationMappings, context));
            registryObject.add(POST_CALCULATION_ASSIGNMENTS, test(src.postCalculationMappings, context));

            return registryObject;
        }

        return null;
    }

    private static JsonArray test(Map<WrappedStack, EnergyValue> valueMap, JsonSerializationContext context) {

        JsonArray jsonArray = new JsonArray();

        for (WrappedStack wrappedStack : valueMap.keySet()) {
            JsonObject jsonMapping = new JsonObject();
            jsonMapping.add(wrappedStack.getWrappedObject().getClass().getSimpleName().toLowerCase(), context.serialize(wrappedStack.getWrappedObject()));
            jsonMapping.addProperty(ENERGY_VALUE, valueMap.get(wrappedStack).getValue());
            jsonArray.add(jsonMapping);
        }

        return jsonArray;
    }
}
