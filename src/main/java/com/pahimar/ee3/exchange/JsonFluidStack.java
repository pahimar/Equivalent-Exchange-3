package com.pahimar.ee3.exchange;

import com.google.gson.*;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;

public class JsonFluidStack implements JsonSerializer<JsonFluidStack>, JsonDeserializer<JsonFluidStack>
{
    public static final Gson jsonSerializer = (new GsonBuilder()).registerTypeAdapter(JsonFluidStack.class, new JsonFluidStack()).create();

    public int fluidID;
    public int amount;
    public NBTTagCompound tag;

    public JsonFluidStack()
    {
        this.fluidID = Integer.MIN_VALUE;
        this.amount = 0;
        this.tag = null;
    }

    public JsonFluidStack(FluidStack fluidStack)
    {
        this.fluidID = fluidStack.fluidID;
        this.amount = fluidStack.amount;
        this.tag = fluidStack.tag;
    }

    public JsonFluidStack(int fluidID, int amount, NBTTagCompound tag)
    {
        this.fluidID = fluidID;
        this.amount = amount;
        this.tag = tag;
    }

    @Override
    public JsonFluidStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (json.isJsonObject())
        {
            JsonObject jsonObject = (JsonObject) json;
            JsonFluidStack jsonFluidStack = new JsonFluidStack();

            if (jsonObject.has("fluidID"))
            {
                jsonFluidStack.fluidID = jsonObject.get("fluidID").getAsInt();
            }
            else
            {
                throw new JsonParseException(""); // TODO Exception message
            }

            if (jsonObject.has("amount"))
            {
                jsonFluidStack.amount = jsonObject.get("amount").getAsInt();
            }
            else
            {
                throw new JsonParseException(""); // TODO Exception message
            }

            if (jsonObject.has("tag"))
            {
                try
                {
                    NBTBase nbtBase = JsonToNBT.func_150315_a(jsonObject.get("tag").getAsString());

                    if (nbtBase instanceof NBTTagCompound)
                    {
                        jsonFluidStack.tag = (NBTTagCompound) nbtBase;
                    }
                }
                catch (NBTException e)
                {
                    throw new JsonParseException(e.getMessage(), e.getCause());
                }
            }

            return jsonFluidStack;
        }

        return null;
    }

    @Override
    public JsonElement serialize(JsonFluidStack src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("fluidID", src.fluidID);
        jsonObject.addProperty("amount", src.amount);

        if (src.tag != null)
        {
            jsonObject.addProperty("tag", src.tag.toString());
        }

        return jsonObject;
    }

    @Override
    public String toString()
    {
        return String.format("fluidID: %s, amount: %s, tag: %s", fluidID, amount, tag);
    }
}
