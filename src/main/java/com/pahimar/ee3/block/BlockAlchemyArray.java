package com.pahimar.ee3.block;

import com.pahimar.ee3.init.Glyphs;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
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
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            // TODO: Place the first glyph of the alchemy glyphs from the player's currently selected glyph
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).addGlyphToAlchemyArray(Glyphs.BASE_CIRCLE);
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

            switch (tileEntityAlchemyArray.getOrientation())
            {
                case DOWN:
                {
                    this.setBlockBounds(0.125F, 0.33F, 0.125F, 0.875F, 1.0F, 0.875F);
                    break;
                }
                case UP:
                {
                    this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.66F, 0.875F);
                    break;
                }
                case NORTH:
                {
                    this.setBlockBounds(0.125F, 0.125F, 0.33F, 0.875F, 0.875F, 1.0F);
                    break;
                }
                case SOUTH:
                {
                    this.setBlockBounds(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 0.66F);
                    break;
                }
                case EAST:
                {
                    this.setBlockBounds(0.0F, 0.125F, 0.125F, 0.66F, 0.875F, 0.875F);
                    break;
                }
                case WEST:
                {
                    this.setBlockBounds(0.33F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);
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
