package com.pahimar.ee3.event;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraftforge.event.entity.player.PlayerEvent;

import static cpw.mods.fml.common.eventhandler.Event.Result.DEFAULT;
import static cpw.mods.fml.common.eventhandler.Event.Result.DENY;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ActionRequestEvent
 *
 * @author pahimar
 */
@Cancelable
public class ActionRequestEvent extends PlayerEvent
{

    public final ActionEvent modEvent;
    public final int x, y, z;
    public final int sideHit;
    public Result allowEvent;

    public ActionRequestEvent(EntityPlayer player, ActionEvent modEvent, int x, int y, int z, int sideHit)
    {

        super(player);
        this.modEvent = modEvent;
        this.x = x;
        this.y = y;
        this.z = z;
        this.sideHit = sideHit;
        if (sideHit == -1)
        {
            allowEvent = DENY;
        }
    }

    @Override
    public void setCanceled(boolean cancel)
    {

        super.setCanceled(cancel);
        allowEvent = cancel ? DENY : allowEvent == DENY ? DENY : DEFAULT;
    }
}
