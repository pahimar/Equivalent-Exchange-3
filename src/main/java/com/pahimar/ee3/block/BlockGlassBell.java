package com.pahimar.ee3.block;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.tileentity.TileGlassBell;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGlassBell extends BlockEE implements ITileEntityProvider
{
    public BlockGlassBell()
    {
        super(Material.glass);
        this.setHardness(1.0f);
        this.setBlockName(Names.Blocks.GLASS_BELL);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileGlassBell();
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
        return RenderIds.glassBell;
    }
}
