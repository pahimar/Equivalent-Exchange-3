package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Particles;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.tileentity.TileEntityAludel;
import com.pahimar.ee3.tileentity.TileEntityGlassBell;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockAludel extends BlockTileEntityEE
{
    public BlockAludel()
    {
        super(Material.anvil);
        this.setHardness(5.0f);
        this.setBlockName(Names.Blocks.ALUDEL);
        this.setBlockBounds(0.10F, 0.0F, 0.10F, 0.90F, 1.0F, 0.90F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityAludel();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderIds.aludel;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityAludel)
        {
            if (((TileEntityAludel) tile).getState() == 1)
            {
                switch (((TileEntityAludel) tile).getOrientation())
                {
                    case NORTH:
                        world.spawnParticle(Particles.FLAME, (double) x + 0.5F, (double) y + 0.33F, (double) z + 0.175F, 0.0D, 0.0D, 0.0D);
                        break;
                    case SOUTH:
                        world.spawnParticle(Particles.FLAME, (double) x + 0.5F, (double) y + 0.33F, (double) z + 0.825F, 0.0D, 0.0D, 0.0D);
                        break;
                    case WEST:
                        world.spawnParticle(Particles.FLAME, (double) x + 0.175F, (double) y + 0.33F, (double) z + 0.5F, 0.0D, 0.0D, 0.0D);
                        break;
                    case EAST:
                        world.spawnParticle(Particles.FLAME, (double) x + 0.825F, (double) y + 0.33F, (double) z + 0.5F, 0.0D, 0.0D, 0.0D);
                        break;
                }

                world.spawnParticle(Particles.NORMAL_SMOKE, (double) x + 0.5F, (double) y + 0.7F, (double) z + 0.0F, 0.0D, 0.05D, 0.0D);
                world.spawnParticle(Particles.NORMAL_SMOKE, (double) x + 0.5F, (double) y + 0.7F, (double) z + 1.0F, 0.0D, 0.05D, 0.0D);
                world.spawnParticle(Particles.NORMAL_SMOKE, (double) x + 0.0F, (double) y + 0.7F, (double) z + 0.5F, 0.0D, 0.05D, 0.0D);
                world.spawnParticle(Particles.NORMAL_SMOKE, (double) x + 1.0F, (double) y + 0.7F, (double) z + 0.5F, 0.0D, 0.05D, 0.0D);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);
        tileEntityAludel.hasGlassBell = world.getTileEntity(x, y + 1, z) instanceof TileEntityGlassBell;

        super.onNeighborBlockChange(world, x, y, z, block);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int faceHit, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                if (world.getTileEntity(x, y, z) instanceof TileEntityAludel && world.getTileEntity(x, y + 1, z) instanceof TileEntityGlassBell)
                {
                    player.openGui(EquivalentExchange3.instance, GUIs.ALUDEL.ordinal(), world, x, y, z);
                }
            }

            if (world.getTileEntity(x, y, z) instanceof TileEntityAludel && ModBlocks.glassBell.canPlaceBlockAt(world, x, y + 1, z) && faceHit == ForgeDirection.UP.ordinal())
            {
                if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemBlock && ((ItemBlock) player.getHeldItem().getItem()).field_150939_a == ModBlocks.glassBell)
                {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityAludel)
        {
            if (((TileEntityAludel) world.getTileEntity(x, y, z)).getState() == 1)
            {
                return 15;
            }
        }

        return super.getLightValue(world, x, y, z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        if (world.getTileEntity(x, y + 1, z) instanceof TileEntityGlassBell)
        {
            world.markBlockForUpdate(x, y + 1, z);
            // NAME UPDATE - this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
            world.func_147451_t(x, y + 1, z);
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);

        if (world.getTileEntity(x, y + 1, z) instanceof TileEntityGlassBell)
        {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y + 1, z);

            tileEntityGlassBell.setOrientation(ForgeDirection.UP);

            if (world.getTileEntity(x, y, z) instanceof TileEntityAludel)
            {
                TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);

                ItemStack itemStackGlassBell = tileEntityGlassBell.getStackInSlot(TileEntityGlassBell.DISPLAY_SLOT_INVENTORY_INDEX);

                tileEntityGlassBell.setInventorySlotContents(TileEntityGlassBell.DISPLAY_SLOT_INVENTORY_INDEX, null);

                tileEntityAludel.setInventorySlotContents(TileEntityAludel.INPUT_INVENTORY_INDEX, itemStackGlassBell);

                tileEntityAludel.hasGlassBell = true;
            }
        }
    }
}
