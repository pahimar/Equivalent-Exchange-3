package com.pahimar.ee3.handler;

import com.pahimar.ee3.reference.Settings;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.File;
import java.io.IOException;

public class PlayerEventHandler
{
    @SubscribeEvent
    public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event)
    {
        if (!event.entityPlayer.worldObj.isRemote)
        {
            if (Settings.PLAYER_DAT_LOCATION == null || Settings.PLAYER_DAT_LOCATION.length() == 0 || !Settings.PLAYER_DAT_LOCATION.equalsIgnoreCase(event.playerDirectory.getPath()))
            {
                Settings.PLAYER_DAT_LOCATION = event.playerDirectory.getPath();
            }

            File playerFile = event.getPlayerFile("ee3");
            if (!playerFile.exists())
            {
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
    }
}
