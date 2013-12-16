package com.pahimar.ee3.api;

import com.google.gson.*;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.WrappedStack;

import java.lang.reflect.Type;

public class StackValueMapping implements JsonSerializer<StackValueMapping>, JsonDeserializer<StackValueMapping>
{

    private static final Gson gsonSerializer = (new GsonBuilder()).registerTypeAdapter(StackValueMapping.class, new StackValueMapping()).create();

    public final WrappedStack wrappedStack;
    public final EmcValue emcValue;

    public StackValueMapping()
    {
        wrappedStack = null;
        emcValue = null;
    }

    public StackValueMapping(Object object, EmcValue emcValue)
    {
        this.wrappedStack = new WrappedStack(object);
        this.emcValue = emcValue;
    }

    public static StackValueMapping createFromJson(String jsonStackValueMapping)
    {

        try
        {
            return gsonSerializer.fromJson(jsonStackValueMapping, StackValueMapping.class);
        }
        catch (JsonSyntaxException exception)
        {
            // TODO Log something regarding the failed parse
        }

        return null;
    }

    public String toJson()
    {
        return gsonSerializer.toJson(this);
    }

    /* (non-Javadoc)
     * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
     */
    @Override
    public JsonElement serialize(StackValueMapping stackValueMapping, Type type, JsonSerializationContext context)
    {

        JsonObject jsonStackValueMapping = new JsonObject();

        Gson gsonWrappedStack = (new GsonBuilder()).registerTypeAdapter(WrappedStack.class, new WrappedStack()).create();
        Gson gsonEmcValue = (new GsonBuilder()).registerTypeAdapter(EmcValue.class, new EmcValue()).create();

        jsonStackValueMapping.add("wrappedStack", gsonWrappedStack.toJsonTree(stackValueMapping.wrappedStack));
        jsonStackValueMapping.add("emcValue", gsonEmcValue.toJsonTree(stackValueMapping.emcValue));

        return jsonStackValueMapping;
    }

    /* (non-Javadoc)
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    @Override
    public StackValueMapping deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException
    {

        if (!jsonElement.isJsonPrimitive())
        {

            JsonObject jsonStackValueMapping = (JsonObject) jsonElement;

            WrappedStack wrappedStack = null;
            EmcValue emcValue = null;

            if (jsonStackValueMapping.get("wrappedStack") != null)
            {
                wrappedStack = new WrappedStack().deserialize(jsonStackValueMapping.get("wrappedStack").getAsJsonObject(), type, context);
            }

            if (jsonStackValueMapping.get("emcValue") != null)
            {
                emcValue = new EmcValue().deserialize(jsonStackValueMapping.get("emcValue").getAsJsonObject(), type, context);
            }

            if (wrappedStack instanceof WrappedStack && emcValue instanceof EmcValue)
            {
                return new StackValueMapping(wrappedStack, emcValue);
            }
            else
            {
                return null;
            }
        }

        return null;
    }
}
