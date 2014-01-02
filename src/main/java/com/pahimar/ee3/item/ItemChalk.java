package com.pahimar.ee3.item;

import com.pahimar.ee3.lib.Strings;

public class ItemChalk extends ItemEE
{
    public ItemChalk(int id)
    {
        super(id);
        this.setUnlocalizedName(Strings.CHALK_NAME);
        this.maxStackSize = 64;
    }
}
