package com.pahimar.ee3.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.EnergyValueStackMapping;
import com.pahimar.ee3.exchange.WrappedStack;
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
    private static final Gson jsonSerializer = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(EnergyValueStackMapping.class, new EnergyValueStackMapping()).registerTypeAdapter(EnergyValue.class, new EnergyValue()).registerTypeAdapter(WrappedStack.class, new WrappedStack()).create();

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

    public static boolean energyValueRegistryFileExist()
    {
        if (FMLCommonHandler.instance().getMinecraftServerInstance() == null)
        {
            return false;
        }

        File dataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + "ee3");
        if (!dataDirectory.exists())
        {
            return false;
        }
        else if (dataDirectory.isFile())
        {
            return false;
        }

        File file = new File(dataDirectory, SerializationHelper.getModListMD5() + ".ee3");

        return file.exists() && file.isFile();
    }

    public static void writeEnergyValueRegistryToFile()
    {
        if (FMLCommonHandler.instance().getMinecraftServerInstance() != null)
        {
            File dataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + "ee3");
            if (!dataDirectory.exists())
            {
                dataDirectory.mkdir();
            }

            NBTTagCompound energyValueRegistryNBT = new NBTTagCompound();
            EnergyValueRegistry.getInstance().writeToNBT(energyValueRegistryNBT);

            try
            {
                File file1 = new File(dataDirectory, SerializationHelper.getModListMD5() + ".ee3.tmp");
                File file2 = new File(dataDirectory, SerializationHelper.getModListMD5() + ".ee3");
                CompressedStreamTools.writeCompressed(energyValueRegistryNBT, new FileOutputStream(file1));

                if (file2.exists())
                {
                    file2.delete();
                }

                file1.renameTo(file2);

                LogHelper.info("Successfully saved EnergyValues to file: " + file2.getAbsolutePath());
            }
            catch (Exception exception)
            {
                LogHelper.warn("Failed to save EnergyValueRegistry to file " + dataDirectory.getPath() + SerializationHelper.getModListMD5() + ".ee3");
            }
        }
    }

    public static void readEnergyValueRegistryFromFile()
    {
        if (energyValueRegistryFileExist())
        {
            File dataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + "ee3");
            File energyValueRegistryFile = new File(dataDirectory, SerializationHelper.getModListMD5() + ".ee3");

            try
            {
                EnergyValueRegistry.getInstance().readFromNBT(CompressedStreamTools.readCompressed(new FileInputStream(energyValueRegistryFile)));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static Map<WrappedStack, EnergyValue> readEnergyValueStackMapFromJsonFile(String fileName)
    {
        return readEnergyValueStackMapFromJsonFile(getFileInDataDirectory(fileName));
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
                EnergyValueStackMapping energyValueStackMapping = jsonSerializer.fromJson(jsonReader, EnergyValueStackMapping.class);
                energyValueStackMap.put(energyValueStackMapping.wrappedStack, energyValueStackMapping.energyValue);
            }
            jsonReader.endArray();
            jsonReader.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO More intelligent log message
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return energyValueStackMap;
    }

    public static void writeEnergyValueStackMapToJsonFile(String fileName, Map<WrappedStack, EnergyValue> energyValueMap)
    {
        writeEnergyValueStackMapToJsonFile(getFileInDataDirectory(fileName), energyValueMap);
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
                jsonSerializer.toJson(new EnergyValueStackMapping(wrappedStack, energyValueMap.get(wrappedStack)), EnergyValueStackMapping.class, jsonWriter);
            }

            jsonWriter.endArray();
            jsonWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static File getFileInDataDirectory(String fileName)
    {
        if (FMLCommonHandler.instance().getMinecraftServerInstance() != null && FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld() != null)
        {
            File dataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + "ee3");
            File dataFile = new File(dataDirectory, fileName);

            return dataFile;
        }

        return null;
    }
}
