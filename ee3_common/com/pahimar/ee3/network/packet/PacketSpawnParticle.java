package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

/**
 * Equivalent-Exchange-3
 * 
 * PacketSpawnParticle
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PacketSpawnParticle extends PacketEE {

    public String particleName;
    public double x, y, z;
    public double velocityX, velocityY, velocityZ;

    public PacketSpawnParticle() {

        super(PacketTypeHandler.SPAWN_PARTICLE, false);
    }

    public PacketSpawnParticle(String particleName, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {

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
    public void writeData(DataOutputStream data) throws IOException {

        data.writeUTF(particleName);
        data.writeDouble(x);
        data.writeDouble(y);
        data.writeDouble(z);
        data.writeDouble(velocityX);
        data.writeDouble(velocityY);
        data.writeDouble(velocityZ);

    }

    @Override
    public void readData(DataInputStream data) throws IOException {

        particleName = data.readUTF();
        x = data.readDouble();
        y = data.readDouble();
        z = data.readDouble();
        velocityX = data.readDouble();
        velocityY = data.readDouble();
        velocityZ = data.readDouble();

    }

    @Override
    public void execute(INetworkManager manager, Player player) {

        EntityPlayer thePlayer = (EntityPlayer) player;

        if (ConfigurationSettings.ENABLE_PARTICLE_FX) {
            thePlayer.worldObj.spawnParticle(particleName, x, y, z, velocityX, velocityY, velocityZ);
        }
    }

}
