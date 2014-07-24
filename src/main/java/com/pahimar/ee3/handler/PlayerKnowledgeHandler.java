package com.pahimar.ee3.handler;

import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.skill.PlayerKnowledge;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PlayerKnowledgeHandler
{
    public static File playerDataDirectory;

    public static void writeKnowledgeData(EntityPlayer entityPlayer)
    {
        writeKnowledgeData(entityPlayer, null);
    }

    public static void writeKnowledgeData(EntityPlayer entityPlayer, PlayerKnowledge playerKnowledge)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            NBTTagCompound playerKnowledgeCompound = null;

            if (playerKnowledge == null)
            {
                if (Settings.Transmutation.useTemplateFile)
                {
                    playerKnowledgeCompound = readTemplateKnowledgeFile();
                }
                else
                {
                    new PlayerKnowledge().writeToNBT(playerKnowledgeCompound);
                }
            }
            else
            {
                playerKnowledge.writeToNBT(playerKnowledgeCompound);
            }

            try
            {
                File file1 = new File(playerDataDirectory, entityPlayer.getUniqueID().toString() + KNOWLEDGE_FILE_EXTENSION + ".tmp");
                File file2 = new File(playerDataDirectory, entityPlayer.getUniqueID().toString() + KNOWLEDGE_FILE_EXTENSION);
                CompressedStreamTools.writeCompressed(playerKnowledgeCompound, new FileOutputStream(file1));

                if (file2.exists())
                {
                    file2.delete();
                }

                file1.renameTo(file2);
            }
            catch (Exception exception)
            {
                LogHelper.warn("Failed to save player knowledge for " + entityPlayer.getCommandSenderName());
            }
        }
    }

    public static NBTTagCompound readPlayerKnowledgeFile(EntityPlayer entityPlayer)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File playerDataFile = new File(playerDataDirectory, entityPlayer.getUniqueID().toString() + KNOWLEDGE_FILE_EXTENSION);

            if (playerDataFile.exists() && playerDataFile.isFile())
            {
                try
                {
                    return CompressedStreamTools.readCompressed(new FileInputStream(playerDataFile));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        new PlayerKnowledge().writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    private static NBTTagCompound readTemplateKnowledgeFile()
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File templateKnowledgeFile = new File(playerDataDirectory, TEMPLATE_FILENAME);

            if (templateKnowledgeFile.exists() && templateKnowledgeFile.isFile())
            {
                try
                {
                    return CompressedStreamTools.readCompressed(new FileInputStream(templateKnowledgeFile));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    NBTTagCompound nbtTagCompound = new NBTTagCompound();
                    new PlayerKnowledge().writeToNBT(nbtTagCompound);
                    return nbtTagCompound;
                }
            }
            else
            {
                initializeTemplateFile(null);
            }
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        new PlayerKnowledge().writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    private static NBTTagCompound readAllowedKnowledgeFile()
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File allowedKnowledgeFile = new File(playerDataDirectory, ALLOWED_KNOWLEDGE_FILENAME);

            if (allowedKnowledgeFile.exists() && allowedKnowledgeFile.isFile())
            {
                try
                {
                    return CompressedStreamTools.readCompressed(new FileInputStream(allowedKnowledgeFile));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    NBTTagCompound nbtTagCompound = new NBTTagCompound();
                    new PlayerKnowledge().writeToNBT(nbtTagCompound);
                    return nbtTagCompound;
                }
            }
            else
            {
                initializeAllowedKnowledgeFile(null);
            }
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        new PlayerKnowledge().writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    public static PlayerKnowledge getAllowedPlayerKnowledge()
    {
        return new PlayerKnowledge(readAllowedKnowledgeFile());
    }

    private static void initializeTemplateFile(PlayerKnowledge templatePlayerKnowledge)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File templatePlayerKnowledgeFile = new File(playerDataDirectory, TEMPLATE_FILENAME);

            if (templatePlayerKnowledge == null)
            {
                templatePlayerKnowledge = new PlayerKnowledge();
            }

            if (!templatePlayerKnowledgeFile.exists())
            {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                templatePlayerKnowledge.writeToNBT(nbtTagCompound);

                try
                {
                    CompressedStreamTools.writeCompressed(nbtTagCompound, new FileOutputStream(templatePlayerKnowledgeFile));
                }
                catch (Exception exception)
                {
                    LogHelper.warn("Failed to initialize player knowledge template file");
                }
            }
        }
    }

    private static void initializeAllowedKnowledgeFile(PlayerKnowledge allowedKnowledgeFile)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File templatePlayerKnowledgeFile = new File(playerDataDirectory, ALLOWED_KNOWLEDGE_FILENAME);

            if (allowedKnowledgeFile == null)
            {
                allowedKnowledgeFile = new PlayerKnowledge();
            }

            if (!templatePlayerKnowledgeFile.exists())
            {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                allowedKnowledgeFile.writeToNBT(nbtTagCompound);

                try
                {
                    CompressedStreamTools.writeCompressed(nbtTagCompound, new FileOutputStream(templatePlayerKnowledgeFile));
                }
                catch (Exception exception)
                {
                    LogHelper.warn("Failed to initialize player knowledge template file");
                }
            }
        }
    }


    public static final String KNOWLEDGE_FILE_EXTENSION = ".ee3";
    private static final String TEMPLATE_FILENAME = "template" + KNOWLEDGE_FILE_EXTENSION;
    private static final String ALLOWED_KNOWLEDGE_FILENAME = "allowedKnowledge" + KNOWLEDGE_FILE_EXTENSION;
}
