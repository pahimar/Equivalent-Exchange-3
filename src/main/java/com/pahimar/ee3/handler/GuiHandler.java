package com.pahimar.ee3.handler;

import com.pahimar.ee3.client.gui.inventory.GuiAlchemicalChest;
import com.pahimar.ee3.inventory.ContainerAlchemicalChest;
import com.pahimar.ee3.reference.GuiIds;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
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
            TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getTileEntity(x, y, z);
            return new ContainerAlchemicalChest(player.inventory, tileAlchemicalChest);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (id == GuiIds.ALCHEMICAL_CHEST)
        {
            TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getTileEntity(x, y, z);
            return new GuiAlchemicalChest(player.inventory, tileAlchemicalChest);
        }

        return null;
    }
}
