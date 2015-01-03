package com.pahimar.ee3.handler;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.*;

public class PlayerEventHandler
{
    @SubscribeEvent
    public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event)
    {
        if (!event.entityPlayer.worldObj.isRemote)
        {
            // Grab the correct directory to be reading/writing player knowledge data to
            if (PlayerKnowledgeHandler.playerDataDirectory == null || !PlayerKnowledgeHandler.playerDataDirectory.getAbsolutePath().equalsIgnoreCase(event.playerDirectory.getAbsolutePath()))
            {
                PlayerKnowledgeHandler.playerDataDirectory = new File(event.playerDirectory, Reference.MOD_ID.toLowerCase());

                if (!PlayerKnowledgeHandler.playerDataDirectory.exists())
                {
                    PlayerKnowledgeHandler.playerDataDirectory.mkdir();
                }
            }

            // If player knowledge data doesn't exist, initialize a file for the player
            File playerDataFile = new File(PlayerKnowledgeHandler.playerDataDirectory, event.entityPlayer.getUniqueID() + PlayerKnowledgeHandler.KNOWLEDGE_FILE_EXTENSION);
            if (!playerDataFile.exists())
            {
                // Add the player name and the knowledge file to the legend file (makes for easier referencing of files to players)
                try
                {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(PlayerKnowledgeHandler.playerDataDirectory, "legend.txt"), true)));
                    out.println(String.format("Player Name = %s", event.entityPlayer.getCommandSenderName()));
                    out.println(String.format("Knowledge File = %s", event.entityPlayer.getUniqueID() + PlayerKnowledgeHandler.KNOWLEDGE_FILE_EXTENSION));
                    out.println();
                    out.close();
                }
                catch (IOException e)
                {
                    LogHelper.warn("Could not add player ");
                }

                PlayerKnowledgeHandler.writeKnowledgeData(event.entityPlayer);
            }
        }
    }

    @SubscribeEvent
    public void syncEnergyValuesOnLogin(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        PacketHandler.INSTANCE.sendTo(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()), (EntityPlayerMP) event.player);
    }
}
