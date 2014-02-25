package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.EquivalentExchange3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketEETileCalcinator extends BasicPacketEE 
{
	public int x, y, z;
    public byte orientation;
    public byte state;
    public String customName;
    public byte leftStackSize, leftStackMeta, rightStackSize, rightStackMeta;
    byte[] data;
    
	public PacketEETileCalcinator()
	{
		super();
	}
	
	public PacketEETileCalcinator(int x, int y, int z, ForgeDirection orientation, byte state, String customName, byte leftStackSize, byte leftStackMeta, byte rightStackSize, byte rightStackMeta)
	{
		this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = (byte) orientation.ordinal();
        this.state = state;
        this.customName = customName;
        this.leftStackSize = leftStackSize;
        this.leftStackMeta = leftStackMeta;
        this.rightStackSize = rightStackSize;
        this.rightStackMeta = rightStackMeta;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		data = new byte[6];
		data[0] = orientation;
		data[1] = state;
		data[2] = leftStackSize;
		data[3] = leftStackMeta;
		data[4] = rightStackSize;
		data[5] = rightStackMeta;
		buffer.writeBytes(data);
		ByteBufUtils.writeUTF8String(buffer, customName);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		data = new byte[6];
		buffer.readBytes(data, 0, 6);
		orientation = data[0];
		state = data[1];
		leftStackSize = data[2];
		leftStackMeta = data[3];
		rightStackSize = data[4];
		rightStackMeta = data[5];
		customName = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void execute(EntityPlayer player)
	{
		EquivalentExchange3.proxy.handleTileCalcinatorPacket(x, y, z, ForgeDirection.getOrientation(orientation), state, customName, leftStackSize, leftStackMeta, rightStackSize, rightStackMeta);
	}
}
