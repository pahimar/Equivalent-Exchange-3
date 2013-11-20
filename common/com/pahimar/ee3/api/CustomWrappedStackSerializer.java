package com.pahimar.ee3.api;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pahimar.ee3.item.CustomWrappedStack;

public class CustomWrappedStackSerializer implements JsonDeserializer<CustomWrappedStack>, JsonSerializer<CustomWrappedStack> {

    @Override
    public JsonElement serialize(CustomWrappedStack customWrappedStack, Type type, JsonSerializationContext context) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CustomWrappedStack deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        // TODO Auto-generated method stub
        return null;
    }

}
