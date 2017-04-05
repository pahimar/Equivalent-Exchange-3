package com.pahimar.ee.proxy;

import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.event.*;

public interface IProxy {

    void onPreInit(FMLPreInitializationEvent event);

    void onInit(FMLInitializationEvent event);

    void onPostInit(FMLPostInitializationEvent event);

    void onServerStarting(FMLServerStartingEvent event);

    void onServerStopping(FMLServerStoppingEvent event);

    default ClientProxy getClientProxy() {
        return null;
    }

    default void spawnParticle(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity) {
        // NOOP
    }
}
