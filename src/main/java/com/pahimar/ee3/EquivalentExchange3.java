package com.pahimar.ee3;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import com.pahimar.ee3.addon.AddonHandler;
import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.command.CommandHandler;
import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.creativetab.CreativeTabEE3;
import com.pahimar.ee3.handler.ActionRequestHandler;
import com.pahimar.ee3.handler.CraftingHandler;
import com.pahimar.ee3.handler.FuelHandler;
import com.pahimar.ee3.handler.GuiHandler;
import com.pahimar.ee3.handler.ItemEventHandler;
import com.pahimar.ee3.handler.VersionCheckTickHandler;
import com.pahimar.ee3.handler.WorldEventHandler;
import com.pahimar.ee3.handler.WorldTransmutationHandler;
import com.pahimar.ee3.helper.FluidHelper;
import com.pahimar.ee3.helper.LogHelper;
import com.pahimar.ee3.helper.VersionHelper;
import com.pahimar.ee3.imc.InterModCommsHandler;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.item.crafting.RecipeAludel;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.proxy.IProxy;
import com.pahimar.ee3.recipe.RecipesAludel;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * Equivalent-Exchange-3
 * <p/>
 * EquivalentExchange3
 *
 * @author pahimar
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, dependencies = Reference.DEPENDENCIES, certificateFingerprint = Reference.FINGERPRINT)
@NetworkMod(channels = {Reference.CHANNEL_NAME}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class EquivalentExchange3
{
    @Instance(Reference.MOD_ID)
    public static EquivalentExchange3 instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static CreativeTabs tabsEE3 = new CreativeTabEE3(CreativeTabs.getNextID());

    @EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
    {
        // Report (log) to the user that the version of Equivalent Exchange 3
        // they are using has been changed/tampered with
        if (Reference.FINGERPRINT.equals("@FINGERPRINT@"))
        {
            LogHelper.warning(Strings.NO_FINGERPRINT_MESSAGE);
        }
        else
        {
            LogHelper.severe(Strings.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        // Initialize the custom commands
        CommandHandler.initCommands(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // set version number
        event.getModMetadata().version = Reference.VERSION_NUMBER;

        // Initialize the log helper
        LogHelper.init();

        // Initialize the configuration
        ConfigurationHandler.init(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.CHANNEL_NAME.toLowerCase() + File.separator);

        // Conduct the version check and log the result
        VersionHelper.execute();

        // Initialize the Version Check Tick Handler (Client only)
        TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);

        // Initialize the InterModCommunications Tick Handler (Server only)
        TickRegistry.registerScheduledTickHandler(new InterModCommsHandler(), Side.SERVER);

        // Initialize the Render Tick Handler (Client only)
        proxy.registerRenderTickHandler();

        // Register the KeyBinding Handler (Client only)
        proxy.registerKeyBindingHandler();

        // Register the Sound Handler (Client only)
        proxy.registerSoundHandler();

        // Initialize mod blocks
        ModBlocks.init();

        // Initialize mod items
        ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Register the GUI Handler
        NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());

        // Register the Item Pickup Handler
        MinecraftForge.EVENT_BUS.register(new ItemEventHandler());

        MinecraftForge.EVENT_BUS.register(new ActionRequestHandler());

        MinecraftForge.EVENT_BUS.register(new WorldTransmutationHandler());

        // Register the hook to initialize the EmcRegistry
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());

        // Register the ItemTooltipEvent Handler 
        proxy.registerItemTooltipHandler();

        // Register the DrawBlockHighlight Handler
        proxy.registerDrawBlockHighlightHandler();

        // Initialize custom rendering and pre-load textures (Client only)
        proxy.initRenderingAndTextures();

        // Initialize our Crafting Handler
        CraftingHandler.init();

        // Handle fluid registration
        FluidHelper.registerFluids();

        // Initialize mod tile entities
        proxy.registerTileEntities();

        // Register our fuels
        GameRegistry.registerFuelHandler(new FuelHandler());

        // Initialize addons (which work with IMC, and must be used in Init)
        AddonHandler.init();
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	//TODO IronChest integration, when I fix my buildspace
    }

    @EventHandler
    public void handleIMCMessages(IMCEvent event)
    {
        InterModCommsHandler.processIMCMessages(event);
    }
}
