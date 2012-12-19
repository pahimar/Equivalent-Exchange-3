package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

import com.pahimar.ee3.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketSpawnParticle extends PacketEE {

    public String particleName;
    public int x, y, z;
    public double velocityX, velocityY, velocityZ;

    public PacketSpawnParticle() {

        super(PacketTypeHandler.SPAWN_PARTICLE, false);
    }

    public PacketSpawnParticle(String particleName, int x, int y, int z, double velocityX, double velocityY, double velocityZ) {

        super(PacketTypeHandler.SPAWN_PARTICLE, false);
        this.particleName = particleName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public void writeData(DataOutputStream data) throws IOException {

        data.writeUTF(particleName);
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeDouble(velocityX);
        data.writeDouble(velocityY);
        data.writeDouble(velocityZ);

    }

    public void readData(DataInputStream data) throws IOException {

        this.particleName = data.readUTF();
        this.x = data.readInt();
        this.y = data.readInt();
        this.z = data.readInt();
        this.velocityX = data.readDouble();
        this.velocityY = data.readDouble();
        this.velocityZ = data.readDouble();

    }

    public void execute(INetworkManager manager, Player player) {

        EntityPlayer thePlayer = (EntityPlayer) player;

        thePlayer.worldObj.spawnParticle(particleName, x, y, z, velocityX, velocityY, velocityZ);
    }

}
