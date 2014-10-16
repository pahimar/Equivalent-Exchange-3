package com.pahimar.ee3.block;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.ee3.tileentity.TileEntityDummy;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockDummy extends BlockEE implements ITileEntityProvider
{
    public BlockDummy()
    {
        super(Material.circuits);
        setCreativeTab(null);
        this.setBlockName(Names.Blocks.DUMMY_BLOCK);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

//    @Override
//    public int getRenderType()
//    {
//        return RenderIds.dummy;
//    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return false;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityDummy)
        {
            int trueXCoord = ((TileEntityDummy) world.getTileEntity(x, y, z)).getTrueXCoord();
            int trueYCoord = ((TileEntityDummy) world.getTileEntity(x, y, z)).getTrueYCoord();
            int trueZCoord = ((TileEntityDummy) world.getTileEntity(x, y, z)).getTrueZCoord();

            if (world.getTileEntity(trueXCoord, trueYCoord, trueZCoord) instanceof TileEntityAlchemyArray)
            {
                TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(trueXCoord, trueYCoord, trueZCoord);
                boolean invalidateAlchemyArray = false;

                if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.UP && !world.isSideSolid(x, y - 1, z, ForgeDirection.UP, true))
                {
                    invalidateAlchemyArray = true;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.DOWN && !world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN, true))
                {
                    invalidateAlchemyArray = true;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.NORTH && !world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true))
                {
                    invalidateAlchemyArray = true;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.SOUTH && !world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true))
                {
                    invalidateAlchemyArray = true;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.EAST && !world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true))
                {
                    invalidateAlchemyArray = true;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.WEST && !world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true))
                {
                    invalidateAlchemyArray = true;
                }

                if (invalidateAlchemyArray)
                {
                    world.getTileEntity(x, y, z).invalidate();
                    tileEntityAlchemyArray.invalidate();
                    world.setBlockToAir(x, y, z);
                    world.setBlockToAir(trueXCoord, trueYCoord, trueZCoord);
                }
            }
        }
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par2)
    {
        return null;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
    {
        // TODO: Make this work using the blocks own orientation
        if (world.getTileEntity(x, y, z) instanceof TileEntityDummy)
        {
            int trueXCoord = ((TileEntityDummy) world.getTileEntity(x, y, z)).getTrueXCoord();
            int trueYCoord = ((TileEntityDummy) world.getTileEntity(x, y, z)).getTrueYCoord();
            int trueZCoord = ((TileEntityDummy) world.getTileEntity(x, y, z)).getTrueZCoord();

            if (world.getTileEntity(trueXCoord, trueYCoord, trueZCoord) instanceof TileEntityAlchemyArray)
            {
                TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(trueXCoord, trueYCoord, trueZCoord);

                switch (tileEntityAlchemyArray.getOrientation())
                {
                    case DOWN:
                    {
                        this.setBlockBounds(0f, 1f, 0f, 1f, 1 - 0.0625f, 1f);
                        break;
                    }
                    case UP:
                    {
                        this.setBlockBounds(0f, 0f, 0f, 1f, 0.0625f, 1f);
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
        }

        return super.collisionRayTrace(world, x, y, z, startVec, endVec);
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int metaData)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityDummy)
        {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            super.breakBlock(world, ((TileEntityDummy) tileEntity).getTrueXCoord(), ((TileEntityDummy) tileEntity).getTrueYCoord(), ((TileEntityDummy) tileEntity).getTrueZCoord(), block, metaData);
        }
        super.breakBlock(world, x, y, z, block, metaData);
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
        return new TileEntityDummy();
    }
}
