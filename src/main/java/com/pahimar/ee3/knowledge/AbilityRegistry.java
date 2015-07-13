package com.pahimar.ee3.knowledge;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.api.event.AbilityEvent;
import com.pahimar.ee3.api.knowledge.AbilityRegistryProxy;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.serialization.AbilityRegistrySerializer;
import com.pahimar.ee3.util.LoaderHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AbilityRegistry
{
    private static AbilityRegistry abilityRegistry = null;
    private static final Object singletonSyncRoot = new Object();

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

    public AbilityRegistry(Set<WrappedStack> notLearnableSet, Set<WrappedStack> notRecoverableSet)
    {
        this();
        this.notLearnableSet = notLearnableSet;
        this.notRecoverableSet = notRecoverableSet;
    }

    public static AbilityRegistry getInstance()
    {
        if (abilityRegistry == null)
        {
            synchronized (singletonSyncRoot)
            {
                if(abilityRegistry == null)
                {
                    abilityRegistry = new AbilityRegistry();
                    abilityRegistry.init();
                }
            }
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
                    LogHelper.trace(String.format("AbilityRegistry[%s]: Mod with ID '%s' set object %s as LEARNABLE", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack));
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
                    LogHelper.trace(String.format("AbilityRegistry[%s]: Mod with ID '%s' set object %s as NOT LEARNABLE", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack));
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
                    LogHelper.trace(String.format("AbilityRegistry[%s]: Mod with ID '%s' set object %s as RECOVERABLE", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack));
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
                    LogHelper.trace(String.format("AbilityRegistry[%s]: Mod with ID '%s' set object %s as NOT RECOVERABLE", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack));
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
                AbilityRegistrySerializer.toJson(this, jsonWriter);
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
            AbilityRegistry abilityRegistry1 = AbilityRegistrySerializer.createFromJson(jsonReader);
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
        LogHelper.info(String.format("BEGIN DUMPING %s ABILITY OBJECTS", abilityType));
        if (abilityType == AbilityRegistryProxy.Abilities.NOT_LEARNABLE)
        {
            if (this.notLearnableSet != null)
            {
                for (WrappedStack wrappedStack : this.notLearnableSet)
                {
                    LogHelper.info(String.format("- Object: %s", wrappedStack));
                }
            }
        }
        else if (abilityType == AbilityRegistryProxy.Abilities.NOT_RECOVERABLE)
        {
            if (this.notRecoverableSet != null)
            {
                for (WrappedStack wrappedStack : this.notRecoverableSet)
                {
                    LogHelper.info(String.format("- Object: %s", wrappedStack));
                }
            }
        }
        else if (abilityType == AbilityRegistryProxy.Abilities.ALL)
        {
            if (this.notLearnableSet != null)
            {
                LogHelper.info("NOT LEARNABLE OBJECTS");
                for (WrappedStack wrappedStack : this.notLearnableSet)
                {
                    LogHelper.info(String.format("- Object: %s", wrappedStack));
                }
            }

            if (this.notRecoverableSet != null)
            {
                LogHelper.info("NOT RECOVERABLE OBJECTS");
                for (WrappedStack wrappedStack : this.notRecoverableSet)
                {
                    LogHelper.info(String.format("- Object: %s", wrappedStack));
                }
            }
        }
        LogHelper.info(String.format("END DUMPING %s ABILITY OBJECTS", abilityType));
    }
}
