package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class EnergyValueMapSerializer implements JsonSerializer<Map<WrappedStack, EnergyValue>>, JsonDeserializer<Map<WrappedStack, EnergyValue>> {

    private static final String ENERGY_VALUE = "energyValue";
    private static final String TYPE_ITEM_STACK = "itemstack";
    private static final String TYPE_ORE_STACK = "orestack";
    private static final String TYPE_FLUID_STACK = "fluidstack";

    @Override
    public Map<WrappedStack, EnergyValue> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Map<WrappedStack, EnergyValue> valueMap = new TreeMap<>();

        if (json.isJsonArray()) {

            JsonArray jsonArray = json.getAsJsonArray();
            Iterator<JsonElement> jsonArrayIterator = jsonArray.iterator();

            while (jsonArrayIterator.hasNext()) {

                JsonElement jsonElement = jsonArrayIterator.next();
                if (validateValueMapping(jsonElement)) {

                    JsonObject jsonValueMapping = jsonElement.getAsJsonObject();

                    WrappedStack wrappedStack = null;
                    EnergyValue energyValue = null;

                    if (jsonValueMapping.get(ENERGY_VALUE).isJsonPrimitive()) {

                        try {
                            energyValue = new EnergyValue(((JsonPrimitive) jsonValueMapping.get(ENERGY_VALUE)).getAsNumber());
                        }
                        catch (NumberFormatException e) {
                            // TODO Logging
                        }
                    }

                    if (jsonValueMapping.has(TYPE_ITEM_STACK)) {

                        try {
                            ItemStack itemStack = context.deserialize(jsonValueMapping.get(TYPE_ITEM_STACK), ItemStack.class);
                            wrappedStack = WrappedStack.wrap(itemStack);
                        }
                        catch (JsonParseException e) {
                            // TODO Logging
                        }
                    }
                    else if (jsonValueMapping.has(TYPE_ORE_STACK)) {

                        try {
                            OreStack oreStack = context.deserialize(jsonValueMapping.get(TYPE_ORE_STACK), OreStack.class);
                            wrappedStack = WrappedStack.wrap(oreStack);
                        }
                        catch (JsonParseException e) {
                            // TODO Logging
                        }
                    }
                    else if (jsonValueMapping.has(TYPE_FLUID_STACK)) {

                        try {
                            FluidStack fluidStack = context.deserialize(jsonValueMapping.get(TYPE_FLUID_STACK), FluidStack.class);
                            wrappedStack = WrappedStack.wrap(fluidStack);
                        }
                        catch (JsonParseException e) {
                            // TODO Logging
                        }
                    }

                    if (wrappedStack != null && energyValue != null) {
                        valueMap.put(wrappedStack, energyValue);
                    }
                }
            }
        }

        return valueMap;
    }

    @Override
    public JsonElement serialize(Map<WrappedStack, EnergyValue> src, Type typeOfSrc, JsonSerializationContext context) {

        JsonArray jsonArray = new JsonArray();

        if (src != null) {
            src.keySet().stream()
                    .filter(wrappedStack -> wrappedStack != null && wrappedStack.getWrappedObject() != null & src.get(wrappedStack) != null)
                    .forEach(wrappedStack -> {
                JsonObject jsonMapping = new JsonObject();
                jsonMapping.add(wrappedStack.getWrappedObject().getClass().getSimpleName().toLowerCase(), context.serialize(wrappedStack.getWrappedObject()));
                jsonMapping.addProperty(ENERGY_VALUE, src.get(wrappedStack).getValue());
                jsonArray.add(jsonMapping);
            });
        }

        return jsonArray;
    }

    /***
     *  Validates whether or not the provided {@link JsonElement} is a valid json encoded {@link WrappedStack}:{@link EnergyValue} mapping
     *
     *  @param jsonElement the {@link JsonElement} being validated
     *  @return true if jsonElement is a valid json encoded {@link WrappedStack}:{@link EnergyValue} mapping, false otherwise
     */
    private static boolean validateValueMapping(JsonElement jsonElement) {

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.entrySet().size() == 2 && jsonObject.has(ENERGY_VALUE) && jsonObject.get(ENERGY_VALUE).isJsonPrimitive()) {
                return (jsonObject.has(TYPE_ITEM_STACK) && jsonObject.get(TYPE_ITEM_STACK).isJsonObject()) ||
                        (jsonObject.has(TYPE_ORE_STACK) && jsonObject.get(TYPE_ORE_STACK).isJsonObject()) ||
                        (jsonObject.has(TYPE_FLUID_STACK) && jsonObject.get(TYPE_FLUID_STACK).isJsonObject());
            }
        }

        return false;
    }
}
