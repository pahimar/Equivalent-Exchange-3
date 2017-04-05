package com.pahimar.ee.item;

import com.pahimar.ee.item.base.ItemBase;

public class ItemAlchemicalFuel extends ItemBase {

    public ItemAlchemicalFuel() {
        super("alchemical_fuel", "alchemical_coal", "mobius_fuel", "aeternalis_fuel");
        setMaxStackSize(64);
        setHasSubtypes(true);
    }
}
