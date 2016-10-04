package com.pahimar.ee3.block.base;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.item.base.ItemBlockEnumEE;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEE extends Block {

    private final String BASE_NAME;

    public BlockEE(String name) {
        this(name, Material.ROCK);
    }

    public BlockEE(String name, Material material) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTab.EE3_TAB);
        BASE_NAME = name;
        ModBlocks.register(this);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s:%s", EquivalentExchange3.MOD_ID, BASE_NAME);
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString()));
    }

    public static ItemBlock getItemBlockFor(BlockEE blockEE) {
        return blockEE instanceof BlockEnumEE ? new ItemBlockEnumEE((BlockEnumEE) blockEE) : new ItemBlock(blockEE);
    }
}
