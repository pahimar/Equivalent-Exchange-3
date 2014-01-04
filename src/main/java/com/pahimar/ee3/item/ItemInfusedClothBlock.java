package com.pahimar.ee3.item;

import net.minecraft.item.ItemBlock;

public class ItemInfusedClothBlock extends ItemBlock
{
    public ItemInfusedClothBlock(int id)
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
