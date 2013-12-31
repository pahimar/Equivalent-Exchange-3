package com.pahimar.ee3.helper;

import com.google.gson.*;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Type;

public class GsonItemStackSerialization implements JsonDeserializer<ItemStack>, JsonSerializer<ItemStack>
{
    @Override
    public ItemStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        if (!jsonElement.isJsonPrimitive())
        {
            JsonObject jsonItemStack = (JsonObject) jsonElement;

            int stackSize = -1;
            int itemID = -1;
            Integer itemDamage = null;

            if (jsonItemStack.get("stackSize") != null)
            {
                stackSize = jsonItemStack.get("stackSize").getAsInt();
            }

            if (jsonItemStack.get("itemID") != null)
            {
                itemID = jsonItemStack.get("itemID").getAsInt();
            }

            if (jsonItemStack.get("itemDamage") != null)
            {
                itemDamage = jsonItemStack.get("itemDamage").getAsInt();
            }

            if (stackSize != -1 && itemID != -1)
            {
                if (itemDamage != null)
                {
                    return new ItemStack(itemID, stackSize, itemDamage);
                }
                else
                {
                    return new ItemStack(itemID, stackSize, 0);
                }
            }
        }

        return null;
    }

    @Override
    public JsonElement serialize(ItemStack itemStack, Type type, JsonSerializationContext context)
    {
        JsonObject jsonItemStack = new JsonObject();

        if (itemStack != null)
        {
            jsonItemStack.addProperty("stackSize", itemStack.stackSize);
            jsonItemStack.addProperty("itemID", itemStack.itemID);
            jsonItemStack.addProperty("itemDamage", itemStack.getItemDamage());
        }

        return jsonItemStack;
    }
}
