package com.pahimar.ee3.proxy;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.tileentity.*;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityAlchemicalChest.class, "tile." + Names.Blocks.ALCHEMICAL_CHEST);
        GameRegistry.registerTileEntity(TileEntityAlchemicalChestSmall.class, "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Small");
        GameRegistry.registerTileEntity(TileEntityAlchemicalChestMedium.class, "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Medium");
        GameRegistry.registerTileEntity(TileEntityAlchemicalChestLarge.class, "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Large");
        GameRegistry.registerTileEntity(TileEntityAludel.class, "tile." + Names.Blocks.ALUDEL);
        GameRegistry.registerTileEntity(TileEntityCalcinator.class, "tile." + Names.Blocks.CALCINATOR);
        GameRegistry.registerTileEntity(TileEntityGlassBell.class, "tile." + Names.Blocks.GLASS_BELL);
    }
}
