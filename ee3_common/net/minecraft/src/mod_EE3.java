package net.minecraft.src;

import ee3.addons.BuildCraftAddon;
import ee3.core.ConfigurationManager;
import ee3.core.CraftingHandler;
import ee3.core.RecipesPhilStone;
import ee3.core.ServerClientProxy;
import ee3.core.Version;
import ee3.core.interfaces.IProxy;
import ee3.emc.EMCList;
import ee3.gui.GuiHandler;
import ee3.item.ModItems;
import ee3.lib.ItemIds;
import ee3.lib.Reference;
import ee3.lib.Sounds;
import ee3.network.PacketHandler;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class mod_EE3 extends NetworkMod {

	public static mod_EE3 instance;
	public static IProxy proxy;
	public static EMCList emcList;
	public static ConfigurationManager config = new ConfigurationManager(Reference.CONFIG_FILE);
	
	public mod_EE3() {
		instance = this;
		emcList = new EMCList();
		proxy = ServerClientProxy.getProxy();
		
		// Forge version check
		MinecraftForge.versionDetect(Reference.MOD_NAME, Version.REQ_FORGE_MAJOR, Version.REQ_FORGE_MINOR, Version.REQ_FORGE_REVISION);
		
		// Register the mod with ModLoader
		ModLoader.setInGameHook(this, true, true);
		
		// Check this version of EE against the remote version authority
		Version.versionCheck();
		
		// Bind Packet Handler
		MinecraftForge.registerConnectionHandler(new PacketHandler());
		
		// Bind GUI Handler
		MinecraftForge.setGuiHandler(this, new GuiHandler());
		
		// Bind Sound Handler
		this.proxy.registerSoundHandler();
		
		// Register the Crafting Handler
		MinecraftForge.registerCraftingHandler(new CraftingHandler());
		
		// Initialise the configuration settings from file
		this.config.init();
		
		// Add in the custom Item rarity types
		this.proxy.addCustomEnumRarityTypes();
		
		// Initialise the mod items
		ModItems.init();
		
		// Initialise the Philosopher Stone recipes
		RecipesPhilStone.initRecipes();
		
		// Initialise the EMC List
		this.emcList.initEMCList();
	}
	
	@Override
	public String getVersion() {
		return Version.getVersion();
	}

	@Override
	public boolean clientSideRequired() {
		return true;
	}
	
	@Override
	public boolean serverSideRequired() {
		return false;
	}
	
	@Override
	public void load() { }
	
}
