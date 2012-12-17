package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pahimar.ee3.event.ActionRequestEvent;
import com.pahimar.ee3.event.WorldTransmutationEvent;
import com.pahimar.ee3.lib.RequestEvents;
import com.pahimar.ee3.network.PacketTypeHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.network.Player;

public class PacketRequestEvent extends PacketEE {

    public byte eventType;
    public int originX, originY, originZ;
    public byte sideHit;
    public byte rangeX, rangeY, rangeZ;
    public String data;

    public PacketRequestEvent() {

        super(PacketTypeHandler.REQUEST_EVENT, false);
    }

    public PacketRequestEvent(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data) {

        super(PacketTypeHandler.REQUEST_EVENT, false);
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

        EntityPlayer thePlayer = (EntityPlayer) player;
        ActionRequestEvent actionRequestEvent = null;
        Event actionEvent = null;;
        
        // TODO Move this logic to a ActionEvent handler to post an appropriate event depending on the request received
        
        if (eventType == RequestEvents.TRANSMUTATION) {
            actionEvent = new WorldTransmutationEvent(thePlayer, thePlayer.worldObj, originX, originY, originZ, data);
        }
        
        if (actionEvent != null) {
            actionRequestEvent = new ActionRequestEvent(thePlayer, actionEvent, originX, originY, originZ, (int) sideHit);
            MinecraftForge.EVENT_BUS.post(actionRequestEvent);
            
            if (actionRequestEvent.allowEvent != Result.DENY) {
                MinecraftForge.EVENT_BUS.post(actionEvent);
            }
        }
        
    }

}
