package com.pahimar.ee3.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileRenderingTank;

public class BlockRenderingTank extends BlockEE {

    public BlockRenderingTank(int id) {

        super(id, Material.anvil);
        this.setUnlocalizedName(Strings.RENDERING_TANK_NAME);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }

    @Override
    public String getUnlocalizedName() {

        StringBuilder unlocalizedName = new StringBuilder();

        unlocalizedName.append("tile.");
        unlocalizedName.append(Strings.RESOURCE_PREFIX);
        unlocalizedName.append(Strings.RENDERING_TANK_NAME);

        return unlocalizedName.toString();
    }

    @Override
    public boolean renderAsNormalBlock() {

        return false;
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public int getRenderType() {

        return RenderIds.renderingTank;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileRenderingTank();
    }

}
