package com.pahimar.ee3.handler;

import com.pahimar.ee3.knowledge.AbilityRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class AbilityRegistrySerializationHandler
{
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            if (FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getWorldTime() % 600 == 0)
            {
                AbilityRegistry.getInstance().save();
            }
        }
    }
}
