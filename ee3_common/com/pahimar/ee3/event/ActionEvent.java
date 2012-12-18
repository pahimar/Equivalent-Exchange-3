package com.pahimar.ee3.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;


public class ActionEvent extends Event {

    public final byte actionType;
    public final EntityPlayer player; 
    public final World world;
    public final int x, y, z;
    public final boolean hasActionOccured;
    public final String data;

    public ActionEvent(byte actionType, EntityPlayer player, World world, int x, int y, int z, boolean hasActionOccured, String data) {
        
        this.actionType = actionType;
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.hasActionOccured = hasActionOccured;
        this.data = data;
    }
}
