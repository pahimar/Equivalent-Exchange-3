package com.pahimar.ee3.handler;

import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.File;
import java.io.IOException;

public class PlayerEventHandler
{
    @SubscribeEvent
    public void onPlayerSaveToFileEvent(PlayerEvent.SaveToFile event)
    {
        LogHelper.info("Save Event: " + event.playerUUID);
        File playerFile = event.getPlayerFile("ee3");
        if (!playerFile.exists())
        {
            LogHelper.info("Creating knowledge file for player with UUID: " + event.playerUUID);
            try
            {
                playerFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event)
    {
        LogHelper.info("Load Event: " + event.playerUUID);

        File playerFile = event.getPlayerFile("ee3");
        try
        {
            playerFile.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
