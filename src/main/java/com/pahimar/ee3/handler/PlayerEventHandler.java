package com.pahimar.ee3.handler;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.File;

public class PlayerEventHandler
{
    public static File playerDataDirectory;
    public static File knowledgeDirectory;

    @SubscribeEvent
    public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event)
    {
        if (!event.entityPlayer.worldObj.isRemote)
        {
            // If player knowledge data doesn't exist, initialize a file for the player
//            File playerTransmutationKnowledgeFile = new File(TransmutationKnowledgeHandler.transmutationKnowledgeDirectory, event.entityPlayer.getUniqueID() + TransmutationKnowledgeHandler.KNOWLEDGE_FILE_EXTENSION);
//            if (!playerTransmutationKnowledgeFile.exists())
//            {
//                TransmutationKnowledgeHandler.savePlayerKnowledge(event.entityPlayer);
//            }
        }
    }

    @SubscribeEvent
    public void syncEnergyValuesOnLogin(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        PacketHandler.INSTANCE.sendTo(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()), (EntityPlayerMP) event.player);
    }
}
