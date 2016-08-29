package com.pahimar.ee3;

import com.pahimar.ee3.array.AlchemyArrayRegistry;
import com.pahimar.ee3.blacklist.BlacklistRegistry;
import com.pahimar.ee3.command.CommandEE;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.handler.*;
import com.pahimar.ee3.init.*;
import com.pahimar.ee3.knowledge.PlayerKnowledgeRegistry;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.proxy.IProxy;
import com.pahimar.ee3.recipe.AludelRecipeManager;
import com.pahimar.ee3.recipe.RecipeRegistry;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.test.EETestSuite;
import com.pahimar.ee3.test.VanillaTestSuite;
import com.pahimar.ee3.util.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = EquivalentExchange3.MOD_ID,
        name = "Equivalent Exchange 3",
        certificateFingerprint = EquivalentExchange3.FINGERPRINT,
        version = "@MOD_VERSION@",
        dependencies = "required-after:Forge@[12.18.1,)",
        guiFactory = "com.pahimar.ee3.client.gui.GuiFactory",
        updateJSON = "http://ee3.pahimar.com/update/versions.json")
public class EquivalentExchange3 {

    public static final String MOD_ID = "ee3";
    protected static final String FINGERPRINT = "@FINGERPRINT@";

    @Mod.Instance(EquivalentExchange3.MOD_ID)
    public static EquivalentExchange3 instance;

    @SidedProxy(clientSide = "com.pahimar.ee3.proxy.ClientProxy", serverSide = "com.pahimar.ee3.proxy.ServerProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event) {

        if (FINGERPRINT.equals("@FINGERPRINT@")) {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        }
        else {
            LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {

        Files.updateFileReferences();
        event.registerServerCommand(new CommandEE());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        Files.init(event);

        Network.init();

        proxy.registerKeybindings();

        ModItems.register();

        ModBlocks.init();

        EnergyValues.init();

        AlchemyArrays.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        // Register the GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        // Initialize the blacklist registry
        BlacklistRegistry.INSTANCE.load();

        // Initialize mod tile entities
        TileEntities.init();

        // Register the Items Event Handler
        proxy.registerEventHandlers();

        CraftingHandler.init();
        Recipes.init();

        // Register our fuels
        GameRegistry.registerFuelHandler(new FuelHandler());

        // Register the Waila data provider
        FMLInterModComms.sendMessage("Waila", "register", "com.pahimar.ee3.waila.WailaDataProvider.callbackRegister");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        Abilities.init();

        // Initialize our test files
        new VanillaTestSuite().build().save();
        new EETestSuite().build().save();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {

        WorldEventHandler.hasInitilialized = false;
        EnergyValueRegistry.INSTANCE.save();
        PlayerKnowledgeRegistry.INSTANCE.saveAll();
        BlacklistRegistry.INSTANCE.saveAll();
    }

    public EnergyValueRegistry getEnergyValueRegistry() {
        return EnergyValueRegistry.INSTANCE;
    }

    public RecipeRegistry getRecipeRegistry() {
        return RecipeRegistry.INSTANCE;
    }

    public AludelRecipeManager getAludelRecipeManager() {
        return AludelRecipeManager.getInstance();
    }

    public BlacklistRegistry getBlacklistRegistry() {
        return BlacklistRegistry.INSTANCE;
    }

    public AlchemyArrayRegistry getAlchemyArrayRegistry() {
        return AlchemyArrayRegistry.INSTANCE;
    }

    public PlayerKnowledgeRegistry getPlayerKnowledgeRegistry() {
        return PlayerKnowledgeRegistry.INSTANCE;
    }
}
