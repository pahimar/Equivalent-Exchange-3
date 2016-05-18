package com.pahimar.ee3.exchange;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Type;

@Deprecated
public class JsonItemStack implements JsonSerializer<JsonItemStack>, JsonDeserializer<JsonItemStack>
{
    public static final Gson jsonSerializer = (new GsonBuilder()).registerTypeAdapter(JsonItemStack.class, new JsonItemStack()).create();

    public String itemName;
    public int itemDamage;
    public NBTTagCompound itemNBTTagCompound;

    public JsonItemStack()
    {
        this.itemName = null;
        this.itemDamage = 0;
        this.itemNBTTagCompound = null;
    }

    public JsonItemStack(ItemStack itemStack)
    {
        this.itemName = Item.itemRegistry.getNameForObject(itemStack.getItem());
        this.itemDamage = itemStack.getItemDamage();
        if (itemStack.stackTagCompound != null)
        {
            this.itemNBTTagCompound = itemStack.getTagCompound();
        }
    }

    @Override
    public JsonItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (json.isJsonObject())
        {
            JsonObject jsonObject = (JsonObject) json;
            JsonItemStack jsonItemStack = new JsonItemStack();

            if (jsonObject.has("itemName"))
            {
                jsonItemStack.itemName = jsonObject.get("itemName").getAsString();
            }
            else
            {
                throw new JsonParseException(""); // TODO Exception message
            }

            if (jsonObject.has("itemDamage"))
            {
                jsonItemStack.itemDamage = jsonObject.get("itemDamage").getAsInt();
            }
            else
            {
                throw new JsonParseException(""); // TODO Exception message
            }

            if (jsonObject.has("itemNBTTagCompound"))
            {
                try
                {
                    NBTBase nbtBase = JsonToNBT.func_150315_a(jsonObject.get("itemNBTTagCompound").getAsString());

                    if (nbtBase instanceof NBTTagCompound)
                    {
                        jsonItemStack.itemNBTTagCompound = (NBTTagCompound) nbtBase;
                    }
                }
                catch (NBTException e)
                {
                    throw new JsonParseException(e.getMessage(), e.getCause());
                }
            }

            return jsonItemStack;
        }

        return null;
    }

    @Override
    public JsonElement serialize(JsonItemStack src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("itemName", src.itemName);
        jsonObject.addProperty("itemDamage", src.itemDamage);

        if (src.itemNBTTagCompound != null)
        {
            jsonObject.addProperty("itemNBTTagCompound", src.itemNBTTagCompound.toString());
        }

        return jsonObject;
    }

    @Override
    public String toString()
    {
        return String.format("itemName: %s, itemDamage: %s, itemNBTTagCompound: %s", itemName, itemDamage, itemNBTTagCompound);
    }
}
