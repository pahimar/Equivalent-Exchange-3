package com.pahimar.ee3.knowledge;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.api.event.AbilityEvent;
import com.pahimar.ee3.api.knowledge.AbilityRegistryProxy;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.util.LoaderHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AbilityRegistry implements JsonSerializer<AbilityRegistry>, JsonDeserializer<AbilityRegistry>
{
    public static final Marker ABILITY_MARKER = MarkerManager.getMarker("EE3_ABILITY", LogHelper.MOD_MARKER);
    private static final Marker NOT_LEARABLE_MARKER = MarkerManager.getMarker("EE3_ABILITY_NOT_LEARNABLE", ABILITY_MARKER);
    private static final Marker LEARABLE_MARKER = MarkerManager.getMarker("EE3_ABILITY_LEARNABLE", ABILITY_MARKER);
    private static final Marker NOT_RECOVERABLE_MARKER = MarkerManager.getMarker("EE3_ABILITY_NOT_RECOVERABLE", ABILITY_MARKER);
    private static final Marker RECOVERABLE_MARKER = MarkerManager.getMarker("EE3_ABILITY_RECOVERABLE", ABILITY_MARKER);

    private static final Gson jsonSerializer = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(AbilityRegistry.class, new AbilityRegistry()).create();
    private static AbilityRegistry abilityRegistry = null;

    private static File abilityDirectory;
    private boolean hasBeenModified;
    private Set<WrappedStack> notLearnableSet;
    private Set<WrappedStack> notRecoverableSet;

    private AbilityRegistry()
    {
        hasBeenModified = false;
        notLearnableSet = new TreeSet<WrappedStack>();
        notRecoverableSet = new TreeSet<WrappedStack>();
    }

    public static AbilityRegistry getInstance()
    {
        if (abilityRegistry == null)
        {
            abilityRegistry = new AbilityRegistry();
            abilityRegistry.init();
        }

        return abilityRegistry;
    }

    private void init()
    {
        notLearnableSet = new TreeSet<WrappedStack>();
        notRecoverableSet = new TreeSet<WrappedStack>();
    }

    public Set<WrappedStack> getNotLearnableStacks()
    {
        return this.notLearnableSet;
    }

    public boolean isLearnable(Object object)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack wrappedObject = WrappedStack.wrap(object);

            if (object instanceof ItemStack && ((ItemStack) object).isItemDamaged())
            {
                return false;
            }
            else
            {
                return !notLearnableSet.contains(wrappedObject) && EnergyValueRegistry.getInstance().hasEnergyValue(wrappedObject);
            }
        }

        return false;
    }

    public void setAsLearnable(Object object)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack wrappedStack = WrappedStack.wrap(object);

            if (wrappedStack != null && !MinecraftForge.EVENT_BUS.post(new AbilityEvent.SetLearnableEvent(object)))
            {
                if (notLearnableSet.remove(wrappedStack))
                {
                    hasBeenModified = true;
                    LogHelper.trace(LEARABLE_MARKER, "[{}] Mod with ID '{}' set object {} as LEARNABLE", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack);
                }
            }
        }
    }

    public void setAsNotLearnable(Object object)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack wrappedStack = WrappedStack.wrap(object);

            if (wrappedStack != null && !MinecraftForge.EVENT_BUS.post(new AbilityEvent.SetNotLearnableEvent(object)))
            {
                if (notLearnableSet.add(wrappedStack))
                {
                    hasBeenModified = true;
                    LogHelper.trace(NOT_LEARABLE_MARKER, "[{}] Mod with ID '{}' set object {} as NOT LEARNABLE", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack);
                }
            }
        }
    }

    public Set<WrappedStack> getNotRecoverableSet()
    {
        return this.notRecoverableSet;
    }

    public boolean isRecoverable(Object object)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack wrappedObject = WrappedStack.wrap(object);
            return !notRecoverableSet.contains(wrappedObject) && EnergyValueRegistry.getInstance().hasEnergyValue(wrappedObject);
        }

        return false;
    }

    public void setAsRecoverable(Object object)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack wrappedStack = WrappedStack.wrap(object);

            if (wrappedStack != null && !MinecraftForge.EVENT_BUS.post(new AbilityEvent.SetRecoverableEvent(object)))
            {
                if (notRecoverableSet.remove(wrappedStack))
                {
                    hasBeenModified = true;
                    LogHelper.trace(RECOVERABLE_MARKER, "[{}] Mod with ID '{}' set object {} as RECOVERABLE", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack);
                }
            }
        }
    }

    public void setAsNotRecoverable(Object object)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack wrappedStack = WrappedStack.wrap(object);

            if (wrappedStack != null && !MinecraftForge.EVENT_BUS.post(new AbilityEvent.SetNotRecoverableEvent(object)))
            {
                if (notRecoverableSet.add(wrappedStack))
                {
                    hasBeenModified = true;
                    LogHelper.trace(NOT_RECOVERABLE_MARKER, "[{}] Mod with ID '{}' set object {} as NOT RECOVERABLE", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack);
                }
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Not Learnables: ");
        for (WrappedStack wrappedStack : notLearnableSet)
        {
            stringBuilder.append(wrappedStack + " ");
        }
        stringBuilder.append(", Not Recoverables: ");
        for (WrappedStack wrappedStack : notRecoverableSet)
        {
            stringBuilder.append(wrappedStack + " ");
        }

        return stringBuilder.toString();
    }

    @Override
    public AbilityRegistry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (json.isJsonObject())
        {
            JsonObject jsonObject = (JsonObject) json;

            Set<WrappedStack> notLearnableStacks = new TreeSet<WrappedStack>();
            Set<WrappedStack> notRecoverableStacks = new TreeSet<WrappedStack>();

            if (jsonObject.has("notLearnable") && jsonObject.get("notLearnable").isJsonArray())
            {
                JsonArray jsonArray = (JsonArray) jsonObject.get("notLearnable");
                Iterator<JsonElement> iterator = jsonArray.iterator();

                while (iterator.hasNext())
                {
                    JsonElement jsonElement = iterator.next();
                    WrappedStack wrappedStack = WrappedStack.jsonSerializer.fromJson(jsonElement, WrappedStack.class);

                    if (wrappedStack != null)
                    {
                        notLearnableStacks.add(wrappedStack);
                    }
                }
            }

            if (jsonObject.has("notRecoverable") && jsonObject.get("notRecoverable").isJsonArray())
            {
                JsonArray jsonArray = (JsonArray) jsonObject.get("notRecoverable");
                Iterator<JsonElement> iterator = jsonArray.iterator();

                while (iterator.hasNext())
                {
                    JsonElement jsonElement = iterator.next();
                    WrappedStack wrappedStack = WrappedStack.jsonSerializer.fromJson(jsonElement, WrappedStack.class);

                    if (wrappedStack != null)
                    {
                        notRecoverableStacks.add(wrappedStack);
                    }
                }
            }

            AbilityRegistry abilityRegistry1 = new AbilityRegistry();
            abilityRegistry1.notLearnableSet = notLearnableStacks;
            abilityRegistry1.notRecoverableSet = notRecoverableStacks;
            return abilityRegistry1;
        }

        return null;
    }

    @Override
    public JsonElement serialize(AbilityRegistry abilityRegistry, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonAbilityRegistry = new JsonObject();

        JsonArray notLearnables = new JsonArray();
        for (WrappedStack wrappedStack : abilityRegistry.getNotLearnableStacks())
        {
            notLearnables.add(WrappedStack.jsonSerializer.toJsonTree(wrappedStack));
        }
        jsonAbilityRegistry.add("notLearnable", notLearnables);

        JsonArray notRecoverables = new JsonArray();
        for (WrappedStack wrappedStack : abilityRegistry.getNotRecoverableSet())
        {
            notRecoverables.add(WrappedStack.jsonSerializer.toJsonTree(wrappedStack));
        }
        jsonAbilityRegistry.add("notRecoverable", notRecoverables);

        return jsonAbilityRegistry;
    }

    public void save()
    {
        if (abilityDirectory != null)
        {
            abilityDirectory.mkdirs();
            writeToFile(new File(abilityDirectory, Files.ABILITIES_JSON_FILE));
        }
    }

    private void writeToFile(File file)
    {
        JsonWriter jsonWriter;

        if (hasBeenModified)
        {
            try
            {
                jsonWriter = new JsonWriter(new FileWriter(file));
                jsonWriter.setIndent("    ");
                jsonSerializer.toJson(this, AbilityRegistry.class, jsonWriter);
                jsonWriter.close();
                hasBeenModified = false;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void loadAbilityRegistryFromFile(boolean loadFileOnly)
    {
        if (abilityDirectory != null)
        {
            File abilitiesFile = new File(abilityDirectory, Files.ABILITIES_JSON_FILE);

            if (abilitiesFile.exists())
            {
                readFromFile(abilitiesFile, loadFileOnly);
            }
        }
        else
        {
            abilityDirectory = new File(SerializationHelper.getInstanceDataDirectory(), "abilities");
            abilityDirectory.mkdirs();
        }
    }

    private void readFromFile(File file, boolean loadFileOnly)
    {
        JsonReader jsonReader;

        try
        {
            jsonReader = new JsonReader(new FileReader(file));
            AbilityRegistry abilityRegistry1 = jsonSerializer.fromJson(jsonReader, AbilityRegistry.class);
            jsonReader.close();

            if (!loadFileOnly)
            {
                for (WrappedStack wrappedStack : abilityRegistry1.getNotLearnableStacks())
                {
                    if (!this.notLearnableSet.contains(wrappedStack))
                    {
                        this.notLearnableSet.add(wrappedStack);
                    }
                }

                for (WrappedStack wrappedStack : abilityRegistry1.getNotRecoverableSet())
                {
                    if (!this.notRecoverableSet.contains(wrappedStack))
                    {
                        this.notRecoverableSet.add(wrappedStack);
                    }
                }
            }
            else
            {
                this.notLearnableSet = abilityRegistry1.notLearnableSet;
                this.notRecoverableSet = abilityRegistry1.notRecoverableSet;
            }

            hasBeenModified = true;
        }
        catch (FileNotFoundException ignored)
        {
            // NOOP
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void dumpAbilityRegistryToLog()
    {
        dumpAbilityRegistryToLog(AbilityRegistryProxy.Abilities.ALL);
    }

    public void dumpAbilityRegistryToLog(AbilityRegistryProxy.Abilities abilityType)
    {
        if (abilityType == AbilityRegistryProxy.Abilities.NOT_LEARNABLE) {
            if (this.notLearnableSet != null) {
                for (WrappedStack wrappedStack : this.notLearnableSet) {
                    LogHelper.info(NOT_LEARABLE_MARKER, "Not Learnable: {}", wrappedStack);
                }
            }
        } else if (abilityType == AbilityRegistryProxy.Abilities.NOT_RECOVERABLE) {
            if (this.notRecoverableSet != null) {
                for (WrappedStack wrappedStack : this.notRecoverableSet) {
                    LogHelper.info(NOT_RECOVERABLE_MARKER, "Not Recoverable: {}", wrappedStack);
                }
            }
        } else if (abilityType == AbilityRegistryProxy.Abilities.ALL) {
            if (this.notLearnableSet != null) {
                for (WrappedStack wrappedStack : this.notLearnableSet) {
                    LogHelper.info(NOT_LEARABLE_MARKER, "Not Learnable: {}", wrappedStack);
                }
            }

            if (this.notRecoverableSet != null) {
                for (WrappedStack wrappedStack : this.notRecoverableSet) {
                    LogHelper.info(NOT_RECOVERABLE_MARKER, "Not Recoverable: {}", wrappedStack);
                }
            }
        }
    }
}
