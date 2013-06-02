package com.pahimar.ee3.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.core.util.ItemUtil;
import com.pahimar.ee3.core.util.OreStack;

public class CustomWrappedStack {

    private int stackSize;
    private ItemStack itemStack;
    private OreStack oreStack;
    
    public CustomWrappedStack(ItemStack itemStack) {

        this.itemStack = itemStack;
        this.oreStack = null;
        stackSize = itemStack.stackSize;

        if (this.itemStack != null) {
            this.itemStack.stackSize = 1;
        }
    }

    public CustomWrappedStack(OreStack oreStack) {

        this.itemStack = null;
        this.oreStack = oreStack;
        stackSize = oreStack.stackSize;

        if (this.oreStack != null) {
            this.oreStack.stackSize = 1;
        }
    }

    public int getStackSize() {

        return stackSize;
    }
    
    public void setStackSize(int stackSize) {
        
        this.stackSize = stackSize;
    }

    public ItemStack getItemStack() {

        return itemStack;
    }

    public OreStack getOreStack() {

        return oreStack;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CustomWrappedStack)) {
            return false;
        }

        CustomWrappedStack customWrappedStack = (CustomWrappedStack) object;

        if (itemStack != null) {
            if (customWrappedStack.itemStack != null) {
                return (ItemUtil.compare(this.itemStack, customWrappedStack.itemStack) && stackSize == customWrappedStack.itemStack.stackSize);
            }
            else if (customWrappedStack.oreStack != null) {
                for (ItemStack oreDictItemStack : OreDictionary.getOres(customWrappedStack.oreStack.oreName)) {
                    if (ItemUtil.compare(itemStack, oreDictItemStack) && stackSize == oreDictItemStack.stackSize) {
                        return true;
                    }
                }
            }
        }
        else if (oreStack != null) {
            if (customWrappedStack.itemStack != null) {
                for (ItemStack oreDictItemStack : OreDictionary.getOres(oreStack.oreName)) {
                    if (ItemUtil.compare(customWrappedStack.itemStack, oreDictItemStack) && stackSize == oreDictItemStack.stackSize) {
                        return true;
                    }
                }
            }
            else if (customWrappedStack.oreStack != null) {
                return (oreStack.equals(customWrappedStack.oreStack) && stackSize == oreStack.stackSize);
            }
        }

        return false;
    }

    @Override
    public String toString() {

        if (itemStack != null) {
            return ItemUtil.toString(itemStack);
        }
        else if (oreStack != null) {
            return oreStack.toString();
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
