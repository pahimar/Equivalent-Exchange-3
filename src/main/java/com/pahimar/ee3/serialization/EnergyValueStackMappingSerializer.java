package com.pahimar.ee3.serialization;

import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueStackMapping;
import com.pahimar.ee3.exchange.WrappedStack;

import java.lang.reflect.Type;

public class EnergyValueStackMappingSerializer implements JsonSerializer<EnergyValueStackMapping>, JsonDeserializer<EnergyValueStackMapping>
{
    private static final String memberWrappedStack = "wrappedStack";
    private static final String memberEnergyValue = "energyValue";

    public static EnergyValueStackMapping createFromJson(String jsonStackValueMapping)
            throws JsonParseException
    {
        return JsonSerialization.jsonSerializer.fromJson(jsonStackValueMapping, EnergyValueStackMapping.class);
    }

    public static String toJson(EnergyValueStackMapping mapping)
    {
        return JsonSerialization.jsonSerializer.toJson(mapping);
    }

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the
     * specified type.
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link com.google.gson.JsonDeserializationContext#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type)} method to create objects
     * for any non-trivial field of the returned object. However, you should never invoke it on the
     * the same type passing {@code jsonElement} since that will cause an infinite loop (Gson will call your
     * call-back method again).
     *
     * @param jsonElement The Json data being deserialized
     * @param typeOfT     The type of the Object to deserialize to
     * @param context
     * @return a deserialized object of the specified type typeOfT which is a subclass of {@code T}
     * @throws com.google.gson.JsonParseException if jsonElement is not in the expected format of {@code typeofT}
     */
    @Override
    public EnergyValueStackMapping deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (jsonElement.isJsonPrimitive())
            return null;

        JsonObject jsonStackValueMapping = (JsonObject) jsonElement;

        WrappedStack wrappedStack = null;
        EnergyValue energyValue = null;

        if (jsonStackValueMapping.get(memberWrappedStack) != null)
        {
            try
            {
                wrappedStack = new WrappedStackSerializer().deserialize(jsonStackValueMapping.get(memberWrappedStack).getAsJsonObject(), typeOfT, context);
            }
            catch (JsonParseException ignored)
            {

            }
        }

        if (jsonStackValueMapping.get(memberEnergyValue) != null)
        {
            try
            {
                energyValue = new EnergyValueSerializer().deserialize(jsonStackValueMapping.get(memberEnergyValue).getAsJsonObject(), typeOfT, context);
            }
            catch (JsonParseException e)
            {

            }
        }

        return wrappedStack != null && energyValue != null
            ? new EnergyValueStackMapping(wrappedStack, energyValue)
            : null;
    }

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     * <p/>
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link com.google.gson.JsonSerializationContext#serialize(Object, java.lang.reflect.Type)} method to create JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param energyValueStackMapping the object that needs to be converted to Json.
     * @param typeOfSrc               the actual type (fully genericized version) of the source object.
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(EnergyValueStackMapping energyValueStackMapping, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonStackValueMapping = new JsonObject();

        jsonStackValueMapping.add(memberWrappedStack, JsonSerialization.jsonSerializer.toJsonTree(energyValueStackMapping.wrappedStack));
        jsonStackValueMapping.add(memberEnergyValue, JsonSerialization.jsonSerializer.toJsonTree(energyValueStackMapping.energyValue));

        return jsonStackValueMapping;
    }
}
