package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

public class ItemMobiusFuel extends ItemEE
{
    public ItemMobiusFuel(int id)
    {
        super(id);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.MOBIUS_FUEL_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        maxStackSize = 64;
    }
}
