package com.pahimar.ee.block.base;

import com.pahimar.ee.EquivalentExchange;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockEnum extends BlockBase {

    private final IEnumMeta[] VARIANTS;

    public BlockEnum(String name, IEnumMeta[] variants) {
        this(name, Material.ROCK, variants);
    }

    public BlockEnum(String name, Material material, IEnumMeta[] variants) {
        super(name, material);
        if (variants.length > 0) {
            VARIANTS = variants;
        }
        else {
            VARIANTS = new IEnumMeta[0];
        }
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() != null && Block.getBlockFromItem(itemStack.getItem()) instanceof BlockBase) {
            if (VARIANTS.length > 0) {
                return String.format("tile.%s:%s", EquivalentExchange.MOD_ID, VARIANTS[Math.abs(itemStack.getMetadata() % VARIANTS.length)].getName());
            }
        }

        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {

        if (Item.getItemFromBlock(this) != null) {
            if (VARIANTS.length > 0) {
                for (IEnumMeta variant : VARIANTS) {
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), variant.getMeta(), new ModelResourceLocation(getRegistryName(), "variant=" + variant.getName()));
                }
            }
            else {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString()));
            }
        }
    }
}
