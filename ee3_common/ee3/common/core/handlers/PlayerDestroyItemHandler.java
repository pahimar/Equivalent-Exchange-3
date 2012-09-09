package ee3.common.core.handlers;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class PlayerDestroyItemHandler {
    
    @ForgeSubscribe
    public void onPlayerDestroyItemEvent(PlayerDestroyItemEvent event) {
        // TODO Come back and actually do what I want here
        System.out.println(event.original.getItemNameandInformation());
    }

}
