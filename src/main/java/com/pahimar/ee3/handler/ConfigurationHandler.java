package com.pahimar.ee3.handler;

import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.reference.Settings;
import com.pahimar.ee3.util.ConfigurationHelper;
import com.pahimar.ee3.util.LogHelper;
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
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        Settings.TRANSMUTATION_KNOWLEDGE_MODE = ConfigurationHelper.getString(configuration, Messages.Configuration.TRANSMUTATION_KNOWLEDGE_MODE, Messages.Configuration.CATEGORY_TRANSMUTATION, "All", StatCollector.translateToLocal(Messages.Configuration.TRANSMUTATION_KNOWLEDGE_MODE_COMMENT), new String[]{"All", "Select", "None"}, Messages.Configuration.TRANSMUTATION_KNOWLEDGE_MODE_LABEL);

        LogHelper.info(Settings.TRANSMUTATION_KNOWLEDGE_MODE);
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
