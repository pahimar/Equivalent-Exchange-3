package com.pahimar.ee.proxy;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.block.base.BlockBase;
import com.pahimar.ee.client.handler.ItemTooltipEventHandler;
import com.pahimar.ee.client.handler.KeyInputEventHandler;
import com.pahimar.ee.client.settings.Keybindings;
import com.pahimar.ee.client.util.ClientParticleHelper;
import com.pahimar.ee.init.ModBlocks;
import com.pahimar.ee.init.ModItems;
import com.pahimar.ee.item.base.ItemBase;
import com.pahimar.ee.settings.ChalkSettings;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    public ChalkSettings chalkSettings = new ChalkSettings();

    @Override
    public ClientProxy getClientProxy()
    {
        return this;
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {

        super.onPreInit(event);

        // Initialize models and textures
        ModItems.getItems().forEach(ItemBase::initModelsAndVariants);
        ModBlocks.getBlocks().forEach(BlockBase::initModelsAndVariants);
        OBJLoader.INSTANCE.addDomain(EquivalentExchange.MOD_ID);

        // Register keybindings
        ClientRegistry.registerKeyBinding(Keybindings.CHARGE);
        ClientRegistry.registerKeyBinding(Keybindings.EXTRA);
        ClientRegistry.registerKeyBinding(Keybindings.RELEASE);
        ClientRegistry.registerKeyBinding(Keybindings.TOGGLE);
    }

    @Override
    public void onInit(FMLInitializationEvent event) {

        super.onInit(event);

        // Register custom item color handler
        ModItems.getItems().stream()
                .filter(itemEE -> itemEE instanceof IItemColor)
                .forEach(itemEE -> FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(((IItemColor) itemEE), itemEE));

        // Register client specific event handlers
        MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
        MinecraftForge.EVENT_BUS.register(new ItemTooltipEventHandler());
    }

    @Override
    public void spawnParticle(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity) {
        ClientParticleHelper.spawnParticleAtLocation(particleType, xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity);
    }
}
