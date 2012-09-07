package ee3.common.core.handlers;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class PlayerDestroyItemHandler {
    
    @ForgeSubscribe
    public void onPlayerDestroyItemEvent(PlayerDestroyItemEvent event) {
        System.out.println(event.original.getItemNameandInformation());
    }

}
