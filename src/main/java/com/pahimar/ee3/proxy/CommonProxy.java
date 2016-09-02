package com.pahimar.ee3.proxy;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.blacklist.BlacklistRegistry;
import com.pahimar.ee3.command.CommandEE;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.handler.*;
import com.pahimar.ee3.init.*;
import com.pahimar.ee3.knowledge.PlayerKnowledgeRegistry;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.test.EETestSuite;
import com.pahimar.ee3.test.VanillaTestSuite;
import com.pahimar.ee3.util.FluidHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        Files.init(event);
        Network.init();
        ModItems.getItems().forEach(GameRegistry::register);
        ModBlocks.init();
        EnergyValues.init();
        AlchemyArrays.init();
    }

    @Override
    public void onInit(FMLInitializationEvent event) {

        // Register the GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(EquivalentExchange3.instance, new GuiHandler());

        // Initialize the blacklist registry
        BlacklistRegistry.INSTANCE.load();

        // Initialize mod tile entities
        TileEntities.init();

        // Register event handlers
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(new ItemEventHandler());
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        MinecraftForge.EVENT_BUS.register(new CraftingHandler());

        // TODO Come back and remove silly init methods that are not necessary
        CraftingHandler.init();
        Recipes.init();

        // Register our fuels
        GameRegistry.registerFuelHandler(new FuelHandler());

        // Register the Waila data provider
        FMLInterModComms.sendMessage("Waila", "register", "com.pahimar.ee3.waila.WailaDataProvider.callbackRegister");
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event){

        FluidHelper.initMilk();
        Abilities.init();

        // Initialize our test files
        new VanillaTestSuite().build().save();
        new EETestSuite().build().save();
    }

    @Override
    public void onServerStarting(FMLServerStartingEvent event){

        Files.updateFileReferences();
        event.registerServerCommand(new CommandEE());
    }

    @Override
    public void onServerStopping(FMLServerStoppingEvent event){

        WorldEventHandler.hasInitilialized = false;
        EnergyValueRegistry.INSTANCE.save();
        PlayerKnowledgeRegistry.INSTANCE.saveAll();
        BlacklistRegistry.INSTANCE.saveAll();
    }

    public void registerEventHandlers() {

        ItemEventHandler itemEventHandler = new ItemEventHandler();
        CraftingHandler craftingHandler = new CraftingHandler();
        PlayerEventHandler playerEventHandler = new PlayerEventHandler();
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
        MinecraftForge.EVENT_BUS.register(craftingHandler);
        MinecraftForge.EVENT_BUS.register(craftingHandler);
    }
}
