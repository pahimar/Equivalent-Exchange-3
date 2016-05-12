package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import com.pahimar.ee3.exchange.OreStack;

import java.lang.reflect.Type;

public class OreStackSerializer implements JsonSerializer<OreStack>, JsonDeserializer<OreStack> {

    // TODO String constants for property names

    @Override
    public OreStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {
            JsonObject jsonObject = (JsonObject) json;

            try {
                if (jsonObject.get("oreName").getAsJsonPrimitive().isString()) {
                    String oreName = jsonObject.get("oreName").getAsString();
                    return new OreStack(oreName);
                }
            }
            catch (IllegalStateException exception) {
                // TODO We could probably log here that an invalid piece of data was found
            }
        }

        return null;
    }

    @Override
    public JsonElement serialize(OreStack src, Type typeOfSrc, JsonSerializationContext context) {

        if (src != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("oreName", src.oreName);
            return jsonObject;
        }

        return null;
    }
}
