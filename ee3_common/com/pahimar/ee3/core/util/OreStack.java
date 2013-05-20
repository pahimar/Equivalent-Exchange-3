package com.pahimar.ee3.core.util;

import java.util.ArrayList;
import java.util.Comparator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreStack implements Comparator<OreStack> {

    private String oreName;
    public int stackSize;
    
    public OreStack() {
        
        stackSize = 0;
        oreName = null;
    }
    
    public OreStack(String oreName) {
        
        this.oreName = oreName;
        stackSize = 1;
    }
    
    public OreStack(int oreID) {
        
        this(OreDictionary.getOreName(oreID));
    }
    
    public OreStack(ItemStack itemStack) {
        
        this(OreDictionary.getOreID(itemStack));
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
