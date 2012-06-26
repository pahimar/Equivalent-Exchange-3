package ee3.core;

import cpw.mods.fml.common.FMLCommonHandler;
import ee3.core.interfaces.IProxy;
import ee3.emc.EMCList;
import ee3.gui.GuiHandler;
import ee3.item.ModItems;
import ee3.lib.ItemIds;
import ee3.lib.Reference;
import ee3.lib.Sounds;
import ee3.lib.Version;
import ee3.network.PacketHandler;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import net.minecraft.src.SidedProxy;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class mod_EE3 extends NetworkMod {

	private static mod_EE3 instance;
	@SidedProxy(clientSide="ee3.client.EEProxy", serverSide="ee3.server.EEProxy")
	public static IProxy proxy;
	public static EMCList emcList;
	public static ConfigurationManager config = new ConfigurationManager(Reference.CONFIG_FILE);
	
	public void load() {
		instance = this;
		emcList = new EMCList();
		
		// Forge version check
		MinecraftForge.versionDetect(Reference.MOD_NAME, Version.REQ_FORGE_MAJOR, Version.REQ_FORGE_MINOR, Version.REQ_FORGE_REVISION);
		
		// Register the Tick Handler
		FMLCommonHandler.instance().registerTickHandler(new TickHandler());
		
		// Check this version of EE against the remote version authority
		Version.versionCheck();
		
		// Register Packet Handler
		MinecraftForge.registerConnectionHandler(new PacketHandler());
		
		// Register KeyBindings
		this.proxy.registerKeyBindings();
		
		// Register GUI Handler
		MinecraftForge.setGuiHandler(this, new GuiHandler());
		
		// Register Sound Handler
		this.proxy.registerSoundHandler();
		
		// Register the EntityLiving Handler
		MinecraftForge.registerEntityLivingHandler(new EntityLivingHandler());
		
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
		//this.emcList.initEMCList();
	}
	
	public static BaseMod instance() {
		return instance;
	}
	
	@Override
	public void keyBindingEvent(Object event) {
		this.proxy.keyBindingEvent(event);
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
	
}
