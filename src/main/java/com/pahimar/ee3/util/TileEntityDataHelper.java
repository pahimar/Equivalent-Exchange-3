package com.pahimar.ee3.util;

import com.pahimar.ee3.tileentity.TileEntityEE;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class TileEntityDataHelper {

    public static final TileEntityDataHelper INSTANCE = new TileEntityDataHelper();

    private TileEntityDataHelper() {
    }

    public Class getTileEntityClass(World world, BlockPos blockPos) {

        if (!world.isRemote) {
            return world.getTileEntity(blockPos).getClass();
        }

        return null;
    }

    public EnumFacing getFacing(World world, BlockPos blockPos) {

        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityEE) {
            return ((TileEntityEE) world.getTileEntity(blockPos)).getFacing();
        }

        return null;
    }

    public short getState(World world, BlockPos blockPos) {

        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityEE) {
            return ((TileEntityEE) world.getTileEntity(blockPos)).getState();
        }

        return Short.MIN_VALUE;
    }

    public String getCustomName(World world, BlockPos blockPos) {

        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityEE) {
            return ((TileEntityEE) world.getTileEntity(blockPos)).getCustomName();
        }

        return null;
    }

    public UUID getOwnerUUID(World world, BlockPos blockPos) {

        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityEE) {
            return ((TileEntityEE) world.getTileEntity(blockPos)).getOwnerUUID();
        }

        return null;
    }

    public String getOwnerName(World world, BlockPos blockPos) {

        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityEE) {
            return ((TileEntityEE) world.getTileEntity(blockPos)).getOwnerName();
        }

        return null;
    }
}
