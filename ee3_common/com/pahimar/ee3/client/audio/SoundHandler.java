package com.pahimar.ee3.client.audio;

import java.util.logging.Level;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.lib.Sounds;

/**
 * Equivalent-Exchange-3
 * 
 * SoundHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class SoundHandler {

    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) {

        // For each custom sound file we have defined in Sounds
        for (String soundFile : Sounds.soundFiles) {
            // Try to add the custom sound file to the pool of sounds
            try {
                event.manager.soundPoolSounds.addSound(soundFile, this.getClass().getResource("/" + soundFile));
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e) {
                LogHelper.log(Level.WARNING, "Failed loading sound file: " + soundFile);
            }
        }
    }
}
