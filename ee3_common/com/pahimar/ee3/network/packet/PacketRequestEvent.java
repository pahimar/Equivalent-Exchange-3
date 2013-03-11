package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

import com.pahimar.ee3.core.handlers.WorldTransmutationHandler;
import com.pahimar.ee3.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

/**
 * Equivalent-Exchange-3
 * 
 * PacketRequestEvent
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
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

    @Override
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

    @Override
    public void readData(DataInputStream data) throws IOException {

        eventType = data.readByte();
        originX = data.readInt();
        originY = data.readInt();
        originZ = data.readInt();
        sideHit = data.readByte();
        rangeX = data.readByte();
        rangeY = data.readByte();
        rangeZ = data.readByte();
        this.data = data.readUTF();
    }

    @Override
    public void execute(INetworkManager manager, Player player) {

        EntityPlayer thePlayer = (EntityPlayer) player;
        WorldTransmutationHandler.handleWorldTransmutation(thePlayer, originX, originY, originZ, rangeX, rangeY, rangeZ, sideHit, data);
    }
}
