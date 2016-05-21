package com.pahimar.ee3.util.serialize;

import com.google.gson.*;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.knowledge.PlayerKnowledge;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Type;

public class PlayerKnowledgeSerializer implements JsonSerializer<PlayerKnowledge>, JsonDeserializer<PlayerKnowledge> {

    @Override
    public PlayerKnowledge deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // FIXME Priority Number 1
        return null;
    }

    @Override
    public JsonElement serialize(PlayerKnowledge src, Type typeOfSrc, JsonSerializationContext context) {

        JsonArray jsonArray = new JsonArray();

        if (src != null) {
            for (ItemStack itemStack : src.getKnownItemStacks()) {
                jsonArray.add(context.serialize(WrappedStack.wrap(itemStack)));
            }
        }

        return jsonArray;
    }
}
