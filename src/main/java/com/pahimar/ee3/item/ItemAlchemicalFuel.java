package com.pahimar.ee3.item;

import com.pahimar.ee3.item.base.ItemEE;

public class ItemAlchemicalFuel extends ItemEE {

    public ItemAlchemicalFuel() {
        super("alchemical_fuel", "alchemical_coal", "mobius_fuel", "aeternalis_fuel");
        setHasSubtypes(true);
    }
}
