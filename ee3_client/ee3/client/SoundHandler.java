package ee3.client;

import static ee3.lib.Sounds.soundFiles;

import net.minecraft.src.Entity;
import net.minecraft.src.SoundManager;
import net.minecraft.src.SoundPoolEntry;
import net.minecraft.src.forge.adaptors.SoundHandlerAdaptor;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class SoundHandler extends SoundHandlerAdaptor {

	@Override
	// Initializes our entries into the Sound Pool
	public void onLoadSoundSettings(SoundManager soundManager) {
		for (int i = 0; i < soundFiles.length; i++)
			soundManager.getSoundsPool().addSound(soundFiles[i], this.getClass().getResource("/" + soundFiles[i]));
	}
}
