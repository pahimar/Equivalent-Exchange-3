package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class PlayerKnowledge {

    private static final int VERSION = 1;

    private int version;
    private Set<ItemStack> knownItemStacks;

    public PlayerKnowledge() {
        this(Collections.EMPTY_SET);
    }

    public PlayerKnowledge(Collection<ItemStack> itemStacks) {

        version = VERSION;
        knownItemStacks = new TreeSet<>(Comparators.ID_COMPARATOR);

        if (itemStacks != null) {
            for (ItemStack itemStack : itemStacks) {
                knownItemStacks.add(ItemHelper.clone(itemStack, 1));
            }
        }
    }

    public boolean isKnown(ItemStack itemStack) {
        return knownItemStacks.contains(ItemHelper.clone(itemStack, 1));
    }

    public Set<ItemStack> get() {
        return knownItemStacks;
    }

    public void learn(ItemStack itemStack) {

        if (itemStack != null) {
            ItemStack unitItemStack = ItemHelper.clone(itemStack, 1);
            knownItemStacks.add(unitItemStack);
        }
    }

    public void forget(ItemStack itemStack) {

        if (itemStack != null) {
            ItemStack unitItemStack = ItemHelper.clone(itemStack, 1);
            knownItemStacks.remove(unitItemStack);
        }
    }

    public void forgetAll() {
        knownItemStacks.clear();
    }
}
