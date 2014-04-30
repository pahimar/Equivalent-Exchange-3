package com.pahimar.ee3.block;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.RenderIds;
import com.pahimar.ee3.tileentity.TileEntityResearchStation;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockResearchStation extends BlockEE implements ITileEntityProvider
{
    public BlockResearchStation()
    {
        super(Material.rock);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.RESEARCH_STATION);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityResearchStation();
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
        return RenderIds.researchStation;
    }
}
