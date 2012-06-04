package ee3.client;

import java.io.File;
import java.lang.reflect.Field;

import cpw.mods.fml.common.ReflectionHelper;

import ee3.core.interfaces.IProxy;
import static ee3.lib.CustomItemRarity.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.World;
import net.minecraft.src.forge.EnumHelperClient;
import net.minecraft.src.forge.MinecraftForgeClient;

/**
 * TODO Class Description 
 * @author pahimar, cpw
 *
 */
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

	public void addCustomEnumRarityTypes() {
		EnumHelperClient.addRarity(JUNK, COLOR_JUNK, DISPLAY_NAME_JUNK);
		EnumHelperClient.addRarity(NORMAL, COLOR_NORMAL, DISPLAY_NAME_NORMAL);
		EnumHelperClient.addRarity(MAGICAL, COLOR_MAGICAL, DISPLAY_NAME_MAGICAL);
		EnumHelperClient.addRarity(RARE, COLOR_RARE, DISPLAY_NAME_RARE);
		EnumHelperClient.addRarity(LEGENDARY, COLOR_LEGENDARY, DISPLAY_NAME_LEGENDARY);
	}

	@Override
	public EnumRarity getCustomEnumRarityType(String custom) {
		for (EnumRarity eRare : EnumRarity.class.getEnumConstants()) {
			if (eRare.name().equals(custom))
				return eRare;
		}
		return EnumRarity.common;
	}
	
}
