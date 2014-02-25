package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.lib.ItemUpdateTypes;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PacketEEItemUpdate extends BasicPacketEE 
{
	public byte slot;
	public byte updateType;
	byte[] data;
	
	public PacketEEItemUpdate()
	{
		super();
	}
	
	public PacketEEItemUpdate(byte Slot, byte UpdateType)
	{
		this.slot = Slot;
		this.updateType = UpdateType;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		data = new byte[2];
		data[0] = slot;
		data[1] = updateType;
		buffer.writeBytes(data);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		data = new byte[2];
		buffer.readBytes(data, 0, 2);
		slot = data[0];
		updateType = data[1];
	}

	@Override
	public void execute(EntityPlayer player)
	{
		EntityPlayer thePlayer = (EntityPlayer) player;
        ItemStack destroyedStack = thePlayer.inventory.getStackInSlot(slot);

        if (updateType == ItemUpdateTypes.DESTROYED)
        {
            thePlayer.renderBrokenItemStack(destroyedStack);
        }
	}
}
