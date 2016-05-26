package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Type;

public class ItemStackSerializer implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {

    private static final String NAME = "name";
    private static final String META_VALUE = "metaValue";
    private static final String TAG_COMPOUND = "tagCompound";

    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();

            String name = null;
            int metaValue = 0;
            NBTTagCompound tagCompound = null;

            if (jsonObject.has(NAME) && jsonObject.get(NAME).isJsonPrimitive()) {
                name = jsonObject.getAsJsonPrimitive(NAME).getAsString();
            }

            if (jsonObject.has(META_VALUE) && jsonObject.get(META_VALUE).isJsonPrimitive()) {
                try {
                    metaValue = jsonObject.getAsJsonPrimitive(META_VALUE).getAsInt();
                }
                catch (NumberFormatException e) {
                    // TODO Logging
                }
            }

            if (jsonObject.has(TAG_COMPOUND) && jsonObject.get(TAG_COMPOUND).isJsonPrimitive()) {

                try {
                    NBTBase nbtBase = JsonToNBT.func_150315_a(jsonObject.getAsJsonPrimitive(TAG_COMPOUND).getAsString());
                    if (nbtBase instanceof NBTTagCompound) {
                        tagCompound = (NBTTagCompound) nbtBase;
                    }
                }
                catch (NBTException e) {
                    // TODO Logging
                }
            }

            if (name != null) {
                Item item = (Item) Item.itemRegistry.getObject(name);

                if (item != null) {
                    ItemStack itemStack = new ItemStack((Item) Item.itemRegistry.getObject(name), 1, metaValue);

                    if (tagCompound != null) {
                        itemStack.setTagCompound(tagCompound);
                    }

                    return itemStack;
                }
            }
        }

        return null;
    }

    @Override
    public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {

        if (src != null && src.getItem() != null) {
            JsonObject jsonObject = new JsonObject();

            if (Item.itemRegistry.getNameForObject(src.getItem()) != null) {
                jsonObject.addProperty(NAME, Item.itemRegistry.getNameForObject(src.getItem()));
            }
            else {
                return JsonNull.INSTANCE;
            }

            if (src.getItemDamage() != 0) {
                jsonObject.addProperty(META_VALUE, src.getItemDamage());
            }

            if (src.getTagCompound() != null) {
                jsonObject.addProperty(TAG_COMPOUND, src.getTagCompound().toString());
            }

            return jsonObject;
        }

        return JsonNull.INSTANCE;
    }
}
