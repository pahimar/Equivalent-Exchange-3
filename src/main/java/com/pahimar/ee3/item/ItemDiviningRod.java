package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

public class ItemDiviningRod extends ItemEE
{
    public ItemDiviningRod(int id)
    {
        super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.DIVINING_ROD_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }
}
