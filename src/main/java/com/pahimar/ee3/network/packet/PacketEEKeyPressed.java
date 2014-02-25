package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.item.IKeyBound;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class PacketEEKeyPressed extends BasicPacketEE 
{
	
	public String key;
	
	public PacketEEKeyPressed()
	{
		super();
	}
	
	public PacketEEKeyPressed(String key)
	{
		this.key = key;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		ByteBufUtils.writeUTF8String(buffer, key);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		key = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void execute(EntityPlayer player)
	{
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof IKeyBound)
        {
            ((IKeyBound) player.getCurrentEquippedItem().getItem()).doKeyBindingAction(player, player.getCurrentEquippedItem(), key);
        }
	}
}
