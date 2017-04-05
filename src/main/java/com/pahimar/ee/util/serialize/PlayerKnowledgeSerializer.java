package com.pahimar.ee.util.serialize;

import com.google.gson.*;
import com.pahimar.ee.exchange.WrappedStack;
import com.pahimar.ee.knowledge.PlayerKnowledge;
import com.pahimar.ee.reference.Comparators;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

public class PlayerKnowledgeSerializer implements JsonSerializer<PlayerKnowledge>, JsonDeserializer<PlayerKnowledge> {

    @Override
    public PlayerKnowledge deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Set<ItemStack> knownItemStacks = new TreeSet<>(Comparators.ID_COMPARATOR);

        if (json.isJsonArray()) {

            JsonArray jsonArray = json.getAsJsonArray();

            for (JsonElement jsonElement : jsonArray) {

                if (jsonElement.isJsonObject()) {

                    WrappedStack wrappedStack = null;

                    try {
                        wrappedStack = context.deserialize(jsonElement, WrappedStack.class);
                    } catch (JsonParseException e) {
                        // TODO Logging
                    }

                    if (wrappedStack != null && wrappedStack.getObject() instanceof ItemStack) {
                        knownItemStacks.add((ItemStack) wrappedStack.getObject());
                    }
                }
            }
        }

        return new PlayerKnowledge(knownItemStacks);
    }

    @Override
    public JsonElement serialize(PlayerKnowledge src, Type typeOfSrc, JsonSerializationContext context) {

        JsonArray jsonArray = new JsonArray();

        if (src != null) {
            for (ItemStack itemStack : src.getKnownItemStacks()) {
                jsonArray.add(context.serialize(WrappedStack.build(itemStack)));
            }
        }

        return jsonArray;
    }
}
