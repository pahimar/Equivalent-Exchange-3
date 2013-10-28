package com.pahimar.ee3.core.handlers;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

import com.pahimar.ee3.core.helper.GeneralHelper;
import com.pahimar.ee3.core.helper.LogHelper;

/**
 * Equivalent-Exchange-3
 * 
 * EquivalencyHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EquivalencyHandler {

    private static final EquivalencyHandler instance = new EquivalencyHandler();

    private static ArrayList<ArrayList<ItemStack>> equivalencyList = new ArrayList<ArrayList<ItemStack>>();

    public static EquivalencyHandler instance() {

        return instance;
    }

    public ArrayList<ArrayList<ItemStack>> getAllLists() {

        return equivalencyList;
    }

    public void addObjects(Object obj1, Object obj2) {

        ItemStack stack1 = GeneralHelper.convertObjectToItemStack(obj1);
        ItemStack stack2 = GeneralHelper.convertObjectToItemStack(obj2);

        ArrayList<ItemStack> currentList = new ArrayList<ItemStack>();

        Integer stack1Index = getIndexInList(stack1);
        Integer stack2Index = getIndexInList(stack2);

        if (stack1Index != null && stack2Index != null)
            return;
        else if (stack1Index != null && stack2Index == null) {
            currentList = equivalencyList.get(stack1Index.intValue());
            currentList.add(stack2);
            equivalencyList.set(stack1Index.intValue(), currentList);
        }
        else if (stack1Index == null && stack2Index != null) {
            currentList = equivalencyList.get(stack2Index.intValue());
            currentList.add(stack1);
            equivalencyList.set(stack2Index.intValue(), currentList);
        }
        else if (stack1Index == null && stack2Index == null) {
            currentList.add(stack1);
            currentList.add(stack2);
            equivalencyList.add(currentList);
        }
    }

    public void addObjects(Object... objList) {

        if (objList.length < 2)
            return;

        for (int i = 0; i < objList.length - 1; i++) {
            addObjects(objList[i], objList[i + 1]);
        }
    }

    public Integer getIndexInList(Object obj) {

        ItemStack checkStack = GeneralHelper.convertObjectToItemStack(obj);
        ArrayList<ItemStack> currentList;
        int i = 0;

        while (i < equivalencyList.size()) {
            currentList = equivalencyList.get(i);
            for (ItemStack currentStack : currentList) {
                if (ItemStack.areItemStacksEqual(checkStack, currentStack))
                    return new Integer(i);
            }
            ++i;
        }

        return null;
    }

    public Integer getIndexInList(int id, int meta) {

        ArrayList<ItemStack> currentList;
        int i = 0;

        while (i < equivalencyList.size()) {
            currentList = equivalencyList.get(i);
            for (ItemStack currentStack : currentList) {
                if (id == currentStack.itemID && meta == currentStack.getItemDamage())
                    return new Integer(i);
            }
            ++i;
        }

        return null;
    }

    public ArrayList<ItemStack> getEquivalencyList(Object obj) {

        ItemStack checkStack = GeneralHelper.convertObjectToItemStack(obj);

        if (checkStack == null)
            return null;

        for (ArrayList<ItemStack> list : equivalencyList) {
            for (ItemStack currentStack : list) {
                if (ItemStack.areItemStacksEqual(checkStack, currentStack))
                    return list;
            }
        }

        return null;
    }

    public ArrayList<ItemStack> getEquivalencyList(int id, int meta) {

        for (ArrayList<ItemStack> list : equivalencyList) {
            for (ItemStack currentStack : list) {
                if (id == currentStack.itemID && meta == currentStack.getItemDamage())
                    return list;
            }
        }

        return null;
    }

    public ItemStack getNextInList(Object obj) {

        ItemStack checkStack = GeneralHelper.convertObjectToItemStack(obj);

        if (checkStack != null)
            return getNextInList(checkStack.itemID, checkStack.getItemDamage());

        return null;
    }

    public ItemStack getNextInList(int id, int meta) {

        ArrayList<ItemStack> list = getEquivalencyList(id, meta);

        ItemStack currentStack;
        ItemStack returnStack = null;
        int i = 0;

        if (list != null) {
            if (list.size() == 1)
                return list.get(i);

            while (i < list.size()) {
                currentStack = list.get(i);

                if (id == currentStack.itemID && meta == currentStack.getItemDamage()) {
                    returnStack = list.get((i + 1) % list.size());
                    break;
                }

                ++i;
            }
        }

        return returnStack;
    }

    public ItemStack getPrevInList(Object obj) {

        ItemStack checkStack = GeneralHelper.convertObjectToItemStack(obj);

        if (checkStack != null)
            return getPrevInList(checkStack.itemID, checkStack.getItemDamage());

        return null;
    }

    public ItemStack getPrevInList(int id, int meta) {

        ArrayList<ItemStack> list = getEquivalencyList(id, meta);

        ItemStack currentStack;
        ItemStack returnStack = null;
        int i = 0;

        if (list != null) {
            if (list.size() == 1)
                return list.get(i);

            while (i < list.size()) {
                currentStack = list.get(i);

                if (id == currentStack.itemID && meta == currentStack.getItemDamage()) {
                    int index = (i - 1 + list.size()) % list.size();
                    returnStack = list.get(index);
                    break;
                }

                ++i;
            }
        }

        return returnStack;
    }

    public boolean areEquivalent(Object obj1, Object obj2) {

        if (getEquivalencyList(obj1) != null && getEquivalencyList(obj2) != null) {
            // TODO This could be cleaner
            if (GeneralHelper.convertObjectToItemStack(obj1).itemID == GeneralHelper.convertObjectToItemStack(obj2).itemID && GeneralHelper.convertObjectToItemStack(obj1).getItemDamage() == GeneralHelper.convertObjectToItemStack(obj2).getItemDamage())
                return true;
            else
                return getEquivalencyList(obj1).equals(getEquivalencyList(obj2));
        }
        else
            return false;
    }

    /* Ignores stack size for world transmutation */
    public boolean areWorldEquivalent(Object obj1, Object obj2) {

        ItemStack first = GeneralHelper.convertObjectToItemStack(obj1);
        if (first == null)
            return false;
        ItemStack second = GeneralHelper.convertObjectToItemStack(obj2);
        if (second == null)
            return false;

        if (getEquivalencyList(first.itemID, first.getItemDamage()) != null && getEquivalencyList(second.itemID, second.getItemDamage()) != null) {
            if (first.itemID == second.itemID && first.getItemDamage() == second.getItemDamage())
                return true;
            else
                return getEquivalencyList(first.itemID, first.getItemDamage()).equals(getEquivalencyList(second.itemID, second.getItemDamage()));
        }
        else
            return false;
    }

    public void debug() {

        int i = 0;
        for (ArrayList<ItemStack> list : equivalencyList) {
            LogHelper.info("equivalencyList[" + i + "]: " + list.toString());
            ++i;
        }
    }
}
