package ee3.client;

import java.io.File;
import java.lang.reflect.Field;

import ee3.core.interfaces.IProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.World;
import net.minecraft.src.forge.MinecraftForgeClient;

/**
 * TODO Class Description 
 * @author pahimar, cpw
 *
 */
public class EEProxy implements IProxy {

	public EEProxy() { }
	
	@SuppressWarnings("unchecked")
    public <T, E> T getPrivateValue(Class <? super E > classToAccess, E instance, int fieldIndex) {
        try {
            Field f = classToAccess.getDeclaredFields()[fieldIndex];
            f.setAccessible(true);
            return (T) f.get(instance);
        }
        catch (Exception e) {
        	ModLoader.getLogger().severe(String.format("There was a problem getting field %d from %s", fieldIndex, classToAccess.getName()));
        	ModLoader.getLogger().throwing("ReflectionHelper", "getPrivateValue", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T, E> T getPrivateValue(Class <? super E > classToAccess, E instance, String fieldName) {
        try {
            Field f = classToAccess.getDeclaredField(fieldName);
            f.setAccessible(true);
            return (T) f.get(instance);
        }
        catch (Exception e) {
        	ModLoader.getLogger().severe(String.format("There was a problem getting field %s from %s", fieldName, classToAccess.getName()));
        	ModLoader.getLogger().throwing("ReflectionHelper", "getPrivateValue", e);
            throw new RuntimeException(e);
        }
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
		return Minecraft.getMinecraftDir();
	}

	@Override
	public boolean isRemote() {
		return ModLoader.getMinecraftInstance().theWorld.isRemote;
	}

	@Override
	public World getCurrentWorld() {
		return ModLoader.getMinecraftInstance().theWorld;
	}

	@Override
	public String getMinecraftVersion() {
		return ModLoader.getMinecraftInstance().getVersion();
	}
	
	public void handleControl(NetworkManager network, int key) { } 
	public void handlePedestalPacket(int x, int y, int z, int itemId, boolean activated) { }
	public void handleTEPacket(int x, int y, int z, byte direction, String player) { }

	@Override
	public void registerSoundHandler() {
		MinecraftForgeClient.registerSoundHandler(new SoundHandler());
	}

	@Override
	// TODO Client side: Handle GUI call
	public Object handleGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void playSound(String soundName, float x, float y, float z, float volume, float pitch) {
		ModLoader.getMinecraftInstance().sndManager.playSound(soundName, x, y, z, volume, pitch);	
	}

}
