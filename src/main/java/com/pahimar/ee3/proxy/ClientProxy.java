package com.pahimar.ee3.proxy;

import com.pahimar.ee3.client.handler.ItemTooltipEventHandler;
import com.pahimar.ee3.client.handler.KeyInputEventHandler;
import com.pahimar.ee3.client.settings.Keybindings;
import com.pahimar.ee3.client.util.ClientParticleHelper;
import com.pahimar.ee3.client.util.ClientSoundHelper;
import com.pahimar.ee3.settings.ChalkSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    public ChalkSettings chalkSettings = new ChalkSettings();

    @Override
    public void registerEventHandlers() {

        super.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
        MinecraftForge.EVENT_BUS.register(new ItemTooltipEventHandler());
    }

    @Override
    public void registerKeybindings() {

        ClientRegistry.registerKeyBinding(Keybindings.charge);
        ClientRegistry.registerKeyBinding(Keybindings.extra);
        ClientRegistry.registerKeyBinding(Keybindings.release);
        ClientRegistry.registerKeyBinding(Keybindings.toggle);
    }

    @Override
    public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch)
    {
        ClientSoundHelper.playSound(soundName, xCoord, yCoord, zCoord, volume, pitch);
    }

    @Override
    public void spawnParticle(String particleName, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity)
    {
        ClientParticleHelper.spawnParticleAtLocation(particleName, xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity);
    }

    @Override
    public ClientProxy getClientProxy()
    {
        return this;
    }
}
