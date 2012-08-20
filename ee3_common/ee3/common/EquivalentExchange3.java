package ee3.common;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import ee3.client.core.KeyBindingHandler;
import ee3.common.core.ConfigurationManager;
import ee3.common.lib.Reference;
import ee3.common.network.PacketHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class EquivalentExchange3 {

	@Instance
	public static EquivalentExchange3 instance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		
		// Initialize the configuration
		ConfigurationManager.init(event.getSuggestedConfigurationFile());
		
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		
		// Initialize the KeyBinding handler
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event) { }
}
