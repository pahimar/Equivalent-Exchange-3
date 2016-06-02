package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;

public class WrappedStackSerializer implements JsonSerializer<WrappedStack>, JsonDeserializer<WrappedStack> {

    private static final String TYPE_ITEM_STACK = "itemstack";
    private static final String TYPE_ORE_STACK = "orestack";
    private static final String TYPE_FLUID_STACK = "fluidstack";

    @Override
    public WrappedStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {

            JsonObject jsonObject = json.getAsJsonObject();
            WrappedStack wrappedStack = null;

            if (jsonObject.has(TYPE_ITEM_STACK)) {
                try {
                    wrappedStack = WrappedStack.wrap(context.deserialize(jsonObject.get(TYPE_ITEM_STACK), ItemStack.class));
                }
                catch (JsonParseException e) {
                    // TODO Logging
                }
            }
            else if (jsonObject.has(TYPE_ORE_STACK)) {
                try {
                    wrappedStack = WrappedStack.wrap(context.deserialize(jsonObject.get(TYPE_ORE_STACK), OreStack.class));
                }
                catch (JsonParseException e) {
                    // TODO Logging
                }
            }
            else if (jsonObject.has(TYPE_FLUID_STACK)) {
                try {
                    wrappedStack = WrappedStack.wrap(context.deserialize(jsonObject.get(TYPE_FLUID_STACK), FluidStack.class));
                }
                catch (JsonParseException e) {
                    // TODO Logging
                }
            }

            if (wrappedStack != null && wrappedStack.getWrappedObject() != null) {
                return wrappedStack;
            }
        }

        return null;
    }

    @Override
    public JsonElement serialize(WrappedStack src, Type typeOfSrc, JsonSerializationContext context) {

        final JsonObject jsonObject = new JsonObject();

        if (src != null && src.getWrappedObject() != null) {
            jsonObject.add(src.getWrappedObject().getClass().getSimpleName().toLowerCase(), context.serialize(src.getWrappedObject()));
            return jsonObject;
        }

        return null;
    }
}
