package com.pahimar.ee.block.base;

import com.pahimar.ee.EquivalentExchange;
import com.pahimar.ee.creativetab.CreativeTab;
import com.pahimar.ee.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockBase extends Block {

    private final String BASE_NAME;

    public BlockBase(String name) {
        this(name, Material.ROCK);
    }

    public BlockBase(String name, Material material) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTab.EE_TAB);
        BASE_NAME = name;
        ModBlocks.register(this);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s:%s", EquivalentExchange.MOD_ID, BASE_NAME);
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString()));
    }
}
