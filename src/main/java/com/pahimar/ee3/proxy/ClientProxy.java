package com.pahimar.ee3.proxy;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.client.renderer.item.ItemAlchemicalChestRenderer;
import com.pahimar.ee3.client.renderer.tileentity.TileEntityAlchemicalChestRenderer;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    @Override
    public void initRenderingAndTextures()
    {
        RenderIds.calcinator = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.aludel = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.alchemicalChest = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.glassBell = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.researchStation = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.alchemicalChest), new ItemAlchemicalChestRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileAlchemicalChest.class, new TileEntityAlchemicalChestRenderer());
    }
}
