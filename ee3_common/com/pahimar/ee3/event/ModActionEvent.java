package com.pahimar.ee3.event;

import static net.minecraftforge.event.Event.Result.*;

import com.pahimar.ee3.lib.ModAction;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.Cancelable;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class ModActionEvent extends PlayerEvent {
    
    public final ModAction modAction;
    public final int x, y, z;
    public final int sideHit;
    
    public Result allowEvent;
    
    public ModActionEvent(EntityPlayer player, ModAction modAction, int x, int y, int z, int sideHit) {

        super(player);
        this.modAction = modAction;
        this.x = x;
        this.y = y;
        this.z = z;
        this.sideHit = sideHit;
        if (sideHit == -1) { 
            allowEvent = DENY;
        }
    }
    
    @Override
    public void setCanceled(boolean cancel)
    {
        super.setCanceled(cancel);
        allowEvent = (cancel ? DENY : allowEvent == DENY ? DENY : DEFAULT);
    }

}
