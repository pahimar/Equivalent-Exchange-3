package com.pahimar.ee3.item.base;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.util.ResourceLocationHelper;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ItemEE extends Item implements IItemVariantHolder<ItemEE> {

    private final String BASE_NAME;
    private final String[] VARIANTS;

    public ItemEE(String name, String ... variants) {
        super();
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTab.EE3_TAB);
        setMaxStackSize(1);
        setNoRepair();

        BASE_NAME = name;
        if (variants.length > 0) {
            VARIANTS = variants;
        }
        else {
            VARIANTS = new String[0];
        }

        ModItems.register(this);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        if (hasSubtypes && itemStack.getMetadata() < VARIANTS.length) {
            return String.format("item.%s:%s", EquivalentExchange3.MOD_ID, VARIANTS[itemStack.getMetadata()]);
        }
        else {
            return String.format("item.%s:%s", EquivalentExchange3.MOD_ID, BASE_NAME);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> list) {

        if (getHasSubtypes() && VARIANTS.length > 0) {
            for (int meta = 0; meta < VARIANTS.length; ++meta) {
                list.add(new ItemStack(this, 1, meta));
            }
        }
        else {
            super.getSubItems(item, creativeTab, list);
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {

        if (getCustomMeshDefinition() != null) {

            for (int i = 0; i < VARIANTS.length; i++) {
                ModelBakery.registerItemVariants(this, ResourceLocationHelper.getModelResourceLocation(VARIANTS[i]));
            }

            ModelLoader.setCustomMeshDefinition(this, getCustomMeshDefinition());
        }
        else {

            if (getHasSubtypes() && VARIANTS.length > 0) {
                List<ModelResourceLocation> modelResources = new ArrayList<>();

                for (int i = 0; i < VARIANTS.length; i++) {
                    modelResources.add(ResourceLocationHelper.getModelResourceLocation(VARIANTS[i]));
                }

                ModelBakery.registerItemVariants(this, modelResources.toArray(new ModelResourceLocation[0]));
                ModelLoader.setCustomMeshDefinition(this, itemStack -> modelResources.get(itemStack.getMetadata()));
            }
            else {
                ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
            }
        }
    }

    @Override
    public ItemEE getItem() {
        return this;
    }

    @Override
    public String[] getVariants() {
        return VARIANTS;
    }
}
