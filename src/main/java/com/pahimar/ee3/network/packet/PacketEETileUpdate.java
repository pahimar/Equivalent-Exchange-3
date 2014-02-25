package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.EquivalentExchange3;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;

public class PacketEETileUpdate extends BasicPacketEE 
{
	public int x, y, z;
    public byte orientation;
    public byte state;
    public String customName;
    
	public PacketEETileUpdate()
	{
		super();
	}
	
	public PacketEETileUpdate(int x, int y, int z, ForgeDirection orientation, byte state, String customName)
	{
		this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = (byte) orientation.ordinal();
        this.state = state;
        this.customName = customName;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeByte(orientation);
		buffer.writeByte(state);
		ByteBufUtils.writeUTF8String(buffer, customName);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		orientation = buffer.readByte();
		state = buffer.readByte();
		customName = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void execute(EntityPlayer player)
	{
		EquivalentExchange3.proxy.handleTileEntityPacket(x, y, z, ForgeDirection.getOrientation(orientation), state, customName);
	}
}
