package com.pahimar.ee3.proxy;

import com.pahimar.ee3.client.handler.ItemTooltipEventHandler;
import com.pahimar.ee3.client.handler.KeyInputEventHandler;
import com.pahimar.ee3.client.renderer.item.*;
import com.pahimar.ee3.client.renderer.tileentity.*;
import com.pahimar.ee3.client.settings.Keybindings;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.tileentity.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
        RenderIds.calcinator = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.aludel = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.alchemicalChest = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.glassBell = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.researchStation = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.alchemicalChest), new ItemAlchemicalChestRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.aludel), new ItemAludelRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.calcinator), new ItemCalcinatorRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.glassBell), new ItemGlassBellRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.researchStation), new ItemResearchStationRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemicalChest.class, new TileEntityAlchemicalChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCalcinator.class, new TileEntityCalcinatorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAludel.class, new TileEntityAludelRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlassBell.class, new TileEntityGlassBellRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityResearchStation.class, new TileEntityResearchStationRenderer());
    }
}
