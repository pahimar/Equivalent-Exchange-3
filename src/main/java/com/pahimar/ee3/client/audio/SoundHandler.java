package com.pahimar.ee3.client.audio;

import com.pahimar.ee3.helper.LogHelper;
import com.pahimar.ee3.lib.Sounds;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

/**
 * Equivalent-Exchange-3
 * <p/>
 * SoundHandler
 *
 * @author pahimar
 */
public class SoundHandler
{

    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event)
    {

        // For each custom sound file we have defined in Sounds
        for (String soundFile : Sounds.soundFiles)
        {
            // Try to add the custom sound file to the pool of sounds
            try
            {
                event.manager.addSound(soundFile);
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e)
            {
                LogHelper.warning("Failed loading sound file: " + soundFile);
            }
        }
    }
}
