package com.pahimar.ee3.exchange;

import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;

import java.lang.reflect.Type;

public class EnergyValueStackMapping implements JsonSerializer<EnergyValueStackMapping>, JsonDeserializer<EnergyValueStackMapping>
{
    public static final Gson jsonSerializer = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(EnergyValueStackMapping.class, new EnergyValueStackMapping()).registerTypeAdapter(EnergyValue.class, new EnergyValue()).registerTypeAdapter(WrappedStack.class, new WrappedStack()).create();

    public final WrappedStack wrappedStack;
    public final EnergyValue energyValue;

    public EnergyValueStackMapping()
    {
        wrappedStack = null;
        energyValue = null;
    }

    public EnergyValueStackMapping(WrappedStack wrappedStack, EnergyValue energyValue)
    {
        this.wrappedStack = wrappedStack;
        this.energyValue = energyValue;
    }

    public static EnergyValueStackMapping createFromJson(String jsonStackValueMapping)
    {
        try
        {
            return jsonSerializer.fromJson(jsonStackValueMapping, EnergyValueStackMapping.class);
        }
        catch (JsonSyntaxException exception)
        {
            exception.printStackTrace();
        }
        catch (JsonParseException exception)
        {
            exception.printStackTrace();
        }

        return null;
    }

    public String toJson()
    {
        return jsonSerializer.toJson(this);
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
        if (!jsonElement.isJsonPrimitive())
        {
            JsonObject jsonStackValueMapping = (JsonObject) jsonElement;

            WrappedStack wrappedStack = null;
            EnergyValue energyValue = null;

            if (jsonStackValueMapping.get("wrappedStack") != null)
            {
                try
                {
                    wrappedStack = new WrappedStack().deserialize(jsonStackValueMapping.get("wrappedStack").getAsJsonObject(), typeOfT, context);
                }
                catch (JsonParseException e)
                {

                }
            }

            if (jsonStackValueMapping.get("energyValue") != null)
            {
                try
                {
                    energyValue = new EnergyValue().deserialize(jsonStackValueMapping.get("energyValue").getAsJsonObject(), typeOfT, context);
                }
                catch (JsonParseException e)
                {

                }
            }

            if (wrappedStack != null && energyValue != null)
            {
                return new EnergyValueStackMapping(wrappedStack, energyValue);
            }
            else
            {
                return null;
            }
        }

        return null;
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

        jsonStackValueMapping.add("wrappedStack", jsonSerializer.toJsonTree(energyValueStackMapping.wrappedStack));
        jsonStackValueMapping.add("energyValue", jsonSerializer.toJsonTree(energyValueStackMapping.energyValue));

        return jsonStackValueMapping;
    }
}
