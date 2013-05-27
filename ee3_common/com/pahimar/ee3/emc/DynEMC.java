package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.item.CustomStackWrapper;

public class DynEMC {

    private static DynEMC dynEMC = null;

    private ArrayList<CustomStackWrapper> discoveredItems = new ArrayList<CustomStackWrapper>();

    private DynEMC() {

    }

    public static DynEMC getInstance() {

        if (dynEMC == null) {
            dynEMC = new DynEMC();
            dynEMC.init();
        }
        
        return dynEMC;
    }
    
    public List<CustomStackWrapper> getDiscoveredItems() {
        
        return discoveredItems;
    }

    private void init() {

        ArrayList<ItemStack> subItems = new ArrayList<ItemStack>();

        /*
         * For every possible item (and sub item), add them to the discovered items list
         */
        for (int i = 0; i < Item.itemsList.length; i++) {
            if (Item.itemsList[i] != null) {
                if (Item.itemsList[i].getHasSubtypes()) {

                    subItems.clear();
                    Item.itemsList[i].getSubItems(i, Item.itemsList[i].getCreativeTab(), subItems);

                    for (ItemStack itemStack : subItems) {
                        if (itemStack != null) {

                            CustomStackWrapper customStackWrapper = new CustomStackWrapper(itemStack);
                            if (!discoveredItems.contains(customStackWrapper)) {
                                discoveredItems.add(customStackWrapper);
                            }
                        }
                    }
                }
                else {

                    ItemStack itemStack = new ItemStack(Item.itemsList[i]);
                    
                    CustomStackWrapper customStackWrapper = new CustomStackWrapper(itemStack);
                    if (!discoveredItems.contains(customStackWrapper)) {
                        discoveredItems.add(customStackWrapper);
                    }
                }
            }
        }

        /**
         * Now that we have discovered as many items as possible, trim out the items that are black listed
         */
        for (CustomStackWrapper customStackWrapper : EmcBlackList.getInstance().getBlackListStacks()) {

            while (discoveredItems.contains(customStackWrapper)) {
                discoveredItems.remove(customStackWrapper);
            }
        }
    }
}
