package com.pahimar.ee3.reference;

import com.pahimar.ee3.util.ItemHelper;

import java.util.Comparator;

public class Comparators
{
    public static final Comparator[] itemComparators = {ItemHelper.displayNameComparator, ItemHelper.energyValueComparator, ItemHelper.idComparator};

    public static Comparator<String> stringComparator = new Comparator<String>()
    {
        @Override
        public int compare(String string1, String string2)
        {
            return string1.compareToIgnoreCase(string2);
        }
    };
}
