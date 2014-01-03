package com.pahimar.ee3.block;

import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileResearchStation;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockResearchStation extends BlockEE implements ITileEntityProvider
{
    public BlockResearchStation(int id)
    {
        super(id);
        this.setUnlocalizedName(Strings.RESEARCH_STATION_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileResearchStation();
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

        return RenderIds.calcinatorRender;
    }
}
