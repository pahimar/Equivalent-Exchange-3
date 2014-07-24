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
        // TODO Come back and do these constants in logical locations and names
        Settings.Transmutation.knowledgeMode = ConfigurationHelper.getString(configuration, Messages.Configuration.TRANSMUTATION_KNOWLEDGE_MODE, Messages.Configuration.CATEGORY_TRANSMUTATION, "All", StatCollector.translateToLocal(Messages.Configuration.TRANSMUTATION_KNOWLEDGE_MODE_COMMENT), new String[]{"All", "Select", "Tier", "Restricted", "None"}, Messages.Configuration.TRANSMUTATION_KNOWLEDGE_MODE_LABEL);
        Settings.Transmutation.maxKnowledgeTier = configuration.getInt(Messages.Configuration.TRANSMUTATION_KNOWLEDGE_MAX_TIER, Messages.Configuration.CATEGORY_TRANSMUTATION, 0, 0, Short.MAX_VALUE, StatCollector.translateToLocal(Messages.Configuration.TRANSMUTATION_KNOWLEDGE_MAX_TIER_COMMENT), Messages.Configuration.TRANSMUTATION_KNOWLEDGE_MAX_TIER_LABEL);
        Settings.Transmutation.useTemplateFile = configuration.getBoolean(Messages.Configuration.TRANSMUTATION_KNOWLEDGE_TEMPLATE, Messages.Configuration.CATEGORY_TRANSMUTATION, true, StatCollector.translateToLocal(Messages.Configuration.TRANSMUTATION_KNOWLEDGE_TEMPLATE_COMMENT), Messages.Configuration.TRANSMUTATION_KNOWLEDGE_TEMPLATE_LABEL);
        Settings.Sounds.soundMode = ConfigurationHelper.getString(configuration, Messages.Configuration.SOUND_MODE, Configuration.CATEGORY_GENERAL, "All", StatCollector.translateToLocal(Messages.Configuration.SOUND_MODE_COMMENT), new String[]{"All", "Self", "None"}, Messages.Configuration.SOUND_MODE_LABEL);

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
