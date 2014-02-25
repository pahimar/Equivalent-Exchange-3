package com.pahimar.ee3.block;

import com.pahimar.ee3.tileentity.TileAlchemySquare;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAlchemySquare extends BlockEE implements ITileEntityProvider
{
    public BlockAlchemySquare()
    {
        super();
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

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) 
	{
		return new TileAlchemySquare();
	}
}
