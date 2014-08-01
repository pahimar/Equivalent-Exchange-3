package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.GuiId;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.tileentity.TileEntityTransmutationSquare;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTransmutationSquare extends BlockEE implements ITileEntityProvider
{
    public BlockTransmutationSquare()
    {
        super(Material.circuits);
        this.setCreativeTab(null);
        this.setBlockName(Names.Blocks.TRANSMUTATION_SQUARE);
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
        return new TileEntityTransmutationSquare();
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
                if (world.getTileEntity(x, y, z) instanceof TileEntityTransmutationSquare)
                {
                    player.openGui(EquivalentExchange3.instance, GuiId.TRANSMUTATION_SQUARE.ordinal(), world, x, y, z);
                }
            }

            return true;
        }
    }
}
