package com.pahimar.ee.util;

import com.pahimar.ee.network.Network;
import com.pahimar.ee.network.message.MessageSingleParticleEvent;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonParticleHelper {

    public static void spawnParticleAtLocation(EnumParticleTypes particleType, int dimensionId, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity) {
        spawnParticleAtLocation(particleType, dimensionId, xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity, 64d);
    }

    public static void spawnParticleAtLocation(EnumParticleTypes particleType, int dimensionId, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity, double range) {
        Network.INSTANCE.sendToAllAround(new MessageSingleParticleEvent(particleType, xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity), new NetworkRegistry.TargetPoint(dimensionId, xCoord, yCoord, zCoord, range));
    }
}
