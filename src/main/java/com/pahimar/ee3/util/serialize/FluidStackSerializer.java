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

    private static final String NAME = "name";
    private static final String TAG_COMPOUND = "tagCompound";

    @Override
    public FluidStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();

            String name = null;
            NBTTagCompound tagCompound = null;

            try {
                if (jsonObject.get(NAME).getAsJsonPrimitive().isString()) {
                    name = jsonObject.get(NAME).getAsString();
                }
            }
            catch (IllegalStateException exception) {
            }

            try {
                if (jsonObject.has(TAG_COMPOUND) && jsonObject.get(TAG_COMPOUND).getAsJsonPrimitive().isString()) {

                    NBTBase nbtBase = JsonToNBT.func_150315_a(jsonObject.get(TAG_COMPOUND).getAsString());
                    if (nbtBase instanceof NBTTagCompound) {
                        tagCompound = (NBTTagCompound) nbtBase;
                    }
                }
            }
            catch (IllegalStateException exception) {
            }
            catch (NBTException e) {
            }

            if (name != null) {
                Fluid fluid = FluidRegistry.getFluid(name);

                if (fluid != null) {
                    FluidStack fluidStack = new FluidStack(fluid, 1);

                    if (tagCompound != null) {
                        fluidStack.tag = tagCompound;
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

            jsonObject.addProperty(NAME, src.getFluid().getName());
            if (src.tag != null) {
                jsonObject.addProperty(TAG_COMPOUND, src.tag.toString());
            }

            return jsonObject;
        }

        return null;
    }
}
