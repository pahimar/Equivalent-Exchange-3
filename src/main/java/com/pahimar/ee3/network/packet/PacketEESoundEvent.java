package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.lib.Commands;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketEESoundEvent extends BasicPacketEE 
{
	public String playerName;
    public String soundName;
    public double x, y, z;
    public float volume, pitch;
	
	public PacketEESoundEvent()
	{
		super();
	}
	
	public PacketEESoundEvent(String playerName, String soundName, double x, double y, double z, float volume, float pitch)
	{
		this.playerName = playerName;
        this.soundName = soundName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.volume = volume;
        this.pitch = pitch;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		ByteBufUtils.writeUTF8String(buffer, playerName);
		ByteBufUtils.writeUTF8String(buffer, soundName);
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
		buffer.writeFloat(volume);
		buffer.writeFloat(pitch);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		playerName = ByteBufUtils.readUTF8String(buffer);
		soundName = ByteBufUtils.readUTF8String(buffer);
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		volume = buffer.readFloat();
		pitch = buffer.readFloat();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		if (ConfigurationSettings.ENABLE_SOUNDS.equalsIgnoreCase(Commands.ALL))
        {
			player.worldObj.playSoundEffect(x, y, z, soundName, volume, pitch);
        }
        else if (ConfigurationSettings.ENABLE_SOUNDS.equalsIgnoreCase(Commands.SELF))
        {
            if (player.getDisplayName().equalsIgnoreCase(playerName))
            {
            	player.worldObj.playSoundEffect(x, y, z, soundName, volume, pitch);
            }
        }
	}
}
