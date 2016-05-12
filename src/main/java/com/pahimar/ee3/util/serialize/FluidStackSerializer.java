package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;

public class FluidStackSerializer implements JsonSerializer<FluidStack>, JsonDeserializer<FluidStack> {

    // TODO String constants for property names

    @Override
    public FluidStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject jsonObject = (JsonObject) json;

            String fluidName = null;
            int fluidAmount = Integer.MIN_VALUE;
            NBTTagCompound nbtTagCompound = null;

            try {
                if (jsonObject.get("fluidName").getAsJsonPrimitive().isString()) {
                    fluidName = jsonObject.get("fluidName").getAsString();
                }
            }
            catch (IllegalStateException exception) {
            }

            try {
                if (jsonObject.get("fluidAmount").getAsJsonPrimitive().isNumber()) {
                    fluidAmount = jsonObject.get("fluidAmount").getAsInt();
                }
            }
            catch (IllegalStateException exception) {
            }

            try {
                if (jsonObject.get("fluidTagCompound").getAsJsonPrimitive().isString()) {

                    NBTBase nbtBase = JsonToNBT.func_150315_a(jsonObject.get("fluidTagCompound").getAsString());
                    if (nbtBase instanceof NBTTagCompound) {
                        nbtTagCompound = (NBTTagCompound) nbtBase;
                    }
                }
            }
            catch (IllegalStateException exception) {
            }
            catch (NBTException e) {
            }

            if (fluidName != null) {
                Fluid fluid = FluidRegistry.getFluid(fluidName);

                if (fluid != null && fluidAmount >= 0) {
                    FluidStack fluidStack = new FluidStack(fluid, fluidAmount);

                    if (nbtTagCompound != null) {
                        fluidStack.tag = nbtTagCompound;
                    }

                    return fluidStack;
                }
            }
        }

        return null;
    }

    @Override
    public JsonElement serialize(FluidStack src, Type typeOfSrc, JsonSerializationContext context) {

        if (src != null) {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("fluidName", src.getFluid().getName());
            jsonObject.addProperty("fluidAmount", src.amount);
            if (src.tag != null) {
                jsonObject.addProperty("fluidTagCompound", src.tag.toString());
            }

            return jsonObject;
        }

        return null;
    }
}
