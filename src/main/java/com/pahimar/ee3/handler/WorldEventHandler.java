package com.pahimar.ee3.handler;

import com.pahimar.ee3.helper.EmcInitializationHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class WorldEventHandler
{
    @ForgeSubscribe
    public void onWorldLoaded(WorldEvent.Load event)
    {
        EmcInitializationHelper.initEmcRegistry();
    }
}
