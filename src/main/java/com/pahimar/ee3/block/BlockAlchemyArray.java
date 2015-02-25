package com.pahimar.ee3.block;

import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.array.AlchemyArrayRegistry;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.settings.ChalkSettings;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.ee3.tileentity.TileEntityEE;
import com.pahimar.ee3.util.CommonSoundHelper;
import com.pahimar.ee3.util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockAlchemyArray extends BlockEE implements ITileEntityProvider
{
    public BlockAlchemyArray()
    {
        super(Material.circuits);
        this.setCreativeTab(null);
        this.setBlockName(Names.Blocks.BASIC_ALCHEMY_ARRAY);
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
        ((TileEntityEE) world.getTileEntity(x, y, z)).setOrientation(world.getBlockMetadata(x, y, z));

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
                ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).setRotation(chalkSettings.getRotation(), facing);
                ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).setAlchemyArray(alchemyArray, chalkSettings.getSize());
                CommonSoundHelper.playChalkSoundAt((EntityPlayer) entityLiving);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray && !entityPlayer.isSneaking())
        {
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).onBlockActivated(world, x, y, z, entityPlayer, sideHit, hitX, hitY, hitZ);
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityAlchemyArray();
    }
}
