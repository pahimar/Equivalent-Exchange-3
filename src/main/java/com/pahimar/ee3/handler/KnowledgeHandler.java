package com.pahimar.ee3.handler;

import com.pahimar.ee3.knowledge.PlayerKnowledge;
import com.pahimar.ee3.knowledge.TransmutationKnowledge;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class KnowledgeHandler
{
    public static File playerDataDirectory;
    public static File transmutationKnowledgeDirectory;

    public static void writeKnowledgeData(EntityPlayer entityPlayer, File knowledgeDirectory)
    {
        writeKnowledgeData(entityPlayer, knowledgeDirectory, null);
    }

    public static void writeKnowledgeData(EntityPlayer entityPlayer, File knowledgeDirectory, PlayerKnowledge playerKnowledge)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory() && knowledgeDirectory != null && knowledgeDirectory.isDirectory())
        {
            NBTTagCompound playerKnowledgeCompound = null;

            if (playerKnowledge == null)
            {
                if (Settings.Transmutation.useTemplateFile)
                {
                    playerKnowledgeCompound = readTemplateKnowledgeFile(); // TODO Generalize this to the provided directory
                }
            }
            else
            {
                playerKnowledge.writeToNBT(playerKnowledgeCompound);
            }

            try
            {
                File file1 = new File(knowledgeDirectory, entityPlayer.getUniqueID().toString() + KNOWLEDGE_FILE_EXTENSION + ".tmp");
                File file2 = new File(knowledgeDirectory, entityPlayer.getUniqueID().toString() + KNOWLEDGE_FILE_EXTENSION);
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
        new TransmutationKnowledge().writeToNBT(nbtTagCompound);
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
            }
            else
            {
                initializeTemplateFile(null);
            }
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        new TransmutationKnowledge().writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    private static NBTTagCompound readAllowedTransmutationFile()
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
                    new TransmutationKnowledge().writeToNBT(nbtTagCompound);
                    return nbtTagCompound;
                }
            }
            else
            {
                initializeAllowedKnowledgeFile(null);
            }
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        new TransmutationKnowledge().writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    public static TransmutationKnowledge getAllowedTransmutationKnowledge()
    {
        return new TransmutationKnowledge(readAllowedTransmutationFile());
    }

    private static void initializeTemplateFile(TransmutationKnowledge templatePlayerKnowledge)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File templatePlayerKnowledgeFile = new File(playerDataDirectory, TEMPLATE_FILENAME);

            if (templatePlayerKnowledge == null)
            {
                templatePlayerKnowledge = new TransmutationKnowledge();
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

    private static void initializeAllowedKnowledgeFile(TransmutationKnowledge allowedKnowledgeFile)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File templatePlayerKnowledgeFile = new File(playerDataDirectory, ALLOWED_KNOWLEDGE_FILENAME);

            if (allowedKnowledgeFile == null)
            {
                allowedKnowledgeFile = new TransmutationKnowledge();
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
                    LogHelper.warn("Failed to initialize transmutation knowledge template file");
                }
            }
        }
    }


    public static final String KNOWLEDGE_FILE_EXTENSION = ".knowledge";
    private static final String TEMPLATE_FILENAME = "template" + KNOWLEDGE_FILE_EXTENSION;
    private static final String ALLOWED_KNOWLEDGE_FILENAME = "allowedKnowledge" + KNOWLEDGE_FILE_EXTENSION;
}
