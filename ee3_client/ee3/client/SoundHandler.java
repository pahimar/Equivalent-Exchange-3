package ee3.client;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import static ee3.lib.Reference.*;
import static ee3.lib.Sounds.*;

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

	@Override
	// Initializes our entries into the Sound Pool
	public void onLoadSoundSettings(SoundManager soundManager) {
		for (int i = 0; i < soundFiles.length; i++)
			soundManager.getSoundsPool().addSound(soundFiles[i], this.getClass().getResource("/" + soundFiles[i]));
	}

	@Override public void onSetupAudio(SoundManager soundManager) { }
	@Override public SoundPoolEntry onPlayBackgroundMusic(SoundManager soundManager, SoundPoolEntry entry) { return entry; }
	@Override public SoundPoolEntry onPlayStreaming(SoundManager soundManager, SoundPoolEntry entry, String soundName, float x, float y, float z) { return entry; }
	@Override public SoundPoolEntry onPlaySound(SoundManager soundManager, SoundPoolEntry entry, String soundName, float x, float y, float z, float volume, float pitch) { return entry; }
	@Override public SoundPoolEntry onPlaySoundEffect(SoundManager soundManager, SoundPoolEntry entry, String soundName, float volume, float pitch) { return entry; }

}
