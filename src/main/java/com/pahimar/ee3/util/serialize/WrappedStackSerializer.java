package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;

public class WrappedStackSerializer implements JsonSerializer<WrappedStack>, JsonDeserializer<WrappedStack> {

    // TODO String constants for property names

    @Override
    public WrappedStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {

            JsonObject jsonObject = json.getAsJsonObject();

            String type = null;
            int stackSize = Integer.MIN_VALUE;
            JsonObject data = null;

            try {
                if (jsonObject.get("type").getAsJsonPrimitive().isString()) {
                    type = jsonObject.get("type").getAsString();
                }
            }
            catch (IllegalStateException exception) {
            }

            try {
                if (jsonObject.get("stackSize").getAsJsonPrimitive().isNumber()) {
                    stackSize = jsonObject.get("stackSize").getAsInt();
                }
            }
            catch (IllegalStateException exception) {
            }

            try {
                if (jsonObject.get("data").isJsonObject()) {
                    data = jsonObject.getAsJsonObject("data");
                }
            }
            catch (IllegalStateException exception) {
            }

            if ("itemstack".equalsIgnoreCase(type) || "orestack".equalsIgnoreCase(type) || "fluidstack".equalsIgnoreCase(type)) {
                if (stackSize >= 1) {

                    if ("itemstack".equalsIgnoreCase(type)) {
                        ItemStack itemStack = SerializationHelper.GSON.fromJson(data, ItemStack.class);

                        if (itemStack != null) {
                            return WrappedStack.wrap(itemStack, stackSize);
                        }
                    }
                    else if ("orestack".equalsIgnoreCase(type)) {
                        OreStack oreStack = SerializationHelper.GSON.fromJson(data, OreStack.class);

                        if (oreStack != null) {
                            return WrappedStack.wrap(oreStack, stackSize);
                        }
                    }
                    else if ("fluidstack".equalsIgnoreCase(type)) {
                        FluidStack fluidStack = SerializationHelper.GSON.fromJson(data, FluidStack.class);

                        if (fluidStack != null) {
                            return WrappedStack.wrap(fluidStack, stackSize);
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    public JsonElement serialize(WrappedStack src, Type typeOfSrc, JsonSerializationContext context) {

        final JsonObject jsonObject = new JsonObject();

        if (src.getWrappedObject() instanceof ItemStack || src.getWrappedObject() instanceof OreStack || src.getWrappedObject() instanceof FluidStack) {
            jsonObject.addProperty("type", src.getWrappedObject().getClass().getSimpleName().toLowerCase());
            jsonObject.add("data", context.serialize(src.getWrappedObject()));
            jsonObject.addProperty("stackSize", src.getStackSize());

            return jsonObject;
        }

        return null;
    }
}
