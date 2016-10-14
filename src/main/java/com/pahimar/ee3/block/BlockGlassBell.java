package com.pahimar.ee3.block;

import com.pahimar.ee3.block.base.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlassBell extends BlockDirectional {

    private static final AxisAlignedBB AABB_FACING_UP = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.66D, 0.875D);
    private static final AxisAlignedBB AABB_FACING_DOWN = new AxisAlignedBB(0.125D, 0.33D, 0.125D, 0.875D, 1.0D, 0.875D);
    private static final AxisAlignedBB AABB_FACING_NORTH = new AxisAlignedBB(0.125F, 0.125F, 0.33F, 0.875F, 0.875F, 1.0F);
    private static final AxisAlignedBB AABB_FACING_SOUTH = new AxisAlignedBB(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 0.66F);
    private static final AxisAlignedBB AABB_FACING_EAST = new AxisAlignedBB(0.0F, 0.125F, 0.125F, 0.66F, 0.875F, 0.875F);
    private static final AxisAlignedBB AABB_FACING_WEST = new AxisAlignedBB(0.33F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);

    public BlockGlassBell() {
        super("glass_bell");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos blockPos) {
        if (state.getValue(FACING) == EnumFacing.UP) {
            return AABB_FACING_UP;
        }
        else if (state.getValue(FACING) == EnumFacing.DOWN) {
            return AABB_FACING_DOWN;
        }
        else if (state.getValue(FACING) == EnumFacing.NORTH) {
            return AABB_FACING_NORTH;
        }
        else if (state.getValue(FACING) == EnumFacing.SOUTH) {
            return AABB_FACING_SOUTH;
        }
        else if (state.getValue(FACING) == EnumFacing.EAST) {
            return AABB_FACING_EAST;
        }
        else if (state.getValue(FACING) == EnumFacing.WEST) {
            return AABB_FACING_WEST;
        }
        else {
            return FULL_BLOCK_AABB;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState state, EntityLivingBase entity, ItemStack itemStack) {
        // NO-OP
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, facing);
    }
}
