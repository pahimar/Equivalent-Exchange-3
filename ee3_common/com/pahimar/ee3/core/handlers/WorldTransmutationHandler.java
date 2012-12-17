package com.pahimar.ee3.core.handlers;

import com.pahimar.ee3.event.WorldTransmutationEvent;

import net.minecraftforge.event.ForgeSubscribe;


public class WorldTransmutationHandler {

    @ForgeSubscribe
    public void onWorldTransmutationEvent(WorldTransmutationEvent event) {
        System.out.println(event.toString());
    }
    
}
