package ee3.lib;

import java.util.ArrayList;

import net.minecraft.src.ItemStack;

public class TransmuteEquivalencyList {
	
	public static ArrayList<ArrayList<ItemStack>> equivalencyLists = new ArrayList<ArrayList<ItemStack>>();
	
	public static void addObjectsToEquivalencyLists(Object ... objList) {
		if (objList.length < 2)
			return;
		
		for (int i = 0; i < objList.length - 1; i++) {
			addObjectToEquivalencyList(objList[i], objList[i+1]);
		}
	}
	
	public static void addObjectToEquivalencyList(Object obj1, Object obj2) {
		ItemStack stack1 = Helper.convertObjectToItemStack(obj1);
		ItemStack stack2 = Helper.convertObjectToItemStack(obj2);
		
		ArrayList<ItemStack> currentList = new ArrayList<ItemStack>();
		
		Integer stack1Index = getEquivalencyIndexForItem(stack1);
		Integer stack2Index = getEquivalencyIndexForItem(stack2);
		
		if ((stack1Index != null) && (stack2Index != null)) {
			return;
		}
		else if ((stack1Index != null) && (stack2Index == null)) {
			currentList = equivalencyLists.get(stack1Index.intValue());
			currentList.add(stack2);
			equivalencyLists.set(stack1Index.intValue(), currentList);
		}
		else if ((stack1Index == null) && (stack2Index != null)) {
			currentList = equivalencyLists.get(stack2Index.intValue());
			currentList.add(stack1);
			equivalencyLists.set(stack2Index.intValue(), currentList);
		}
		else if ((stack1Index == null) && (stack2Index == null)) {
			currentList.add(stack1);
			currentList.add(stack2);
			equivalencyLists.add(currentList);
		}
	}
	
	public static Integer getEquivalencyIndexForItem(Object obj) {
		ItemStack checkStack = Helper.convertObjectToItemStack(obj);
		ArrayList<ItemStack> currentList;
		int i = 0;
		
		while (i < equivalencyLists.size()) {
			currentList = equivalencyLists.get(i);
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
		ItemStack checkStack = Helper.convertObjectToItemStack(obj);
		
		if (checkStack == null)
			return null;
		
		for (ArrayList<ItemStack> list : equivalencyLists) {
			for (ItemStack currentStack : list) {
				if (checkStack.isStackEqual(currentStack)) {
					return list;
				}
			}
		}
		
		return null;
	}

	public static void debugPrintEquivalencyList() {
		int i = 0;
		for (ArrayList list : equivalencyLists) {
			System.out.println("equivalencyList[" + i + "]: " + list.toString());
			++i;
		}
	}
}
