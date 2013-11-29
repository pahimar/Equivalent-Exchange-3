package com.pahimar.ee3.configuration;

import java.io.File;
import java.util.logging.Level;

import com.pahimar.ee3.lib.ItemIds;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.Configuration;

public class ItemConfiguration {

    private static Configuration itemConfiguration;
    
    private static final String CATEGORY_DURABILITY = "durability";
    
    protected static void init(File configFile) {

        itemConfiguration = new Configuration(configFile);
        
        try {
            itemConfiguration.load();
            
            /* Item configs */
            ItemIds.MINIUM_SHARD = itemConfiguration.getItem(Strings.MINIUM_SHARD_NAME, ItemIds.MINIUM_SHARD_DEFAULT).getInt(ItemIds.MINIUM_SHARD_DEFAULT);
            ItemIds.INERT_STONE = itemConfiguration.getItem(Strings.INERT_STONE_NAME, ItemIds.INERT_STONE_DEFAULT).getInt(ItemIds.INERT_STONE_DEFAULT);
            ItemIds.MINIUM_STONE = itemConfiguration.getItem(Strings.MINIUM_STONE_NAME, ItemIds.MINIUM_STONE_DEFAULT).getInt(ItemIds.MINIUM_STONE_DEFAULT);
            ItemIds.PHILOSOPHERS_STONE = itemConfiguration.getItem(Strings.PHILOSOPHERS_STONE_NAME, ItemIds.PHILOSOPHERS_STONE_DEFAULT).getInt(ItemIds.PHILOSOPHERS_STONE_DEFAULT);
            ItemIds.ALCHEMICAL_DUST = itemConfiguration.getItem(Strings.ALCHEMICAL_DUST_NAME, ItemIds.ALCHEMICAL_DUST_DEFAULT).getInt(ItemIds.ALCHEMICAL_DUST_DEFAULT);
            ItemIds.ALCHEMICAL_BAG = itemConfiguration.getItem(Strings.ALCHEMICAL_BAG_NAME, ItemIds.ALCHEMICAL_BAG_DEFAULT).getInt(ItemIds.ALCHEMICAL_BAG_DEFAULT);
            ItemIds.ALCHEMICAL_CHALK = itemConfiguration.getItem(Strings.ALCHEMICAL_CHALK_NAME, ItemIds.ALCHEMICAL_CHALK_DEFAULT).getInt(ItemIds.ALCHEMICAL_CHALK_DEFAULT);

            /* Item durability configs */
            ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY = itemConfiguration.get(CATEGORY_DURABILITY, ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY_CONFIGNAME, ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY_DEFAULT).getInt(ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY_DEFAULT);
            ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY = itemConfiguration.get(CATEGORY_DURABILITY, ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY_CONFIGNAME, ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY_DEFAULT).getInt(ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY_DEFAULT);
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its item configuration");
        }
        finally {
            itemConfiguration.save();
        }
    }

}
