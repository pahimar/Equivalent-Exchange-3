package com.pahimar.ee3;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.handler.CraftingHandler;
import com.pahimar.ee3.handler.GuiHandler;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.proxy.IProxy;
import com.pahimar.ee3.reference.EventHandlers;
import com.pahimar.ee3.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, certificateFingerprint = Reference.FINGERPRINT, version = "0.2")
public class EquivalentExchange3
{
    @Instance
    public static EquivalentExchange3 instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
    {

    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Initialize mod blocks
        ModBlocks.init();

        // Initialize mod items
        ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Register the GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        // Register the Items Event Handler
        FMLCommonHandler.instance().bus().register(EventHandlers.itemEventHandler);
        MinecraftForge.EVENT_BUS.register(EventHandlers.itemEventHandler);

        CraftingHandler.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @EventHandler
    public void handleIMCMessages(IMCEvent event)
    {

    }
}
