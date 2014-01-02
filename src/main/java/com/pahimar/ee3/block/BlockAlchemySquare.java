package com.pahimar.ee3.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAlchemySquare extends BlockContainerEE
{
    public BlockAlchemySquare(int id)
    {
        super(id);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param world
     */
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return null;
    }
}
