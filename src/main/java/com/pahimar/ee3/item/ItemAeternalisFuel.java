package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

public class ItemAeternalisFuel extends ItemEE
{
    public ItemAeternalisFuel(int id)
    {
        super(id);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.AETERNALIS_FUEL_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        maxStackSize = 64;
    }
}
