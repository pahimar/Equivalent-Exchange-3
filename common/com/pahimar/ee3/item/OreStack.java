package com.pahimar.ee3.item;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreStack implements Comparable<OreStack> {

    public String oreName;
    public int stackSize;

    public OreStack(String oreName, int stackSize) {

        this.oreName = oreName;
        this.stackSize = stackSize;
    }

    public OreStack() {

        this(null, 0);
    }

    public OreStack(String oreName) {

        this(oreName, 0);
    }

    public OreStack(int oreID) {

        if (oreID != -1) {
            this.oreName = OreDictionary.getOreName(oreID);
        }
        else {
            this.oreName = null;
        }

        this.stackSize = 0;
    }

    public OreStack(int oreID, int stackSize) {

        if (oreID != -1) {
            this.oreName = OreDictionary.getOreName(oreID);
        }
        else {
            this.oreName = null;
        }

        this.stackSize = stackSize;
    }

    public OreStack(ItemStack itemStack) {

        this(OreDictionary.getOreID(itemStack), itemStack.stackSize);
    }

    public ArrayList<ItemStack> getOres() {

        return OreDictionary.getOres(oreName);
    }

    public int getOreID() {

        return OreDictionary.getOreID(oreName);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%dxoreDictionary.%s", stackSize, oreName));

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof OreStack))
            return false;

        OreStack oreStackObject = (OreStack) object;

        if ((this.oreName != null) && (oreStackObject.oreName != null)) {
            return (stackSize == oreStackObject.stackSize) && oreName.equalsIgnoreCase(oreStackObject.oreName);
        }
        else if ((this.oreName == null) && (oreStackObject.oreName == null)) {
            return (stackSize == oreStackObject.stackSize);
        }
        else {
            return false;
        }
    }

    public static boolean compareOreNames(OreStack oreStack1, OreStack oreStack2) {

        if (oreStack1 != null && oreStack2 != null) {
            if ((oreStack1.oreName != null) && (oreStack2.oreName != null)) {
                return oreStack1.oreName.equalsIgnoreCase(oreStack2.oreName);
            }
        }

        return false;
    }

    @Override
    public int compareTo(OreStack oreStack) {

        if (oreStack != null) {
            if ((this.oreName != null) && (oreStack.oreName != null)) {
                if (this.oreName.equalsIgnoreCase(oreStack.oreName)) {
                    return (this.stackSize - oreStack.stackSize);
                }
                else {
                    return this.oreName.compareToIgnoreCase(oreStack.oreName);
                }
            }
            else if ((this.oreName != null) && (oreStack.oreName == null)) {
                return 1;
            }
            else if ((this.oreName == null) && (oreStack.oreName != null)) {
                return -1;
            }
            else {
                return (this.stackSize - oreStack.stackSize);
            }
        }
        else {
            return 1;
        }
    }
}
