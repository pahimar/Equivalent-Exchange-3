package com.pahimar.ee3.util;

import com.pahimar.ee3.tileentity.TileEntityEE;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.UUID;

public class TileEntityDataHelper
{
    private static TileEntityDataHelper tileEntityDataHelper = null;

    private TileEntityDataHelper()
    {

    }

    public static TileEntityDataHelper getInstance()
    {
        if (tileEntityDataHelper == null)
        {
            tileEntityDataHelper = new TileEntityDataHelper();
        }

        return tileEntityDataHelper;
    }

    public Class getTileEntityClass(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote)
        {
            return world.getTileEntity(xCoord, yCoord, zCoord).getClass();
        }

        return null;
    }

    public ForgeDirection getOrientation(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getOrientation();
        }

        return null;
    }

    public short getState(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getState();
        }

        return Short.MIN_VALUE;
    }

    public String getCustomName(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getCustomName();
        }

        return null;
    }

    public UUID getOwnerUUID(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getOwnerUUID();
        }

        return null;
    }

    public String getOwnerName(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getOwnerName();
        }

        return null;
    }
}
