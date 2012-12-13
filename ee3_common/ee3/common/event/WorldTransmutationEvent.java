package ee3.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;

public class WorldTransmutationEvent extends Event {
    
    public final EntityPlayer player; 
    public final World world;
    public final int originX, originY, originZ;
    public final byte sideHit;
    public final byte rangeX, rangeY, rangeZ;
    public final String data;

    public WorldTransmutationEvent(EntityPlayer player, World world, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data) {
        
        this.player = player;
        this.world = world;
        this.originX = originX;
        this.originY = originY;
        this.originZ = originZ;
        this.sideHit = sideHit;
        this.rangeX = rangeX;
        this.rangeY = rangeY;
        this.rangeZ = rangeZ;
        this.data = data;
    }
}
