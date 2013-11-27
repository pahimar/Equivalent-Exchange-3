package com.pahimar.ee3.item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pahimar.ee3.lib.Compare;

public class OreStack implements Comparable<OreStack> {

    // Gson serializer for serializing to/deserializing from json
    private static final Gson gsonSerializer = new Gson();
    
    private static final int ORE_DICTIONARY_NOT_FOUND = -1;
    
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
                
                if (this.oreName == null) {
                    this.oreId = -1;
                    this.oreName = OreDictionary.getOreName(oreId);
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
        return String.format("%sxoreDictionary.%s[oreId:%s]", stackSize, oreName, oreId);
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof OreStack))
            return false;

        return (comparator.compare(this, (OreStack) object) == Compare.EQUALS);
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

        return comparator.compare(this, oreStack);
    }
    
    /**
     * Deserializes a OreStack object from the given serialized json
     * String
     * 
     * @param jsonEmcValue
     *            Json encoded String representing a OreStack object
     * @return The OreStack that was encoded as json, or null if a valid
     *         OreStack could not be decoded from given String
     */
    public static OreStack createFromJson(String jsonOreStack) {

        try {
            return (OreStack) gsonSerializer.fromJson(jsonOreStack, OreStack.class);
        }
        catch (JsonSyntaxException exception) {
            // TODO Log something regarding the failed parse
        }

        return null;
    }
    
    /**
     * Returns this OreStack as a json serialized String
     * 
     * @return Json serialized String of this OreStack
     */
    public String toJson() {

        return gsonSerializer.toJson(this);
    }
    
    public static OreStack getOreStackFromList(List<?> objectList) {

        for (Object listElement : objectList) {
            if (listElement instanceof ItemStack) {
                ItemStack stack = (ItemStack) listElement;

                if (OreDictionary.getOreID(stack) != ORE_DICTIONARY_NOT_FOUND) {
                    return new OreStack(stack);
                }
            }
        }

        return null;
    }
    
    public static int compare(OreStack oreStack1, OreStack oreStack2) {
        return comparator.compare(oreStack1, oreStack2);
    }
    
    public static Comparator<OreStack> comparator = new Comparator<OreStack>() {

        @Override
        public int compare(OreStack oreStack1, OreStack oreStack2) {
            
            if (oreStack1 != null) {
                if (oreStack2 != null) {
                    if (oreStack1.oreId == oreStack2.oreId) {
                        if (oreStack1.oreName.equalsIgnoreCase(oreStack2.oreName)) {
                            return oreStack1.stackSize - oreStack2.stackSize;
                        }
                        else {
                            return oreStack1.oreName.compareToIgnoreCase(oreStack2.oreName);
                        }
                    }
                    else {
                        return oreStack1.oreId - oreStack2.oreId;
                    }
                }
                else {
                    return Compare.LESSER_THAN;
                }
            }
            else {
                if (oreStack2 != null) {
                    return Compare.GREATER_THAN;
                }
                else {
                    return Compare.EQUALS;
                }
            }
        }
        
    };
}
