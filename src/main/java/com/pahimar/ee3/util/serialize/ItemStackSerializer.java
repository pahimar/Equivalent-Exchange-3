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

            try {
                if (jsonObject.has(NAME) && jsonObject.get(NAME).getAsJsonPrimitive().isString()) {
                    name = jsonObject.get(NAME).getAsString();
                }
            }
            catch (IllegalStateException exception) {
                // TODO We could probably log here that an invalid piece of data was found
            }

            try {
                if (jsonObject.has(META_VALUE) && jsonObject.get(META_VALUE).getAsJsonPrimitive().isNumber()) {
                    metaValue = jsonObject.get(META_VALUE).getAsInt();
                }
            }
            catch (IllegalStateException exception) {
                // TODO We could probably log here that an invalid piece of data was found
            }

            try {
                if (jsonObject.has(TAG_COMPOUND) && jsonObject.get(TAG_COMPOUND).getAsJsonPrimitive().isString()) {

                    NBTBase nbtBase = JsonToNBT.func_150315_a(jsonObject.get(TAG_COMPOUND).getAsString());
                    if (nbtBase instanceof NBTTagCompound) {
                        tagCompound = (NBTTagCompound) nbtBase;
                    }
                }
            }
            catch (NBTException e) {
            }
            catch (IllegalStateException exception) {
                // TODO We could probably log here that an invalid piece of data was found
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

        if (src != null) {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty(NAME, Item.itemRegistry.getNameForObject(src.getItem()));

            if (src.getItemDamage() != 0) {
                jsonObject.addProperty(META_VALUE, src.getItemDamage());
            }

            if (src.getTagCompound() != null) {
                jsonObject.addProperty(TAG_COMPOUND, src.getTagCompound().toString());
            }

            return jsonObject;
        }

        return null;
    }
}
