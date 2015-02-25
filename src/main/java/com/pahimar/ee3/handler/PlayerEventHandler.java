package com.pahimar.ee3.handler;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageChalkSettings;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.settings.ChalkSettings;
import com.pahimar.ee3.util.EntityHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerEventHandler
{
    @SubscribeEvent
    public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event)
    {
        if (!event.entityPlayer.worldObj.isRemote)
        {
            TransmutationKnowledgeRegistry.getInstance().loadPlayerFromDiskIfNeeded(event.entityPlayer);
        }
    }

    @SubscribeEvent
    public void onPlayerSaveToFileEvent(PlayerEvent.SaveToFile event)
    {
        if (!event.entityPlayer.worldObj.isRemote)
        {
            TransmutationKnowledgeRegistry.getInstance().savePlayerKnowledgeToDisk(event.entityPlayer);
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.player != null)
        {
            NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(event.player);

            // Chalk Settings
            ChalkSettings chalkSettings = new ChalkSettings();
            chalkSettings.readFromNBT(playerCustomData);
            chalkSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(event.player, playerCustomData);
            PacketHandler.INSTANCE.sendTo(new MessageChalkSettings(chalkSettings), (EntityPlayerMP) event.player);

            TransmutationKnowledgeRegistry.getInstance().loadPlayerFromDiskIfNeeded(event.player);
            PacketHandler.INSTANCE.sendTo(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()), (EntityPlayerMP) event.player);
        }

    }

    @SubscribeEvent
    public void onPlayerLoggedOut(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event)
    {
        if (!event.player.worldObj.isRemote)
        {
            TransmutationKnowledgeRegistry.getInstance().unloadPlayer(event.player);
        }
    }
}
