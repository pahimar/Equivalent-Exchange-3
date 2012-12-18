package com.pahimar.ee3.core.handlers;

import com.pahimar.ee3.event.ActionEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class WorldTransmutationHandler {

    @ForgeSubscribe
    public void onWorldTransmutationEvent(ActionEvent event) {

        System.out.println(event.data);
    }

}
