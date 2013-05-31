package com.pahimar.ee3.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.core.util.ItemUtil;
import com.pahimar.ee3.core.util.OreStack;

public class CustomStackWrapper {

    private ItemStack itemStack;
    private OreStack oreStack;

    public CustomStackWrapper(ItemStack itemStack) {

        this.itemStack = itemStack;
        this.oreStack = null;

        if (this.itemStack != null) {
            this.itemStack.stackSize = 1;
        }
    }

    public CustomStackWrapper(OreStack oreStack) {

        this.itemStack = null;
        this.oreStack = oreStack;

        if (this.oreStack != null) {
            this.oreStack.stackSize = 1;
        }
    }

    public int getWrappedStackSize() {

        if (itemStack != null) {
            return itemStack.stackSize;
        }
        else if (oreStack != null) {
            return oreStack.stackSize;
        }

        return -1;
    }
    
    public void setWrappedStackSize(int stackSize) {
        
        if (itemStack != null) {
            itemStack.stackSize = stackSize;
        }
        else if (oreStack != null) {
            oreStack.stackSize = stackSize;
        }
    }

    public ItemStack getItemStack() {

        return itemStack;
    }

    public OreStack getOreStack() {

        return oreStack;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CustomStackWrapper)) {
            return false;
        }

        CustomStackWrapper customWrappedStack = (CustomStackWrapper) object;

        if (itemStack != null) {
            if (customWrappedStack.itemStack != null) {
                return ItemUtil.compare(this.itemStack, customWrappedStack.itemStack);
            }
            else if (customWrappedStack.oreStack != null) {
                for (ItemStack oreDictItemStack : OreDictionary.getOres(customWrappedStack.oreStack.oreName)) {
                    if (ItemUtil.compare(itemStack, oreDictItemStack)) {
                        return true;
                    }
                }
            }
        }
        else if (oreStack != null) {
            if (customWrappedStack.itemStack != null) {
                for (ItemStack oreDictItemStack : OreDictionary.getOres(oreStack.oreName)) {
                    if (ItemUtil.compare(customWrappedStack.itemStack, oreDictItemStack)) {
                        return true;
                    }
                }
            }
            else if (customWrappedStack.oreStack != null) {
                return oreStack.equals(customWrappedStack.oreStack);
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

        if (itemStack != null) {
            hashCode = 37 * hashCode + itemStack.itemID;

            if (itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
                hashCode = 37 * hashCode;
            }
            else {
                hashCode = 37 * hashCode + itemStack.getItemDamage();
            }

            hashCode = 37 * hashCode + itemStack.stackSize;
            hashCode = 37 * hashCode + itemStack.getItemName().hashCode();
        }
        else if (oreStack != null) {
            hashCode = 37 * hashCode + oreStack.stackSize;
            hashCode = 37 * hashCode + oreStack.oreName.hashCode();
        }

        return hashCode;
    }
}
