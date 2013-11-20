package com.pahimar.ee3.api;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pahimar.ee3.emc.EmcValue;

public class EmcValueSerializer implements JsonDeserializer<EmcValue>, JsonSerializer<EmcValue> {

    @Override
    public JsonElement serialize(EmcValue emcValue, Type type, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();
        return null;
    }

    @Override
    public EmcValue deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        // TODO Auto-generated method stub
        return null;
    }

}
