package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

public class ItemChalk extends ItemEE
{
    public ItemChalk(int id)
    {
        super(id);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.CHALK_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.maxStackSize = 64;
    }
}
