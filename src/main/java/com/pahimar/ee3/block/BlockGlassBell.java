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

    protected static final AxisAlignedBB AABB_FACING_UP = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.66D, 0.875D);
    protected static final AxisAlignedBB AABB_FACING_DOWN = new AxisAlignedBB(0.125D, 0.33D, 0.125D, 0.875D, 1.0D, 0.875D);

    public BlockGlassBell() {
        super("glass_bell");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
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

        return AABB_FACING_UP;
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
