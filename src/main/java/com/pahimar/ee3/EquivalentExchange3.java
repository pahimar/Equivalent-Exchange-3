package com.pahimar.ee3;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.handler.ConfigurationHandler;
import com.pahimar.ee3.handler.CraftingHandler;
import com.pahimar.ee3.handler.FuelHandler;
import com.pahimar.ee3.handler.GuiHandler;
import com.pahimar.ee3.init.EnergyValues;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.init.Recipes;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.proxy.IProxy;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.skill.SkillRegistry;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, certificateFingerprint = Reference.FINGERPRINT, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class EquivalentExchange3
{
    @Instance(Reference.MOD_ID)
    public static EquivalentExchange3 instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
    {
        // Report (log) to the user that the version of Equivalent Exchange 3
        // they are using has been changed/tampered with
        if (Reference.FINGERPRINT.equals("@FINGERPRINT@"))
        {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        }
        else
        {
            LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        PacketHandler.init();

        proxy.registerKeybindings();

        ModItems.init();

        ModBlocks.init();

        EnergyValues.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Register the GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        // Initialize mod tile entities
        proxy.registerTileEntities();

        // Initialize custom rendering and pre-load textures (Client only)
        proxy.initRenderingAndTextures();

        // Register the Items Event Handler
        proxy.registerEventHandlers();

        CraftingHandler.init();
        Recipes.init();

        // Register our fuels
        GameRegistry.registerFuelHandler(new FuelHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        RecipeRegistry.getInstance();
    }

    @EventHandler
    public void handleIMCMessages(IMCEvent event)
    {

    }

    public EnergyValueRegistry getEnergyValueRegistry()
    {
        return EnergyValueRegistry.getInstance();
    }

    public RecipeRegistry getRecipeRegistry()
    {
        return RecipeRegistry.getInstance();
    }

    public SkillRegistry getSkillRegistry()
    {
        return SkillRegistry.getInstance();
    }
}
