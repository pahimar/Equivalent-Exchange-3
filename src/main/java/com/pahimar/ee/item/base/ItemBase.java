package com.pahimar.ee.item.base;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.creativetab.CreativeTab;
import com.pahimar.ee.init.ModItems;
import com.pahimar.ee.util.ResourceLocationUtil;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Finish Javadoc
 *
 * @author      pahimar
 *
 * @since       3.0.0
 */
public class ItemBase extends Item {

    /**
     * TODO Finish Javadoc
     */
    private final String baseName;

    /**
     * TODO Finish Javadoc
     */
    private final String[] variants;

    /**
     * TODO Finish Javadoc
     *
     * @param   baseName
     *          TODO
     * @param   variants
     *          TODO
     *
     * @since   3.0.0
     */
    public ItemBase(String baseName, String ... variants) {
        super();
        setRegistryName(baseName);
        setUnlocalizedName(baseName);
        setMaxStackSize(1);
        setNoRepair();
        setCreativeTab(CreativeTab.MOD_TAB);

        this.baseName = baseName;
        if (variants.length > 0) {
            this.variants = variants;
        }
        else {
            this.variants = new String[0];
        }
        ModItems.register(this);
    }

    /**
     * {@inheritDoc}
     *
     * @since   3.0.0
     */
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        if (hasSubtypes && itemStack.getMetadata() < variants.length) {
            return String.format("item.%s:%s", EquivalentExchange.MOD_ID, variants[Math.abs(itemStack.getMetadata() % variants.length)]);
        }
        else {
            return String.format("item.%s:%s", EquivalentExchange.MOD_ID, baseName);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @since   3.0.0
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

        if (getHasSubtypes() && variants.length > 0) {
            for (int meta = 0; meta < variants.length; ++meta) {
                items.add(new ItemStack(this, 1, meta));
            }
        }
        else {
            super.getSubItems(tab, items);
        }
    }

    /**
     * TODO Finish Javadoc
     *
     * @since
     */
    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {

        if (getCustomMeshDefinition() != null) {
            for (String variant : variants) {
                ModelBakery.registerItemVariants(this, ResourceLocationUtil.getResourceLocation(variant));
            }

            ModelLoader.setCustomMeshDefinition(this, getCustomMeshDefinition());
        }
        else {

            if (getHasSubtypes() && variants.length > 0) {
                List<ModelResourceLocation> modelResources = new ArrayList<>();

                for (String variant : variants) {
                    modelResources.add(ResourceLocationUtil.getModelResourceLocation(variant));
                }

                ModelBakery.registerItemVariants(this, modelResources.toArray(new ModelResourceLocation[0]));
                ModelLoader.setCustomMeshDefinition(this, itemStack -> modelResources.get(itemStack.getMetadata()));
            }
            else {
                ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
            }
        }
    }

    /**
     * TODO Finish Javadoc
     *
     * @return  TODO
     *
     * @since   3.0.0
     */
    private ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }
}
