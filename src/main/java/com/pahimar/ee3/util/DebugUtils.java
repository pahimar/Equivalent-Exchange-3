package com.pahimar.ee3.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DebugUtils
{
    public static void dumpSortedOreDictionaryNames()
    {
        List<String> oreNames = new ArrayList<String>(Arrays.asList(OreDictionary.getOreNames()));
        Collections.sort(oreNames);

        for (String oreName : oreNames)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("Ore: %s, ItemStacks: ", oreName));
            for (ItemStack itemStack : OreDictionary.getOres(oreName))
            {
                stringBuilder.append(String.format("%s ", ItemHelper.toString(itemStack)));
            }
            LogHelper.info(stringBuilder.toString());
        }
    }
}
