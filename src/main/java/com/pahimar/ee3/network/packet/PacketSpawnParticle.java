package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.network.PacketTypeHandler;
import net.minecraft.entity.player.EntityPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Equivalent-Exchange-3
 * <p/>
 * PacketSpawnParticle
 *
 * @author pahimar
 */
public class PacketSpawnParticle extends PacketEE
{

    public String particleName;
    public double x, y, z;
    public double velocityX, velocityY, velocityZ;

    public PacketSpawnParticle()
    {

        super(PacketTypeHandler.SPAWN_PARTICLE, false);
    }

    public PacketSpawnParticle(String particleName, double x, double y, double z, double velocityX, double velocityY, double velocityZ)
    {

        super(PacketTypeHandler.SPAWN_PARTICLE, false);
        this.particleName = particleName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    @Override
    public void writeData(DataOutputStream data) throws IOException
    {

        data.writeUTF(particleName);
        data.writeDouble(x);
        data.writeDouble(y);
        data.writeDouble(z);
        data.writeDouble(velocityX);
        data.writeDouble(velocityY);
        data.writeDouble(velocityZ);
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {

        particleName = data.readUTF();
        x = data.readDouble();
        y = data.readDouble();
        z = data.readDouble();
        velocityX = data.readDouble();
        velocityY = data.readDouble();
        velocityZ = data.readDouble();
    }

    @Override
    public void execute(INetworkManager manager, Player player)
    {

        EntityPlayer thePlayer = (EntityPlayer) player;

        if (ConfigurationSettings.ENABLE_PARTICLE_FX)
        {
            thePlayer.worldObj.spawnParticle(particleName, x, y, z, velocityX, velocityY, velocityZ);
        }
    }
}
