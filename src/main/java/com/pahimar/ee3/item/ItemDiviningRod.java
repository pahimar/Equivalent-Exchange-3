package com.pahimar.ee3.item;

import com.pahimar.ee3.lib.Strings;

public class ItemDiviningRod extends ItemEE
{
    public ItemDiviningRod(int id)
    {
        super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.DIVINING_ROD_NAME);
    }
}
