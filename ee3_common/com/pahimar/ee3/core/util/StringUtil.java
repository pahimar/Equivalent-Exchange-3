package com.pahimar.ee3.core.util;

import net.minecraft.item.ItemStack;


public class StringUtil {

    public static String toString(ItemStack itemStack) {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(String.format("itemID: %d, metaData: %d, stackSize: %d, itemName: %s, className: %s", itemStack.itemID, itemStack.getItemDamage(), itemStack.stackSize, itemStack.getItemName(), itemStack.getItem().getClass().toString()));
        
        return stringBuilder.toString();
    }
}
