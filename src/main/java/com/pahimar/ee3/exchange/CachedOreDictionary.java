package com.pahimar.ee3.exchange;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class CachedOreDictionary
{
    private static CachedOreDictionary cachedOreDictionary = null;
    private ImmutableMap<Integer, String> idToNameMap;
    private ImmutableMap<String, List<ItemStack>> oreNameToItemStackMap;
    private ImmutableMultimap<ItemStack, String> itemStackToOreNameMap;

    private CachedOreDictionary()
    {
        Map<Integer, String> idToOreNameMap = new TreeMap<Integer, String>();
        Map<String, List<ItemStack>> nameToStackMap = new TreeMap<String, List<ItemStack>>(Comparators.stringComparator);
        Multimap<ItemStack, String> stackToNameMultiMap = TreeMultimap.create(ItemHelper.idComparator, Comparators.stringComparator);

        for (String oreName : OreDictionary.getOreNames())
        {
            idToOreNameMap.put(OreDictionary.getOreID(oreName), oreName);

            List<ItemStack> oreNameItemStacks = OreDictionary.getOres(oreName);
            nameToStackMap.put(oreName, oreNameItemStacks);

            for (ItemStack itemStack : oreNameItemStacks)
            {
                stackToNameMultiMap.put(itemStack, oreName);
            }
        }

        idToNameMap = ImmutableMap.copyOf(idToOreNameMap);
        oreNameToItemStackMap = ImmutableMap.copyOf(nameToStackMap);
        itemStackToOreNameMap = ImmutableMultimap.copyOf(stackToNameMultiMap);
    }

    public static CachedOreDictionary getInstance()
    {
        if (cachedOreDictionary == null)
        {
            cachedOreDictionary = new CachedOreDictionary();
        }

        return cachedOreDictionary;
    }

    public Set<String> getOreNames()
    {
        return oreNameToItemStackMap.keySet();
    }

    public List<ItemStack> getItemStacksForOreName(String oreName)
    {
        if (oreNameToItemStackMap.containsKey(oreName))
        {
            return oreNameToItemStackMap.get(oreName);
        }

        return new ArrayList<ItemStack>();
    }

    public List<String> getOreNamesForItemStack(ItemStack itemStack)
    {
        List<String> oreNameList = new ArrayList<String>();
        if (itemStackToOreNameMap.containsKey(itemStack))
        {
            for (String oreName : itemStackToOreNameMap.get(itemStack))
            {
                oreNameList.add(oreName);
            }
        }

        return oreNameList;
    }
}
