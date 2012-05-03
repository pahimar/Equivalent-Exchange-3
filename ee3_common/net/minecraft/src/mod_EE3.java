package net.minecraft.src;

import net.minecraft.src.ModLoader;
import net.minecraft.src.ee3.core.ConfigurationManager;
import net.minecraft.src.ee3.core.IProxy;
import net.minecraft.src.ee3.core.ServerClientProxy;
import net.minecraft.src.ee3.core.Version;
import net.minecraft.src.ee3.gui.GuiHandler;
import net.minecraft.src.ee3.lib.Reference;
import net.minecraft.src.ee3.lib.Sounds;
import net.minecraft.src.ee3.network.PacketHandler;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;


public class mod_EE3 extends NetworkMod {

	public static mod_EE3 instance;
	public static IProxy proxy;
	public static ConfigurationManager config = new ConfigurationManager(Reference.CONFIG_FILE);
	
	public mod_EE3() {
		instance = this;
		proxy = ServerClientProxy.getProxy();
		
		// Forge version check
		MinecraftForge.versionDetect("Equivalent Exchange 3", Version.REQ_FORGE_MAJOR, Version.REQ_FORGE_MINOR, Version.REQ_FORGE_REVISION);
		
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
		
		// Initialise the configuration settings from file
		config.init();
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
