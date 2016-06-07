package com.pahimar.ee3.proxy;

import net.minecraft.util.EnumParticleTypes;

public interface IProxy {

    ClientProxy getClientProxy();

    void registerEventHandlers();

    void registerKeybindings();

    void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch);

    void spawnParticle(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity);
}
