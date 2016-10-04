package com.pahimar.ee3.block;

import com.pahimar.ee3.block.base.BlockEnumEE;
import com.pahimar.ee3.block.base.IEnumMeta;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockAlchemicalFuel extends BlockEnumEE {

    public static final PropertyEnum<FuelVariant> VARIANT = PropertyEnum.create("variant", FuelVariant.class);

    public BlockAlchemicalFuel() {
        super("alchemical_fuel_block", FuelVariant.VARIANTS);
        setHasSubtypes(true);
        setDefaultState(getDefaultState().withProperty(VARIANT, FuelVariant.ALCHEMICAL_COAL));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public int getMetaFromState(IBlockState blockState) {
        return blockState.getValue(VARIANT).getMeta();
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, FuelVariant.byMeta(meta));
    }

    @Override
    public int damageDropped(IBlockState blockState) {
        return blockState.getValue(VARIANT).getMeta();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks) {

        for (FuelVariant fuelVariant : FuelVariant.VARIANTS) {
            itemStacks.add(new ItemStack(item, 1, fuelVariant.getMeta()));
        }
    }

    public enum FuelVariant implements IEnumMeta, Comparable<FuelVariant> {
        ALCHEMICAL_COAL,
        MOBIUS_FUEL,
        AETERNALIS_FUEL;

        private final int meta;
        protected static final FuelVariant[] VARIANTS = values();

        FuelVariant() {
            meta = ordinal();
        }

        @Override
        public int getMeta() {
            return meta;
        }

        public static FuelVariant byMeta(int meta) {
            return VARIANTS[Math.abs(meta) % VARIANTS.length];
        }
    }
}
