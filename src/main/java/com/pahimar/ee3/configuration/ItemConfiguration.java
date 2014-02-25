package com.pahimar.ee3.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class ItemConfiguration
{

    private static Configuration itemConfiguration;

    private static final String CATEGORY_DURABILITY = "durability";

    protected static void init(File configFile)
    {

        itemConfiguration = new Configuration(configFile);

        try
        {
            itemConfiguration.load();
            
            /* Item configs */

            /* Item durability configs */
            ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY = itemConfiguration.get(CATEGORY_DURABILITY, ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY_CONFIGNAME, ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY_DEFAULT).getInt(ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY_DEFAULT);
            ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY = itemConfiguration.get(CATEGORY_DURABILITY, ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY_CONFIGNAME, ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY_DEFAULT).getInt(ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY_DEFAULT);
        }
        catch (Exception e)
        {
        	FMLLog.severe("The mod %s has had a problem loading its item configuration.", Reference.MOD_NAME);
        }
        finally
        {
            itemConfiguration.save();
        }
    }
}
