package com.pahimar.ee3.serialization;

import com.google.gson.*;
import com.pahimar.ee3.exchange.JsonFluidStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;

import java.lang.reflect.Type;

public class JsonFluidStackSerializer implements JsonSerializer<JsonFluidStack>, JsonDeserializer<JsonFluidStack>
{
    private static final String memberFluidNameKey = "fluidName";
    private static final String memberAmountKey = "amount";
    private static final String memberTagKey = "tag";

    @Override
    public JsonFluidStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (!json.isJsonObject())
            return null;

        JsonObject jsonObject = (JsonObject) json;
        JsonFluidStack jsonFluidStack = new JsonFluidStack();

        if (!jsonObject.has(memberFluidNameKey))
            throw new JsonParseException("FluidStack must contain a fluidName entry."); // TODO Exception message localization

        if (!jsonObject.has(memberAmountKey))
            throw new JsonParseException("FluidStack must contain an amount entry."); // TODO Exception message localization

        jsonFluidStack.fluid = FluidRegistry.getFluid(jsonObject.get(memberFluidNameKey).getAsString());
        jsonFluidStack.amount = jsonObject.get(memberAmountKey).getAsInt();

        if (jsonObject.has(memberTagKey))
        {
            try
            {
                NBTBase nbtBase = JsonToNBT.func_150315_a(jsonObject.get(memberTagKey).getAsString());
                if (nbtBase instanceof NBTTagCompound)
                    jsonFluidStack.tag = (NBTTagCompound) nbtBase;
            }
            catch (NBTException e)
            {
                throw new JsonParseException(e.getMessage(), e.getCause());
            }
        }

        return jsonFluidStack;
    }

    @Override
    public JsonElement serialize(JsonFluidStack src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(memberFluidNameKey, src.fluid.getName());
        jsonObject.addProperty(memberAmountKey, src.amount);

        if (src.tag != null)
            jsonObject.addProperty(memberTagKey, src.tag.toString());

        return jsonObject;
    }
}
