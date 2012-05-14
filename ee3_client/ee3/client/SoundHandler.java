package ee3.client;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import net.minecraft.src.ModLoader;
import net.minecraft.src.SoundManager;
import net.minecraft.src.SoundPoolEntry;
import net.minecraft.src.mod_EE3;
import net.minecraft.src.forge.ISoundHandler;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class SoundHandler implements ISoundHandler {

	private String[] eeSoundFiles = {"ee/sound/break.ogg", "ee/sound/chargetick.ogg", "ee/sound/destruct.ogg", 
			"ee/sound/flash.ogg", "ee/sound/gust.ogg", "ee/sound/heal.ogg", 
			"ee/sound/kinesis.ogg", "ee/sound/launch.ogg", "ee/sound/nova.ogg", 
			"ee/sound/philball.ogg", "ee/sound/tock.ogg", "ee/sound/transmute.ogg", 
			"ee/sound/wall.ogg",	"ee/sound/waterball.ogg", "ee/sound/wind.ogg"};
	
	@Override
	// Initializes our entries into the Sound Pool
	public void onLoadSoundSettings(SoundManager soundManager) {
		for (int i = 0; i < eeSoundFiles.length; i++)
			soundManager.getSoundsPool().addSound(eeSoundFiles[i], this.getClass().getResource("/" + eeSoundFiles[i]));
	}

	@Override public void onSetupAudio(SoundManager soundManager) { }
	@Override public SoundPoolEntry onPlayBackgroundMusic(SoundManager soundManager, SoundPoolEntry entry) { return entry; }
	@Override public SoundPoolEntry onPlayStreaming(SoundManager soundManager, SoundPoolEntry entry, String soundName, float x, float y, float z) { return entry; }
	@Override public SoundPoolEntry onPlaySound(SoundManager soundManager, SoundPoolEntry entry, String soundName, float x, float y, float z, float volume, float pitch) { return entry; }
	@Override public SoundPoolEntry onPlaySoundEffect(SoundManager soundManager, SoundPoolEntry entry, String soundName, float volume, float pitch) { return entry; }

}
