package com.pahimar.ee3.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public class WorldTransmutationEvent extends ActionEvent {

    public int targetID, targetMeta;
    
    public WorldTransmutationEvent(byte actionType, EntityPlayer player, World world, int x, int y, int z, boolean hasActionOccured, String data) {

        super(actionType, player, world, x, y, z, hasActionOccured, data);
        targetID = Integer.parseInt(data.substring(0, data.indexOf(":")));
        targetMeta = Integer.parseInt(data.substring(data.indexOf(":") + 1));
    }

}
