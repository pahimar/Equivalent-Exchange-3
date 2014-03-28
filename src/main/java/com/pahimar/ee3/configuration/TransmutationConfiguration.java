package com.pahimar.ee3.configuration;

import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.io.File;

public class TransmutationConfiguration
{
    private static final String CATEGORY_TRANSMUTATION = "transmutation";
    private static Configuration transmutationConfiguration;

    protected static void init(File configFile)
    {

        transmutationConfiguration = new Configuration(configFile);

        try
        {
            transmutationConfiguration.load();

            /* Transmutation configs */
            ConfigurationSettings.TRANSMUTE_COST_ITEM = transmutationConfiguration.get(CATEGORY_TRANSMUTATION, ConfigurationSettings.TRANSMUTE_COST_ITEM_CONFIGNAME, ConfigurationSettings.TRANSMUTE_COST_ITEM_DEFAULT).getInt(ConfigurationSettings.TRANSMUTE_COST_ITEM_DEFAULT);
            ConfigurationSettings.TRANSMUTE_COST_BLOCK = transmutationConfiguration.get(CATEGORY_TRANSMUTATION, ConfigurationSettings.TRANSMUTE_COST_BLOCK_CONFIGNAME, ConfigurationSettings.TRANSMUTE_COST_BLOCK_DEFAULT).getInt(ConfigurationSettings.TRANSMUTE_COST_BLOCK_DEFAULT);
            ConfigurationSettings.TRANSMUTE_COST_MOB = transmutationConfiguration.get(CATEGORY_TRANSMUTATION, ConfigurationSettings.TRANSMUTE_COST_MOB_CONFIGNAME, ConfigurationSettings.TRANSMUTE_COST_MOB_DEFAULT).getInt(ConfigurationSettings.TRANSMUTE_COST_MOB_DEFAULT);
        }
        catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, Reference.MOD_NAME + " has had a problem loading its transmutation configuration");
        }
        finally
        {
            transmutationConfiguration.save();
        }
    }
}
