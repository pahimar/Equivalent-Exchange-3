package com.pahimar.ee3.knowledge;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.exchange.JsonItemStack;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.INBTTaggable;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

// TODO Think up of an interface for predictable loading/saving of JSON files for use in SerializationHelper
public class TransmutationKnowledge implements INBTTaggable, JsonSerializer<TransmutationKnowledge>, JsonDeserializer<TransmutationKnowledge>
{
    private static final Gson jsonSerializer = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(TransmutationKnowledge.class, new TransmutationKnowledge()).create();
    private Set<ItemStack> knownTransmutations;

    public TransmutationKnowledge()
    {
        this.knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
    }

    public TransmutationKnowledge(Collection<ItemStack> knownTransmutations)
    {
        this.knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.knownTransmutations.addAll(knownTransmutations);
    }

    public TransmutationKnowledge(ItemStack... knownTransmutations)
    {
        this(Arrays.asList(knownTransmutations));
    }

    public TransmutationKnowledge(NBTTagCompound nbtTagCompound)
    {
        this.knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.readFromNBT(nbtTagCompound);
    }

    public boolean isKnown(Item item)
    {
        return isKnown(new ItemStack(item));
    }

    public boolean isKnown(Block block)
    {
        return isKnown(new ItemStack(block));
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

    public boolean learnTransmutation(Item item)
    {
        return learnTransmutation(new ItemStack(item));
    }

    public boolean learnTransmutation(Block block)
    {
        return learnTransmutation(new ItemStack(block));
    }

    public boolean learnTransmutation(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (!this.knownTransmutations.contains(unitItemStack))
        {
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
            return this.knownTransmutations.remove(unitItemStack);
        }

        return false;
    }

    public void forgetAllTransmutations()
    {
        this.knownTransmutations.clear();
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE))
        {
            if (nbtTagCompound.hasKey(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE))
            {
                NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE, 10);
                knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
                for (int i = 0; i < tagList.tagCount(); ++i)
                {
                    NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                    ItemStack itemStack = ItemStack.loadItemStackFromNBT(tagCompound);
                    knownTransmutations.add(itemStack);
                }
            } else
            {
                knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
            }
        } else
        {
            knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        NBTTagList tagList = new NBTTagList();
        for (ItemStack itemStack : knownTransmutations)
        {
            NBTTagCompound tagCompound = new NBTTagCompound();
            itemStack.writeToNBT(tagCompound);
            tagList.appendTag(tagCompound);
        }
        nbtTagCompound.setTag(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE, tagList);
    }

    @Override
    public String getTagLabel()
    {
        return "TransmutationKnowledge";
    }

    public static TransmutationKnowledge readTransmutationKnowledgeFromNBT(NBTTagCompound nbtTagCompound)
    {
        TransmutationKnowledge transmutationKnowledge = new TransmutationKnowledge();

        transmutationKnowledge.readFromNBT(nbtTagCompound);

        return transmutationKnowledge;
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
        } catch (JsonSyntaxException exception)
        {
            exception.printStackTrace();
        } catch (JsonParseException exception)
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
        if (json.isJsonArray()) {
            JsonArray jsonArray = (JsonArray) json;
            Set<ItemStack> itemStacks = new TreeSet<ItemStack>(ItemHelper.comparator);
            Iterator<JsonElement> iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                JsonElement jsonElement = iterator.next();
                if (jsonElement.isJsonObject()) {
                    JsonItemStack jsonItemStack = jsonSerializer.fromJson(jsonElement, JsonItemStack.class);

                    ItemStack itemStack = null;
                    Item item = (Item) Item.itemRegistry.getObject(jsonItemStack.itemName);
                    if (item != null) {
                        itemStack = new ItemStack(item, 1, jsonItemStack.itemDamage);
                        if (jsonItemStack.itemNBTTagCompound != null) {
                            itemStack.stackTagCompound = jsonItemStack.itemNBTTagCompound;
                        }
                    }

                    if (itemStack != null) {
                        itemStacks.add(itemStack);
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
        JsonArray jsonTransmutationKnowledge = new JsonArray();

        for (ItemStack itemStack : transmutationKnowledge.getKnownTransmutations())
        {
            jsonTransmutationKnowledge.add(jsonSerializer.toJsonTree(new JsonItemStack(itemStack)));
        }

        return jsonTransmutationKnowledge;
    }

    public static void saveToFile(File file, TransmutationKnowledge transmutationKnowledge) {
        JsonWriter jsonWriter;

        try {
            jsonWriter = new JsonWriter(new FileWriter(file));
            jsonWriter.setIndent("    ");
            jsonWriter.beginArray();

            Iterator<ItemStack> iterator = transmutationKnowledge.getKnownTransmutations().iterator();

            while (iterator.hasNext())
            {
                ItemStack itemStack = iterator.next();
                jsonSerializer.toJson(new JsonItemStack(itemStack), JsonItemStack.class, jsonWriter);
            }

            jsonWriter.endArray();
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TransmutationKnowledge readFromFile(File file) {
        Set<ItemStack> itemStacks = new TreeSet<ItemStack>(ItemHelper.comparator);
        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileReader(file));
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                JsonItemStack jsonItemStack = jsonSerializer.fromJson(jsonReader, JsonItemStack.class);

                ItemStack itemStack = null;
                Item item = (Item) Item.itemRegistry.getObject(jsonItemStack.itemName);
                if (item != null) {
                    itemStack = new ItemStack(item, 1, jsonItemStack.itemDamage);
                    if (jsonItemStack.itemNBTTagCompound != null) {
                        itemStack.stackTagCompound = jsonItemStack.itemNBTTagCompound;
                    }
                }

                if (itemStack != null) {
                    itemStacks.add(itemStack);
                }
            }
            jsonReader.endArray();
            jsonReader.close();
        } catch (FileNotFoundException ignored) {
            // NOOP
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new TransmutationKnowledge(itemStacks);
    }
}
