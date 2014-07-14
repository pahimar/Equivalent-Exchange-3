package com.pahimar.ee3.handler;

import com.pahimar.ee3.exchange.DynamicEnergyValueInitThread;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class WorldEventHandler
{
    private static boolean hasInitilialized = false;

    @SubscribeEvent
    public void onWorldLoadEvent(WorldEvent.Load event)
    {
        if (!hasInitilialized)
        {
            DynamicEnergyValueInitThread.initEnergyValueRegistry();
            hasInitilialized = true;
        }
    }
}
