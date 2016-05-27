package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.ItemStackUtils;
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

    public PlayerKnowledge(PlayerKnowledge playerKnowledge) {
        this(playerKnowledge.knownItemStacks);
    }

    public PlayerKnowledge(Collection<?> objects) {

        knownItemStacks = new TreeSet<>(Comparators.ID_COMPARATOR);

        if (objects != null) {
            for (Object object : objects) {
                learn(object);
            }
        }
    }

    public boolean isKnown(Object object) {

        if (object instanceof ItemStack) {
            return knownItemStacks.contains(ItemStackUtils.clone((ItemStack) object, 1));
        }

        return false;
    }

    public Set<ItemStack> getKnownItemStacks() {
        return knownItemStacks;
    }

    public void learn(Object object) {

        if (object instanceof ItemStack) {
            ItemStack unitItemStack = ItemStackUtils.clone((ItemStack) object, 1);
            knownItemStacks.add(unitItemStack);
        }
    }

    public void learn(Collection<?> objects) {

        if (objects != null) {
            for (Object object : objects) {
                learn(object);
            }
        }
    }

    public void forget(Object object) {

        if (object instanceof ItemStack) {
            ItemStack unitItemStack = ItemStackUtils.clone((ItemStack) object, 1);
            knownItemStacks.remove(unitItemStack);
        }
    }

    public void forget(Collection<?> objects) {

        if (objects != null) {
            for (Object object : objects) {
                forget(object);
            }
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
            stringBuilder.append(String.format("%s, ", ItemStackUtils.toString(itemStack)));
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
