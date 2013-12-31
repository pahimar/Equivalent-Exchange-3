package com.pahimar.ee3.helper;

import com.google.gson.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
            NBTTagCompound stackTagCompound = null;

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

            if (jsonItemStack.get("stackTagCompound") != null && !jsonItemStack.get("stackTagCompound").isJsonPrimitive())
            {
                stackTagCompound = new Gson().fromJson(jsonItemStack.get("stackTagCompound"), NBTTagCompound.class);
            }

            if (stackSize != -1 && itemID != -1)
            {
                ItemStack itemStack;

                if (itemDamage != null)
                {
                    itemStack = new ItemStack(itemID, stackSize, itemDamage);
                }
                else
                {
                    itemStack = new ItemStack(itemID, stackSize, 0);
                }

                if (stackTagCompound != null)
                {
                    itemStack.setTagCompound(stackTagCompound);
                }

                return itemStack;
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
            jsonItemStack.add("stackTagCompound", new Gson().toJsonTree(itemStack.getTagCompound()));
        }

        return jsonItemStack;
    }
}
