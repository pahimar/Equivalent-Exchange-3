package com.pahimar.ee3.item;

import com.google.gson.*;
import com.pahimar.ee3.helper.LogHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EquivalencyStack implements Comparable<EquivalencyStack>, JsonDeserializer<EquivalencyStack>, JsonSerializer<EquivalencyStack>
{
    // Gson serializer for serializing to/deserializing from json
    private static final Gson gsonSerializer = (new GsonBuilder()).registerTypeAdapter(EquivalencyStack.class, new EquivalencyStack()).create();

    public List<WrappedStack> equivalencyList;
    public int stackSize;

    public EquivalencyStack()
    {
        equivalencyList = null;
        stackSize = -1;
    }

    public EquivalencyStack(List<?> equivalencyList)
    {
        this(equivalencyList, 1);
    }

    public EquivalencyStack(List<?> list, int stackSize)
    {
        List<WrappedStack> equivalencyList = new ArrayList<WrappedStack>();
        for (Object listObject : list)
        {
            if (WrappedStack.canBeWrapped(listObject))
            {
                equivalencyList.add(new WrappedStack(listObject));
            }
        }

        if (equivalencyList.size() == list.size())
        {
            this.equivalencyList = equivalencyList;
            this.stackSize = stackSize;
        }
        else
        {
            this.equivalencyList = null;
            this.stackSize = -1;
        }
    }

    @Override
    public String toString()
    {
        return String.format("%sxlistStack.%s", stackSize, equivalencyList);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof EquivalencyStack)
        {
            compareTo((EquivalencyStack) object);
        }

        return false;
    }

    /**
     * Deserializes a EquivalencyStack object from the given serialized json
     * String
     *
     * @param jsonEquivalencyStack Json encoded String representing a OreStack object
     * @return The EquivalencyStack that was encoded as json, or null if a valid
     * OreStack could not be decoded from given String
     */
    public static EquivalencyStack createFromJson(String jsonEquivalencyStack)
    {
        try
        {
            return (EquivalencyStack) gsonSerializer.fromJson(jsonEquivalencyStack, EquivalencyStack.class);
        }
        catch (JsonSyntaxException exception)
        {
            LogHelper.severe(String.format("Failed to create an EquivalencyStack object for String = '%s'", jsonEquivalencyStack));
        }

        return null;
    }

    /**
     * Returns this OreStack as a json serialized String
     *
     * @return Json serialized String of this OreStack
     */
    public String toJson()
    {
        return gsonSerializer.toJson(this);
    }

    @Override
    public int compareTo(EquivalencyStack equivalencyStack) {
        return compare(this, equivalencyStack);
    }

    public static int compare(EquivalencyStack equivalencyStack1, EquivalencyStack equivalencyStack2)
    {
        return comparator.compare(equivalencyStack1, equivalencyStack2);
    }

    public static Comparator<EquivalencyStack> comparator = new Comparator<EquivalencyStack>()
    {
        @Override
        public int compare(EquivalencyStack equivalencyStack1, EquivalencyStack equivalencyStack2)
        {
            if (equivalencyStack1.equivalencyList.size() == equivalencyStack2.equivalencyList.size())
            {
                if (equivalencyStack1.equivalencyList.hashCode() == equivalencyStack2.equivalencyList.hashCode())
                {
                    return equivalencyStack1.stackSize - equivalencyStack1.stackSize;
                }
                else
                {
                    return equivalencyStack1.equivalencyList.hashCode() - equivalencyStack2.equivalencyList.hashCode();
                }
            }
            else
            {
                return equivalencyStack1.equivalencyList.size() - equivalencyStack2.equivalencyList.size();
            }
        }
    };

    @Override
    public EquivalencyStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        if (!jsonElement.isJsonPrimitive())
        {
            JsonObject jsonEquivalencyStack = (JsonObject) jsonElement;

            List<WrappedStack> equivalencyList = new ArrayList<WrappedStack>();
            int stackSize = -1;

            if (jsonEquivalencyStack.get("equivalencyList") != null)
            {
                JsonArray jsonEquivalencyList = jsonEquivalencyStack.get("equivalencyList").getAsJsonArray();

                for (int i = 0; i < jsonEquivalencyList.size(); i++)
                {
                    WrappedStack wrappedStack = new WrappedStack().deserialize(jsonEquivalencyList.get(i).getAsJsonObject(), type, context);
                    equivalencyList.add(wrappedStack);
                }
            }

            if (jsonEquivalencyStack.get("stackSize") != null)
            {
                stackSize = jsonEquivalencyStack.get("stackSize").getAsInt();
            }

            return new EquivalencyStack(equivalencyList, stackSize);
        }

        return null;
    }

    @Override
    public JsonElement serialize(EquivalencyStack equivalencyStack, Type type, JsonSerializationContext context)
    {
        JsonObject jsonEquivalencyStack = new JsonObject();

        Gson gsonWrappedStack = (new GsonBuilder()).registerTypeAdapter(WrappedStack.class, new WrappedStack()).create();
        JsonArray jsonArray = new JsonArray();
        if (equivalencyStack.equivalencyList != null)
        {
            for (WrappedStack wrappedStack : equivalencyStack.equivalencyList)
            {
                jsonArray.add(gsonWrappedStack.toJsonTree(wrappedStack));
            }
        }

        jsonEquivalencyStack.addProperty("stackSize", equivalencyStack.stackSize);
        jsonEquivalencyStack.add("equivalencyList", jsonArray);

        return jsonEquivalencyStack;
    }
}
