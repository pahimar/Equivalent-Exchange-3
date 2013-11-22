package com.pahimar.ee3.core.handler;

import com.pahimar.ee3.emc.EmcRegistry;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Load;

public class WorldEventHandler {
    
    @ForgeSubscribe
    public void onWorldLoaded(Load event){
        
        EmcRegistry.lazyInit();
    }
}
