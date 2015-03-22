package com.pahimar.ee3.proxy;

public interface IProxy
{
    public abstract ClientProxy getClientProxy();

    public abstract void initRenderingAndTextures();

    public abstract void registerEventHandlers();

    public abstract void registerKeybindings();

    public abstract void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch);

    public abstract void spawnParticle(String particleName, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity);
}
