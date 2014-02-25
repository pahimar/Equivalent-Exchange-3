package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.handler.WorldTransmutationHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketEERequestEvent extends BasicPacketEE 
{
	public byte eventType;
    public int originX, originY, originZ;
    public byte sideHit;
    public byte rangeX, rangeY, rangeZ;
    public String data;
    
    byte[] sendData;
    
	public PacketEERequestEvent()
	{
		super();
	}
	
	public PacketEERequestEvent(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY,
			byte rangeZ, String data)
	{
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
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		sendData = new byte[5];
		sendData[0] = eventType;
		sendData[1] = sideHit;
		sendData[2] = rangeX;
		sendData[3] = rangeY;
		sendData[4] = rangeZ;
		buffer.writeBytes(sendData);
		buffer.writeInt(originX);
		buffer.writeInt(originY);
		buffer.writeInt(originZ);
		ByteBufUtils.writeUTF8String(buffer, data);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		sendData = new byte[5];
		buffer.readBytes(sendData, 0, 5);
		eventType = sendData[0];
		sideHit = sendData[1];
		rangeX = sendData[2];
		rangeY = sendData[3];
		rangeZ = sendData[4];
		originX = buffer.readInt();
		originY = buffer.readInt();
		originZ = buffer.readInt();
		data = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void execute(EntityPlayer player)
	{
		WorldTransmutationHandler.handleWorldTransmutation(player, originX, originY, originZ, rangeX, rangeY, rangeZ, sideHit, data);
	}
}
