package com.pahimar.ee3.proxy;

import com.pahimar.ee3.client.gui.inventory.*;
import com.pahimar.ee3.inventory.*;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileCalcinator;
import com.pahimar.ee3.tileentity.TileGlassBell;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CommonProxy
 *
 * @author pahimar
 */
public class CommonProxy implements IGuiHandler
{

    public void registerKeyBindingHandler()
    {

    }

    public void registerRenderTickHandler()
    {

    }

    public void registerDrawBlockHighlightHandler()
    {

    }

    public void setKeyBinding(String name, int value)
    {

    }

    public void registerSoundHandler()
    {

    }

    public void initRenderingAndTextures()
    {

    }

    public void registerTileEntities()
    {

        GameRegistry.registerTileEntity(TileCalcinator.class, Strings.TE_CALCINATOR_NAME);
        GameRegistry.registerTileEntity(TileAludel.class, Strings.TE_ALUDEL_NAME);
        GameRegistry.registerTileEntity(TileAlchemicalChest.class, Strings.TE_ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerTileEntity(TileGlassBell.class, Strings.TE_GLASS_BELL_NAME);
    }

    public void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit)
    {

    }

    public void sendRequestEventPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data)
    {

    }

    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName)
    {

    }

    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize, int color)
    {

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

        if (ID == GuiIds.PORTABLE_CRAFTING)
        {
            return new ContainerPortableCrafting(player.inventory, world, x, y, z);
        }
        else if (ID == GuiIds.PORTABLE_TRANSMUTATION)
        {
            return new ContainerPortableTransmutation();
        }
        else if (ID == GuiIds.CALCINATOR)
        {
            TileCalcinator tileCalcinator = (TileCalcinator) world.getBlockTileEntity(x, y, z);
            return new ContainerCalcinator(player.inventory, tileCalcinator);
        }
        else if (ID == GuiIds.ALCHEMICAL_CHEST)
        {
            TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getBlockTileEntity(x, y, z);
            return new ContainerAlchemicalChest(player.inventory, tileAlchemicalChest);
        }
        else if (ID == GuiIds.ALCHEMICAL_BAG)
        // TODO Alchemical Bag inventory work is incomplete
        {
            return new ContainerAlchemicalBag(player.inventory);
        }
        else if (ID == GuiIds.ALUDEL)
        {
            TileAludel tileAludel = (TileAludel) world.getBlockTileEntity(x, y, z);
            return new ContainerAludel(player.inventory, tileAludel);
        }
        else if (ID == GuiIds.GLASS_BELL)
        {
            TileGlassBell tileGlassBell = (TileGlassBell) world.getBlockTileEntity(x, y, z);
            return new ContainerGlassBell(player.inventory, tileGlassBell);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

        if (ID == GuiIds.PORTABLE_CRAFTING)
        {
            return new GuiPortableCrafting(player, world, x, y, z);
        }
        else if (ID == GuiIds.PORTABLE_TRANSMUTATION)
        {
            return new GuiPortableTransmutation(null);
        }
        else if (ID == GuiIds.CALCINATOR)
        {
            TileCalcinator tileCalcinator = (TileCalcinator) world.getBlockTileEntity(x, y, z);
            return new GuiCalcinator(player.inventory, tileCalcinator);
        }
        else if (ID == GuiIds.ALCHEMICAL_CHEST)
        {
            TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getBlockTileEntity(x, y, z);
            return new GuiAlchemicalChest(player.inventory, tileAlchemicalChest);
        }
        else if (ID == GuiIds.ALCHEMICAL_BAG)
        // TODO Alchemical Bag inventory work is incomplete
        {
            return new GuiAlchemicalBag(player.inventory);
        }
        else if (ID == GuiIds.ALUDEL)
        {
            TileAludel tileAludel = (TileAludel) world.getBlockTileEntity(x, y, z);
            return new GuiAludel(player.inventory, tileAludel);
        }
        else if (ID == GuiIds.GLASS_BELL)
        {
            TileGlassBell tileGlassBell = (TileGlassBell) world.getBlockTileEntity(x, y, z);
            return new GuiGlassBell(player.inventory, tileGlassBell);
        }

        return null;
    }
}
