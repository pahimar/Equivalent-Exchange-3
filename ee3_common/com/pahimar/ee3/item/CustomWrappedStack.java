package com.pahimar.ee3.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.core.util.ItemUtil;
import com.pahimar.ee3.core.util.OreStack;

public class CustomWrappedStack {

    private int stackSize;
    private ItemStack itemStack;
    private OreStack oreStack;

    /**
     * Creates a new CustomWrappedStack object which wraps the given input. Valid inputs would be ItemStacks or OreStacks.
     * If something other than an ItemStack or an OreStack is used as input, nothing is wrapped and the size of the wrapped
     * stack is set to -1 to indicate an invalid wrapped stack.
     * 
     * @param object The newly created wrapped stack object
     */
    public CustomWrappedStack(Object object) {

        /*
         * We are given an ItemStack to wrap
         */
        if (object instanceof ItemStack) {

            ItemStack itemStack = (ItemStack) object;
            
            /*
             * If the ItemStack does not exist in the OreDictionary, wrap it as an ItemStack
             */
            if (OreDictionary.getOreID(itemStack) == -1) {
                this.itemStack = itemStack;
                oreStack = null;
                stackSize = itemStack.stackSize;
                this.itemStack.stackSize = 1;
            }
            /*
             * Else the ItemStack exists in the OreDictionary, so wrap it as an OreStack instead of an ItemStack
             */
            else {
                this.itemStack = null;
                oreStack = new OreStack(itemStack);
                stackSize = oreStack.stackSize;
                oreStack.stackSize = 1;
            }
        }
        /*
         * We are given an OreStack to wrap
         */
        else if (object instanceof OreStack) {

            itemStack = null;
            oreStack = (OreStack) object;
            stackSize = oreStack.stackSize;
            oreStack.stackSize = 1;
        }
        /*
         * Else, we are given something we cannot wrap
         */
        else {
            stackSize = -1;
        }
    }

    /**
     * Returns the stack size of the wrapped stack, or -1 if we wrapped an invalid input
     * 
     * @return The size of the wrapped stack
     */
    public int getStackSize() {

        return stackSize;
    }

    /**
     * Sets the size of the wrapped stack
     * 
     * @param stackSize The new size of the wrapped stack
     */
    public void setStackSize(int stackSize) {

        this.stackSize = stackSize;
    }

    /**
     * Returns the wrapped stack
     * 
     * @return The wrapped ItemStack or OreStack, or null if something other than an ItemStack or OreStack was used to create this object
     */
    public Object getWrappedStack() {

        if (itemStack != null) {
            return itemStack;
        }
        else if (oreStack != null) {
            return oreStack;
        }
        
        return null;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CustomWrappedStack))
            return false;

        CustomWrappedStack customWrappedStack = (CustomWrappedStack) object;

        if (itemStack != null) {
            if (customWrappedStack.itemStack != null)
                return ItemUtil.compare(itemStack, customWrappedStack.itemStack) && stackSize == customWrappedStack.itemStack.stackSize;
            else if (customWrappedStack.oreStack != null) {
                for (ItemStack oreDictItemStack : OreDictionary.getOres(customWrappedStack.oreStack.oreName)) {
                    if (ItemUtil.compare(itemStack, oreDictItemStack) && stackSize == oreDictItemStack.stackSize)
                        return true;
                }
            }
        }
        else if (oreStack != null) {
            if (customWrappedStack.itemStack != null) {
                for (ItemStack oreDictItemStack : OreDictionary.getOres(oreStack.oreName)) {
                    if (ItemUtil.compare(customWrappedStack.itemStack, oreDictItemStack) && stackSize == oreDictItemStack.stackSize)
                        return true;
                }
            }
            else if (customWrappedStack.oreStack != null)
                return oreStack.equals(customWrappedStack.oreStack) && stackSize == oreStack.stackSize;
        }

        return false;
    }

    @Override
    public String toString() {

        if (itemStack != null) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(String.format("itemID: %d, metaData: %d, stackSize: %d, ", itemStack.itemID, itemStack.getItemDamage(), stackSize));
            
            if (itemStack.hasTagCompound()) {
                stringBuilder.append(String.format("nbtTagCompound: %s, ", itemStack.getTagCompound().toString()));
            }
            
            stringBuilder.append(String.format("itemName: %s, className: %s ", itemStack.getItemName(), itemStack.getItem().getClass().toString()));

            return stringBuilder.toString();
        }
        else if (oreStack != null) {
            return stackSize + "xoreDictionary." + oreStack.oreName;
        }

        return null;
    }

    @Override
    public int hashCode() {

        int hashCode = 1;

        hashCode = 37 * hashCode + stackSize;

        if (itemStack != null) {
            hashCode = 37 * hashCode + itemStack.itemID;

            if (itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
                hashCode = 37 * hashCode;
            }
            else {
                hashCode = 37 * hashCode + itemStack.getItemDamage();
            }

            hashCode = 37 * hashCode + itemStack.getItemName().hashCode();
        }
        else if (oreStack != null) {
            hashCode = 37 * hashCode + oreStack.oreName.hashCode();
        }

        return hashCode;
    }
}
