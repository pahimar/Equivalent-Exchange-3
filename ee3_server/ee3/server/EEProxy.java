package ee3.server;

import java.io.File;
import java.lang.reflect.Field;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ReflectionHelper;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.World;
import ee3.core.interfaces.IProxy;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class EEProxy implements IProxy {

	public EEProxy() { }
	
	@SuppressWarnings("unchecked")
    public <T, E> T getPrivateValue(Class <? super E > classToAccess, E instance, int fieldIndex) {
		return ReflectionHelper.getPrivateValue(classToAccess, instance, fieldIndex);
    }

    @SuppressWarnings("unchecked")
    public <T, E> T getPrivateValue(Class <? super E > classToAccess, E instance, String fieldName) {
    	return ReflectionHelper.getPrivateValue(classToAccess, instance, fieldName);
    }
	
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

	@Override
	public void registerSoundHandler() { }

	@Override
	// TODO Server side: Handle GUI call
	public Object handleGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void playSound(String soundName, float x, float y, float z, float volume, float pitch) {	}

	@Override
	public void addCustomEnumRarityTypes() { }

	@Override
	public EnumRarity getCustomEnumRarityType(String custom) {
		return null;
	}
}
