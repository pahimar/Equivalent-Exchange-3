package ee3.client.core.handlers;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler {
	
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) {
		System.out.println(event.manager.toString());
	}

}
