package com.pahimar.ee3.util;

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

    public static Set<ItemStack> filterByNameContains(Set<ItemStack> unfilteredItemStackSet, String filterString)
    {
        return filterByNameContains(unfilteredItemStackSet, filterString, ItemHelper.baseComparator);
    }

    public static Set<ItemStack> filterByNameContains(Set<ItemStack> unfilteredItemStackSet, String filterString, Comparator comparator)
    {
        Set<ItemStack> nameSortedSet = new TreeSet<ItemStack>(comparator);

        for (ItemStack itemStack : unfilteredItemStackSet)
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

    public static void filterOutListItemsWithInvalidIcons(List<ItemStack> unfilteredCollection)
    {
        filterOutListItemsWithInvalidIcons(unfilteredCollection, ItemHelper.baseComparator);
    }

    public static Set<ItemStack> filterOutItemsWithInvalidIcons(Set<ItemStack> unfilteredCollection, Comparator comparator)
    {
        List<ItemStack> itemsToRemove = new ArrayList<ItemStack>();
        Set<ItemStack> filteredSet = new TreeSet<ItemStack>(comparator);

        for (ItemStack itemStack : unfilteredCollection)
        {
            try
            {
                itemStack.getItem().getIconIndex(itemStack);
                filteredSet.add(itemStack);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                itemsToRemove.add(itemStack);
            }
        }

        return filteredSet;
    }

    public static void filterOutListItemsWithInvalidIcons(List<ItemStack> unfilteredCollection, Comparator comparator)
    {
        List<ItemStack> itemsToRemove = new ArrayList<ItemStack>();

        for (ItemStack itemStack : unfilteredCollection)
        {
            try
            {
                itemStack.getItem().getIconIndex(itemStack);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                itemsToRemove.add(itemStack);
            }
        }

        unfilteredCollection.removeAll(itemsToRemove);
        Collections.sort(unfilteredCollection, comparator);
    }
}
