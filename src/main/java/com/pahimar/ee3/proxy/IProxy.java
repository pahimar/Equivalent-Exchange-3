package com.pahimar.ee3.proxy;

import net.minecraft.util.EnumParticleTypes;

public interface IProxy {

    ClientProxy getClientProxy();

    void registerEventHandlers();

    void registerKeybindings();

    void initModelsAndVariants();

    void spawnParticle(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity);
}
