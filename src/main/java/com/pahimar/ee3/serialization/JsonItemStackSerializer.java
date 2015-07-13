package com.pahimar.ee3.serialization;

import com.google.gson.*;
import com.pahimar.ee3.exchange.JsonItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Type;

public class JsonItemStackSerializer implements JsonSerializer<JsonItemStack>, JsonDeserializer<JsonItemStack>
{
    private static final String memberItemNameKey = "itemName";
    private static final String memberDamageKey = "itemDamage";
    private static final String memberTagKey = "itemNBTTagCompound";

    @Override
    public JsonItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (!json.isJsonObject())
            return null;

        JsonObject jsonObject = (JsonObject) json;
        JsonItemStack jsonItemStack = new JsonItemStack();

        if (!jsonObject.has(memberItemNameKey))
            throw new JsonParseException("ItemStack must contain an itemName entry."); // TODO Exception message localization

        jsonItemStack.itemName = jsonObject.get(memberItemNameKey).getAsString();

        // Why throw an exception? We can just assume 0.
        // throw new JsonParseException(""); TODO Exception message(?)
        jsonItemStack.itemDamage = jsonObject.has(memberDamageKey)
            ? jsonObject.get(memberDamageKey).getAsInt()
            : 0;

        if (jsonObject.has(memberTagKey))
        {
            try
            {
                NBTBase nbtBase = JsonToNBT.func_150315_a(jsonObject.get(memberTagKey).getAsString());
                if (nbtBase instanceof NBTTagCompound)
                    jsonItemStack.itemNBTTagCompound = (NBTTagCompound) nbtBase;
            }
            catch (NBTException e)
            {
                throw new JsonParseException(e.getMessage(), e.getCause());
            }
        }

        return jsonItemStack;

    }

    @Override
    public JsonElement serialize(JsonItemStack src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(memberItemNameKey, src.itemName);
        jsonObject.addProperty(memberDamageKey, src.itemDamage);

        if (src.itemNBTTagCompound != null)
            jsonObject.addProperty(memberTagKey, src.itemNBTTagCompound.toString());

        return jsonObject;
    }
}
