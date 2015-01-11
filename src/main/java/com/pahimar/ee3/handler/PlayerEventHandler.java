package com.pahimar.ee3.handler;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.knowledge.TransmutationKnowledge;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.reference.Reference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.File;

public class PlayerEventHandler
{
    @SubscribeEvent
    public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event)
    {
        if (!event.entityPlayer.worldObj.isRemote)
        {
            // Grab the correct directory to be reading/writing player knowledge data to
            if (KnowledgeHandler.playerDataDirectory == null || !KnowledgeHandler.playerDataDirectory.getAbsolutePath().equalsIgnoreCase(event.playerDirectory.getAbsolutePath()))
            {
                KnowledgeHandler.playerDataDirectory = new File(event.playerDirectory, Reference.MOD_ID.toLowerCase());

                if (!KnowledgeHandler.playerDataDirectory.exists())
                {
                    KnowledgeHandler.playerDataDirectory.mkdir();
                }

                KnowledgeHandler.transmutationKnowledgeDirectory = new File(KnowledgeHandler.playerDataDirectory, new TransmutationKnowledge().getKnowledgeLabel());

                if (!KnowledgeHandler.transmutationKnowledgeDirectory.exists())
                {
                    KnowledgeHandler.transmutationKnowledgeDirectory.mkdir();
                }
            }

            // If player knowledge data doesn't exist, initialize a file for the player
            File playerTransmutationKnowledgeFile = new File(KnowledgeHandler.transmutationKnowledgeDirectory, event.entityPlayer.getUniqueID() + KnowledgeHandler.KNOWLEDGE_FILE_EXTENSION);
            if (!playerTransmutationKnowledgeFile.exists())
            {
                KnowledgeHandler.writeKnowledgeData(event.entityPlayer, KnowledgeHandler.transmutationKnowledgeDirectory);
            }
        }
    }

    @SubscribeEvent
    public void syncEnergyValuesOnLogin(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        PacketHandler.INSTANCE.sendTo(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()), (EntityPlayerMP) event.player);
    }
}
