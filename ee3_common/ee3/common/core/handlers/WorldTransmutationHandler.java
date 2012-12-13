package ee3.common.core.handlers;

import ee3.common.event.WorldTransmutationEvent;
import net.minecraftforge.event.ForgeSubscribe;


public class WorldTransmutationHandler {

    @ForgeSubscribe
    public void onWorldTransmutationEvent(WorldTransmutationEvent event) {
        System.out.println(event.toString());
    }
    
}
