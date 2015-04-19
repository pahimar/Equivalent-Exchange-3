package com.pahimar.ee3.exchange;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class CachedOreDictionary
{
    private static CachedOreDictionary cachedOreDictionary = null;
    private ImmutableMap<Integer, String> idToNameMap;
    private ImmutableMap<String, List<ItemStack>> oreNameToItemStackMap;
    private ImmutableMultimap<WrappedStack, String> itemStackToOreNameMap;

    private CachedOreDictionary()
    {
        Map<Integer, String> idToOreNameMap = new TreeMap<Integer, String>();
        Map<String, List<ItemStack>> nameToStackMap = new TreeMap<String, List<ItemStack>>(Comparators.stringComparator);
        Multimap<WrappedStack, String> stackToNameMultiMap = TreeMultimap.create(WrappedStack.comparator, Comparators.stringComparator);

        for (String oreName : OreDictionary.getOreNames())
        {
            idToOreNameMap.put(OreDictionary.getOreID(oreName), oreName);

            List<ItemStack> oreNameItemStacks = OreDictionary.getOres(oreName);
            nameToStackMap.put(oreName, oreNameItemStacks);

            for (ItemStack itemStack : oreNameItemStacks)
            {
                stackToNameMultiMap.put(WrappedStack.wrap(itemStack), oreName);
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
        WrappedStack wrappedStack = WrappedStack.wrap(itemStack);

        if (itemStackToOreNameMap.containsKey(wrappedStack))
        {
            for (String oreName : itemStackToOreNameMap.get(wrappedStack))
            {
                oreNameList.add(oreName);
            }
        }
        else
        {
            for (WrappedStack wrappedStack1 : itemStackToOreNameMap.keySet())
            {

            }
        }

        return oreNameList;
    }

    public void dumpCachedOreDictionaryToLog()
    {
        for (String oreName : CachedOreDictionary.getInstance().getOreNames())
        {
            LogHelper.info(String.format("OreName: %s, ItemStacks: %s", oreName, CachedOreDictionary.getInstance().getItemStacksForOreName(oreName)));
        }
    }
}
