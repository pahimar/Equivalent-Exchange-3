package com.pahimar.ee3.api.exchange;

import com.google.gson.*;
import com.pahimar.ee3.serialization.EnergyValueSerializer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.lang.reflect.Type;

public final class EnergyValue implements Comparable<EnergyValue>
{
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
            return EnergyValueSerializer.createFromJson(jsonEnergyValue);
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
        return EnergyValueSerializer.toJson(this);
    }
}
