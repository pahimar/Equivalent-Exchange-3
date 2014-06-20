package com.pahimar.ee3.handler;

import com.pahimar.ee3.thread.DynamicEnergyValueInitThread;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class WorldEventHandler
{
    @SubscribeEvent
    public void onWorldLoadEvent(WorldEvent.Load event)
    {
        DynamicEnergyValueInitThread.initEnergyValueRegistry();
    }
}
