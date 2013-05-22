package com.pahimar.ee3.emc;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class DynEMC {
    
    private static final DynEMC dynEMC = new DynEMC();
    private static final ArrayList<ItemStack> discoveredItemStacks = new ArrayList<ItemStack>();
    
    private DynEMC() {
        
    }
    
    public static DynEMC getInstance() {
        
        return dynEMC;
    }

    public void init() {
        
        ArrayList<ItemStack> subItems = new ArrayList<ItemStack>();
        
        for (int i = 0; i < Item.itemsList.length; i++) {
            if (Item.itemsList[i] != null) {
                if (Item.itemsList[i].getHasSubtypes()) {
                    
                    subItems.clear();
                    Item.itemsList[i].getSubItems(i, Item.itemsList[i].getCreativeTab(), subItems);
                    
                    for (ItemStack itemStack : subItems) {
                        if (itemStack != null) {
                            if (!discoveredItemStacks.contains(itemStack)) {
                                discoveredItemStacks.add(itemStack);
                            }
                        }
                    }
                }
                else {
                    
                    ItemStack itemStack = new ItemStack(Item.itemsList[i]);
                    
                    if (!discoveredItemStacks.contains(itemStack)) {
                        discoveredItemStacks.add(itemStack);
                    }
                }
            }
        }
    }
}
