package com.pahimar.ee3.block;

import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class BlockAshInfusedStoneSlab extends BlockSlab
{
    @SideOnly(Side.CLIENT)
    protected IIcon blockIcon, sideVariant1, sideVariant2, topVariant1, topVariant2, topVariant3, topVariant4;

    public BlockAshInfusedStoneSlab()
    {
        super(false, Material.rock);
        this.setCreativeTab(CreativeTab.EE3_TAB);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.ASH_INFUSED_STONE_SLAB);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }
    
    public int getLightOpacity()
    {
        return 0;
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
    {
        if (isAssociatedWithValidTablet(world, x, y, z))
        {
            int metaData = world.getBlockMetadata(x, y, z);

            if (metaData == 1)
            {
                this.setBlockBounds(0.5F, 0.0F, 0.5F, 1.0F, 0.625F, 1.0F);
                super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
            }
            else if (metaData == 2)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.625F, 1.0F);
                super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
            }
            else if (metaData == 3)
            {
                this.setBlockBounds(0.0F, 0.0F, 1.0F, 0.5F, 0.625F, 0.5F);
                super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
            }
            else if (metaData == 4)
            {
                this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
                super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
            }
            else if (metaData == 5)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.625F, 1.0F);
                super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
            }
            else if (metaData == 6)
            {
                this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.625F, 0.5F);
                super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
            }
            else if (metaData == 7)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 0.5F);
                super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
            }
            else if (metaData == 8)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.625F, 0.5F);
                super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
            }
        }

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        int metaData = world.getBlockMetadata(x, y, z);

        int shiftedX = x;
        int shiftedZ = z;
        if (metaData != 0)
        {
            if (metaData == 1)
            {
                shiftedX++;
                shiftedZ++;
            }
            else if (metaData == 2)
            {
                shiftedZ++;
            }
            else if (metaData == 3)
            {
                shiftedX--;
                shiftedZ++;
            }
            else if (metaData == 4)
            {
                shiftedX++;
            }
            else if (metaData == 5)
            {
                shiftedX--;
            }
            else if (metaData == 6)
            {
                shiftedX++;
                shiftedZ--;
            }
            else if (metaData == 7)
            {
                shiftedZ--;
            }
            else if (metaData == 8)
            {
                shiftedX--;
                shiftedZ--;
            }

            if (world.getTileEntity(shiftedX, y, shiftedZ) instanceof TileEntityTransmutationTablet)
            {
                world.getBlock(shiftedX, y, shiftedZ).onBlockActivated(world, shiftedX, y, shiftedZ, entityPlayer, sideHit, hitX, hitY, hitZ);
                return true;
            }
        }

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
        sideVariant1 = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_side1"));
        sideVariant2 = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_side2"));
        topVariant1 = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_top1"));
        topVariant2 = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_top2"));
        topVariant3 = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_top3"));
        topVariant4 = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_top4"));
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        ForgeDirection forgeDirection = ForgeDirection.getOrientation(side);
        if (forgeDirection == ForgeDirection.SOUTH || forgeDirection == ForgeDirection.NORTH || forgeDirection == ForgeDirection.EAST || forgeDirection == ForgeDirection.WEST)
        {
            if (meta == 1 || meta == 3 || meta == 6 || meta == 8)
            {
                return this.sideVariant1;
            }
            else
            {
                return this.sideVariant2;
            }
        }
        else if (forgeDirection == ForgeDirection.UP)
        {
            if (meta == 1)
            {
                return this.topVariant1;
            }
            else if (meta == 3)
            {
                return this.topVariant4;
            }
            else if (meta == 6)
            {
                return this.topVariant2;
            }
            else if (meta == 8)
            {
                return this.topVariant3;
            }
        }

        return this.blockIcon;
    }

    @Override
    public String func_150002_b(int meta)
    {
        return getUnlocalizedName();
    }

    private boolean isAssociatedWithValidTablet(World world, int x, int y, int z)
    {
        int metaData = world.getBlockMetadata(x, y, z);

        if (metaData != 0)
        {
            int shiftedX = x;
            int shiftedZ = z;

            if (metaData == 1)
            {
                shiftedX++;
                shiftedZ++;
            }
            else if (metaData == 2)
            {
                shiftedZ++;
            }
            else if (metaData == 3)
            {
                shiftedX--;
                shiftedZ++;
            }
            else if (metaData == 4)
            {
                shiftedX++;
            }
            else if (metaData == 5)
            {
                shiftedZ--;
            }
            else if (metaData == 6)
            {
                shiftedX++;
                shiftedZ--;
            }
            else if (metaData == 7)
            {
                shiftedZ--;
            }
            else if (metaData == 8)
            {
                shiftedX--;
                shiftedZ--;
            }

            if (world.getTileEntity(shiftedX, y, shiftedZ) instanceof TileEntityTransmutationTablet)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(ModBlocks.ashInfusedStoneSlab);
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return 0;
    }
}
