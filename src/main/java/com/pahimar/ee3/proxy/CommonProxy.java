package com.pahimar.ee3.proxy;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAlchemicalChestLarge;
import com.pahimar.ee3.tileentity.TileAlchemicalChestMedium;
import com.pahimar.ee3.tileentity.TileAlchemicalChestSmall;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileAlchemicalChest.class, "tile." + Names.Blocks.ALCHEMICAL_CHEST);
        GameRegistry.registerTileEntity(TileAlchemicalChestSmall.class, "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Small");
        GameRegistry.registerTileEntity(TileAlchemicalChestMedium.class, "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Medium");
        GameRegistry.registerTileEntity(TileAlchemicalChestLarge.class, "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Large");
    }
}
