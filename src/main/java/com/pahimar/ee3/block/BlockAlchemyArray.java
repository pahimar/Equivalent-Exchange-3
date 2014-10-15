package com.pahimar.ee3.block;

import com.pahimar.ee3.api.Glyph;
import com.pahimar.ee3.array.GlyphTextureRegistry;
import com.pahimar.ee3.item.ItemChalk;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.settings.ChalkSettings;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.ee3.tileentity.TileEntityEE;
import com.pahimar.ee3.util.CommonSoundHelper;
import com.pahimar.ee3.util.EntityHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
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

//    @Override
//    public boolean canPlaceBlockAt(World world, int x, int y, int z)
//    {
//        return super.canPlaceBlockAt(world, x, y, z);
//    }

//    @Override
//    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
//    {
//        return false;
//    }

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

            // Set adjusted rotation
            int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).setRotation(chalkSettings.getRotation(), facing);

            ResourceLocation glyphTexture = GlyphTextureRegistry.getInstance().getResourceLocation(chalkSettings.getIndex());

            ((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).addGlyphToAlchemyArray(new Glyph(glyphTexture, GlyphTextureRegistry.getInstance().getGlyphs().get(glyphTexture)), chalkSettings.getSize());

            CommonSoundHelper.playChalkSoundAt((EntityPlayer) entityLiving);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityAlchemyArray)
        {
            if (entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemChalk)
            {
                NBTTagCompound customEntityData = EntityHelper.getCustomEntityData(entityPlayer);
                ChalkSettings chalkSettings = new ChalkSettings();
                chalkSettings.readFromNBT(customEntityData);

                ResourceLocation glyphTexture = GlyphTextureRegistry.getInstance().getResourceLocation(chalkSettings.getIndex());

                if (((TileEntityAlchemyArray) world.getTileEntity(x, y, z)).addGlyphToAlchemyArray(new Glyph(glyphTexture, GlyphTextureRegistry.getInstance().getGlyphs().get(glyphTexture)), chalkSettings.getSize()))
                {
                    world.markBlockForUpdate(x, y, z);
                    world.getTileEntity(x, y, z).markDirty();
                    CommonSoundHelper.playChalkSoundAt(entityPlayer);
                    return true;
                }
            }
        }

        return false;
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
