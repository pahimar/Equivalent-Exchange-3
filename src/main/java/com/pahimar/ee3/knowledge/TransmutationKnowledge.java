package com.pahimar.ee3.knowledge;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.exchange.JsonItemStack;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.INBTTaggable;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class TransmutationKnowledge implements INBTTaggable, JsonSerializer<TransmutationKnowledge>, JsonDeserializer<TransmutationKnowledge>
{
    private static final Gson jsonSerializer = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(TransmutationKnowledge.class, new TransmutationKnowledge()).create();
    private boolean canTransmuteEverything;
    private Set<ItemStack> knownTransmutations;
    private boolean hasBeenModified = false;

    public TransmutationKnowledge()
    {
        this(new TreeSet<ItemStack>(ItemHelper.comparator));
    }

    public TransmutationKnowledge(Collection<ItemStack> knownTransmutations)
    {
        this(knownTransmutations, false);
    }

    public TransmutationKnowledge(Collection<ItemStack> knownTransmutations, boolean canTransmuteEverything)
    {
        this.canTransmuteEverything = canTransmuteEverything;
        this.knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.knownTransmutations.addAll(knownTransmutations);
        hasBeenModified = false;
    }

    public TransmutationKnowledge(ItemStack... knownTransmutations)
    {
        this(Arrays.asList(knownTransmutations));
    }

    public TransmutationKnowledge(NBTTagCompound nbtTagCompound)
    {
        canTransmuteEverything = false;
        this.knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.readFromNBT(nbtTagCompound);
        hasBeenModified = false;
    }

    public boolean isKnown(ItemStack itemStack)
    {
        if (canTransmuteEverything)
        {
            return true;
        }
        else
        {
            ItemStack unitItemStack = itemStack.copy();
            unitItemStack.stackSize = 1;
            return this.knownTransmutations.contains(unitItemStack);
        }
    }

    public boolean canTransmuteEverything()
    {
        return canTransmuteEverything;
    }

    public void setCanTransmuteEverything(boolean canTransmuteEverything)
    {
        this.canTransmuteEverything = canTransmuteEverything;
        hasBeenModified = true;
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
        canTransmuteEverything = false;
        hasBeenModified = true;
    }

    public boolean hasBeenModified()
    {
        return hasBeenModified;
    }

    @Override
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
            }
            else
            {
                knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
            }

            if (nbtTagCompound.hasKey(Names.NBT.CAN_TRANSMUTE_ANYTHING))
            {
                canTransmuteEverything = nbtTagCompound.getBoolean(Names.NBT.CAN_TRANSMUTE_ANYTHING);
            }
            else
            {
                canTransmuteEverything = false;
            }
        }
        else
        {
            knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
            canTransmuteEverything = false;
        }
    }

    @Override
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
        nbtTagCompound.setBoolean(Names.NBT.CAN_TRANSMUTE_ANYTHING, canTransmuteEverything);
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

            boolean canTransmuteEverything;
            Set<ItemStack> itemStacks = new TreeSet<ItemStack>(ItemHelper.comparator);

            if (jsonObject.has("canTransmuteEverything") && jsonObject.get("canTransmuteEverything").isJsonPrimitive())
            {
                canTransmuteEverything = jsonObject.get("canTransmuteEverything").getAsBoolean();
            }
            else
            {
                canTransmuteEverything = false;
            }

            if (jsonObject.has("knownTransmutations") && jsonObject.get("knownTransmutations").isJsonArray())
            {
                JsonArray jsonArray = (JsonArray) jsonObject.get("knownTransmutations");
                Iterator<JsonElement> iterator = jsonArray.iterator();

                while (iterator.hasNext())
                {
                    JsonElement jsonElement = iterator.next();
                    if (jsonElement.isJsonObject())
                    {
                        JsonItemStack jsonItemStack = jsonSerializer.fromJson(jsonElement, JsonItemStack.class);

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
                }
            }

            return new TransmutationKnowledge(itemStacks, canTransmuteEverything);
        }

        return null;
    }

    @Override
    public JsonElement serialize(TransmutationKnowledge transmutationKnowledge, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonTransmutationKnowledge = new JsonObject();

        jsonTransmutationKnowledge.addProperty("canTransmuteEverything", transmutationKnowledge.canTransmuteEverything());

        JsonArray knownTransmutations = new JsonArray();
        for (ItemStack itemStack : transmutationKnowledge.getKnownTransmutations())
        {
            knownTransmutations.add(jsonSerializer.toJsonTree(new JsonItemStack(itemStack)));
        }
        jsonTransmutationKnowledge.add("knownTransmutations", knownTransmutations);

        return jsonTransmutationKnowledge;
    }

    public static void saveToFile(File file, TransmutationKnowledge transmutationKnowledge)
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
