package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import com.pahimar.ee3.exchange.OreStack;

import java.lang.reflect.Type;

public class OreStackSerializer implements JsonSerializer<OreStack>, JsonDeserializer<OreStack> {

    private static final String NAME = "name";

    @Override
    public OreStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {

            JsonObject jsonObject = (JsonObject) json;

            if (jsonObject.has(NAME) && jsonObject.get(NAME).isJsonPrimitive()) {
                String name = jsonObject.getAsJsonPrimitive(NAME).getAsString();
                return new OreStack(name);
            }
        }

        return null;
    }

    @Override
    public JsonElement serialize(OreStack src, Type typeOfSrc, JsonSerializationContext context) {

        if (src != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(NAME, src.oreName);
            return jsonObject;
        }

        return null;
    }
}
