package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Particles;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileCalcinator;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Equivalent-Exchange-3
 * <p/>
 * BlockCalcinator
 *
 * @author pahimar
 */
public class BlockCalcinator extends BlockEE implements ITileEntityProvider
{
    public BlockCalcinator(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName(Strings.CALCINATOR_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setHardness(2.0F);
        this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileCalcinator();
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
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        // TODO Vary light levels depending on whether or not we are calcinating something
        return super.getLightValue(world, x, y, z);
    }

    @Override
    public int getRenderType()
    {

        return RenderIds.calcinatorRender;
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
                if (world.getBlockTileEntity(x, y, z) instanceof TileCalcinator)
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
        if (world.getBlockTileEntity(x, y, z) instanceof TileCalcinator)
        {
            if (((TileCalcinator) world.getBlockTileEntity(x, y, z)).isBurning())
            {
                // Fire pot particles
                // TODO TileEntity.onClientEvent to update particles
                world.spawnParticle(Particles.NORMAL_SMOKE, (double) x + 0.5F, (double) y + 0.4F, (double) ((z + 0.5F) + (random.nextFloat() * 0.5F - 0.3F)), 0.0D, 0.0D, 0.0D);
                world.spawnParticle(Particles.FLAME, (double) x + 0.5F, (double) y + 0.4F, (double) z + 0.5F, 0.0D, 0.0D, 0.0D);

                // Bowl particle effects
                // TODO Decide if the bowl should have particle effects
            }
        }
    }
}
