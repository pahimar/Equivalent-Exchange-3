package com.pahimar.ee3.util;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.reference.Comparators;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class FilterUtils {

    public static Set<ItemStack> filterByDisplayName(Set<ItemStack> itemStacks, String filterString) {
        return filterByDisplayName(itemStacks, filterString, NameFilterType.STARTS_WITH, null);
    }

    public static Set<ItemStack> filterByDisplayName(Set<ItemStack> itemStacks, String filterString, NameFilterType filterType) {
        return filterByDisplayName(itemStacks, filterString, filterType, null);
    }

    public static Set<ItemStack> filterByDisplayName(Collection<ItemStack> itemStacks, String filterString, NameFilterType filterType, Comparator comparator) {

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

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> itemStacks, Number valueBound, ValueFilterType filterType, Comparator comparator) {
        return filterByEnergyValue(itemStacks, new EnergyValue(valueBound.floatValue()), filterType, comparator);
    }

    public static Set<ItemStack> filterByEnergyValue(Collection<ItemStack> itemStacks, EnergyValue valueBound, ValueFilterType filterType, Comparator comparator) {

        Set<ItemStack> filteredSet = (comparator != null ? new TreeSet<>(comparator) : new TreeSet<>(Comparators.DISPLAY_NAME_COMPARATOR));

        if (itemStacks != null) {

            if (valueBound == null) {
                filteredSet.addAll(itemStacks);
            }
            else {
                for (ItemStack itemStack : itemStacks) {

                    EnergyValue energyValue = EnergyValueRegistryProxy.getEnergyValue(itemStack);

                    if (energyValue != null) {
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

    public enum NameFilterType {
        STARTS_WITH,
        CONTAINS
    }

    public enum ValueFilterType {
        VALUE_GREATER_THAN_BOUND,
        VALUE_LOWER_THAN_BOUND
    }
}
