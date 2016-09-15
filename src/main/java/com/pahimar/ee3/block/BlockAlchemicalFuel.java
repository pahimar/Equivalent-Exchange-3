package com.pahimar.ee3.block;

import com.pahimar.ee3.block.base.BlockEE;
import com.pahimar.ee3.block.base.IEnumMeta;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class BlockAlchemicalFuel extends BlockEE {

    public static final PropertyEnum<FuelType> TYPE = PropertyEnum.create("type", FuelType.class);

    public BlockAlchemicalFuel() {
        super("alchemical_fuel_block");
        setDefaultState(blockState.getBaseState().withProperty(TYPE, FuelType.ALCHEMICAL_COAL));
    }

    @Override
    public int getMetaFromState(IBlockState blockState) {
        return blockState.getValue(TYPE).getMeta();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TYPE);
    }

    public enum FuelType implements IEnumMeta, Comparable<FuelType> {
        ALCHEMICAL_COAL,
        MOBIUS_FUEL,
        AETERNALIS_FUEL;

        public final int meta;

        FuelType() {
            meta = ordinal();
        }

        @Override
        public int getMeta() {
            return meta;
        }
    }
}
