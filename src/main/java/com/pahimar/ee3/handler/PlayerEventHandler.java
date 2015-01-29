package com.pahimar.ee3.handler;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.knowledge.KnowledgeRegistry;
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
            KnowledgeRegistry.getInstance().loadPlayerFromDisk(event.entityPlayer);
        }
    }

    @SubscribeEvent
    public void onPlayerSaveToFileEvent(PlayerEvent.SaveToFile event)
    {
        if (!event.entityPlayer.worldObj.isRemote)
        {
            KnowledgeRegistry.getInstance().savePlayerKnowledgeToDisk(event.entityPlayer);
        }
    }

    @SubscribeEvent
    public void syncEnergyValuesOnLogin(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        PacketHandler.INSTANCE.sendTo(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()), (EntityPlayerMP) event.player);
    }
}
