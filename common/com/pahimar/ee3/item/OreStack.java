package com.pahimar.ee3.item;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreStack implements Comparable<OreStack> {

    public int oreId;
    public String oreName;
    public int stackSize;

    public OreStack(String oreName, int stackSize) {
        
        if (oreName != null && oreName.length() > 0) {
            
            if (this.oreName == null) {
                
                for (String oreDictionaryName : OreDictionary.getOreNames()) {
                    
                    if (oreDictionaryName.equalsIgnoreCase(oreName)) {
                        
                        this.oreId = OreDictionary.getOreID(oreDictionaryName);
                        this.oreName = oreDictionaryName;
                    }
                }
            }
        }
        else {
            
            this.oreId = -1;
            this.oreName = OreDictionary.getOreName(oreId);
        }
        
        this.stackSize = stackSize;
    }

    public OreStack(String oreName) {

        this(oreName, 1);
    }

    public OreStack(int oreId) {

        this(oreId, 1);
    }
    
    public OreStack(int oreId, int stackSize) {
        
        this(OreDictionary.getOreName(oreId), stackSize);
    }

    public OreStack(ItemStack itemStack) {

        this(OreDictionary.getOreID(itemStack), itemStack.stackSize);
    }

    public ArrayList<ItemStack> getOres() {

        if (this.oreId != -1) {
            return OreDictionary.getOres(this.oreId);
        }
        
        return new ArrayList<ItemStack>();
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%dxoreDictionary.%s[%s]", stackSize, oreName, oreId));

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof OreStack))
            return false;

        OreStack oreStackObject = (OreStack) object;

        return (compareTo(oreStackObject) == 0);
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
            
            if (this.oreId != oreStack.oreId) {
                if (this.oreId > oreStack.oreId) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else {
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
        }
        else {
            return 1;
        }
    }
}
