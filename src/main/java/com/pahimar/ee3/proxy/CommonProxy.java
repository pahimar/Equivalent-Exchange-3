package com.pahimar.ee3.proxy;

import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.*;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileCalcinator.class, "tile." + Strings.CALCINATOR_NAME);
        GameRegistry.registerTileEntity(TileAludel.class, "tile." + Strings.ALUDEL_NAME);
        GameRegistry.registerTileEntity(TileAlchemicalChest.class, "tile." + Strings.ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerTileEntity(TileAlchemicalChestSmall.class, "tile." + Strings.ALCHEMICAL_CHEST_NAME + "Small");
        GameRegistry.registerTileEntity(TileAlchemicalChestMedium.class, "tile." + Strings.ALCHEMICAL_CHEST_NAME + "Medium");
        GameRegistry.registerTileEntity(TileAlchemicalChestLarge.class, "tile." + Strings.ALCHEMICAL_CHEST_NAME + "Large");
        GameRegistry.registerTileEntity(TileGlassBell.class, "tile." + Strings.GLASS_BELL_NAME);
    }
}
