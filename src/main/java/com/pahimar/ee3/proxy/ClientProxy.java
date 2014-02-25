package com.pahimar.ee3.proxy;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.client.handler.DrawBlockHighlightHandler;
import com.pahimar.ee3.client.handler.ItemTooltipEventHandler;
import com.pahimar.ee3.client.helper.KeyBindingHelper;
import com.pahimar.ee3.client.renderer.item.ItemAlchemicalChestRenderer;
import com.pahimar.ee3.client.renderer.item.ItemAludelRenderer;
import com.pahimar.ee3.client.renderer.item.ItemCalcinatorRenderer;
import com.pahimar.ee3.client.renderer.item.ItemGlassBellRenderer;
import com.pahimar.ee3.client.renderer.item.ItemResearchStationRenderer;
import com.pahimar.ee3.client.renderer.tileentity.TileEntityAlchemicalChestRenderer;
import com.pahimar.ee3.client.renderer.tileentity.TileEntityAludelRenderer;
import com.pahimar.ee3.client.renderer.tileentity.TileEntityCalcinatorRenderer;
import com.pahimar.ee3.client.renderer.tileentity.TileEntityGlassBellRenderer;
import com.pahimar.ee3.client.renderer.tileentity.TileEntityResearchStationRenderer;
import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.helper.TransmutationHelper;
import com.pahimar.ee3.item.IChargeable;
import com.pahimar.ee3.lib.ActionTypes;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.network.packet.PacketEERequestEvent;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileCalcinator;
import com.pahimar.ee3.tileentity.TileEE;
import com.pahimar.ee3.tileentity.TileGlassBell;
import com.pahimar.ee3.tileentity.TileResearchStation;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ClientProxy
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerKeyBindingHandler()
    {
        //KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
    }

    @Override
    public void registerRenderTickHandler()
    {
    }

    @Override
    public void registerDrawBlockHighlightHandler()
    {
        MinecraftForge.EVENT_BUS.register(new DrawBlockHighlightHandler());
    }

    @Override
    public void setKeyBinding(String name, int value, String Category)
    {
        KeyBindingHelper.addKeyBinding(name, value, Category);
        KeyBindingHelper.addIsRepeating(false);
    }

    @Override
    public void initRenderingAndTextures()
    {
        RenderIds.calcinatorRender = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.aludelRender = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.alchemicalChestRender = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.glassBell = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.researchStation = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock( ModBlocks.calcinator), new ItemCalcinatorRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.aludelBase), new ItemAludelRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.alchemicalChest), new ItemAlchemicalChestRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.glassBell), new ItemGlassBellRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.researchStation), new ItemResearchStationRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileCalcinator.class, new TileEntityCalcinatorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAludel.class, new TileEntityAludelRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAlchemicalChest.class, new TileEntityAlchemicalChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileGlassBell.class, new TileEntityGlassBellRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileResearchStation.class, new TileEntityResearchStationRenderer());
    }

    @Override
    public void registerItemTooltipHandler()
    {
        MinecraftForge.EVENT_BUS.register(new ItemTooltipEventHandler());
    }

    @Override
    public void sendRequestEventPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data)
    {
    	EquivalentExchange3.packetpipeline.sendToServer(new PacketEERequestEvent(eventType, originX, originY, originZ, sideHit, rangeX, rangeY, rangeZ, data));
    }

    @Override
    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(x, y, z);

        if (tileEntity != null)
        {
            if (tileEntity instanceof TileEE)
            {
                ((TileEE) tileEntity).setOrientation(orientation);
                ((TileEE) tileEntity).setState(state);
                ((TileEE) tileEntity).setCustomName(customName);
            }
        }
    }

    @Override
    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, String itemName, int metaData, int stackSize, int color)
    {
        World world = FMLClientHandler.instance().getClient().theWorld;
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        this.handleTileEntityPacket(x, y, z, orientation, state, customName);

        if (tileEntity instanceof TileGlassBell)
        {
            ItemStack itemStack = null;
            if (itemName != "")
            {
                itemStack = new ItemStack(Block.getBlockFromName(itemName), stackSize, metaData);
                if (color != Integer.parseInt(Colours.PURE_WHITE, 16))
                {
                    ItemHelper.setColor(itemStack, color);
                }
            }

            ((TileGlassBell) tileEntity).outputItemStack = itemStack;
            
            world.func_147451_t(x, y, z);
        }
        else if (tileEntity instanceof TileAludel)
        {
            ItemStack itemStack = null;

            if (itemName != "")
            {
                itemStack = new ItemStack(Block.getBlockFromName(itemName), stackSize, metaData);
                if (color != Integer.parseInt(Colours.PURE_WHITE, 16))
                {
                    ItemHelper.setColor(itemStack, color);
                }
            }

            ((TileAludel) tileEntity).outputItemStack = itemStack;
            world.func_147451_t(x, y, z);
        }
    }

    @Override
    public void handleTileCalcinatorPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, byte leftStackSize, byte leftStackMeta, byte rightStackSize, byte rightStackMeta)
    {
        World world = FMLClientHandler.instance().getClient().theWorld;
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        this.handleTileEntityPacket(x, y, z, orientation, state, customName);

        if (tileEntity instanceof TileCalcinator)
        {
            ((TileCalcinator) tileEntity).leftStackSize = leftStackSize;
            ((TileCalcinator) tileEntity).leftStackMeta = leftStackMeta;
            ((TileCalcinator) tileEntity).rightStackSize = rightStackSize;
            ((TileCalcinator) tileEntity).rightStackMeta = rightStackMeta;
            world.func_147451_t(x, y, z);
        }
    }

    @Override
    public void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit)
    {
        if (TransmutationHelper.targetBlockStack != null)
        {
            if (itemStack != null)
            {
                int pnX = 1;
                int pnY = 1;
                int pnZ = 1;
                if (itemStack.getItem() instanceof IChargeable)
                {
                    int charge = ((IChargeable) itemStack.getItem()).getCharge(itemStack) * 2;
                    switch (ForgeDirection.getOrientation(sideHit))
                    {
                        case UP:
                        {
                            pnX = 1 + charge;
                            pnZ = 1 + charge;
                            break;
                        }
                        case DOWN:
                        {
                            pnX = 1 + charge;
                            pnZ = 1 + charge;
                            break;
                        }
                        case NORTH:
                        {
                            pnX = 1 + charge;
                            pnY = 1 + charge;
                            break;
                        }
                        case SOUTH:
                        {
                            pnX = 1 + charge;
                            pnY = 1 + charge;
                            break;
                        }
                        case EAST:
                        {
                            pnY = 1 + charge;
                            pnZ = 1 + charge;
                            break;
                        }
                        case WEST:
                        {
                            pnY = 1 + charge;
                            pnZ = 1 + charge;
                            break;
                        }
                        case UNKNOWN:
                        {
                            pnX = 0;
                            pnY = 0;
                            pnZ = 0;
                            break;
                        }
                        default:
                            break;
                    }
                }

                EquivalentExchange3.proxy.sendRequestEventPacket(ActionTypes.TRANSMUTATION, x, y, z, (byte) sideHit, (byte) pnX, (byte) pnY, (byte) pnZ, TransmutationHelper.formatTargetBlockInfo(TransmutationHelper.targetBlockStack));
            }
        }
    }

	@Override
	public void registerSoundHandler() {
		// TODO Auto-generated method stub
		
	}
}
