package com.pahimar.ee3.item;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.core.helper.ItemHelper;
import com.pahimar.ee3.lib.Reference;

public class CustomWrappedStack implements Comparable<CustomWrappedStack> {

    private int stackSize;
    private final ItemStack itemStack;
    private final OreStack oreStack;
    private final EnergyStack energyStack;

    /**
     * Creates a new CustomWrappedStack object which wraps the given input.
     * Valid inputs would be ItemStacks or OreStacks. If something other than an
     * ItemStack or an OreStack is used as input, nothing is wrapped and the
     * size of the wrapped stack is set to -1 to indicate an invalid wrapped
     * stack.
     * 
     * @param object
     *            The newly created wrapped stack object
     */
    public CustomWrappedStack(Object object) {

        /*
         * If we are given an Item or a Block, convert it to an ItemStack for
         * further inspection
         */
        if (object instanceof Item) {
            object = new ItemStack((Item) object);
        }
        else if (object instanceof Block) {
            object = new ItemStack((Block) object);
        }

        /*
         * We are given an ItemStack to wrap
         */
        if (object instanceof ItemStack) {

            ItemStack itemStack = (ItemStack) object;

            /*
             * If the ItemStack does not exist in the OreDictionary, wrap it as
             * an ItemStack
             */
            if (OreDictionary.getOreID(itemStack) == Reference.ORE_DICTIONARY_NOT_FOUND) {
                this.itemStack = itemStack.copy();
                oreStack = null;
                energyStack = null;
                stackSize = this.itemStack.stackSize;
                this.itemStack.stackSize = 1;
            }
            /*
             * Else the ItemStack exists in the OreDictionary, so wrap it as an
             * OreStack instead of an ItemStack
             */
            else {
                this.itemStack = null;
                oreStack = new OreStack(itemStack);
                energyStack = null;
                stackSize = oreStack.stackSize;
                oreStack.stackSize = 1;
            }
        }
        /*
         * Or we are given an OreStack to wrap
         */
        else if (object instanceof OreStack) {

            itemStack = null;
            oreStack = (OreStack) object;
            energyStack = null;
            stackSize = oreStack.stackSize;
            oreStack.stackSize = 1;
        }
        else if (object instanceof ArrayList) {

            itemStack = null;

            ArrayList<?> objectList = (ArrayList<?>) object;

            OreStack tempOreStack = getOreStackFromList(objectList);

            if (tempOreStack != null) {
                oreStack = new OreStack(tempOreStack.oreName, 1);
                stackSize = tempOreStack.stackSize;
            }
            else {
                oreStack = null;
                stackSize = -1;
            }

            energyStack = null;
        }
        /*
         * Or we are given an EnergyStack to wrap
         */
        else if (object instanceof EnergyStack) {
            itemStack = null;
            oreStack = null;
            energyStack = (EnergyStack) object;
            stackSize = energyStack.stackSize;
            energyStack.stackSize = 1;
        }
        else if (object instanceof CustomWrappedStack) {
            CustomWrappedStack wrappedStack = (CustomWrappedStack) object;

            itemStack = wrappedStack.itemStack;
            oreStack = wrappedStack.oreStack;
            energyStack = wrappedStack.energyStack;
            stackSize = wrappedStack.stackSize;
        }
        /*
         * Else, we are given something we cannot wrap
         */
        else {
            itemStack = null;
            oreStack = null;
            energyStack = null;
            stackSize = -1;
        }
    }

    /**
     * Returns the stack size of the wrapped stack, or -1 if we wrapped an
     * invalid input
     * 
     * @return The size of the wrapped stack
     */
    public int getStackSize() {

        return stackSize;
    }

    /**
     * Sets the size of the wrapped stack
     * 
     * @param stackSize
     *            The new size of the wrapped stack
     */
    public void setStackSize(int stackSize) {

        this.stackSize = stackSize;
    }

    /**
     * Returns the wrapped stack
     * 
     * @return The wrapped ItemStack, OreStack, or EnergyStack, or null if
     *         something other than an ItemStack, OreStack, or EnergyStack was
     *         used to create this object
     */
    public Object getWrappedStack() {

        if (itemStack != null)
            return itemStack;
        else if (oreStack != null)
            return oreStack;
        else if (energyStack != null)
            return energyStack;

        return null;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CustomWrappedStack)) {
            return false;
        }

        CustomWrappedStack customWrappedStack = (CustomWrappedStack) object;

        if ((this.getWrappedStack() instanceof ItemStack) && (customWrappedStack.getWrappedStack() instanceof ItemStack)) {
            return (ItemHelper.compare(itemStack, customWrappedStack.itemStack) && (stackSize == customWrappedStack.itemStack.stackSize));
        }
        else if ((this.getWrappedStack() instanceof OreStack) && (customWrappedStack.getWrappedStack() instanceof OreStack)) {
            return (oreStack.equals(customWrappedStack.getWrappedStack()) && (stackSize == customWrappedStack.stackSize));
        }
        else if ((this.getWrappedStack() instanceof EnergyStack) && (customWrappedStack.getWrappedStack() instanceof EnergyStack)) {
            return (energyStack.equals(customWrappedStack.energyStack) && (stackSize == customWrappedStack.stackSize));
        }

        return false;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        if (itemStack != null) {
            try {
                stringBuilder.append(String.format("%sxitemStack[%s:%s:%s:%s]", stackSize, itemStack.itemID, itemStack.getItemDamage(), itemStack.getUnlocalizedName(), itemStack.getItem().getClass().getCanonicalName()));
            } catch (ArrayIndexOutOfBoundsException e) { 
                
            }
        }
        else if (oreStack != null) {
            stringBuilder.append(String.format("%dxoreDictionary.%s", stackSize, oreStack.oreName));
        }
        else if (energyStack != null) {
            stringBuilder.append(String.format("%dxenergyStack.%s", stackSize, energyStack.energyName));
        }
        else {
            stringBuilder.append("null");
        }

        return stringBuilder.toString();
    }

    public String encodeAsPropertyKey() {

        StringBuilder stringBuilder = new StringBuilder();

        if (itemStack != null) {
            stringBuilder.append(String.format("%sxitemStack[%s:%s:%s:%s]", stackSize, itemStack.itemID, itemStack.getItemDamage(), itemStack.getUnlocalizedName(), itemStack.getItem().getClass().getCanonicalName()));
        }
        else if (oreStack != null) {
            stringBuilder.append(String.format("%dxoreDictionary.%s", stackSize, oreStack.oreName));
        }
        else if (energyStack != null) {
            stringBuilder.append(String.format("%dxenergyStack.%s", stackSize, energyStack.energyName));
        }

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {

        int hashCode = 1;

        hashCode = 37 * hashCode + stackSize;

        if (itemStack != null) {
            hashCode = 37 * hashCode + itemStack.itemID;
            hashCode = 37 * hashCode + itemStack.getItemDamage();
        }
        else if (oreStack != null) {
            if (oreStack.oreName != null) {
                hashCode = 37 * hashCode + oreStack.oreName.hashCode();
            }
        }
        else if (energyStack != null) {
            if (energyStack.energyName != null) {
                hashCode = 37 * hashCode + energyStack.energyName.hashCode();
            }
        }

        return hashCode;
    }

    public static boolean canBeWrapped(Object object) {

        return object instanceof CustomWrappedStack || object instanceof ItemStack || object instanceof OreStack || object instanceof EnergyStack || object instanceof Item || object instanceof Block;
    }

    @Override
    /*
     * Sort order (class-wise) goes null, EnergyStack, OreStack, ItemStack
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(CustomWrappedStack customWrappedStack) {

        if (this.getWrappedStack() instanceof EnergyStack) {

            if (customWrappedStack.getWrappedStack() instanceof EnergyStack) {
                if (energyStack.equals(customWrappedStack.energyStack))
                    return stackSize - customWrappedStack.stackSize;
                else
                    return energyStack.compareTo(customWrappedStack.energyStack);
            }
            else if (customWrappedStack.getWrappedStack() instanceof OreStack)
                return -1;
            else if (customWrappedStack.getWrappedStack() instanceof ItemStack)
                return -1;
            else
                return 1;
        }
        else if (this.getWrappedStack() instanceof OreStack) {

            if (customWrappedStack.getWrappedStack() instanceof EnergyStack)
                return 1;
            else if (customWrappedStack.getWrappedStack() instanceof OreStack) {
                if (oreStack.equals(customWrappedStack.oreStack))
                    return stackSize - customWrappedStack.stackSize;
                else
                    return oreStack.compareTo(customWrappedStack.oreStack);
            }
            else if (customWrappedStack.getWrappedStack() instanceof ItemStack)
                return -1;
            else
                return 1;
        }
        else if (this.getWrappedStack() instanceof ItemStack) {

            if (customWrappedStack.getWrappedStack() instanceof EnergyStack)
                return 1;
            else if (customWrappedStack.getWrappedStack() instanceof OreStack)
                return 1;
            else if (customWrappedStack.getWrappedStack() instanceof ItemStack) {
                if (ItemHelper.compare(itemStack, customWrappedStack.itemStack))
                    return stackSize - customWrappedStack.stackSize;
                else
                    return ItemHelper.ItemStackComparator.compare(itemStack, customWrappedStack.itemStack);
            }
            else
                return 1;
        }
        else {
            if (customWrappedStack.getWrappedStack() != null)
                return -1;
            else
                return 0;
        }
    }

    private OreStack getOreStackFromList(ArrayList<?> objectList) {

        for (Object listElement : objectList) {
            if (listElement instanceof ItemStack) {
                ItemStack stack = (ItemStack) listElement;

                if (OreDictionary.getOreID(stack) != Reference.ORE_DICTIONARY_NOT_FOUND) {
                    return new OreStack(stack);
                }
            }
        }

        return null;
    }
}
