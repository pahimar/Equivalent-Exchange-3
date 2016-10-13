package com.pahimar.ee3.block.base;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockDirectional extends BlockBase {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockDirectional(String name) {
        super(name);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState state, EntityLivingBase entity, ItemStack itemStack) {
        world.setBlockState(blockPos, state.withProperty(FACING, getFacingFromEntity(blockPos, entity)), 2);
    }

    public static EnumFacing getFacingFromEntity(BlockPos blockPos, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector((float) (entity.posX - blockPos.getX()), (float) (entity.posY - blockPos.getY()), (float) (entity.posZ - blockPos.getZ()));
    }
}
