package com.pahimar.ee3.util;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SerializationHelper
{
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
}
