package com.pahimar.ee3.handler;

import com.pahimar.ee3.client.gui.inventory.*;
import com.pahimar.ee3.inventory.*;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileCalcinator;
import com.pahimar.ee3.tileentity.TileGlassBell;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GuiIds.PORTABLE_CRAFTING)
        {
            return new ContainerPortableCrafting(player.inventory, world, x, y, z);
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
        {
            return new ContainerAlchemicalBag(player.inventory, player.getHeldItem());
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
        {
            return new GuiAlchemicalBag(player.inventory, player.getHeldItem());
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
