package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class PlayerKnowledge {

    private Set<ItemStack> knownItemStacks;

    public PlayerKnowledge() {
        this(Collections.EMPTY_SET);
    }

    public PlayerKnowledge(Collection<ItemStack> itemStacks) {

        knownItemStacks = new TreeSet<>(Comparators.ID_COMPARATOR);

        if (itemStacks != null) {
            for (ItemStack itemStack : itemStacks) {
                learn(itemStack);
            }
        }
    }

    public boolean isKnown(ItemStack itemStack) {
        return knownItemStacks.contains(ItemHelper.clone(itemStack, 1));
    }

    public Set<ItemStack> getKnownItemStacks() {
        return knownItemStacks;
    }

    public void learn(ItemStack itemStack) {

        if (itemStack != null) {
            ItemStack unitItemStack = ItemHelper.clone(itemStack, 1);
            knownItemStacks.add(unitItemStack);
        }
    }

    public void learn(Collection<ItemStack> itemStacks) {

        for (ItemStack itemStack : itemStacks) {
            learn(itemStack);
        }
    }

    public void forget(ItemStack itemStack) {

        if (itemStack != null) {
            ItemStack unitItemStack = ItemHelper.clone(itemStack, 1);
            knownItemStacks.remove(unitItemStack);
        }
    }

    public void forget(Collection<ItemStack> itemStacks) {

        for (ItemStack itemStack : itemStacks) {
            forget(itemStack);
        }
    }

    public void forgetAll() {
        knownItemStacks.clear();
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (ItemStack itemStack : knownItemStacks) {
            stringBuilder.append(String.format("%s, ", ItemHelper.toString(itemStack)));
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
