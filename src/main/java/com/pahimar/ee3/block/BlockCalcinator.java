package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.GuiId;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Particles;
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
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
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
                    player.openGui(EquivalentExchange3.instance, GuiId.CALCINATOR.ordinal(), world, x, y, z);
                }
            }

            return true;
        }
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventData)
    {
        super.onBlockEventReceived(world, x, y, z, eventId, eventData);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(eventId, eventData);
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
}
