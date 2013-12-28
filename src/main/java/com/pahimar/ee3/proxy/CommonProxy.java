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
        GameRegistry.registerTileEntity(TileCalcinator.class, "tile." + Strings.TE_CALCINATOR_NAME);
        GameRegistry.registerTileEntity(TileAludel.class, "tile." + Strings.TE_ALUDEL_NAME);
        GameRegistry.registerTileEntity(TileAlchemicalChest.class, "tile." + Strings.TE_ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerTileEntity(TileGlassBell.class, "tile." + Strings.TE_GLASS_BELL_NAME);
    }
}
