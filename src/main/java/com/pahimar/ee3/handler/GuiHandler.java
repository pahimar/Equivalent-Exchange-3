package com.pahimar.ee3.handler;

import com.pahimar.ee3.client.gui.inventory.GuiAlchemicalBag;
import com.pahimar.ee3.client.gui.inventory.GuiAlchemicalChest;
import com.pahimar.ee3.client.gui.inventory.GuiGlassBell;
import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.inventory.ContainerAlchemicalChest;
import com.pahimar.ee3.inventory.ContainerGlassBell;
import com.pahimar.ee3.inventory.InventoryAlchemicalBag;
import com.pahimar.ee3.reference.GuiIds;
import com.pahimar.ee3.tileentity.TileEntityAlchemicalChest;
import com.pahimar.ee3.tileentity.TileEntityGlassBell;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (id == GuiIds.ALCHEMICAL_CHEST)
        {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
            return new ContainerAlchemicalChest(player.inventory, tileEntityAlchemicalChest);
        }
        else if (id == GuiIds.GLASS_BELL)
        {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
            return new ContainerGlassBell(player.inventory, tileEntityGlassBell);
        }
        else if (id == GuiIds.ALCHEMICAL_BAG)
        {
            return new ContainerAlchemicalBag(player, new InventoryAlchemicalBag(player.getHeldItem()));
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (id == GuiIds.ALCHEMICAL_CHEST)
        {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
            return new GuiAlchemicalChest(player.inventory, tileEntityAlchemicalChest);
        }
        else if (id == GuiIds.GLASS_BELL)
        {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
            return new GuiGlassBell(player.inventory, tileEntityGlassBell);
        }
        else if (id == GuiIds.ALCHEMICAL_BAG)
        {
            return new GuiAlchemicalBag(player, new InventoryAlchemicalBag(player.getHeldItem()));
        }

        return null;
    }
}
