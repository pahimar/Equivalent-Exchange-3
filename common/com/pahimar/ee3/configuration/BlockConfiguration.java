package com.pahimar.ee3.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.pahimar.ee3.lib.BlockIds;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.common.FMLLog;

public class BlockConfiguration {

    private static Configuration blockConfiguration;

    protected static void init(File configFile) {

        blockConfiguration = new Configuration(configFile);

        try {
            blockConfiguration.load();

            /* Block configs */
            BlockIds.CALCINATOR = blockConfiguration.getBlock(Strings.CALCINATOR_NAME, BlockIds.CALCINATOR_DEFAULT).getInt(BlockIds.CALCINATOR_DEFAULT);
            BlockIds.ALUDEL_BASE = blockConfiguration.getBlock(Strings.ALUDEL_NAME, BlockIds.ALUDEL_BASE_DEFAULT).getInt(BlockIds.ALUDEL_BASE_DEFAULT);
            BlockIds.ALCHEMICAL_CHEST = blockConfiguration.getBlock(Strings.ALCHEMICAL_CHEST_NAME, BlockIds.ALCHEMICAL_CHEST_DEFAULT).getInt(BlockIds.ALCHEMICAL_CHEST_DEFAULT);
            BlockIds.GLASS_BELL = blockConfiguration.getBlock(Strings.GLASS_BELL_NAME, BlockIds.GLASS_BELL_DEFAULT).getInt(BlockIds.GLASS_BELL_DEFAULT);
            BlockIds.ALCHEMY_TABLE = blockConfiguration.getBlock(Strings.ALCHEMY_TABLE_NAME, BlockIds.ALCHEMY_TABLE_DEFAULT).getInt(BlockIds.ALCHEMY_TABLE_DEFAULT);
            BlockIds.RENDERING_TANK = blockConfiguration.getBlock(Strings.RENDERING_TANK_NAME, BlockIds.RENDERING_TANK_DEFAULT).getInt(BlockIds.RENDERING_TANK_DEFAULT);
            BlockIds.RED_WATER_STILL = blockConfiguration.getBlock(Strings.RED_WATER_STILL_NAME, BlockIds.RED_WATER_STILL_DEFAULT).getInt(BlockIds.RED_WATER_STILL_DEFAULT);
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its block configuration");
        }
        finally {
            blockConfiguration.save();
        }
    }

}
