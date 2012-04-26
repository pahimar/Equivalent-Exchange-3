package net.minecraft.src.ee3.server;

import java.io.File;

import net.minecraft.src.ModLoader;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.World;
import net.minecraft.src.ee3.core.IProxy;

public class EEProxy implements IProxy {

	public EEProxy() { }
	
	@Override
	public void registerRenderInformation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerTileEntities() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerTranslations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public File getMinecraftDir() {
		return new File(".");
	}

	@Override
	public boolean isRemote() {
		return false;
	}

	@Override
	public World getCurrentWorld() {
		return null;
	}

	@Override
	public String getMinecraftVersion() {
		return ModLoader.getMinecraftServerInstance().getVersion();
	}
	
	public void handleControl(NetworkManager network, int key) { } 
	public void handlePedestalPacket(int x, int y, int z, int itemId, boolean activated) { }
	public void handleTEPacket(int x, int y, int z, byte direction, String player) { }
}
