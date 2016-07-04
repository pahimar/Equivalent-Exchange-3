package com.pahimar.ee3.handler;

import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageChalkSettings;
import com.pahimar.ee3.network.message.MessageSyncBlacklist;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.settings.ChalkSettings;
import com.pahimar.ee3.util.EntityUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy.Blacklist;

public class PlayerEventHandler {

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {

        if (event.player != null) {
            NBTTagCompound playerCustomData = EntityUtils.getCustomEntityData(event.player);

            // Chalk Settings
            ChalkSettings chalkSettings = new ChalkSettings();
            chalkSettings.readFromNBT(playerCustomData);
            chalkSettings.writeToNBT(playerCustomData);
            EntityUtils.saveCustomEntityData(event.player, playerCustomData);
            Network.INSTANCE.sendTo(new MessageChalkSettings(chalkSettings), (EntityPlayerMP) event.player);

            Network.INSTANCE.sendTo(new MessageSyncEnergyValues(), (EntityPlayerMP) event.player);
            Network.INSTANCE.sendTo(new MessageSyncBlacklist(Blacklist.KNOWLEDGE), (EntityPlayerMP) event.player);
            Network.INSTANCE.sendTo(new MessageSyncBlacklist(Blacklist.EXCHANGE), (EntityPlayerMP) event.player);
        }
    }
}
