package com.pahimar.ee3.item;

import com.pahimar.ee3.item.base.ItemEE;

public class ItemAlchemicalFuel extends ItemEE {

    private static final String NAME = "alchemical_fuel";
    private static final String[] VARIANTS = {"alchemical_coal", "mobius_fuel", "aeternalis_fuel"};

    public ItemAlchemicalFuel() {
        super(NAME, VARIANTS);
        setHasSubtypes(true);
    }
}
