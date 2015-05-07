package com.pahimar.ee3.api.util;

import com.pahimar.ee3.EquivalentExchange3;
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
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getOrientation(world, xCoord, yCoord, zCoord);
        }

        return null;
    }

    public static short getState(World world, int xCoord, int yCoord, int zCoord)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getState(world, xCoord, yCoord, zCoord);
        }

        return Short.MIN_VALUE;
    }

    public static String getCustomName(World world, int xCoord, int yCoord, int zCoord)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getCustomName(world, xCoord, yCoord, zCoord);
        }

        return null;
    }

    public static UUID getOwnerUUID(World world, int xCoord, int yCoord, int zCoord)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getOwnerUUID(world, xCoord, yCoord, zCoord);
        }

        return null;
    }

    public static String getOwnerName(World world, int xCoord, int yCoord, int zCoord)
    {
        init();

        if (ee3Mod != null)
        {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getOwnerName(world, xCoord, yCoord, zCoord);
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
