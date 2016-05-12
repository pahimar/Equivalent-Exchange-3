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

    // TODO String constants for property names

    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {
            JsonObject jsonObject = (JsonObject) json;

            String itemName = null;
            int itemDamage = Integer.MIN_VALUE;
            NBTTagCompound nbtTagCompound = null;

            try {
                if (jsonObject.get("itemName").getAsJsonPrimitive().isString()) {
                    itemName = jsonObject.get("itemName").getAsString();
                }
            }
            catch (IllegalStateException exception) {
            }

            try {
                if (jsonObject.get("itemDamage").getAsJsonPrimitive().isNumber()) {
                    itemDamage = jsonObject.get("itemDamage").getAsInt();
                }
            }
            catch (IllegalStateException exception) {
            }

            try {
                if (jsonObject.get("itemTagCompound").getAsJsonPrimitive().isString()) {

                    NBTBase nbtBase = JsonToNBT.func_150315_a(jsonObject.get("itemTagCompound").getAsString());
                    if (nbtBase instanceof NBTTagCompound) {
                        nbtTagCompound = (NBTTagCompound) nbtBase;
                    }
                }
            }
            catch (IllegalStateException exception) {
            }
            catch (NBTException e) {
            }

            if (itemName != null) {
                Item item = (Item) Item.itemRegistry.getObject(itemName);

                if (item != null) {
                    ItemStack itemStack = new ItemStack((Item) Item.itemRegistry.getObject(itemName));

                    if (itemDamage >= 0) {
                        itemStack.setItemDamage(itemDamage);
                    }

                    if (nbtTagCompound != null) {
                        itemStack.setTagCompound(nbtTagCompound);
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

            jsonObject.addProperty("itemName", Item.itemRegistry.getNameForObject(src.getItem()));
            jsonObject.addProperty("itemDamage", src.getItemDamage());
            if (src.getTagCompound() != null) {
                jsonObject.addProperty("itemTagCompound", src.getTagCompound().toString());
            }

            return jsonObject;
        }

        return null;
    }
}
