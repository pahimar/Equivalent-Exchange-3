package com.pahimar.ee3.proxy;

import com.pahimar.ee3.handler.ItemEventHandler;
import com.pahimar.ee3.handler.WorldEventHandler;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.tileentity.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        ItemEventHandler itemEventHandler = new ItemEventHandler();

        FMLCommonHandler.instance().bus().register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
    }

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
