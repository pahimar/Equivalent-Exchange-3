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

    /**
     * TODO Finish JavaDoc
     */
    public PlayerKnowledge() {
        this(Collections.EMPTY_SET);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerKnowledge
     */
    public PlayerKnowledge(PlayerKnowledge playerKnowledge) {
        this(playerKnowledge.knownItemStacks);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param objects
     */
    public PlayerKnowledge(Collection<?> objects) {

        knownItemStacks = new TreeSet<>(Comparators.ID_COMPARATOR);

        if (objects != null) {
            objects.forEach(this::learn);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @return
     */
    public boolean isKnown(Object object) {
        return object instanceof ItemStack && knownItemStacks.contains(ItemStackUtils.clone((ItemStack) object, 1));
    }

    /**
     * TODO Finish JavaDoc
     *
     * @return
     */
    public Set<ItemStack> getKnownItemStacks() {
        return knownItemStacks;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     */
    public void learn(Object object) {

        if (object instanceof ItemStack) {
            ItemStack unitItemStack = ItemStackUtils.clone((ItemStack) object, 1);
            knownItemStacks.add(unitItemStack);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param objects
     */
    public void learn(Collection<?> objects) {

        if (objects != null) {
            objects.forEach(this::learn);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     */
    public void forget(Object object) {

        if (object instanceof ItemStack) {
            ItemStack unitItemStack = ItemStackUtils.clone((ItemStack) object, 1);
            knownItemStacks.remove(unitItemStack);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param objects
     */
    public void forget(Collection<?> objects) {

        if (objects != null) {
            objects.forEach(this::forget);
        }
    }

    /**
     * TODO Finish JavaDoc
     */
    public void forgetAll() {
        knownItemStacks.clear();
    }
}
