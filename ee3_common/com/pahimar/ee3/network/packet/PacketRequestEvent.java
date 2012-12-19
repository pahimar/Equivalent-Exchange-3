package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pahimar.ee3.event.ActionEvent;
import com.pahimar.ee3.event.ActionRequestEvent;
import com.pahimar.ee3.event.WorldTransmutationEvent;
import com.pahimar.ee3.lib.ActionTypes;
import com.pahimar.ee3.network.PacketTypeHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
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
        ActionEvent actionEvent = null;

        int lowerBoundX = -1 * rangeX / 2;
        int upperBoundX = -1 * lowerBoundX;
        int lowerBoundY = -1 * rangeY / 2;
        int upperBoundY = -1 * lowerBoundY;
        int lowerBoundZ = -1 * rangeZ / 2;
        int upperBoundZ = -1 * lowerBoundZ;

        for (int x = lowerBoundX; x <= upperBoundX; x++) {
            for (int y = lowerBoundY; y <= upperBoundY; y++) {
                for (int z = lowerBoundZ; z <= upperBoundZ; z++) {

                    actionEvent = new WorldTransmutationEvent(ActionTypes.TRANSMUTATION, thePlayer, thePlayer.worldObj, originX + x, originY + y, originZ + z, false, data);

                    if (actionEvent != null) {
                        actionRequestEvent = new ActionRequestEvent(thePlayer, actionEvent, originX + x, originY + y, originZ + z, (int) sideHit);
                        MinecraftForge.EVENT_BUS.post(actionRequestEvent);

                        if (actionRequestEvent.allowEvent != Result.DENY) {
                            MinecraftForge.EVENT_BUS.post(actionEvent);
                        }
                    }

                }
            }
        }
    }

}
