package com.pahimar.ee3.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;

public class WorldTransmutationEvent extends Event {
    
    public final EntityPlayer player; 
    public final World world;
    public final int x, y, z;
    public final String data;

    public WorldTransmutationEvent(EntityPlayer player, World world, int x, int y, int z, String data) {
        
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.data = data;
    }
}
