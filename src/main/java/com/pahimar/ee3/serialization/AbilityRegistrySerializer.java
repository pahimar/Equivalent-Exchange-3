package com.pahimar.ee3.serialization;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.knowledge.AbilityRegistry;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AbilityRegistrySerializer implements JsonSerializer<AbilityRegistry>, JsonDeserializer<AbilityRegistry>
{
    private static final String memberNotLearnableKey = "notLearnable";
    private static final String memberNotRecoverableKey = "notRecoverable";

    public static String toJson(AbilityRegistry registry)
    {
        return JsonSerialization.jsonSerializer.toJson(registry);
    }

    public static void toJson(AbilityRegistry registry, JsonWriter writer)
    {
        JsonSerialization.jsonSerializer.toJson(registry, AbilityRegistry.class, writer);
    }

    public static AbilityRegistry createFromJson(String jsonRegistry)
            throws JsonParseException
    {
        return JsonSerialization.jsonSerializer.fromJson(jsonRegistry, AbilityRegistry.class);
    }

    public static AbilityRegistry createFromJson(JsonReader reader)
            throws JsonParseException
    {
        return JsonSerialization.jsonSerializer.fromJson(reader, AbilityRegistry.class);
    }

    @Override
    public AbilityRegistry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (!json.isJsonObject())
            return null;

        JsonObject jsonObject = (JsonObject) json;

        Set<WrappedStack> notLearnableStacks = new TreeSet<WrappedStack>();
        Set<WrappedStack> notRecoverableStacks = new TreeSet<WrappedStack>();

        if (jsonObject.has(memberNotLearnableKey) && jsonObject.get(memberNotLearnableKey).isJsonArray())
        {
            JsonArray jsonArray = (JsonArray) jsonObject.get(memberNotLearnableKey);
            Iterator<JsonElement> iterator = jsonArray.iterator();

            while (iterator.hasNext())
            {
                JsonElement jsonElement = iterator.next();
                WrappedStack wrappedStack = JsonSerialization.jsonSerializer.fromJson(jsonElement, WrappedStack.class);

                if (wrappedStack != null)
                    notLearnableStacks.add(wrappedStack);
            }
        }

        if (jsonObject.has(memberNotRecoverableKey) && jsonObject.get(memberNotRecoverableKey).isJsonArray())
        {
            JsonArray jsonArray = (JsonArray) jsonObject.get(memberNotRecoverableKey);
            Iterator<JsonElement> iterator = jsonArray.iterator();

            while (iterator.hasNext())
            {
                JsonElement jsonElement = iterator.next();
                WrappedStack wrappedStack = JsonSerialization.jsonSerializer.fromJson(jsonElement, WrappedStack.class);

                if (wrappedStack != null)
                    notRecoverableStacks.add(wrappedStack);
            }
        }

        return new AbilityRegistry(notLearnableStacks, notRecoverableStacks);
    }

    @Override
    public JsonElement serialize(AbilityRegistry abilityRegistry, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonAbilityRegistry = new JsonObject();

        JsonArray notLearnables = new JsonArray();
        for (WrappedStack wrappedStack : abilityRegistry.getNotLearnableStacks())
            notLearnables.add(JsonSerialization.jsonSerializer.toJsonTree(wrappedStack));

        jsonAbilityRegistry.add(memberNotLearnableKey, notLearnables);

        JsonArray notRecoverables = new JsonArray();
        for (WrappedStack wrappedStack : abilityRegistry.getNotRecoverableSet())
            notRecoverables.add(JsonSerialization.jsonSerializer.toJsonTree(wrappedStack));

        jsonAbilityRegistry.add(memberNotRecoverableKey, notRecoverables);

        return jsonAbilityRegistry;
    }
}
