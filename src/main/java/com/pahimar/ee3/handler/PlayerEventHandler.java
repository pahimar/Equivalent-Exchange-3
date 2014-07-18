package com.pahimar.ee3.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
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
            if (PlayerKnowledgeHandler.playerDataDirectory == null || !PlayerKnowledgeHandler.playerDataDirectory.getAbsolutePath().equalsIgnoreCase(event.playerDirectory.getAbsolutePath()))
            {
                PlayerKnowledgeHandler.playerDataDirectory = event.playerDirectory;
            }

            // If player knowledge data doesn't exist, initialize a file for the player
            File playerDataFile = event.getPlayerFile("ee3");
            if (!playerDataFile.exists())
            {
                PlayerKnowledgeHandler.writeKnowledgeData(event.entityPlayer);
            }
        }
    }
}
