package com.pahimar.ee3.util;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.api.EnergyValueRegistryProxy;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.*;

public class FilterUtils
{
    public static Set<ItemStack> filterForItemBlock(Set<ItemStack> unfilteredItemStackSet)
    {
        return filterForItemBlock(unfilteredItemStackSet, ItemHelper.baseComparator);
    }

    public static Set<ItemStack> filterForItemBlock(Set<ItemStack> unfilteredItemStackSet, Comparator comparator)
    {
        Set<ItemStack> itemBlockOnlySet = new TreeSet<ItemStack>(comparator);

        for (ItemStack itemStack : unfilteredItemStackSet)
        {
            if (itemStack.getItem() instanceof ItemBlock)
            {
                itemBlockOnlySet.add(itemStack);
            }
        }

        return itemBlockOnlySet;
    }

    public static Set<ItemStack> filterByNameStartsWith(Set<ItemStack> unfilteredItemStackSet, String filterString)
    {
        return filterByNameStartsWith(unfilteredItemStackSet, filterString, ItemHelper.baseComparator);
    }

    public static Set<ItemStack> filterByNameStartsWith(Set<ItemStack> unfilteredItemStackSet, String filterString, Comparator comparator)
    {
        Set<ItemStack> nameSortedSet = new TreeSet<ItemStack>(comparator);

        for (ItemStack itemStack : unfilteredItemStackSet)
        {
            String itemDisplayName = itemStack.getDisplayName().toLowerCase();

            if (filterString != null)
            {
                if (itemDisplayName.startsWith(filterString.toLowerCase()))
                {
                    nameSortedSet.add(itemStack);
                }
            }
            else
            {
                nameSortedSet.add(itemStack);
            }
        }

        return nameSortedSet;
    }

    public static Set<ItemStack> filterByNameContains(Collection<ItemStack> unfilteredCollection, String filterString)
    {
        Set<ItemStack> nameSortedSet = new HashSet<ItemStack>();

        for (ItemStack itemStack : unfilteredCollection)
        {
            String itemDisplayName = itemStack.getDisplayName().toLowerCase();

            if (filterString != null)
            {
                if (itemDisplayName.contains(filterString.toLowerCase()))
                {
                    nameSortedSet.add(itemStack);
                }
            }
            else
            {
                nameSortedSet.add(itemStack);
            }
        }

        return nameSortedSet;
    }

    public static Set<ItemStack> filterByNameContains(Collection<ItemStack> unfilteredCollection, String filterString, Comparator comparator)
    {
        Set<ItemStack> nameSortedSet = new TreeSet<ItemStack>(comparator);

        for (ItemStack itemStack : unfilteredCollection)
        {
            String itemDisplayName = itemStack.getDisplayName().toLowerCase();

            if (filterString != null)
            {
                if (itemDisplayName.contains(filterString.toLowerCase()))
                {
                    nameSortedSet.add(itemStack);
                }
            }
            else
            {
                nameSortedSet.add(itemStack);
            }
        }

        return nameSortedSet;
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> unfilteredCollection, EnergyValue energyValue)
    {
        return filterByEnergyValue(unfilteredCollection, energyValue.getEnergyValue());
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> unfilteredCollection, EnergyValue energyValue, Comparator<ItemStack> comparator)
    {
        return filterByEnergyValue(unfilteredCollection, energyValue.getEnergyValue(), comparator);
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> unfilteredCollection, float energyValue)
    {
        Set<ItemStack> sortedSet = new HashSet<ItemStack>();

        for (ItemStack itemStack : unfilteredCollection)
        {
            if (energyValue > 0f && EnergyValueRegistryProxy.hasEnergyValue(itemStack))
            {
                if (EnergyValueRegistryProxy.getEnergyValue(itemStack).getEnergyValue() <= energyValue)
                {
                    sortedSet.add(itemStack);
                }
            }
        }

        return sortedSet;
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> unfilteredCollection, float energyValue, Comparator<ItemStack> comparator)
    {
        Set<ItemStack> sortedSet = new TreeSet<ItemStack>(comparator);

        for (ItemStack itemStack : unfilteredCollection)
        {
            if (energyValue > 0f && EnergyValueRegistryProxy.hasEnergyValue(itemStack))
            {
                if (EnergyValueRegistryProxy.getEnergyValue(itemStack).getEnergyValue() <= energyValue)
                {
                    sortedSet.add(itemStack);
                }
            }
        }

        return sortedSet;
    }
}
