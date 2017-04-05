package com.pahimar.ee.util;

import com.pahimar.ee.api.exchange.EnergyValue;
import com.pahimar.ee.exchange.EnergyValueRegistry;
import com.pahimar.ee.exchange.WrappedStack;
import com.pahimar.ee.reference.Comparators;
import net.minecraft.item.ItemStack;

import java.util.*;

public class FilterUtils {

    public static Set<ItemStack> filterByDisplayName(Set<ItemStack> itemStacks, String filterString) {
        return filterByDisplayName(itemStacks, filterString, NameFilterType.STARTS_WITH, null);
    }

    public static Set<ItemStack> filterByDisplayName(Set<ItemStack> itemStacks, String filterString, NameFilterType filterType) {
        return filterByDisplayName(itemStacks, filterString, filterType, null);
    }

    public static Set<ItemStack> filterByDisplayName(Collection<ItemStack> itemStacks, String filterString, NameFilterType filterType, Comparator<ItemStack> comparator) {

        Set<ItemStack> filteredSet = (comparator != null ? new TreeSet<>(comparator) : new TreeSet<>(Comparators.DISPLAY_NAME_COMPARATOR));

        if (itemStacks != null) {
            if (filterString == null || filterString.isEmpty()) {
                filteredSet.addAll(itemStacks);
            }
            else {
                for (ItemStack itemStack : itemStacks) {

                    String itemDisplayName = itemStack.getDisplayName().toLowerCase();

                    if (filterType == NameFilterType.STARTS_WITH && itemDisplayName.startsWith(filterString.toLowerCase())) {
                        filteredSet.add(itemStack);
                    } else if (filterType == NameFilterType.CONTAINS && itemDisplayName.contains(filterString.toLowerCase())) {
                        filteredSet.add(itemStack);
                    }
                }
            }
        }

        return filteredSet;
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> itemStacks, Number valueBound) {
        return filterByEnergyValue(itemStacks, new EnergyValue(valueBound.floatValue()), ValueFilterType.VALUE_LOWER_THAN_BOUND, null);
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> itemStacks, EnergyValue valueBound) {
        return filterByEnergyValue(itemStacks, valueBound, ValueFilterType.VALUE_LOWER_THAN_BOUND, null);
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> itemStacks, Number valueBound, ValueFilterType filterType) {
        return filterByEnergyValue(itemStacks, new EnergyValue(valueBound.floatValue()), filterType, null);
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> itemStacks, EnergyValue valueBound, ValueFilterType filterType) {
        return filterByEnergyValue(itemStacks, valueBound, filterType, null);
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> itemStacks, Number valueBound, ValueFilterType filterType, Comparator<ItemStack> comparator) {
        return filterByEnergyValue(EnergyValueRegistry.INSTANCE.getEnergyValues(), itemStacks, new EnergyValue(valueBound.floatValue()), filterType, comparator);
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> itemStacks, EnergyValue valueBound, ValueFilterType filterType, Comparator<ItemStack> comparator) {
        return filterByEnergyValue(EnergyValueRegistry.INSTANCE.getEnergyValues(), itemStacks, valueBound, filterType, comparator);
    }

    public static Set<ItemStack> filterByEnergyValue(Map<WrappedStack, EnergyValue> valueMap, Collection<ItemStack> itemStacks, EnergyValue valueBound, ValueFilterType filterType, Comparator<ItemStack> comparator) {

        Set<ItemStack> filteredSet = (comparator != null ? new TreeSet<>(comparator) : new TreeSet<>(Comparators.DISPLAY_NAME_COMPARATOR));

        if (itemStacks != null) {

            if (valueBound == null) {
                filteredSet.addAll(itemStacks);
            }
            else {
                for (ItemStack itemStack : itemStacks) {

                    EnergyValue energyValue = EnergyValueRegistry.INSTANCE.getEnergyValue(valueMap, itemStack, false);

                    if (energyValue != null && Float.compare(energyValue.getValue(), 0) > 0) {
                        if (filterType == ValueFilterType.VALUE_LOWER_THAN_BOUND && energyValue.compareTo(valueBound) <= 0) {
                            filteredSet.add(itemStack);
                        }
                        else if (filterType == ValueFilterType.VALUE_GREATER_THAN_BOUND && energyValue.compareTo(valueBound) >= 0) {
                            filteredSet.add(itemStack);
                        }
                    }
                }
            }
        }

        return filteredSet;
    }

    public static Collection<ItemStack> filterForItemStacks(Collection<?> objects) {

        Set<ItemStack> itemStacks = new TreeSet<>(Comparators.ID_COMPARATOR);

        for (Object object : objects) {
            if (object instanceof ItemStack) {
                itemStacks.add((ItemStack) object);
            }
        }

        return itemStacks;
    }

    public enum NameFilterType {
        STARTS_WITH,
        CONTAINS
    }

    public enum ValueFilterType {
        VALUE_GREATER_THAN_BOUND,
        VALUE_LOWER_THAN_BOUND
    }
}
