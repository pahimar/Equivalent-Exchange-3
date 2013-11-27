package com.pahimar.ee3.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class TransmutationConfiguration {

    private static Configuration transmutationConfiguration;

    private static final String CATEGORY_TRANSMUTATION = "transmutation";

    protected static void init(File configFile) {

        transmutationConfiguration = new Configuration(configFile);

        try {
            transmutationConfiguration.load();

            /* Transmutation configs */
            ConfigurationSettings.TRANSMUTE_COST_ITEM = transmutationConfiguration.get(CATEGORY_TRANSMUTATION, ConfigurationSettings.TRANSMUTE_COST_ITEM_CONFIGNAME, ConfigurationSettings.TRANSMUTE_COST_ITEM_DEFAULT).getInt(ConfigurationSettings.TRANSMUTE_COST_ITEM_DEFAULT);
            ConfigurationSettings.TRANSMUTE_COST_BLOCK = transmutationConfiguration.get(CATEGORY_TRANSMUTATION, ConfigurationSettings.TRANSMUTE_COST_BLOCK_CONFIGNAME, ConfigurationSettings.TRANSMUTE_COST_BLOCK_DEFAULT).getInt(ConfigurationSettings.TRANSMUTE_COST_BLOCK_DEFAULT);
            ConfigurationSettings.TRANSMUTE_COST_MOB = transmutationConfiguration.get(CATEGORY_TRANSMUTATION, ConfigurationSettings.TRANSMUTE_COST_MOB_CONFIGNAME, ConfigurationSettings.TRANSMUTE_COST_MOB_DEFAULT).getInt(ConfigurationSettings.TRANSMUTE_COST_MOB_DEFAULT);
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its transmutation configuration");
        }
        finally {
            transmutationConfiguration.save();
        }
    }

}
