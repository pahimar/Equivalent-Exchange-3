package com.pahimar.ee3.proxy;

import net.minecraft.util.EnumParticleTypes;

public class ServerProxy extends CommonProxy {

    @Override
    public ClientProxy getClientProxy() {
        return null;
    }

    @Override
    public void registerKeybindings() {
        // NOOP
    }

    @Override
    public void spawnParticle(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity) {
        // NOOP
    }
}
