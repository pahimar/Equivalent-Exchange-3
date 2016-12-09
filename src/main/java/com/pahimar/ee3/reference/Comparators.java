package com.pahimar.ee3.reference;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public class Comparators {

    public static final Comparator<Collection<ItemStack>> ITEM_STACK_COLLECTION_COMPARATOR = (o1, o2) -> {

        if (o1 != null && o2 != null) {
            if (o1.size() == o2.size()) {
                if (o1.containsAll(o2)) {
                    if (o2.containsAll(o1)) {
                        return 0;
                    }
                    else {
                        return 1;
                    }
                }
                else {
                    return -1;
                }
            }
            else {
                return o1.size() - o2.size();
            }
        }
        else if (o1 != null) {
            return -1;
        }
        else if (o2 != null) {
            return 1;
        }
        else {
            return 0;
        }
    };

    public static final Comparator<Set<WrappedStack>> WRAPPED_STACK_SET_COMPARATOR = (collection1, collection2) -> {

        if (collection1 != null && collection2 != null) {
            if (collection1.size() == collection2.size()) {
                if (collection1.containsAll(collection2)) {
                    if (collection2.containsAll(collection1)) {
                        return 0;
                    }
                    else {
                        return 1;
                    }
                }
                else {
                    return -1;
                }
            }
            else {
                return collection1.size() - collection2.size();
            }
        }
        else if (collection1 != null) {
            return -1;
        }
        else if (collection2 != null) {
            return 1;
        }
        else {
            return 0;
        }
    };

    public static final Comparator<String> STRING_COMPARATOR = String::compareToIgnoreCase;

    public static final Comparator<ItemStack> ID_COMPARATOR = (itemStack1, itemStack2) -> {

        if (itemStack1 != null && itemStack2 != null) {
            if (itemStack1.getItem() != null && itemStack2.getItem() != null) {
                // Sort on id
                if (Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem()) == 0) {
                    // Sort on item
                    if (itemStack1.getItem() == itemStack2.getItem()) {
                        // Then sort on meta
                        if ((itemStack1.getItemDamage() == itemStack2.getItemDamage()) || itemStack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || itemStack2.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
                            // Then sort on NBT
                            if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound()) {
                                // Then sort on stack size
                                if (ItemStack.areItemStackTagsEqual(itemStack1, itemStack2)) {
                                    return (itemStack1.getCount() - itemStack2.getCount());
                                }
                                else {
                                    return itemStack1.getTagCompound().toString().compareTo(itemStack2.getTagCompound().toString());
                                }
                            }
                            else if (!(itemStack1.hasTagCompound()) && itemStack2.hasTagCompound()) {
                                return -1;
                            }
                            else if (itemStack1.hasTagCompound() && !(itemStack2.hasTagCompound())) {
                                return 1;
                            }
                            else {
                                return (itemStack1.getCount() - itemStack2.getCount());
                            }
                        }
                        else {
                            return (itemStack1.getItemDamage() - itemStack2.getItemDamage());
                        }
                    }
                    else {
                        return itemStack1.getItem().getUnlocalizedName(itemStack1).compareToIgnoreCase(itemStack2.getItem().getUnlocalizedName(itemStack2));
                    }
                }
                else {
                    return Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem());
                }
            }
            else if (itemStack1.getItem() != null) {
                return -1;
            }
            else if (itemStack2.getItem() != null) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else if (itemStack1 != null) {
            return -1;
        }
        else if (itemStack2 != null) {
            return 1;
        }
        else {
            return 0;
        }
    };

    public static final Comparator<ItemStack> DISPLAY_NAME_COMPARATOR = (itemStack1, itemStack2) -> {

        if (itemStack1 != null && itemStack2 != null) {
            if (itemStack1.getDisplayName().equalsIgnoreCase(itemStack2.getDisplayName())) {
                return ID_COMPARATOR.compare(itemStack1, itemStack2);
            }
            else {
                return itemStack1.getDisplayName().compareToIgnoreCase(itemStack2.getDisplayName());
            }
        }
        else if (itemStack1 != null) {
            return -1;
        }
        else if (itemStack2 != null) {
            return 1;
        }
        else {
            return 0;
        }
    };

    public static final Comparator<ItemStack> ENERGY_VALUE_ITEM_STACK_COMPARATOR = (itemStack1, itemStack2) -> {

        if (itemStack1 != null && itemStack2 != null) {
            if (EnergyValueRegistryProxy.hasEnergyValue(itemStack1) && EnergyValueRegistryProxy.hasEnergyValue(itemStack2)) {
                return Float.compare(EnergyValueRegistryProxy.getEnergyValue(itemStack1).getValue(), EnergyValueRegistryProxy.getEnergyValue(itemStack2).getValue());
            }
            else {
                return ID_COMPARATOR.compare(itemStack1, itemStack2);
            }
        }
        else if (itemStack1 != null) {
            return -1;
        }
        else if (itemStack2 != null) {
            return 1;
        }
        else {
            return 0;
        }
    };
}
