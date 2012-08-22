package ee3.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import ee3.common.core.CommonProxy;
import ee3.common.core.handlers.ConfigurationHandler;
import ee3.common.lib.Reference;
import ee3.common.network.PacketHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class EquivalentExchange3 {

	@Instance
	public static EquivalentExchange3 instance;
	
	@SidedProxy(clientSide = "ee3.client.core.ClientProxy", serverSide = "ee3.core.common.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		
		// Initialize the configuration
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());

		// Register the KeyBinding Handler (Client only)
		proxy.registerKeyBindingHandler();

		// Register the Sound Handler (Client only)
		proxy.registerSoundHandler();

	}
	
	@Init
	public void load(FMLInitializationEvent event) { }
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event) { }
}
