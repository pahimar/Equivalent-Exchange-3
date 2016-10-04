package com.pahimar.ee3.handler;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.util.ConfigurationUtils;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;

    private static final String CATEGORY_ENERGY_VALUE = "general.energy_value";
    private static final String CATEGORY_PLAYER_KNOWLEDGE = "general.player_knowledge";
    private static final String CATEGORY_DEBUG = "general.debug";
    private static final String CATEGORY_SERVER = "general.server";

    public static void init(File configFile) {

        if (configuration == null) {
            configuration = new Configuration(configFile, true);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {

        Settings.serverSyncThreshold = configuration.getInt(
                Settings.SERVER_SYNC_THRESHOLD_NAME,
                CATEGORY_SERVER,
                Settings.SERVER_SYNC_THRESHOLD_DEFAULT,
                Settings.SERVER_SYNC_THRESHOLD_MIN,
                Settings.SERVER_SYNC_THRESHOLD_MAX,
                I18n.translateToLocal(Settings.SERVER_SYNC_THRESHOLD_COMMENT),
                Settings.SERVER_SYNC_THRESHOLD_LABEL);

        Settings.regenerateEnergyValuesWhen = ConfigurationUtils.getString(configuration,
                Settings.ENERGY_VALUE_REGENERATE_WHEN_NAME,
                CATEGORY_ENERGY_VALUE,
                Settings.ENERGY_VALUE_REGENERATE_WHEN_DEFAULT,
                I18n.translateToLocal(Settings.ENERGY_VALUE_REGENERATE_WHEN_COMMENT),
                Settings.ENERGY_VALUE_REGENERATE_WHEN_OPTIONS,
                Settings.ENERGY_VALUE_REGENERATE_WHEN_LABEL);

        Settings.playerKnowledgeTemplateEnabled = configuration.getBoolean(
                Settings.USE_PLAYER_KNOWLEDGE_TEMPLATE_NAME,
                CATEGORY_PLAYER_KNOWLEDGE,
                Settings.USE_PLAYER_KNOWLEDGE_TEMPLATE_DEFAULT,
                I18n.translateToLocal(Settings.USE_PLAYER_KNOWLEDGE_TEMPLATE_COMMENT),
                Settings.USE_PLAYER_KNOWLEDGE_TEMPLATE_LABEL);

        Settings.debugEnabled = configuration.getBoolean(
                Settings.DEBUG_ENABLED_NAME,
                CATEGORY_DEBUG,
                Settings.DEBUG_ENABLED_DEFAULT,
                I18n.translateToLocal(Settings.DEBUG_ENABLED_COMMENT),
                Settings.DEBUG_ENABLED_LABEL);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.getModID().equalsIgnoreCase(EquivalentExchange3.MOD_ID)) {
            loadConfiguration();
        }
    }

    public static class Settings {

        public static int serverSyncThreshold;
        private static final String SERVER_SYNC_THRESHOLD_NAME = "sync_threshold";
        private static final String SERVER_SYNC_THRESHOLD_LABEL = "server.sync_threshold.label";
        private static final String SERVER_SYNC_THRESHOLD_COMMENT = "server.sync_threshold.comment";
        private static final int SERVER_SYNC_THRESHOLD_DEFAULT = 5;
        private static final int SERVER_SYNC_THRESHOLD_MIN = 0;
        private static final int SERVER_SYNC_THRESHOLD_MAX = Short.MAX_VALUE;

        public static String regenerateEnergyValuesWhen;
        private static final String ENERGY_VALUE_REGENERATE_WHEN_NAME = "regenerate_values_when";
        private static final String ENERGY_VALUE_REGENERATE_WHEN_LABEL = "energy_value.regenerate_values_when.label";
        private static final String ENERGY_VALUE_REGENERATE_WHEN_COMMENT = "energy_value.regenerate_values_when.comment";
        private static final String ENERGY_VALUE_REGENERATE_WHEN_DEFAULT = "As Needed";
        private static final String[] ENERGY_VALUE_REGENERATE_WHEN_OPTIONS = new String[]{"As Needed", "Always"};

        public static boolean playerKnowledgeTemplateEnabled;
        private static final String USE_PLAYER_KNOWLEDGE_TEMPLATE_NAME = "use_template";
        private static final String USE_PLAYER_KNOWLEDGE_TEMPLATE_LABEL = "player_knowledge.use_template.label";
        private static final String USE_PLAYER_KNOWLEDGE_TEMPLATE_COMMENT = "player_knowledge.use_template.comment";
        private static final boolean USE_PLAYER_KNOWLEDGE_TEMPLATE_DEFAULT = true;

        public static boolean debugEnabled;
        private static final String DEBUG_ENABLED_NAME = "enabled";
        private static final String DEBUG_ENABLED_LABEL = "debug.enabled.label";
        private static final String DEBUG_ENABLED_COMMENT = "debug.enabled.comment";
        private static final boolean DEBUG_ENABLED_DEFAULT = false;
    }
}
