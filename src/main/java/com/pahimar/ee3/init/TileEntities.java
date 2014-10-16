package com.pahimar.ee3.init;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.tileentity.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityDummy.class, Names.Blocks.DUMMY_BLOCK);
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChest.class, Names.Blocks.ALCHEMICAL_CHEST, "tile." + Names.Blocks.ALCHEMICAL_CHEST);
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChestSmall.class, Names.Blocks.ALCHEMICAL_CHEST + "Small", "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Small");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChestMedium.class, Names.Blocks.ALCHEMICAL_CHEST + "Medium", "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Medium");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChestLarge.class, Names.Blocks.ALCHEMICAL_CHEST + "Large", "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Large");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAludel.class, Names.Blocks.ALUDEL, "tile." + Names.Blocks.ALUDEL);
        GameRegistry.registerTileEntityWithAlternatives(TileEntityCalcinator.class, Names.Blocks.CALCINATOR, "tile." + Names.Blocks.CALCINATOR);
        GameRegistry.registerTileEntityWithAlternatives(TileEntityGlassBell.class, Names.Blocks.GLASS_BELL, "tile." + Names.Blocks.GLASS_BELL);
        GameRegistry.registerTileEntity(TileEntityResearchStation.class, Names.Blocks.RESEARCH_STATION);
        GameRegistry.registerTileEntity(TileEntityAugmentationTable.class, Names.Blocks.AUGMENTATION_TABLE);
        GameRegistry.registerTileEntity(TileEntityAlchemyArray.class, Names.Blocks.ALCHEMY_ARRAY);
    }
}
