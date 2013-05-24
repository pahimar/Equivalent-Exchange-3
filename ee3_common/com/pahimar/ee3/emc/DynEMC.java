package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import com.pahimar.ee3.core.util.ItemUtil;
import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.core.util.RecipeHelper;

public class DynEMC {

    private static final DynEMC dynEMC = new DynEMC();
    private static final ArrayList<Integer> idBlackList = new ArrayList<Integer>();
    private static final ArrayList<ItemStack> itemStackBlackList = new ArrayList<ItemStack>();
    private static final HashMap<List<Integer>, ItemStack> discoveredItems = new HashMap<List<Integer>, ItemStack>();

    private DynEMC() {

    }

    public static DynEMC getInstance() {

        return dynEMC;
    }

    public void populateBlackList() {

        // List of ids to blacklist, more to clean up here
        int[] blackints = { 7, 8, 9, 10, 11, 14, 15, 16, 21, 26, 34, 36, 43, 51, 52, 55, 56, 59, 60, 62, 63, 64, 68, 71, 73, 74, 75, 83, 90, 92, 93, 94, 95, 97, 104, 105, 115, 117, 118, 119, 120, 124, 125, 127, 129, 132, 137, 140, 141, 142, 144, 149, 150, 153 };

        for (int i : blackints) {
            idBlackList.add(i);
        }
    }

    public void init() {

        populateBlackList();

        ArrayList<ItemStack> subItems = new ArrayList<ItemStack>();

        for (int i = 0; i < Item.itemsList.length; i++) {
            if ((Item.itemsList[i] != null) && (!idBlackList.contains(i))) {
                if (Item.itemsList[i].getHasSubtypes()) {

                    subItems.clear();
                    Item.itemsList[i].getSubItems(i, Item.itemsList[i].getCreativeTab(), subItems);

                    for (ItemStack itemStack : subItems) {
                        if (itemStack != null) {
                            List<Integer> idMeta = Arrays.asList(itemStack.itemID, itemStack.getItemDamage());
                            if (!discoveredItems.containsKey(idMeta)) {
                                discoveredItems.put(idMeta, itemStack);
                            }
                        }
                    }
                }
                else {

                    ItemStack itemStack = new ItemStack(Item.itemsList[i]);
                    List<Integer> idMeta = Arrays.asList(itemStack.itemID, itemStack.getItemDamage());

                    if (!discoveredItems.containsKey(idMeta)) {
                        discoveredItems.put(idMeta, itemStack);
                    }
                }
            }
        }

        for (ItemStack itemStack : itemStackBlackList) {
            List<Integer> idMeta = Arrays.asList(itemStack.itemID, itemStack.getItemDamage());

            if (discoveredItems.containsKey(idMeta)) {
                discoveredItems.remove(idMeta);
            }
        }

        for (ItemStack itemStack : discoveredItems.values()) {
            ArrayList<IRecipe> recipes = RecipeHelper.getReverseRecipes(itemStack);
            if (recipes.size() > 0) {
                for (IRecipe recipe : recipes) {
                    LogHelper.log(Level.INFO, ItemUtil.toString(itemStack) + " <-- " + RecipeHelper.getRecipeInputs(recipe));
                }
            }
            else {
                LogHelper.log(Level.INFO, ItemUtil.toString(itemStack));
            }
        }
    }
}
