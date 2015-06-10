package com.pahimar.ee3.block;

import com.pahimar.ee3.api.array.AlchemyArray;
import com.pahimar.ee3.array.AlchemyArrayRegistry;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.reference.Sounds;
import com.pahimar.ee3.settings.ChalkSettings;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.ee3.tileentity.TileEntityEE;
import com.pahimar.ee3.util.CommonSoundHelper;
import com.pahimar.ee3.util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockAlchemyArray extends BlockTileEntityEE
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

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(x, y, z);

            return tileEntityAlchemyArray.getLightLevel();
        }

        return 0;
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par2)
    {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        if (iBlockAccess.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) iBlockAccess.getTileEntity(x, y, z);

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
                default:
                {
                    this.setBlockBounds(0f, 0f, 0f, 1f, 0.0625f, 1f);
                }
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return world.getBlock(x, y, z).isReplaceable(world, x, y, z) && (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST) ||
                world.isSideSolid(x + 1, y, z, ForgeDirection.WEST) ||
                world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH) ||
                world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH) ||
                world.isSideSolid(x, y - 1, z, ForgeDirection.UP) ||
                world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN));
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int sideHit)
    {
        ForgeDirection side = ForgeDirection.getOrientation(sideHit);
        return world.getBlock(x, y, z).isReplaceable(world, x, y, z) && ((side == ForgeDirection.DOWN && world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN)) ||
                (side == ForgeDirection.UP && world.isSideSolid(x, y - 1, z, ForgeDirection.UP)) ||
                (side == ForgeDirection.NORTH && world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH)) ||
                (side == ForgeDirection.SOUTH && world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH)) ||
                (side == ForgeDirection.WEST && world.isSideSolid(x + 1, y, z, ForgeDirection.WEST)) ||
                (side == ForgeDirection.EAST && world.isSideSolid(x - 1, y, z, ForgeDirection.EAST)));
    }

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
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(x, y, z);
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
                tileEntityAlchemyArray.invalidate();
                world.setBlockToAir(x, y, z);
            }
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData)
    {
        return sideHit;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray && entityLiving instanceof EntityPlayer)
        {
            NBTTagCompound customEntityData = EntityHelper.getCustomEntityData(entityLiving);
            ChalkSettings chalkSettings = new ChalkSettings();
            chalkSettings.readFromNBT(customEntityData);
            AlchemyArray alchemyArray = AlchemyArrayRegistry.getInstance().getAlchemyArray(chalkSettings.getIndex());

            if (alchemyArray != null)
            {
                // Set adjusted rotation
                int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
                world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z), 3);
                ((TileEntityEE) world.getTileEntity(x, y, z)).setOrientation(world.getBlockMetadata(x, y, z));
                ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).setRotation(chalkSettings.getRotation(), facing);
                ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).setAlchemyArray(alchemyArray, chalkSettings.getSize());
                CommonSoundHelper.playSoundAtPlayer((EntityPlayer) entityLiving, Sounds.Chalk.getRandomChalkSound(), 1f, 1f);

                ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).onBlockActivated(world, x, y, z, entityPlayer, sideHit, hitX, hitY, hitZ);
        }

        return false;
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer entityPlayer)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).onBlockClicked(world, x, y, z, entityPlayer);
        }
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).onBlockDestroyedByExplosion(world, x, y, z, explosion);
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).onBlockDestroyedByPlayer(world, x, y, z, metaData);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).onEntityCollidedWithBlock(world, x, y, z, entity);
        }
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).onFallenUpon(world, x, y, z, entity, fallDistance);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityAlchemyArray();
    }
}
