package com.pahimar.ee.api.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class WorldTransmutationEvent extends BlockEvent {

    private final EnumFacing sideHit;
    private final EntityLiving entityLiving;

    public WorldTransmutationEvent(World world, BlockPos blockPos, IBlockState blockState, EnumFacing sideHit, EntityLiving entityLiving) {

        super(world, blockPos, blockState);
        this.sideHit = sideHit;
        this.entityLiving = entityLiving;
    }

    public final EnumFacing getSideHit() {
        return sideHit;
    }

    public final EntityLiving getEntityLiving() {
        return entityLiving;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
