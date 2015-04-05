package com.pahimar.ee3.reference;

import com.pahimar.ee3.util.ItemHelper;

import java.util.Comparator;

public class Comparators
{
    public static final Comparator[] itemComparators = {ItemHelper.displayNameComparator, ItemHelper.energyValueComparator, ItemHelper.idComparator};
}
