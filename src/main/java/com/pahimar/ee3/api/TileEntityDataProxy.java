package com.pahimar.ee3.api;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.tileentity.TileEntityEE;
import cpw.mods.fml.common.Mod;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.UUID;

public class TileEntityDataProxy
{
    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static Class getTileEntityClass(World world, int xCoord, int yCoord, int zCoord)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getTileEntityClass(world, xCoord, yCoord, zCoord);
        }

        return null;
    }

    public static ForgeDirection getOrientation(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getOrientation();
        }

        return null;
    }

    public static short getState(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getState();
        }

        return Short.MIN_VALUE;
    }

    public static String getCustomName(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getCustomName();
        }

        return null;
    }

    public static UUID getOwnerUUID(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getOwnerUUID();
        }

        return null;
    }

    public static String getOwnerName(World world, int xCoord, int yCoord, int zCoord)
    {
        if (!world.isRemote && world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEE)
        {
            return ((TileEntityEE) world.getTileEntity(xCoord, yCoord, zCoord)).getOwnerName();
        }

        return null;
    }

    private static class EE3Wrapper
    {
        private static EquivalentExchange3 ee3mod;
    }

    private static void init()
    {
        if (ee3Mod != null)
        {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }
}
