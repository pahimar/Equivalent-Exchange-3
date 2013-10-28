package com.pahimar.ee3.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAlchemyTable;

public class BlockAlchemyTable extends BlockEE {

    public BlockAlchemyTable(int id) {

        super(id, Material.iron);
        this.setUnlocalizedName(Strings.ALCHEMY_TABLE_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }

    @Override
    public String getUnlocalizedName() {

        StringBuilder unlocalizedName = new StringBuilder();

        unlocalizedName.append("tile.");
        unlocalizedName.append(Strings.RESOURCE_PREFIX);
        unlocalizedName.append(Strings.ALCHEMY_TABLE_NAME);

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

        return RenderIds.alchemyTable;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileAlchemyTable();
    }
}
