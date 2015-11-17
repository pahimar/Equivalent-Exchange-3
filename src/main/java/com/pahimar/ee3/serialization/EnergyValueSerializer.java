package com.pahimar.ee3.serialization;

import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;

import java.lang.reflect.Type;

public class EnergyValueSerializer implements JsonDeserializer<EnergyValue>, JsonSerializer<EnergyValue>
{
    private static final String memberValueKey = "value";

    /**
     * Deserializes an EmcValue object from the given serialized json String
     *
     * @param jsonEnergyValue Json encoded String representing a EmcValue object
     * @return The EmcValue that was encoded as json, or null if a valid EmcValue could not be decoded from given String
     */
    @SuppressWarnings("unused")
    public static EnergyValue createFromJson(String jsonEnergyValue)
        throws JsonParseException
    {
        return JsonSerialization.jsonSerializer.fromJson(jsonEnergyValue, EnergyValue.class);
    }

    /**
     * Returns this EmcValue as a json serialized String
     *
     * @return Json serialized String of this EmcValue
     */
    public static String toJson(EnergyValue value)
    {
        return JsonSerialization.jsonSerializer.toJson(value);
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
    public EnergyValue deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonObject jsonEnergyValue = (JsonObject) jsonElement;

        if (jsonEnergyValue.get(memberValueKey) != null && jsonEnergyValue.get(memberValueKey).isJsonPrimitive())
        {
            float energyValue = jsonEnergyValue.get(memberValueKey).getAsFloat();
            if (Float.compare(energyValue, 0f) >= 0)
                return new EnergyValue(energyValue);
        }

        return null;
    }

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     * <p/>
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link com.google.gson.JsonSerializationContext#serialize(Object, java.lang.reflect.Type)} method to create JsonElements for any
     * non-trivial field of the {@code energyValueObject} object. However, you should never invoke it on the
     * {@code energyValueObject} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param energyValueObject the object that needs to be converted to Json.
     * @param typeOfSrc         the actual type (fully genericized version) of the source object.
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(EnergyValue energyValueObject, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonEnergyValue = new JsonObject();
        jsonEnergyValue.addProperty(memberValueKey, energyValueObject.getValue());

        return jsonEnergyValue;
    }
}
