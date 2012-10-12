package ee3.common.core.handlers;

import java.util.ArrayList;

import ee3.common.core.helper.GeneralHelper;
import net.minecraft.src.ItemStack;

public class EquivalencyHandler {

    public static ArrayList<ArrayList<ItemStack>> equivalencyList = new ArrayList<ArrayList<ItemStack>>();

    public static void addObjectToEquivalencyList(Object obj1, Object obj2) {
        ItemStack stack1 = GeneralHelper.convertObjectToItemStack(obj1);
        ItemStack stack2 = GeneralHelper.convertObjectToItemStack(obj2);

        ArrayList<ItemStack> currentList = new ArrayList<ItemStack>();

        Integer stack1Index = getEquivalencyIndexForItem(stack1);
        Integer stack2Index = getEquivalencyIndexForItem(stack2);

        if ((stack1Index != null) && (stack2Index != null)) {
            return;
        } 
        else if ((stack1Index != null) && (stack2Index == null)) {
            currentList = equivalencyList.get(stack1Index.intValue());
            currentList.add(stack2);
            equivalencyList.set(stack1Index.intValue(), currentList);
        }
        else if ((stack1Index == null) && (stack2Index != null)) {
            currentList = equivalencyList.get(stack2Index.intValue());
            currentList.add(stack1);
            equivalencyList.set(stack2Index.intValue(), currentList);
        }
        else if ((stack1Index == null) && (stack2Index == null)) {
            currentList.add(stack1);
            currentList.add(stack2);
            equivalencyList.add(currentList);
        }
    }

    public static void addObjectsToEquivalencyLists(Object... objList) {
        if (objList.length < 2)
            return;

        for (int i = 0; i < objList.length - 1; i++) {
            addObjectToEquivalencyList(objList[i], objList[i + 1]);
        }
    }

    public static Integer getEquivalencyIndexForItem(Object obj) {
        ItemStack checkStack = GeneralHelper.convertObjectToItemStack(obj);
        ArrayList<ItemStack> currentList;
        int i = 0;

        while (i < equivalencyList.size()) {
            currentList = equivalencyList.get(i);
            for (ItemStack currentStack : currentList) {
                if (checkStack.isStackEqual(currentStack)) {
                    return new Integer(i);
                }
            }
            ++i;
        }

        return null;
    }

    public static ArrayList<ItemStack> getEquivalencyListForItem(Object obj) {
        ItemStack checkStack = GeneralHelper.convertObjectToItemStack(obj);

        if (checkStack == null)
            return null;

        for (ArrayList<ItemStack> list : equivalencyList) {
            for (ItemStack currentStack : list) {
                if (checkStack.isStackEqual(currentStack)) {
                    return list;
                }
            }
        }

        return null;
    }

    public static void debug() {
        int i = 0;
        for (ArrayList list : equivalencyList) {
            System.out.println("equivalencyList[" + i + "]: " + list.toString());
            ++i;
        }
    }
}
