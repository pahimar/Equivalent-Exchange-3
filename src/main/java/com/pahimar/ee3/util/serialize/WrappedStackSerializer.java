package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;

public class WrappedStackSerializer implements JsonSerializer<WrappedStack>, JsonDeserializer<WrappedStack> {

    // TODO String constants for property names

    @Override
    public WrappedStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {

            JsonObject jsonObject = (JsonObject) json;

            String type = null;
            int stackSize = Integer.MIN_VALUE;
            String data = null;


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
                if (jsonObject.get("data").getAsJsonPrimitive().isString()) {
                    data = jsonObject.get("data").getAsString();
                }
            }
            catch (IllegalStateException exception) {
            }

            if ("itemstack".equalsIgnoreCase(type) || "orestack".equalsIgnoreCase(type) || "fluidstack".equalsIgnoreCase(type)) {
                if (stackSize >= 1) {
                    // TODO PICK UP HERE
                    // TODO ALSO LOOK INTO MOVING THESE ALL INTO SerializationHelper
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
