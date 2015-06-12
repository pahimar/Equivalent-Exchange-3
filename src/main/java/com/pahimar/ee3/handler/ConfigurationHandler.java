package com.pahimar.ee3.handler;

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

    private final static String CATEGORY_DEBUG = "general.Debug";

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
        // TODO Come back and do these constants in logical locations and names
        Settings.General.syncThreshold = configuration.getInt(Messages.Configuration.GENERAL_SYNC_THRESHOLD, Configuration.CATEGORY_GENERAL, 5, 0, Short.MAX_VALUE, StatCollector.translateToLocal(Messages.Configuration.GENERAL_SYNC_THRESHOLD_COMMENT), Messages.Configuration.GENERAL_SYNC_THRESHOLD_LABEL);
        Settings.Sounds.soundMode = ConfigurationHelper.getString(configuration, Messages.Configuration.SOUND_MODE, Configuration.CATEGORY_GENERAL, "All", StatCollector.translateToLocal(Messages.Configuration.SOUND_MODE_COMMENT), new String[]{"All", "Self", "None"}, Messages.Configuration.SOUND_MODE_LABEL);
        Settings.Abilities.onlyLoadFile = configuration.getBoolean(Messages.Configuration.ABILITIES_ONLY_LOAD_FILE, Configuration.CATEGORY_GENERAL, false, StatCollector.translateToLocal(Messages.Configuration.ABILITIES_ONLY_LOAD_FILE_COMMENT), Messages.Configuration.ABILITIES_ONLY_LOAD_FILE_LABEL);
        Settings.DynamicEnergyValueGeneration.regenerateEnergyValuesWhen = ConfigurationHelper.getString(configuration, Messages.Configuration.REGENERATE_ENERGYVALUES_WHEN, Configuration.CATEGORY_GENERAL, "Always", StatCollector.translateToLocal(Messages.Configuration.REGENERATE_ENERGYVALUES_WHEN_COMMENT), new String[]{"Never", "When Mods Change", "Always"}, Messages.Configuration.REGENERATE_ENERGYVALUES_WHEN_LABEL);
        Settings.Debug.logTraceToInfo = configuration.getBoolean(Messages.Configuration.LOG_TRACE_TO_INFO, CATEGORY_DEBUG, false, StatCollector.translateToLocal(Messages.Configuration.LOG_TRACE_TO_INFO_COMMENT), Messages.Configuration.LOG_TRACE_TO_INFO_LABEL);

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
