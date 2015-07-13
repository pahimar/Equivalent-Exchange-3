package com.pahimar.ee3.serialization;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.exchange.JsonItemStack;
import com.pahimar.ee3.knowledge.TransmutationKnowledge;
import com.pahimar.ee3.reference.Comparators;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TransmutationKnowledgeSerializer implements JsonSerializer<TransmutationKnowledge>, JsonDeserializer<TransmutationKnowledge>
{
    private static final String memberKnownTransmutationsKey = "knownTransmutations";

    public static String toJson(TransmutationKnowledge ransmutationKnowledge)
    {
        return JsonSerialization.jsonSerializer.toJson(ransmutationKnowledge);
    }

    public static void toJson(TransmutationKnowledge ransmutationKnowledge, JsonWriter writer)
    {
        JsonSerialization.jsonSerializer.toJson(ransmutationKnowledge, TransmutationKnowledge.class, writer);
    }

    public static TransmutationKnowledge createFromJson(String jsonTransmutationKnowledge)
            throws JsonParseException
    {
        return JsonSerialization.jsonSerializer.fromJson(jsonTransmutationKnowledge, TransmutationKnowledge.class);
    }

    public static TransmutationKnowledge createFromJson(JsonReader reader)
            throws JsonParseException
    {
        return JsonSerialization.jsonSerializer.fromJson(reader, TransmutationKnowledge.class);
    }

    @Override
    public TransmutationKnowledge deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (!json.isJsonObject())
            return null;

        JsonObject jsonObject = (JsonObject) json;

        Set<ItemStack> itemStacks = new TreeSet<ItemStack>(Comparators.idComparator);

        if (jsonObject.has(memberKnownTransmutationsKey) && jsonObject.get(memberKnownTransmutationsKey).isJsonArray())
        {
            JsonArray jsonArray = (JsonArray) jsonObject.get(memberKnownTransmutationsKey);
            Iterator<JsonElement> iterator = jsonArray.iterator();

            while (iterator.hasNext())
            {
                JsonElement jsonElement = iterator.next();
                if (jsonElement.isJsonObject())
                {
                    try
                    {
                        JsonItemStack jsonItemStack = JsonSerialization.jsonSerializer.fromJson(jsonElement, JsonItemStack.class);

                        ItemStack itemStack = null;
                        Item item = (Item) Item.itemRegistry.getObject(jsonItemStack.itemName);
                        if (item != null)
                        {
                            itemStack = new ItemStack(item, 1, jsonItemStack.itemDamage);
                            if (jsonItemStack.itemNBTTagCompound != null)
                                itemStack.stackTagCompound = jsonItemStack.itemNBTTagCompound;
                        }

                        if (itemStack != null)
                            itemStacks.add(itemStack);
                    }
                    catch (JsonParseException ignored)
                    {
                    }
                }
            }
        }

        return new TransmutationKnowledge(itemStacks);
    }

    @Override
    public JsonElement serialize(TransmutationKnowledge transmutationKnowledge, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonTransmutationKnowledge = new JsonObject();

        JsonArray knownTransmutations = new JsonArray();
        for (ItemStack itemStack : transmutationKnowledge.getKnownTransmutations())
            knownTransmutations.add(JsonSerialization.jsonSerializer.toJsonTree(new JsonItemStack(itemStack)));

        jsonTransmutationKnowledge.add(memberKnownTransmutationsKey, knownTransmutations);
        return jsonTransmutationKnowledge;
    }
}
