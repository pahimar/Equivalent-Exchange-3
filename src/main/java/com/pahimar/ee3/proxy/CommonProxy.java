package com.pahimar.ee3.proxy;

import com.pahimar.ee3.handler.*;
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
        CraftingHandler craftingHandler = new CraftingHandler();
        PlayerEventHandler playerEventHandler = new PlayerEventHandler();

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        FMLCommonHandler.instance().bus().register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
        FMLCommonHandler.instance().bus().register(playerEventHandler);
        FMLCommonHandler.instance().bus().register(craftingHandler);
        MinecraftForge.EVENT_BUS.register(craftingHandler);
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChest.class, Names.Blocks.ALCHEMICAL_CHEST, "tile." + Names.Blocks.ALCHEMICAL_CHEST);
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChestSmall.class, Names.Blocks.ALCHEMICAL_CHEST + "Small", "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Small");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChestMedium.class, Names.Blocks.ALCHEMICAL_CHEST + "Medium", "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Medium");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChestLarge.class, Names.Blocks.ALCHEMICAL_CHEST + "Large", "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Large");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAludel.class, Names.Blocks.ALUDEL, "tile." + Names.Blocks.ALUDEL);
        GameRegistry.registerTileEntityWithAlternatives(TileEntityCalcinator.class, Names.Blocks.CALCINATOR, "tile." + Names.Blocks.CALCINATOR);
        GameRegistry.registerTileEntityWithAlternatives(TileEntityGlassBell.class, Names.Blocks.GLASS_BELL, "tile." + Names.Blocks.GLASS_BELL);
        GameRegistry.registerTileEntity(TileEntityResearchStation.class, Names.Blocks.RESEARCH_STATION);
        GameRegistry.registerTileEntity(TileEntityAugmentationTable.class, Names.Blocks.AUGMENTATION_TABLE);
        GameRegistry.registerTileEntity(TileEntityTransmutationSquare.class, Names.Blocks.TRANSMUTATION_SQUARE);
    }
}
