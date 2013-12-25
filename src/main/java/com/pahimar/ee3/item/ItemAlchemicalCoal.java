package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

public class ItemAlchemicalCoal extends ItemEE
{
    public ItemAlchemicalCoal(int id)
    {
        super(id);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_COAL_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        maxStackSize = 64;
    }
}
