package com.pahimar.ee3.core.util;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreStack implements Comparable<OreStack> {

    public String oreName;
    public int stackSize;

    public OreStack() {

        stackSize = 0;
        oreName = null;
    }

    public OreStack(String oreName, int stackSize) {

        this.oreName = oreName;
        this.stackSize = stackSize;
    }

    public OreStack(String oreName) {

        this(oreName, 1);
    }

    public OreStack(int oreID) {

        this(OreDictionary.getOreName(oreID));
    }

    public OreStack(int oreID, int stackSize) {

        this(OreDictionary.getOreName(oreID), stackSize);
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

        return stackSize == oreStackObject.stackSize && oreName.equalsIgnoreCase(oreStackObject.oreName);
    }

    public static boolean compareOreNames(OreStack oreStack1, OreStack oreStack2) {

    	if (oreStack1 != null && oreStack2 != null) {
            if (oreStack1.oreName.equalsIgnoreCase(oreStack2.oreName))
                return true;
        }

        return false;
    }

	@Override
	public int compareTo(OreStack oreStack) {
		
		if (this.oreName.equalsIgnoreCase(oreStack.oreName)) {
			return (this.stackSize - oreStack.stackSize);
		}
		else {
			return this.oreName.toLowerCase().compareTo(oreStack.oreName.toLowerCase());
		}
	}
}
