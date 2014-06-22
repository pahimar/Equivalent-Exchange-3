package com.pahimar.ee3.handler;

import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerEventHandler
{
    @SubscribeEvent
    public void onPlayerSaveToFileEvent(PlayerEvent.SaveToFile event)
    {
        LogHelper.info("Save Event: " + event.playerUUID);
    }

    @SubscribeEvent
    public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event)
    {
        LogHelper.info("Load Event: " + event.playerUUID);
    }
}
