package ee3.core.interfaces;

import java.io.File;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.World;
import net.minecraft.src.forge.IGuiHandler;

public interface IProxy {
	
	public abstract void registerRenderInformation();
	
	public abstract void registerTileEntities();
	
	public abstract void registerTranslations();
	
	public abstract File getMinecraftDir();
	
	public abstract boolean isRemote();
	
	public abstract World getCurrentWorld();
	
	public abstract String getMinecraftVersion();
	
	public abstract void registerSoundHandler();
	
	public abstract void handleControl(NetworkManager network, int key);
	
	public abstract void handlePedestalPacket(int x, int y, int z, int itemId, boolean activated);
	
	public abstract void handleTEPacket(int x, int y, int z, byte direction, String player);
	
	public abstract Object handleGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);
	
	public abstract void playSound(String soundName, float x, float y, float z, float volume, float pitch);
}
