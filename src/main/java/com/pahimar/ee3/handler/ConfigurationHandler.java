package com.pahimar.ee3.handler;

import com.pahimar.ee3.configuration.ConfigurationReader;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.util.ConfigurationHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;

    public static void init(File configFile)
    {
        if (configuration == null)
        {
            configuration = new Configuration(configFile, true);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        // [[TODO Come back and do these constants in logical locations and names]]
        // I did not change the classes used for configuration to minimize needed refactoring.
        // I think Settings should be moved from reference.
        ConfigurationReader.ReadConfig(configuration);

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }
}
