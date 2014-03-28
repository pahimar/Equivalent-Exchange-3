package com.pahimar.ee3.handler;

import com.pahimar.ee3.helper.EmcInitializationHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class WorldEventHandler
{
    @SubscribeEvent
    public void onWorldLoaded(WorldEvent.Load event)
    {
        EmcInitializationHelper.initEmcRegistry();
    }
}
