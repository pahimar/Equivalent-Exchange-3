package com.pahimar.ee3.core.handlers;

import java.io.File;
import java.util.logging.Level;

import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Sounds;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

/**
 * SoundHandler
 * 
 * Client specific handler for sound related events
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
