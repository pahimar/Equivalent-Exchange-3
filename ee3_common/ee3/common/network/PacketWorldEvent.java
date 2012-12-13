package ee3.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.network.Player;
import ee3.common.event.ModActionEvent;
import ee3.common.event.WorldTransmutationEvent;
import ee3.common.lib.ModAction;
import ee3.common.lib.WorldEvents;

public class PacketWorldEvent extends PacketEE {

    public byte eventType;
    public int originX, originY, originZ;
    public byte sideHit;
    public byte rangeX, rangeY, rangeZ;
    public String data;

    public PacketWorldEvent() {

        super(PacketTypeHandler.WORLD_EVENT, false);
    }

    public PacketWorldEvent(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data) {

        super(PacketTypeHandler.WORLD_EVENT, false);
        this.eventType = eventType;
        this.originX = originX;
        this.originY = originY;
        this.originZ = originZ;
        this.sideHit = sideHit;
        this.rangeX = rangeX;
        this.rangeY = rangeY;
        this.rangeZ = rangeZ;
        this.data = data;
    }

    public void setEventType(byte eventType) {

        this.eventType = eventType;
    }

    public void setOrigin(int originX, int originY, int originZ) {

        this.originX = originX;
        this.originY = originY;
        this.originZ = originZ;
    }
    
    public void setSideHit(byte sideHit) {
        this.sideHit = sideHit;
    }

    public void setRange(byte rangeX, byte rangeY, byte rangeZ) {

        this.rangeX = rangeX;
        this.rangeY = rangeY;
        this.rangeZ = rangeZ;
    }

    public void setData(String data) {

        this.data = data;
    }

    public void writeData(DataOutputStream data) throws IOException {

        data.writeByte(eventType);
        data.writeInt(originX);
        data.writeInt(originY);
        data.writeInt(originZ);
        data.writeByte(sideHit);
        data.writeByte(rangeX);
        data.writeByte(rangeY);
        data.writeByte(rangeZ);
        data.writeUTF(this.data);
    }

    public void readData(DataInputStream data) throws IOException {

        this.eventType = data.readByte();
        this.originX = data.readInt();
        this.originY = data.readInt();
        this.originZ = data.readInt();
        this.sideHit = data.readByte();
        this.rangeX = data.readByte();
        this.rangeY = data.readByte();
        this.rangeZ = data.readByte();
        this.data = data.readUTF();
    }

    public void execute(INetworkManager manager, Player player) {

        /*
         * Server knows the world, the player, and all the packet data
         * Server checks (for each block);
         *  1) If the action on that block is allowed for the player
         *  2) If the action is a valid action
         *  
         *  AoE options are; 1x1, 3x3, 5x5, and 7x7
         *  Charge options; 0, 1, 2, 3
         *  so Range would be 1, 2, 4, 6
         *  1 + 0, 1 + 1, 1 + 3, 1 + 5 
         */
        
        EntityPlayer thePlayer = (EntityPlayer) player;
        ModActionEvent modActionEvent;
        WorldTransmutationEvent worldTransmutationEvent;
        
        modActionEvent= new ModActionEvent(thePlayer, ModAction.TRANSMUTATION, originX, originY, originZ, (int) sideHit);
        MinecraftForge.EVENT_BUS.post(modActionEvent);
        
        if (modActionEvent.allowEvent != Result.DENY) {
            worldTransmutationEvent = new WorldTransmutationEvent(thePlayer, thePlayer.worldObj, originX, originY, originZ, sideHit, rangeX, rangeY, rangeZ, data);
            MinecraftForge.EVENT_BUS.post(worldTransmutationEvent);
        }
        
    }

}
