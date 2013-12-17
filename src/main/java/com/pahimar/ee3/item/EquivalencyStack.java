package com.pahimar.ee3.item;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

public class EquivalencyStack implements Comparable<EquivalencyStack>, JsonDeserializer<EquivalencyStack>, JsonSerializer<EquivalencyStack>
{
    // Gson serializer for serializing to/deserializing from json
    private static final Gson gsonSerializer = (new GsonBuilder()).registerTypeAdapter(EquivalencyStack.class, new EquivalencyStack()).create();

    public List<?> equivalencyList;
    public int stackSize;

    public EquivalencyStack()
    {
        this(null, -1);
    }

    public EquivalencyStack(List<?> equivalencyList)
    {
        this(equivalencyList, 1);
    }

    public EquivalencyStack(List equivalencyList, int stackSize)
    {
        this.equivalencyList = equivalencyList;
        this.stackSize = stackSize;
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
            // TODO Log something regarding the failed parse
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
    public EquivalencyStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(EquivalencyStack src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
