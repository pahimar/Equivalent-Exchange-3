package com.pahimar.ee3.handler;

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

public class TransmutationKnowledgeHandler
{
    public static File playerDataDirectory;
    public static File transmutationKnowledgeDirectory;

    private static TransmutationKnowledge allowedTransmutationKnowledge;
    private static TransmutationKnowledge templateTransmutationKnowledge;
    // TODO Look into caching TransmutationKnowledge for currently logged in players, rather than going to disk constantly

    public static NBTTagCompound getPlayerKnowledge(EntityPlayer entityPlayer)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File playerDataFile = new File(playerDataDirectory, entityPlayer.getUniqueID().toString() + KNOWLEDGE_FILE_EXTENSION);

            if (playerDataFile.exists() && playerDataFile.isFile())
            {
                try
                {
                    return CompressedStreamTools.readCompressed(new FileInputStream(playerDataFile));
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        new TransmutationKnowledge().writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    public static void savePlayerKnowledge(EntityPlayer entityPlayer)
    {
        savePlayerKnowledge(entityPlayer, null);
    }

    public static void savePlayerKnowledge(EntityPlayer entityPlayer, TransmutationKnowledge transmutationKnowledge)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory() && transmutationKnowledgeDirectory != null && transmutationKnowledgeDirectory.isDirectory())
        {
            NBTTagCompound transmutationKnowledgeCompound = null;

            if (transmutationKnowledge == null)
            {
                if (Settings.Transmutation.useTemplateFile)
                {
                    transmutationKnowledgeCompound = getTemplateKnowledgeCompound();
                }
            }
            else
            {
                transmutationKnowledge.writeToNBT(transmutationKnowledgeCompound);
            }

            try
            {
                File file1 = new File(transmutationKnowledgeDirectory, entityPlayer.getUniqueID().toString() + KNOWLEDGE_FILE_EXTENSION + ".tmp");
                File file2 = new File(transmutationKnowledgeDirectory, entityPlayer.getUniqueID().toString() + KNOWLEDGE_FILE_EXTENSION);
                CompressedStreamTools.writeCompressed(transmutationKnowledgeCompound, new FileOutputStream(file1));

                if (file2.exists())
                {
                    file2.delete();
                }

                file1.renameTo(file2);
            }
            catch (Exception exception)
            {
                LogHelper.warn("Failed to save transmutation knowledge for player '" + entityPlayer.getCommandSenderName() + "'");
            }
        }
    }

    public static TransmutationKnowledge getTemplateKnowledge()
    {
        if (templateTransmutationKnowledge == null)
        {
            // TODO Init
        }

        return templateTransmutationKnowledge;
    }

    private static void initializeTemplateKnowledge(TransmutationKnowledge templatePlayerKnowledge)
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
                } catch (Exception exception)
                {
                    LogHelper.warn("Failed to initialize player knowledge template file");
                }
            }
        }
    }

    private static NBTTagCompound getTemplateKnowledgeCompound()
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
                initializeTemplateKnowledge(null);
            }
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        new TransmutationKnowledge().writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    public static void saveTemplateKnowledge()
    {

    }

    public static TransmutationKnowledge getAllowedTransmutations()
    {
        if (allowedTransmutationKnowledge == null)
        {
            // TODO Init
        }

        return allowedTransmutationKnowledge;
//        return new TransmutationKnowledge(getAllowedKnowledgeFile());
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

    private static NBTTagCompound getAllowedKnowledgeFile()
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File allowedKnowledgeFile = new File(playerDataDirectory, ALLOWED_KNOWLEDGE_FILENAME);

            if (allowedKnowledgeFile.exists() && allowedKnowledgeFile.isFile())
            {
                try
                {
                    return CompressedStreamTools.readCompressed(new FileInputStream(allowedKnowledgeFile));
                } catch (IOException e)
                {
                    e.printStackTrace();
                } finally
                {
                    NBTTagCompound nbtTagCompound = new NBTTagCompound();
                    new TransmutationKnowledge().writeToNBT(nbtTagCompound);
                    return nbtTagCompound;
                }
            } else
            {
                initializeAllowedKnowledgeFile(null);
            }
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        new TransmutationKnowledge().writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    public static void saveAllowedKnowledge()
    {

    }

    public static final String KNOWLEDGE_FILE_EXTENSION = ".knowledge";
    private static final String TEMPLATE_FILENAME = "template" + KNOWLEDGE_FILE_EXTENSION;
    private static final String ALLOWED_KNOWLEDGE_FILENAME = "allowedKnowledge" + KNOWLEDGE_FILE_EXTENSION;
}
