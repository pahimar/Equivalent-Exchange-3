package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.configuration.ConfigurationSettings;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketEESpawnParticle extends BasicPacketEE
{
	public String particleName;
    public double x, y, z;
    public double velocityX, velocityY, velocityZ;
	
	public PacketEESpawnParticle()
	{
		super();
	}
	
	public PacketEESpawnParticle(String particleName, double x, double y, double z, double velocityX, double velocityY, double velocityZ)
	{
		this.particleName = particleName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		ByteBufUtils.writeUTF8String(buffer, particleName);
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
		buffer.writeDouble(velocityX);
		buffer.writeDouble(velocityY);
		buffer.writeDouble(velocityZ);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		particleName = ByteBufUtils.readUTF8String(buffer);
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		velocityX = buffer.readDouble();
		velocityY = buffer.readDouble();
		velocityZ = buffer.readDouble();
	}

	@Override
	public void execute(EntityPlayer player)
	{
		if (ConfigurationSettings.ENABLE_PARTICLE_FX)
        {
            player.worldObj.spawnParticle(particleName, x, y, z, velocityX, velocityY, velocityZ);
        }
	}
}
