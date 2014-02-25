package com.pahimar.ee3.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;

public class BasicPacketEE extends IPacket 
{
	
	public BasicPacketEE()
	{
		
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{

	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{

	}

	@Override
	public void handleClientSide(EntityPlayer player) 
	{

	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{

	}
	
	@Override
	public void execute(EntityPlayer player)
	{
		
	}

	@Override
	public void readPacketData(PacketBuffer var1) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writePacketData(PacketBuffer var1) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processPacket(INetHandler var1) 
	{
		// TODO Auto-generated method stub
		
	}
}
