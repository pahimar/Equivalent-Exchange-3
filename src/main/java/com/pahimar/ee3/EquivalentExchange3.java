package com.pahimar.ee3;

import com.pahimar.ee3.array.GlyphTextureRegistry;
import com.pahimar.ee3.command.CommandSetCurrentItemValue;
import com.pahimar.ee3.command.CommandSetValue;
import com.pahimar.ee3.command.CommandSyncValues;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.handler.*;
import com.pahimar.ee3.init.*;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.proxy.IProxy;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.recipe.RecipesAludel;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.skill.SkillRegistry;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import java.io.File;

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
    public void onServerStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandSetValue());
        event.registerServerCommand(new CommandSetCurrentItemValue());
        event.registerServerCommand(new CommandSyncValues());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        PacketHandler.init();

        proxy.registerKeybindings();

        ModItems.init();

        ModBlocks.init();

        Glyphs.init();

        EnergyValues.addDefaultEnergyValues();

        Skills.addDefaultSkills();
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

        // Register the Waila data provider
        FMLInterModComms.sendMessage("Waila", "register", "com.pahimar.ee3.waila.WailaDataProvider.callbackRegister");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        RecipeRegistry.getInstance().registerVanillaRecipes();
        RecipesAludel.registerRecipes();
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event)
    {
        if (EnergyValueRegistry.getInstance().getShouldRegenNextRestart())
        {
            File dataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + Reference.MOD_ID.toLowerCase());
            File energyValueRegistryFile = new File(dataDirectory, SerializationHelper.getModListMD5() + "." + Reference.MOD_ID.toLowerCase());

            if (energyValueRegistryFile.exists())
            {
                energyValueRegistryFile.delete();
            }
        }
        else
        {
            SerializationHelper.writeEnergyValueRegistryToFile(SerializationHelper.getModListMD5() + "." + Reference.MOD_ID.toLowerCase());
        }

        WorldEventHandler.hasInitilialized = false;
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

    public GlyphTextureRegistry getGlyphRegistry()
    {
        return GlyphTextureRegistry.getInstance();
    }
}
