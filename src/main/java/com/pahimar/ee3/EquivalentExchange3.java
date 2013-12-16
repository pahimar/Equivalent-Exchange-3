package com.pahimar.ee3;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.command.CommandHandler;
import com.pahimar.ee3.configuration.ConfigurationHandler;
import com.pahimar.ee3.core.handler.*;
import com.pahimar.ee3.core.handler.addon.AddonIMCHandler;
import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.core.helper.VersionHelper;
import com.pahimar.ee3.core.proxy.CommonProxy;
import com.pahimar.ee3.creativetab.CreativeTabEE3;
import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.imc.InterModCommsHandler;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.item.crafting.RecipesAlchemicalBagDyes;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.network.PacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;

/**
 * Equivalent-Exchange-3
 * <p/>
 * EquivalentExchange3
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, dependencies = Reference.DEPENDENCIES, certificateFingerprint = Reference.FINGERPRINT)
@NetworkMod(channels = {Reference.CHANNEL_NAME}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class EquivalentExchange3
{
    @Instance(Reference.MOD_ID)
    public static EquivalentExchange3 instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static CreativeTabs tabsEE3 = new CreativeTabEE3(CreativeTabs.getNextID(), Reference.MOD_ID);

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
    @SuppressWarnings("unchecked")
    public void load(FMLInitializationEvent event)
    {
        // Register the GUI Handler
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);

        // Register the PlayerDestroyItem Handler
        MinecraftForge.EVENT_BUS.register(new PlayerDestroyItemHandler());

        // Register the Item Pickup Handler
        MinecraftForge.EVENT_BUS.register(new ItemEventHandler());

        // Register the EntityLiving Handler
        MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());

        MinecraftForge.EVENT_BUS.register(new ActionRequestHandler());

        MinecraftForge.EVENT_BUS.register(new WorldTransmutationHandler());

        MinecraftForge.EVENT_BUS.register(new ItemTooltipEventHandler());

        GameRegistry.registerCraftingHandler(new CraftingHandler());

        // Register the DrawBlockHighlight Handler
        proxy.registerDrawBlockHighlightHandler();

        // Initialize mod tile entities
        proxy.registerTileEntities();

        // Initialize custom rendering and pre-load textures (Client only)
        proxy.initRenderingAndTextures();

        // Add in the ability to dye Alchemical Bags
        CraftingManager.getInstance().getRecipeList().add(new RecipesAlchemicalBagDyes());

        // Initialize addons (which work with IMC, and must be used in Init)
        AddonIMCHandler.init();
    }

    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent event)
    {
        EmcRegistry.lazyInit();
    }

    @EventHandler
    public void handleIMCMessages(IMCEvent event)
    {
        InterModCommsHandler.processIMCMessages(event);
    }
}
