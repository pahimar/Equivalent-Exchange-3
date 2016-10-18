package com.pahimar.ee3.item;

import com.pahimar.ee3.item.base.ItemBase;

public class ItemAlchemicalFuel extends ItemBase {

    public ItemAlchemicalFuel() {
        super("alchemical_fuel", "alchemical_coal", "mobius_fuel", "aeternalis_fuel");
        setMaxStackSize(64);
        setHasSubtypes(true);
    }
}
