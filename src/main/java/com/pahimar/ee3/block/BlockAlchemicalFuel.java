package com.pahimar.ee3.block;

import com.pahimar.ee3.block.base.BlockEnumEE;
import com.pahimar.ee3.block.base.IEnumMeta;
import com.pahimar.ee3.util.ResourceLocationHelper;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class BlockAlchemicalFuel extends BlockEnumEE {

    public static final PropertyEnum<FuelVariant> VARIANT = PropertyEnum.create("variant", FuelVariant.class);

    public BlockAlchemicalFuel() {
        super("alchemical_fuel_block", "alchemical_coal", "mobius_fuel", "aeternalis_fuel");
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
        return this.getDefaultState().withProperty(VARIANT, FuelVariant.byMeta(meta));
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

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {

        if (VARIANTS.length > 0) {
            List<ModelResourceLocation> modelResources = new ArrayList<>();

            for (int i = 0; i < VARIANTS.length; i++) {
                modelResources.add(ResourceLocationHelper.getModelResourceLocation(VARIANTS[i]));
            }

//            ModelBakery.registerItemVariants(this, modelResources.toArray(new ModelResourceLocation[0]));
//            ModelLoader.setCustomMeshDefinition(this, itemStack -> modelResources.get(itemStack.getMetadata()));

            for (FuelVariant fuelVariant : FuelVariant.VARIANTS) {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), fuelVariant.getMetadata(), new ModelResourceLocation(getRegistryName(), "variant=" + fuelVariant.getUnlocalizedName()));
            }
        }
        else {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString()));
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
