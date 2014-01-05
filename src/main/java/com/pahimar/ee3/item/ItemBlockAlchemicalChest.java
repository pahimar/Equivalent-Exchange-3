package com.pahimar.ee3.item;

import net.minecraft.item.ItemBlock;

public class ItemBlockAlchemicalChest extends ItemBlock
{
    public ItemBlockAlchemicalChest(int id)
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
