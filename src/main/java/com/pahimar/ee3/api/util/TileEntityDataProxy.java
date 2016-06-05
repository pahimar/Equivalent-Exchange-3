package com.pahimar.ee3.api.util;

import com.pahimar.ee3.EquivalentExchange3;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

public class TileEntityDataProxy {

    public static Class getTileEntityClass(World world, BlockPos blockPos) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getTileEntityClass(world, blockPos);
        }

        return null;
    }

    public static EnumFacing getFacing(World world, BlockPos blockPos) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getFacing(world, blockPos);
        }

        return null;
    }

    public static short getState(World world, BlockPos blockPos) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getState(world, blockPos);
        }

        return Short.MIN_VALUE;
    }

    public static String getCustomName(World world, BlockPos blockPos) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getCustomName(world, blockPos);
        }

        return null;
    }

    public static UUID getOwnerUUID(World world, BlockPos blockPos) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getOwnerUUID(world, blockPos);
        }

        return null;
    }

    public static String getOwnerName(World world, BlockPos blockPos) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getTileEntityDataHelper().getOwnerName(world, blockPos);
        }

        return null;
    }

    @Mod.Instance("EE3")
    private static Object ee3Mod;

    private static class EE3Wrapper {
        private static EquivalentExchange3 ee3mod;
    }

    private static void init() {
        if (ee3Mod != null) {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }
}
