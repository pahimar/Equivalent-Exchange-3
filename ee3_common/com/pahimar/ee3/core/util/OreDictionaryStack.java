package com.pahimar.ee3.core.util;

import java.util.ArrayList;
import java.util.Comparator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryStack implements Comparator<OreDictionaryStack> {

    private String oreDictionaryName;
    public int stackSize;
    
    public OreDictionaryStack() {
        
        stackSize = 0;
        oreDictionaryName = null;
    }
    
    public OreDictionaryStack(String oreDictionaryName) {
        
        this.oreDictionaryName = oreDictionaryName;
        stackSize = 1;
    }
    
    public OreDictionaryStack(int oreID) {
        
        this(OreDictionary.getOreName(oreID));
    }
    
    public OreDictionaryStack(ItemStack itemStack) {
        
        this(OreDictionary.getOreID(itemStack));
    }
    
    public ArrayList<ItemStack> getOres() {
        
        return OreDictionary.getOres(oreDictionaryName);
    }
    
    public int getOreID() {
        
        return OreDictionary.getOreID(oreDictionaryName);
    }
    
    public String toString() {
        return "" + stackSize + "xoreDictionary." + oreDictionaryName;
    }

    @Override
    public int compare(OreDictionaryStack oreStack1, OreDictionaryStack oreStack2) {

        if ((oreStack1 != null) && (oreStack2 != null)) {
            if (oreStack1.oreDictionaryName.equals(oreStack2.oreDictionaryName)) {
                return 0;
            }
        }
        
        return -1;
    }
    
    public static boolean compareStacks(OreDictionaryStack oreStack1, OreDictionaryStack oreStack2) {
        
        return oreStack1.compareToStack(oreStack2);
    }
    
    public boolean compareToStack(OreDictionaryStack oreStack) {
        
        return (compare(this, oreStack) == 0);
    }
}
