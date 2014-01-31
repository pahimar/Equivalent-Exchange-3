package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Particles;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileGlassBell;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.Random;

/**
 * Equivalent-Exchange-3
 * <p/>
 * BlockAludel
 *
 * @author pahimar
 */
public class BlockAludelBase extends BlockEE implements ITileEntityProvider
{
    public BlockAludelBase(int id)
    {
        super(id, Material.anvil);
        this.setUnlocalizedName(Strings.ALUDEL_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setBlockBounds(0.10F, 0.0F, 0.10F, 0.90F, 1.0F, 0.90F);
        this.setHardness(5F);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAludel();
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

        return RenderIds.aludelRender;
    }

    @Override
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta)
    {
        if (world.getBlockTileEntity(x, y + 1, z) instanceof TileGlassBell)
        {
            world.markBlockForUpdate(x, y + 1, z);
            world.updateAllLightTypes(x, y + 1, z);
        }

        super.breakBlock(world, x, y, z, id, meta);
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
                if (world.getBlockTileEntity(x, y, z) instanceof TileAludel && world.getBlockTileEntity(x, y + 1, z) instanceof TileGlassBell)
                {
                    player.openGui(EquivalentExchange3.instance, GuiIds.ALUDEL, world, x, y, z);
                }
                else if (world.getBlockTileEntity(x, y, z) instanceof TileAludel && ModBlocks.glassBell.canPlaceBlockAt(world, x, y + 1, z) && faceHit == ForgeDirection.UP.ordinal())
                {
                    if (player.getHeldItem() != null && player.getHeldItem().itemID == ModBlocks.glassBell.blockID)
                    {
                        player.getHeldItem().getItem().onItemUse(player.getHeldItem(), player, world, x, y, z, faceHit, par7, par8, par9);
                    }
                }
            }

            return true;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);

        if (world.getBlockTileEntity(x, y + 1, z) instanceof TileGlassBell)
        {
            TileGlassBell tileGlassBell = (TileGlassBell) world.getBlockTileEntity(x, y + 1, z);

            tileGlassBell.setOrientation(ForgeDirection.UP);

            if (world.getBlockTileEntity(x, y, z) instanceof TileAludel)
            {
                TileAludel tileAludel = (TileAludel) world.getBlockTileEntity(x, y, z);

                ItemStack itemStackGlassBell = tileGlassBell.getStackInSlot(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX);

                tileGlassBell.setInventorySlotContents(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX, null);

                tileAludel.setInventorySlotContents(TileAludel.INPUT_INVENTORY_INDEX, itemStackGlassBell);
            }
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlockTileEntity(x, y, z) instanceof TileAludel)
        {
            if (((TileAludel) world.getBlockTileEntity(x, y, z)).getState() == 1)
            {
                return 15;
            }
        }

        return super.getLightValue(world, x, y, z);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile instanceof TileAludel)
        {
            if (((TileAludel) tile).getState() == 1)
            {
                switch(((TileAludel) tile).getOrientation())
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
}
