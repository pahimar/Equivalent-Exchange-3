package com.pahimar.ee3.knowledge;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.exchange.JsonItemStack;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.FilterUtils;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class TransmutationKnowledge implements JsonSerializer<TransmutationKnowledge>, JsonDeserializer<TransmutationKnowledge>
{
    private static final Gson jsonSerializer = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(TransmutationKnowledge.class, new TransmutationKnowledge()).create();
    private Set<ItemStack> knownTransmutations;
    private boolean hasBeenModified = false;

    public TransmutationKnowledge()
    {
        this(new TreeSet<ItemStack>(Comparators.idComparator));
    }

    public TransmutationKnowledge(Collection<ItemStack> knownTransmutations)
    {
        this.knownTransmutations = new TreeSet<ItemStack>(Comparators.idComparator);
        this.knownTransmutations.addAll(knownTransmutations);
        hasBeenModified = false;
    }

    public TransmutationKnowledge(ItemStack... knownTransmutations)
    {
        this(Arrays.asList(knownTransmutations));
    }

    public boolean isKnown(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;
        return this.knownTransmutations.contains(unitItemStack);
    }

    public Set<ItemStack> getKnownTransmutations()
    {
        return this.knownTransmutations;
    }

    public boolean learnTransmutation(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (!this.knownTransmutations.contains(unitItemStack))
        {
            hasBeenModified = true;
            return this.knownTransmutations.add(unitItemStack);
        }

        return false;
    }

    public boolean forgetTransmutation(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (this.knownTransmutations.contains(unitItemStack))
        {
            hasBeenModified = true;
            return this.knownTransmutations.remove(unitItemStack);
        }

        return false;
    }

    public void forgetAllTransmutations()
    {
        this.knownTransmutations.clear();
        hasBeenModified = true;
    }

    public boolean hasBeenModified()
    {
        return hasBeenModified;
    }

    public Set<ItemStack> filterByNameStartsWith(String filterString)
    {
        return FilterUtils.filterByDisplayName(getKnownTransmutations(), filterString, FilterUtils.NameFilterType.STARTS_WITH);
    }

    public Set<ItemStack> filterByNameContains(String filterString)
    {
        return FilterUtils.filterByDisplayName(getKnownTransmutations(), filterString, FilterUtils.NameFilterType.CONTAINS);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (ItemStack itemStack : knownTransmutations)
        {
            stringBuilder.append(String.format("%s, ", ItemHelper.toString(itemStack)));
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    public static TransmutationKnowledge createFromJson(String jsonTransmutationKnowledge) throws JsonParseException
    {
        try
        {
            return jsonSerializer.fromJson(jsonTransmutationKnowledge, TransmutationKnowledge.class);
        }
        catch (JsonSyntaxException exception)
        {
            exception.printStackTrace();
        }
        catch (JsonParseException exception)
        {
            exception.printStackTrace();
        }

        return null;
    }

    public String toJson()
    {
        return jsonSerializer.toJson(this);
    }

    @Override
    public TransmutationKnowledge deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (json.isJsonObject())
        {
            JsonObject jsonObject = (JsonObject) json;

            Set<ItemStack> itemStacks = new TreeSet<ItemStack>(Comparators.idComparator);

            if (jsonObject.has("knownTransmutations") && jsonObject.get("knownTransmutations").isJsonArray())
            {
                JsonArray jsonArray = (JsonArray) jsonObject.get("knownTransmutations");
                Iterator<JsonElement> iterator = jsonArray.iterator();

                while (iterator.hasNext())
                {
                    JsonElement jsonElement = iterator.next();
                    if (jsonElement.isJsonObject())
                    {
                        try
                        {
                            JsonItemStack jsonItemStack = JsonItemStack.jsonSerializer.fromJson(jsonElement, JsonItemStack.class);

                            ItemStack itemStack = null;
                            Item item = (Item) Item.itemRegistry.getObject(jsonItemStack.itemName);
                            if (item != null)
                            {
                                itemStack = new ItemStack(item, 1, jsonItemStack.itemDamage);
                                if (jsonItemStack.itemNBTTagCompound != null)
                                {
                                    itemStack.stackTagCompound = jsonItemStack.itemNBTTagCompound;
                                }
                            }

                            if (itemStack != null)
                            {
                                itemStacks.add(itemStack);
                            }
                        }
                        catch (JsonParseException e)
                        {
                        }
                    }
                }
            }

            return new TransmutationKnowledge(itemStacks);
        }

        return null;
    }

    @Override
    public JsonElement serialize(TransmutationKnowledge transmutationKnowledge, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonTransmutationKnowledge = new JsonObject();

        JsonArray knownTransmutations = new JsonArray();
        for (ItemStack itemStack : transmutationKnowledge.getKnownTransmutations())
        {
            knownTransmutations.add(JsonItemStack.jsonSerializer.toJsonTree(new JsonItemStack(itemStack)));
        }
        jsonTransmutationKnowledge.add("knownTransmutations", knownTransmutations);

        return jsonTransmutationKnowledge;
    }

    public static void writeToFile(File file, TransmutationKnowledge transmutationKnowledge)
    {
        JsonWriter jsonWriter;

        try
        {
            jsonWriter = new JsonWriter(new FileWriter(file));
            jsonWriter.setIndent("    ");
            jsonSerializer.toJson(transmutationKnowledge, TransmutationKnowledge.class, jsonWriter);
            jsonWriter.close();
            transmutationKnowledge.hasBeenModified = false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static TransmutationKnowledge readFromFile(File file)
    {
        JsonReader jsonReader;

        try
        {
            jsonReader = new JsonReader(new FileReader(file));
            TransmutationKnowledge transmutationKnowledge = jsonSerializer.fromJson(jsonReader, TransmutationKnowledge.class);
            jsonReader.close();
            return transmutationKnowledge;
        }
        catch (FileNotFoundException ignored)
        {
            // NOOP
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
