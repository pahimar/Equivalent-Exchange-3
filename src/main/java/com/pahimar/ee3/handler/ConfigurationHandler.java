package com.pahimar.ee3.handler;

import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.util.ConfigurationHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;

    private static final String CATEGORY_SOUND = "general.sound";
    private static final String CATEGORY_ENERGY_VALUE = "general.energy_value";

    public static void init(File configFile) {

        if (configuration == null) {
            configuration = new Configuration(configFile, true);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {

        Settings.serverSyncThreshold = configuration.getInt(
                Settings.ENERGY_VALUE_SYNC_THRESHOLD_NAME,
                CATEGORY_ENERGY_VALUE,
                Settings.ENERGY_VALUE_SYNC_THRESHOLD_DEFAULT,
                Settings.ENERGY_VALUE_SYNC_THRESHOLD_MIN,
                Settings.ENERGY_VALUE_SYNC_THRESHOLD_MAX,
                StatCollector.translateToLocal(Settings.ENERGY_VALUE_SYNC_THRESHOLD_COMMENT),
                Settings.ENERGY_VALUE_SYNC_THRESHOLD_LABEL);

        Settings.regenerateEnergyValuesWhen = ConfigurationHelper.getString(configuration,
                Settings.ENERGY_VALUE_REGENERATE_WHEN_NAME,
                CATEGORY_ENERGY_VALUE,
                Settings.ENERGY_VALUE_REGENERATE_WHEN_DEFAULT,
                StatCollector.translateToLocal(Settings.ENERGY_VALUE_REGENERATE_WHEN_COMMENT),
                Settings.ENERGY_VALUE_REGENERATE_WHEN_OPTIONS,
                Settings.ENERGY_VALUE_REGENERATE_WHEN_LABEL);

        Settings.energyValueDebugLoggingEnabled = configuration.getBoolean(
                Settings.ENERGY_VALUE_DEBUG_LOGGING_ENABLED_NAME,
                CATEGORY_ENERGY_VALUE,
                Settings.ENERGY_VALUE_DEBUG_LOGGING_ENABLED_DEFAULT,
                StatCollector.translateToLocal(Settings.ENERGY_VALUE_DEBUG_LOGGING_ENABLED_COMMENT),
                Settings.ENERGY_VALUE_DEBUG_LOGGING_ENABLED_LABEL);

        Settings.soundMode = ConfigurationHelper.getString(configuration,
                Settings.SOUND_MODE_NAME,
                CATEGORY_SOUND,
                Settings.SOUND_MODE_DEFAULT,
                StatCollector.translateToLocal(Settings.SOUND_MODE_COMMENT),
                Settings.SOUND_MODE_OPTIONS,
                Settings.SOUND_MODE_LABEL);

        Settings.onlyLoadFile = configuration.getBoolean(
                Settings.ABILITIES_ONLY_LOAD_FILE_NAME,
                Configuration.CATEGORY_GENERAL,
                false,
                StatCollector.translateToLocal(Settings.ABILITIES_ONLY_LOAD_FILE_COMMENT),
                Settings.ABILITIES_ONLY_LOAD_FILE_LABEL);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }

    public static class Settings {

        public static int serverSyncThreshold;
        private static final String ENERGY_VALUE_SYNC_THRESHOLD_NAME = "sync_threshold";
        private static final String ENERGY_VALUE_SYNC_THRESHOLD_LABEL = "energy_value.sync_threshold.label";
        private static final String ENERGY_VALUE_SYNC_THRESHOLD_COMMENT = "energy_value.sync_threshold.comment";
        private static final int ENERGY_VALUE_SYNC_THRESHOLD_DEFAULT = 5;
        private static final int ENERGY_VALUE_SYNC_THRESHOLD_MIN = 0;
        private static final int ENERGY_VALUE_SYNC_THRESHOLD_MAX = Short.MAX_VALUE;

        public static String regenerateEnergyValuesWhen;
        private static final String ENERGY_VALUE_REGENERATE_WHEN_NAME = "regenerate_values_when";
        private static final String ENERGY_VALUE_REGENERATE_WHEN_LABEL = "energy_value.regenerate_values_when.label";
        private static final String ENERGY_VALUE_REGENERATE_WHEN_COMMENT = "energy_value.regenerate_values_when.comment";
        private static final String ENERGY_VALUE_REGENERATE_WHEN_DEFAULT = "As Needed";
        private static final String[] ENERGY_VALUE_REGENERATE_WHEN_OPTIONS = new String[]{"As Needed", "Always"};

        public static boolean energyValueDebugLoggingEnabled;
        private static final String ENERGY_VALUE_DEBUG_LOGGING_ENABLED_NAME = "debug_logging_enabled";
        private static final String ENERGY_VALUE_DEBUG_LOGGING_ENABLED_LABEL = "energy_value.debug_logging_enabled.label";
        private static final String ENERGY_VALUE_DEBUG_LOGGING_ENABLED_COMMENT = "energy_value.debug_logging_enabled.comment";
        private static final boolean ENERGY_VALUE_DEBUG_LOGGING_ENABLED_DEFAULT = false;

        public static String soundMode;
        private static final String SOUND_MODE_NAME = "mode";
        private static final String SOUND_MODE_LABEL = "sound.mode.label";
        private static final String SOUND_MODE_COMMENT = "sound.mode.comment";
        private static final String SOUND_MODE_DEFAULT = "All";
        private static final String[] SOUND_MODE_OPTIONS = new String[]{"All", "Self", "None"};

        public static boolean onlyLoadFile;
        private static final String ABILITIES_ONLY_LOAD_FILE_NAME = "abilities.onlyLoadFile";
        private static final String ABILITIES_ONLY_LOAD_FILE_LABEL = "general.abilities.onlyLoadFile.label";
        private static final String ABILITIES_ONLY_LOAD_FILE_COMMENT = "general.abilities.onlyLoadFile.comment";
    }
}
