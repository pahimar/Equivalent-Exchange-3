package com.pahimar.ee3.block;

import com.pahimar.ee3.init.Glyphs;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.ee3.tileentity.TileEntityEE;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class BlockAlchemyArray extends BlockEE implements ITileEntityProvider
{
    public BlockAlchemyArray()
    {
        super(Material.circuits);
        this.setCreativeTab(null);
        this.setBlockName(Names.Blocks.ALCHEMY_ARRAY);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderIds.alchemyArray;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param world
     * @param metaData
     */
    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityAlchemyArray();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        ((TileEntityEE) world.getTileEntity(x, y, z)).setOrientation(world.getBlockMetadata(x, y, z));
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            // TODO: Place the first glyph of the alchemy glyphs from the player's currently selected glyph
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).addGlyphToAlchemyArray(Glyphs.BASE_CIRCLE, 3, facing);
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).addGlyphToAlchemyArray(Glyphs.BASE_CIRCLE, 2, facing);
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).addGlyphToAlchemyArray(Glyphs.BASE_CIRCLE, 1, facing);
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).addGlyphToAlchemyArray(Glyphs.TRIANGLE, 1, facing);
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).addGlyphToAlchemyArray(Glyphs.TRIANGLE, 2);
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).addGlyphToAlchemyArray(Glyphs.TRIANGLE, 3);
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData)
    {
        return sideHit;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(x, y, z);

            int largestGlyphSize = tileEntityAlchemyArray.getAlchemyArray().getLargestGlyphSize();

            switch (tileEntityAlchemyArray.getOrientation())
            {
                case DOWN:
                {
                    this.setBlockBounds(0f, 1f, 0f, 1f, 1 - 0.0625f, 1f);
                    break;
                }
                case UP:
                {
                    this.setBlockBounds(0 - (largestGlyphSize - 1) / 2, 0f, 0 - (largestGlyphSize - 1) / 2, 1 + (largestGlyphSize - 1) / 2, 0.0625f, 1 + (largestGlyphSize - 1) / 2);
                    break;
                }
                case NORTH:
                {
                    this.setBlockBounds(0f, 0f, 1 - 0.0625f, 1f, 1f, 1f);
                    break;
                }
                case SOUTH:
                {
                    this.setBlockBounds(0f, 0f, 0f, 1f, 1f, 0.0625f);
                    break;
                }
                case EAST:
                {
                    this.setBlockBounds(0f, 0f, 0f, 0.0625f, 1f, 1f);
                    break;
                }
                case WEST:
                {
                    this.setBlockBounds(1f, 0f, 0f, 1 - 0.0625f, 1f, 1f);
                    break;
                }
                case UNKNOWN:
                {
                    break;
                }
            }
        }

        return super.collisionRayTrace(world, x, y, z, startVec, endVec);
    }
}
