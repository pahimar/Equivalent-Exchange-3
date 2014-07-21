package com.pahimar.ee3.proxy;

import com.pahimar.ee3.client.handler.DrawBlockHighlightEventHandler;
import com.pahimar.ee3.client.handler.ItemTooltipEventHandler;
import com.pahimar.ee3.client.handler.KeyInputEventHandler;
import com.pahimar.ee3.client.renderer.item.*;
import com.pahimar.ee3.client.renderer.tileentity.*;
import com.pahimar.ee3.client.settings.Keybindings;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.tileentity.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
        MinecraftForge.EVENT_BUS.register(new ItemTooltipEventHandler());
        MinecraftForge.EVENT_BUS.register(new DrawBlockHighlightEventHandler());
    }

    @Override
    public void registerKeybindings()
    {
        ClientRegistry.registerKeyBinding(Keybindings.charge);
        ClientRegistry.registerKeyBinding(Keybindings.extra);
        ClientRegistry.registerKeyBinding(Keybindings.release);
        ClientRegistry.registerKeyBinding(Keybindings.toggle);
    }

    @Override
    public void initRenderingAndTextures()
    {
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.alchemicalChest), new ItemRendererAlchemicalChest());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.aludel), new ItemRendererAludel());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.calcinator), new ItemRendererCalcinator());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.glassBell), new ItemRendererGlassBell());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.researchStation), new ItemRendererResearchStation());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.augmentationTable), new ItemRendererAugmentationTable());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemicalChest.class, new TileEntityRendererAlchemicalChest());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCalcinator.class, new TileEntityRendererCalcinator());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAludel.class, new TileEntityRendererAludel());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlassBell.class, new TileEntityRendererGlassBell());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityResearchStation.class, new TileEntityRendererResearchStation());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAugmentationTable.class, new TileEntityRendererAugmentationTable());
    }
}
