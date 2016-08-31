package com.pahimar.ee3.proxy;

import com.pahimar.ee3.client.handler.ItemTooltipEventHandler;
import com.pahimar.ee3.client.handler.KeyInputEventHandler;
import com.pahimar.ee3.client.settings.Keybindings;
import com.pahimar.ee3.client.util.ClientParticleHelper;
import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.settings.ChalkSettings;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    public ChalkSettings chalkSettings = new ChalkSettings();

    @Override
    public ClientProxy getClientProxy()
    {
        return this;
    }

    @Override
    public void registerEventHandlers() {

        super.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
        MinecraftForge.EVENT_BUS.register(new ItemTooltipEventHandler());
    }

    @Override
    public void registerKeybindings() {

        ClientRegistry.registerKeyBinding(Keybindings.CHARGE);
        ClientRegistry.registerKeyBinding(Keybindings.EXTRA);
        ClientRegistry.registerKeyBinding(Keybindings.RELEASE);
        ClientRegistry.registerKeyBinding(Keybindings.TOGGLE);
    }

    @Override
    public void initModelsAndVariants() {

        ModItems.initModelsAndVariants();
    }

    @Override
    public void spawnParticle(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity) {
        ClientParticleHelper.spawnParticleAtLocation(particleType, xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity);
    }
}
