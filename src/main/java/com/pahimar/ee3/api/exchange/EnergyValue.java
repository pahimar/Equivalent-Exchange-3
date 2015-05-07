package com.pahimar.ee3.api.exchange;

import com.google.gson.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.lang.reflect.Type;

public final class EnergyValue implements Comparable<EnergyValue>, JsonDeserializer<EnergyValue>, JsonSerializer<EnergyValue>
{
    private static final Gson jsonSerializer = (new GsonBuilder()).registerTypeAdapter(EnergyValue.class, new EnergyValue()).create();
    private float energyValue;

    public EnergyValue()
    {
        this(0);
    }

    public EnergyValue(float energyValue)
    {
        this.energyValue = energyValue;
    }

    @Override
    public boolean equals(Object object)
    {
        return object instanceof EnergyValue && (compareTo((EnergyValue) object) == 0);
    }

    @Override
    public String toString()
    {
        return String.format("%s", energyValue);
    }

    @Override
    public int compareTo(EnergyValue energyValue)
    {
        if (energyValue != null)
        {
            return Float.compare(this.energyValue, energyValue.getValue());
        }
        else
        {
            return -1;
        }
    }

    public float getValue()
    {
        return this.energyValue;
    }

    public IChatComponent getChatComponent()
    {
        return new ChatComponentText("" + this.getValue());
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setFloat("energyValue", energyValue);
        return nbtTagCompound;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound.hasKey("energyValue"))
        {
            this.energyValue = nbtTagCompound.getFloat("energyValue");
        }
    }

    public static NBTTagCompound writeEnergyValueToNBT(EnergyValue energyValue)
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        energyValue.writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    public static EnergyValue loadEnergyValueFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound.hasKey("energyValue"))
        {
            float energyValue = nbtTagCompound.getFloat("energyValue");

            return new EnergyValue(energyValue);
        }

        return null;
    }

    /**
     * Deserializes an EmcValue object from the given serialized json String
     *
     * @param jsonEnergyValue Json encoded String representing a EmcValue object
     * @return The EmcValue that was encoded as json, or null if a valid EmcValue could not be decoded from given String
     */
    @SuppressWarnings("unused")
    public static EnergyValue createFromJson(String jsonEnergyValue)
    {
        try
        {
            return jsonSerializer.fromJson(jsonEnergyValue, EnergyValue.class);
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

    /**
     * Returns this EmcValue as a json serialized String
     *
     * @return Json serialized String of this EmcValue
     */
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
    public EnergyValue deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonEnergyValue = (JsonObject) jsonElement;

        if (jsonEnergyValue.get("value") != null && jsonEnergyValue.get("value").isJsonPrimitive())
        {
            float energyValue = jsonEnergyValue.get("value").getAsFloat();

            if (Float.compare(energyValue, 0f) >= 0)
            {
                return new EnergyValue(energyValue);
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

        jsonEnergyValue.addProperty("value", energyValueObject.energyValue);

        return jsonEnergyValue;
    }
}
