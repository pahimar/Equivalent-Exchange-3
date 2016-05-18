package com.pahimar.ee3.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class OreDictionaryHelper {

    public static Collection<String> getOreNames(ItemStack itemStack) {

        Set<String> oreNames = new TreeSet<>();

        for (int oreId : OreDictionary.getOreIDs(itemStack)) {
            oreNames.add(OreDictionary.getOreName(oreId));
        }

        return oreNames;
    }
}
