package com.pahimar.ee3.handler;

import com.pahimar.ee3.skill.PlayerKnowledge;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            try
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                new PlayerKnowledge(entityPlayer).writeToNBT(nbttagcompound);
                File file1 = new File(playerDataDirectory, entityPlayer.getUniqueID().toString() + ".ee3.tmp");
                File file2 = new File(playerDataDirectory, entityPlayer.getUniqueID().toString() + ".ee3");
                CompressedStreamTools.writeCompressed(nbttagcompound, new FileOutputStream(file1));

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

    public static PlayerKnowledge readKnowledgeData(EntityPlayer entityPlayer)
    {
        if (playerDataDirectory != null && playerDataDirectory.isDirectory())
        {
            File playerDataFile = new File(playerDataDirectory, entityPlayer.getUniqueID().toString() + ".ee3");

            if (playerDataFile.exists() && playerDataFile.isFile())
            {
                LogHelper.info(playerDataFile.getAbsolutePath());
                try
                {
                    PlayerKnowledge playerKnowledge = PlayerKnowledge.readPlayerKnowledgeFromNBT(CompressedStreamTools.readCompressed(new FileInputStream(playerDataFile)));

                    LogHelper.info(playerKnowledge.getPlayerUUID());

                    for (ItemStack itemStack : playerKnowledge.getKnownItemStacks())
                    {
                        LogHelper.info(ItemHelper.toString(itemStack));
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
