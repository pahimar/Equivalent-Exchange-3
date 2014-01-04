package com.pahimar.ee3.item;

import net.minecraft.item.ItemBlock;

public class ItemInfusedOreBlock extends ItemBlock
{
    public ItemInfusedOreBlock(int id)
    {
        super(id);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }
}
