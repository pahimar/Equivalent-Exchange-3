package com.pahimar.ee3.knowledge;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.exchange.JsonItemStack;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.serialization.TransmutationKnowledgeSerializer;
import com.pahimar.ee3.util.FilterUtils;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class TransmutationKnowledge
{
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
        return FilterUtils.filterByNameStartsWith(getKnownTransmutations(), filterString);
    }

    public Set<ItemStack> filterByNameContains(String filterString)
    {
        return FilterUtils.filterByNameContains(getKnownTransmutations(), filterString);
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

    public static TransmutationKnowledge createFromJson(String jsonTransmutationKnowledge)
    {
        try
        {
            return TransmutationKnowledgeSerializer.createFromJson(jsonTransmutationKnowledge);
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
        return TransmutationKnowledgeSerializer.toJson(this);
    }

    public static void writeToFile(File file, TransmutationKnowledge transmutationKnowledge)
    {
        JsonWriter jsonWriter;

        try
        {
            jsonWriter = new JsonWriter(new FileWriter(file));
            jsonWriter.setIndent("    ");
            TransmutationKnowledgeSerializer.toJson(transmutationKnowledge, jsonWriter);
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
            TransmutationKnowledge transmutationKnowledge = TransmutationKnowledgeSerializer.createFromJson(jsonReader);
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
