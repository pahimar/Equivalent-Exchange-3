package com.pahimar.ee3.item;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class OreStack implements Comparable<OreStack> {

    // Gson serializer for serializing to/deserializing from json
    private static final Gson gsonSerializer = new Gson();
    
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

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%dxoreDictionary.%s[oreId:%s]", stackSize, oreName, oreId));

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
}
