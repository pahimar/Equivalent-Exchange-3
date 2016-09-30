package com.pahimar.ee3.block;

import com.pahimar.ee3.block.base.BlockEE;
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

public class BlockAlchemicalFuel extends BlockEE {

    public static final PropertyEnum<FuelVariant> VARIANT = PropertyEnum.create("variant", FuelVariant.class);

    public BlockAlchemicalFuel() {
        super("alchemical_fuel_block");
        setDefaultState(getDefaultState().withProperty(VARIANT, FuelVariant.ALCHEMICAL_COAL));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public int getMetaFromState(IBlockState blockState) {
        return blockState.getValue(VARIANT).getMetadata();
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        // TODO Better this (not bare array access)
        return this.getDefaultState().withProperty(VARIANT, FuelVariant.VARIANTS[meta]);
    }

    @Override
    public int damageDropped(IBlockState blockState) {
        return blockState.getValue(VARIANT).getMetadata();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks) {

        for (FuelVariant fuelVariant : FuelVariant.VARIANTS) {
            itemStacks.add(new ItemStack(item, 1, fuelVariant.getMetadata()));
        }
    }

    public enum FuelVariant implements IEnumMeta, Comparable<FuelVariant> {
        ALCHEMICAL_COAL("alchemical_coal"),
        MOBIUS_FUEL("mobius_fuel"),
        AETERNALIS_FUEL("aeternalis_fuel");

        private final int meta;
        private final String unlocalizedName;
        protected static final FuelVariant[] VARIANTS = values();

        FuelVariant(String unlocalizedName) {
            meta = ordinal();
            this.unlocalizedName = unlocalizedName;
        }

        @Override
        public int getMetadata() {
            return meta;
        }

        public static FuelVariant byMeta(int meta) {
            return VARIANTS[Math.abs(meta) % VARIANTS.length];
        }

        public String getUnlocalizedName() {
            return unlocalizedName;
        }
    }
}
