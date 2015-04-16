package com.pahimar.ee3.util;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueStackMapping;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.knowledge.TransmutationKnowledge;
import com.pahimar.ee3.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.util.*;

public class SerializationHelper
{
    private static File dataDirectory;
    private static File playerDataDirectory;

    /**
     * Returns a File reference to the mod specific directory in the data directory
     *
     * @return
     */
    public static File getDataDirectory()
    {
        return dataDirectory;
    }

    /**
     * Returns a File reference to the mod specific directory in the playerdata directory
     *
     * @return
     */
    public static File getPlayerDataDirectory()
    {
        return playerDataDirectory;
    }

    /**
     * Creates (if one does not exist already) and initializes a mod specific File reference inside of the current world's playerdata directory
     */
    public static void initModDataDirectories()
    {
        dataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + Reference.LOWERCASE_MOD_ID);
        dataDirectory.mkdirs();

        playerDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "playerdata" + File.separator + Reference.LOWERCASE_MOD_ID);
        playerDataDirectory.mkdirs();
    }

    public static String getModListMD5()
    {
        List<String> modList = new ArrayList<String>();

        for (ModContainer modContainer : Loader.instance().getModList())
        {
            modList.add("[" + modContainer.getModId() + "-" + modContainer.getName() + "-" + modContainer.getVersion() + "]");
        }

        Collections.sort(modList);

        StringBuilder modListString = new StringBuilder();
        for (String modEntry : modList)
        {
            modListString.append(modEntry);
        }

        return DigestUtils.md5Hex(modListString.toString());
    }

    public static NBTTagCompound readNBTFromFile(File nbtEncodedFile)
    {
        if (nbtEncodedFile.exists() && nbtEncodedFile.isFile())
        {
            try
            {
                return CompressedStreamTools.readCompressed(new FileInputStream(nbtEncodedFile));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void writeNBTToFile(File directory, String fileName, INBTTaggable nbtTaggable)
    {
        writeNBTToFile(directory, fileName, nbtTaggable, false);
    }

    public static void writeNBTToFile(File directory, String fileName, INBTTaggable nbtTaggable, boolean verboseLogging)
    {
        if (directory != null && fileName != null && nbtTaggable != null)
        {
            if (!directory.exists())
            {
                directory.mkdirs();
            }

            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            nbtTaggable.writeToNBT(nbtTagCompound);

            try
            {
                File file1 = new File(directory, fileName + ".tmp");
                File file2 = new File(directory, fileName);
                CompressedStreamTools.writeCompressed(nbtTagCompound, new FileOutputStream(file1));

                if (file2.exists())
                {
                    file2.delete();
                }

                file1.renameTo(file2);

                if (verboseLogging)
                {
                    LogHelper.info(String.format("Successfully saved %s to file: %s", nbtTaggable.getTagLabel(), file2.getAbsolutePath()));
                }
            }
            catch (Exception exception)
            {
                LogHelper.warn(String.format("Failed to save %s to file: %s%s%s", nbtTaggable.getTagLabel(), directory.getAbsolutePath(), File.separator, fileName));
                exception.printStackTrace();
            }
        }
    }

    public static TransmutationKnowledge readTransmutationKnowledgeFromFile(File directory, String fileName)
    {
        if (!directory.exists())
        {
            directory.mkdirs();
        }

        return TransmutationKnowledge.readFromFile(new File(directory, fileName));
    }

    public static void writeTransmutationKnowledgeToFile(File directory, String fileName, TransmutationKnowledge transmutationKnowledge)
    {
        writeTransmutationKnowledgeToFile(directory, fileName, transmutationKnowledge, false);
    }

    public static void writeTransmutationKnowledgeToFile(File directory, String fileName, TransmutationKnowledge transmutationKnowledge, boolean verboseLogging)
    {
        if (directory != null && fileName != null)
        {
            if (!directory.exists())
            {
                directory.mkdirs();
            }

            if (transmutationKnowledge == null)
            {
                transmutationKnowledge = new TransmutationKnowledge();
            }

            try
            {
                File file1 = new File(directory, fileName + ".tmp");
                File file2 = new File(directory, fileName);
                TransmutationKnowledge.writeToFile(file1, transmutationKnowledge);

                if (file2.exists())
                {
                    file2.delete();
                }

                file1.renameTo(file2);

                if (verboseLogging)
                {
                    LogHelper.info(String.format("Successfully saved TransmutationKnowledge to file: %s", file2.getAbsolutePath()));
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                LogHelper.error(String.format("Failed to save TransmutationKnowledge to file: %s%s", directory.getAbsolutePath(), fileName));
            }
        }
    }

    public static Map<WrappedStack, EnergyValue> readEnergyValueStackMapFromJsonFile(String fileName)
    {
        File energyValuesDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + Reference.LOWERCASE_MOD_ID + File.separator + "energyvalues");
        return readEnergyValueStackMapFromJsonFile(new File(energyValuesDataDirectory, fileName));
    }

    public static Map<WrappedStack, EnergyValue> readEnergyValueStackMapFromJsonFile(File jsonFile)
    {
        Map<WrappedStack, EnergyValue> energyValueStackMap = new TreeMap<WrappedStack, EnergyValue>();
        JsonReader jsonReader;

        try
        {
            jsonReader = new JsonReader(new FileReader(jsonFile));
            jsonReader.beginArray();
            while (jsonReader.hasNext())
            {
                EnergyValueStackMapping energyValueStackMapping = EnergyValueStackMapping.jsonSerializer.fromJson(jsonReader, EnergyValueStackMapping.class);
                if (energyValueStackMapping != null)
                {
                    energyValueStackMap.put(energyValueStackMapping.wrappedStack, energyValueStackMapping.energyValue);
                }
            }
            jsonReader.endArray();
            jsonReader.close();
        }
        catch (FileNotFoundException ignored)
        {
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return energyValueStackMap;
    }

    public static void writeEnergyValueStackMapToJsonFile(String fileName, Map<WrappedStack, EnergyValue> energyValueMap)
    {
        File energyValuesDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + Reference.LOWERCASE_MOD_ID + File.separator + "energyvalues");
        writeEnergyValueStackMapToJsonFile(new File(energyValuesDataDirectory, fileName), energyValueMap);
    }

    public static void writeEnergyValueStackMapToJsonFile(File jsonFile, Map<WrappedStack, EnergyValue> energyValueMap)
    {
        JsonWriter jsonWriter;

        try
        {
            jsonWriter = new JsonWriter(new FileWriter(jsonFile));
            jsonWriter.setIndent("    ");
            jsonWriter.beginArray();
            for (WrappedStack wrappedStack : energyValueMap.keySet())
            {
                if (wrappedStack.getWrappedObject() != null)
                {
                    EnergyValueStackMapping.jsonSerializer.toJson(new EnergyValueStackMapping(wrappedStack, energyValueMap.get(wrappedStack)), EnergyValueStackMapping.class, jsonWriter);
                }
            }

            jsonWriter.endArray();
            jsonWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
