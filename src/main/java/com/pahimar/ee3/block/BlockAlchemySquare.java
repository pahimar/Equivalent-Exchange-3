package com.pahimar.ee3.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAlchemySquare extends BlockEE implements ITileEntityProvider
{
    public BlockAlchemySquare(int id)
    {
        super(id);
    }

    public TileEntity createNewTileEntity(World world)
    {
        return createTileEntity(world, 0);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return null;
    }
}
