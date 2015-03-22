package com.pahimar.ee3.client.util;

import cpw.mods.fml.client.FMLClientHandler;

public class ClientParticleHelper
{
    public static void spawnParticle(String particleName, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity)
    {
        FMLClientHandler.instance().getWorldClient().spawnParticle(particleName, xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity);
    }
}
