package com.pahimar.ee3.exchange;

import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.FilterUtils;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public final class OreStack implements Comparable<OreStack> {

    private final String oreName;
    private final int stackSize;

    public OreStack() {
        oreName = null;
        stackSize = -1;
    }

    public OreStack(String oreName) {
        this(oreName, 1);
    }

    public OreStack(String oreName, int stackSize) {
        this.oreName = oreName;
        this.stackSize = stackSize;
    }

    public OreStack(OreStack oreStack) {
        this.oreName = oreStack.oreName;
        this.stackSize = oreStack.stackSize;
    }

    public String getOreName() {
        return oreName;
    }

    public int getStackSize() {
        return stackSize;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof OreStack && (COMPARATOR.compare(this, (OreStack) object) == 0);
    }

    @Override
    public String toString() {
        return String.format("%sxoreStack.%s", stackSize, oreName);
    }

    @Override
    public int compareTo(OreStack oreStack) {
        return COMPARATOR.compare(this, oreStack);
    }

    public static OreStack build(String oreName) {
        return build(oreName, 1);
    }

    public static OreStack build(String oreName, int stackSize) {

        if (OreDictionary.doesOreNameExist(oreName)) {
            return new OreStack(oreName, stackSize);
        }

        return null;
    }

    public static OreStack copy(OreStack oreStack) {

        if (oreStack != null) {
            return build(oreStack.oreName, oreStack.stackSize);
        }

        return null;
    }

    public static OreStack copy(OreStack oreStack, int stackSize) {

        if (oreStack != null) {
            return build(oreStack.oreName, stackSize);
        }

        return null;
    }

    public static boolean compareOreNames(OreStack oreStack1, OreStack oreStack2) {

        if (oreStack1 != null && oreStack2 != null) {
            if ((oreStack1.oreName != null) && (oreStack2.oreName != null)) {
                return oreStack1.oreName.equalsIgnoreCase(oreStack2.oreName);
            }
        }

        return false;
    }

    public static OreStack getOreStackFrom(Object... objects) {
        return getOreStackFrom(Arrays.asList(objects));
    }

    public static OreStack getOreStackFrom(Collection<?> objects) {

        for (String oreName : OreDictionary.getOreNames()) {
            if (Comparators.ITEM_STACK_COLLECTION_COMPARATOR.compare(FilterUtils.filterForItemStacks(objects), OreDictionary.getOres(oreName)) == 0) {
                return new OreStack(oreName, 1);
            }
        }

        return null;
    }

    public static int compare(OreStack oreStack1, OreStack oreStack2) {
        return COMPARATOR.compare(oreStack1, oreStack2);
    }

    private static final Comparator<OreStack> COMPARATOR = (oreStack1, oreStack2) -> {
        if (oreStack1 != null && oreStack1.oreName != null) {
            if (oreStack2 != null && oreStack2.oreName != null) {
                if (oreStack1.oreName.equalsIgnoreCase(oreStack2.oreName)) {
                    return oreStack1.stackSize - oreStack2.stackSize;
                }
                else {
                    return oreStack1.oreName.compareToIgnoreCase(oreStack2.oreName);
                }
            }
            else {
                return -1;
            }
        }
        else {
            if (oreStack2 != null) {
                return 1;
            }
            else {
                return 0;
            }
        }
    };
}
