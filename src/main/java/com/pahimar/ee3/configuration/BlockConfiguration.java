package com.pahimar.ee3.configuration;

import com.pahimar.ee3.lib.BlockIds;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.FMLLog;

import java.io.File;
import java.util.logging.Level;

public class BlockConfiguration
{
    protected static void init(File configFile)
    {
        Configuration blockConfiguration = new Configuration(configFile);

        try
        {
            blockConfiguration.load();

            BlockIds.ALCHEMICAL_FUEL = blockConfiguration.getBlock(Strings.ALCHEMICAL_FUEL_NAME, BlockIds.ALCHEMICAL_FUEL_DEFAULT).getInt(BlockIds.ALCHEMICAL_FUEL_DEFAULT);
            BlockIds.CHALK = blockConfiguration.getBlock(Strings.CHALK_NAME, BlockIds.CHALK_DEFAULT).getInt(BlockIds.CHALK_DEFAULT);
            BlockIds.ALCHEMY_SQUARE = blockConfiguration.getBlock(Strings.ALCHEMY_SQUARE_NAME, BlockIds.ALCHEMY_SQUARE_DEFAULT).getInt(BlockIds.ALCHEMY_SQUARE_DEFAULT);
            BlockIds.CALCINATOR = blockConfiguration.getBlock(Strings.CALCINATOR_NAME, BlockIds.CALCINATOR_DEFAULT).getInt(BlockIds.CALCINATOR_DEFAULT);
            BlockIds.ALUDEL_BASE = blockConfiguration.getBlock(Strings.ALUDEL_NAME, BlockIds.ALUDEL_BASE_DEFAULT).getInt(BlockIds.ALUDEL_BASE_DEFAULT);
            BlockIds.ALCHEMICAL_CHEST = blockConfiguration.getBlock(Strings.ALCHEMICAL_CHEST_NAME, BlockIds.ALCHEMICAL_CHEST_DEFAULT).getInt(BlockIds.ALCHEMICAL_CHEST_DEFAULT);
            BlockIds.GLASS_BELL = blockConfiguration.getBlock(Strings.GLASS_BELL_NAME, BlockIds.GLASS_BELL_DEFAULT).getInt(BlockIds.GLASS_BELL_DEFAULT);
            BlockIds.RESEARCH_STATION = blockConfiguration.getBlock(Strings.RESEARCH_STATION_NAME, BlockIds.RESEARCH_STATION_DEFAULT).getInt(BlockIds.RESEARCH_STATION_DEFAULT);
            BlockIds.INFUSED_CLOTH = blockConfiguration.getBlock(Strings.INFUSED_CLOTH_NAME, BlockIds.INFUSED_CLOTH_DEFAULT).getInt(BlockIds.INFUSED_CLOTH_DEFAULT);
            BlockIds.INFUSED_WOOD = blockConfiguration.getBlock(Strings.INFUSED_WOOD_NAME, BlockIds.INFUSED_WOOD_DEFAULT).getInt(BlockIds.INFUSED_WOOD_DEFAULT);
            BlockIds.INFUSED_PLANKS = blockConfiguration.getBlock(Strings.INFUSED_PLANKS_NAME, BlockIds.INFUSED_PLANKS_DEFAULT).getInt(BlockIds.INFUSED_PLANKS_DEFAULT);
        }
        catch (Exception e)
        {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its block configuration");
        }
        finally
        {
            blockConfiguration.save();
        }
    }
}
