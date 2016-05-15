package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import com.pahimar.ee3.exchange.OreStack;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Type;

public class WrappedStackSerializer implements JsonSerializer<WrappedStack>, JsonDeserializer<WrappedStack> {

    private static final String DATA = "data";
    private static final String TYPE = "type";
    private static final String TYPE_ITEMSTACK = "itemstack";
    private static final String TYPE_ORESTACK = "orestack";
    private static final String TYPE_FLUIDSTACK = "fluidstack";

    @Override
    // TODO Update deserialize to match up with new serialize method implementation
    public WrappedStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {

            JsonObject jsonObject = json.getAsJsonObject();

            String type = null;

            try {
                if (jsonObject.get(TYPE).getAsJsonPrimitive().isString()) {
                    type = jsonObject.get(TYPE).getAsString();
                }
            }
            catch (IllegalStateException exception) {
            }

            if (jsonObject.get(DATA).isJsonObject()) {
                JsonObject data = jsonObject.getAsJsonObject(DATA);

                if (TYPE_ITEMSTACK.equalsIgnoreCase(type)) {
                    ItemStack itemStack = SerializationHelper.GSON.fromJson(data, ItemStack.class);

                    if (itemStack != null) {
                        return WrappedStack.wrap(itemStack);
                    }
                }
                else if (TYPE_ORESTACK.equalsIgnoreCase(type)) {
                    OreStack oreStack = SerializationHelper.GSON.fromJson(data, OreStack.class);

                    if (oreStack != null) {
                        return WrappedStack.wrap(oreStack);
                    }
                }
                else if (TYPE_FLUIDSTACK.equalsIgnoreCase(type)) {
                    FluidStack fluidStack = SerializationHelper.GSON.fromJson(data, FluidStack.class);

                    if (fluidStack != null) {
                        return WrappedStack.wrap(fluidStack);
                    }
                }
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
