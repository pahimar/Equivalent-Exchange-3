package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.core.util.ItemUtil;
import com.pahimar.ee3.core.util.LogHelper;
import com.pahimar.ee3.core.util.RecipeHelper;
import com.pahimar.ee3.item.CustomStackWrapper;

public class DynEMC {

    private static final DynEMC dynEMC = new DynEMC();

    private static final ArrayList<CustomStackWrapper> discoveredItems = new ArrayList<CustomStackWrapper>();

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

        for (CustomStackWrapper customStackWrapper : EMCBlackList.getInstance().getBlackListStacks()) {

            while (discoveredItems.contains(customStackWrapper)) {
                discoveredItems.remove(customStackWrapper);
            }
        }

        for (CustomStackWrapper customStackWrapper : discoveredItems) {
            ArrayList<IRecipe> recipes = RecipeHelper.getReverseRecipes(customStackWrapper.itemStack);
            if (recipes.size() > 0) {
                for (IRecipe recipe : recipes) {
                    LogHelper.log(Level.INFO, ItemUtil.toString(customStackWrapper.itemStack) + " <-- " + RecipeHelper.getRecipeInputs(recipe));
                }
            }
            else {
                LogHelper.log(Level.INFO, ItemUtil.toString(customStackWrapper.itemStack));
            }
        }
    }
}
