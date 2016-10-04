package com.pahimar.ee3.item.base;

import com.pahimar.ee3.block.base.BlockEnumEE;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockEnumEE extends ItemBlock {

    protected final BlockEnumEE block;

    public ItemBlockEnumEE(BlockEnumEE block) {
        super(block);
        this.block = block;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return (block != null) ? this.block.getUnlocalizedName(itemStack) : super.getUnlocalizedName(itemStack);
    }
}
