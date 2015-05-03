package com.pahimar.ee3.exchange;

import com.google.gson.*;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;

public class JsonFluidStack implements JsonSerializer<JsonFluidStack>, JsonDeserializer<JsonFluidStack>
{
    public static final Gson jsonSerializer = (new GsonBuilder()).registerTypeAdapter(JsonFluidStack.class, new JsonFluidStack()).create();

    public Fluid fluid;
    public int amount;
    public NBTTagCompound tag;

    public JsonFluidStack()
    {
        this.fluid = null;
        this.amount = 0;
        this.tag = null;
    }

    public JsonFluidStack(FluidStack fluidStack)
    {
        this.fluid = fluidStack.getFluid();
        this.amount = fluidStack.amount;
        this.tag = fluidStack.tag;
    }

    @Override
    public JsonFluidStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (json.isJsonObject())
        {
            JsonObject jsonObject = (JsonObject) json;
            JsonFluidStack jsonFluidStack = new JsonFluidStack();

            if (jsonObject.has("fluidName"))
            {
                jsonFluidStack.fluid = FluidRegistry.getFluid(jsonObject.get("fluidName").getAsString());
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

        jsonObject.addProperty("fluidName", src.fluid.getName());
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
        return String.format("fluid: %s, amount: %s, tag: %s", fluid, amount, tag);
    }
}
