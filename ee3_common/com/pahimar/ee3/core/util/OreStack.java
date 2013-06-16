package com.pahimar.ee3.core.util;

import java.util.ArrayList;
import java.util.Comparator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreStack implements Comparator<OreStack> {

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
    
    public String toString() {
        return "" + stackSize + "xoreDictionary." + oreName;
    }
    
    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof OreStack)) {
            return false;
        }
        
        OreStack oreStackObject = (OreStack) object;
        
        return ((stackSize == oreStackObject.stackSize) && (oreName.equals(oreStackObject.oreName)));
    }

    @Override
    public int compare(OreStack oreStack1, OreStack oreStack2) {

        if ((oreStack1 != null) && (oreStack2 != null)) {
            if (oreStack1.oreName.equals(oreStack2.oreName)) {
                return 0;
            }
        }
        
        return -1;
    }
    
    public static boolean compareStacks(OreStack oreStack1, OreStack oreStack2) {
        
        return oreStack1.compareToStack(oreStack2);
    }
    
    public boolean compareToStack(OreStack oreStack) {
        
        return (compare(this, oreStack) == 0);
    }
}
