package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.GuiIds;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Particles;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.tileentity.TileEntityCalcinator;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCalcinator extends BlockEE implements ITileEntityProvider
{
    public BlockCalcinator()
    {
        super(Material.rock);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.CALCINATOR);
        this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityCalcinator();
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
        return RenderIds.calcinator;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if ((world.getTileEntity(x, y, z) instanceof TileEntityCalcinator) && (((TileEntityCalcinator) world.getTileEntity(x, y, z)).getState() == 1))
        {
            return 15;
        }

        return super.getLightValue(world, x, y, z);
    }

    @Override
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
        return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                if (world.getTileEntity(x, y, z) instanceof TileEntityCalcinator)
                {
                    player.openGui(EquivalentExchange3.instance, GuiIds.CALCINATOR, world, x, y, z);
                }
            }

            return true;
        }
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityCalcinator)
        {
            if (((TileEntityCalcinator) world.getTileEntity(x, y, z)).getState() == 1)
            {
                // Fire pot particles
                world.spawnParticle(Particles.NORMAL_SMOKE, (double) x + 0.5F, (double) y + 0.4F, (double) ((z + 0.5F) + (random.nextFloat() * 0.5F - 0.3F)), 0.0D, 0.0D, 0.0D);
                world.spawnParticle(Particles.FLAME, (double) x + 0.5F, (double) y + 0.4F, (double) z + 0.5F, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
