package com.pahimar.ee3.exchange;

import com.google.gson.*;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.serialization.EnergyValueStackMappingSerializer;
import com.pahimar.ee3.serialization.WrappedStackSerializer;

import java.lang.reflect.Type;

public class EnergyValueStackMapping
{
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
            return EnergyValueStackMappingSerializer.createFromJson(jsonStackValueMapping);
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
        return EnergyValueStackMappingSerializer.toJson(this);
    }

}
