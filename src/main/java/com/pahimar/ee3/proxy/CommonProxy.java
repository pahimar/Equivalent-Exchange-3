package com.pahimar.ee3.proxy;

import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileCalcinator;
import com.pahimar.ee3.tileentity.TileGlassBell;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileCalcinator.class, "tile." + Strings.CALCINATOR_NAME);
        GameRegistry.registerTileEntity(TileAludel.class, "tile." + Strings.ALUDEL_NAME);
        GameRegistry.registerTileEntity(TileAlchemicalChest.class, "tile." + Strings.ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerTileEntity(TileGlassBell.class, "tile." + Strings.GLASS_BELL_NAME);
    }
}
