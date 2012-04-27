package net.minecraft.src.ee3.client;

import net.minecraft.src.SoundManager;
import net.minecraft.src.SoundPoolEntry;
import net.minecraft.src.forge.ISoundHandler;

public class SoundHandler implements ISoundHandler {

	@Override
	public void onSetupAudio(SoundManager soundManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadSoundSettings(SoundManager soundManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SoundPoolEntry onPlayBackgroundMusic(SoundManager soundManager,
			SoundPoolEntry entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoundPoolEntry onPlayStreaming(SoundManager soundManager,
			SoundPoolEntry entry, String soundName, float x, float y, float z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoundPoolEntry onPlaySound(SoundManager soundManager,
			SoundPoolEntry entry, String soundName, float x, float y, float z,
			float volume, float pitch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoundPoolEntry onPlaySoundEffect(SoundManager soundManager,
			SoundPoolEntry entry, String soundName, float volume, float pitch) {
		// TODO Auto-generated method stub
		return null;
	}

}
