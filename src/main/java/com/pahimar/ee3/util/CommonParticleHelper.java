package com.pahimar.ee3.util;

import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageSingleParticleEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonParticleHelper
{
    public static void spawnParticleAtLocation(String particleName, int dimensionId, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity)
    {
        spawnParticleAtLocation(particleName, dimensionId, xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity, 64d);
    }

    public static void spawnParticleAtLocation(String particleName, int dimensionId, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity, double range)
    {
        Network.INSTANCE.sendToAllAround(new MessageSingleParticleEvent(particleName, xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity), new NetworkRegistry.TargetPoint(dimensionId, xCoord, yCoord, zCoord, range));
    }
}
