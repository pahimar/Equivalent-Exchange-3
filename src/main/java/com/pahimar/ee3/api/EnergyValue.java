package com.pahimar.ee3.api;

import com.google.gson.*;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Type;

public final class EnergyValue implements Comparable<EnergyValue>, JsonDeserializer<EnergyValue>, JsonSerializer<EnergyValue>
{
    private static final Gson jsonSerializer = (new GsonBuilder()).registerTypeAdapter(EnergyValue.class, new EnergyValue()).create();
    private final float energyValue;
    private final EnergyType energyType;

    public EnergyValue()
    {
        this(0f, EnergyType.UNKNOWN);
    }

    public EnergyValue(int energyValue)
    {
        this((float) energyValue);
    }

    public EnergyValue(double energyValue)
    {
        this((float) energyValue);
    }

    public EnergyValue(float energyValue)
    {
        this(energyValue, EnergyType.DEFAULT);
    }

    public EnergyValue(float energyValue, EnergyType energyType)
    {
        this.energyValue = energyValue;
        this.energyType = energyType;
    }

    public EnergyValue(int energyValue, EnergyType energyType)
    {
        this((float) energyValue, energyType);
    }

    @Override
    public boolean equals(Object object)
    {
        return object instanceof EnergyValue && (compareTo((EnergyValue) object) == 0);
    }

    @Override
    public String toString()
    {
        return String.format(" %s@%s ", energyValue, energyType);
    }

    @Override
    public int compareTo(EnergyValue energyValue)
    {
        if (energyValue != null)
        {
            if (this.energyType == energyValue.getEnergyType())
            {
                return Float.compare(this.energyValue, energyValue.getEnergyValue());
            }
            else
            {
                return (this.energyType.ordinal() - energyValue.getEnergyType().ordinal());
            }
        }
        else
        {
            return -1;
        }
    }

    public EnergyType getEnergyType()
    {
        return this.energyType;
    }

    public float getEnergyValue()
    {
        return this.energyValue;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setFloat("energyValue", energyValue);
        nbtTagCompound.setInteger("energyType", energyType.ordinal());
        return nbtTagCompound;
    }

    public static NBTTagCompound writeEnergyValueToNBT(EnergyValue energyValue)
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        energyValue.writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    public static EnergyValue loadEnergyValueFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound.hasKey("energyValue") && nbtTagCompound.hasKey("energyType"))
        {
            float energyValue = nbtTagCompound.getFloat("energyValue");
            EnergyType energyType = EnergyType.getEnergyTypeFromOrdinal(nbtTagCompound.getInteger("energyType"));

            return new EnergyValue(energyValue, energyType);
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

        if (jsonEnergyValue.get("type") != null && jsonEnergyValue.get("type").isJsonPrimitive() && jsonEnergyValue.get("value") != null && jsonEnergyValue.get("value").isJsonPrimitive())
        {
            EnergyType energyType = EnergyType.getEnergyTypeFromOrdinal(jsonEnergyValue.get("type").getAsInt());
            float energyValue = jsonEnergyValue.get("value").getAsFloat();

            if (Float.compare(energyValue, 0f) > 0)
            {
                return new EnergyValue(energyValue, energyType);
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

        jsonEnergyValue.addProperty("type", energyValueObject.energyType.ordinal());
        jsonEnergyValue.addProperty("value", energyValueObject.energyValue);

        return jsonEnergyValue;
    }

    public static enum EnergyType
    {
        UNKNOWN, CORPOREAL, KINETIC, TEMPORAL, ESSENTIA, AMORPHOUS, VOID, OMNI;

        public static final EnergyType DEFAULT = EnergyType.CORPOREAL;

        public static EnergyType getEnergyTypeFromOrdinal(int ordinal)
        {
            if (ordinal == CORPOREAL.ordinal())
            {
                return CORPOREAL;
            }
            else if (ordinal == KINETIC.ordinal())
            {
                return KINETIC;
            }
            else if (ordinal == TEMPORAL.ordinal())
            {
                return TEMPORAL;
            }
            else if (ordinal == ESSENTIA.ordinal())
            {
                return ESSENTIA;
            }
            else if (ordinal == AMORPHOUS.ordinal())
            {
                return AMORPHOUS;
            }
            else if (ordinal == VOID.ordinal())
            {
                return VOID;
            }
            else if (ordinal == OMNI.ordinal())
            {
                return OMNI;
            }
            else
            {
                return UNKNOWN;
            }
        }
    }
}
