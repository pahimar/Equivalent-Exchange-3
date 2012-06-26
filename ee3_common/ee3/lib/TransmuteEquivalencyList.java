package ee3.lib;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.src.Block;
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
	
	public static Integer getEquivalencyIndexForItem(int id, int meta) {
		ArrayList<ItemStack> currentList;
		int i = 0;
		
		while (i < equivalencyLists.size()) {
			currentList = equivalencyLists.get(i);
			for (ItemStack currentStack : currentList) {
				if ((id == currentStack.itemID) && (meta == currentStack.getItemDamage())) {
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
	
	public static ArrayList<ItemStack> getEquivalencyListForItem(int id, int meta) {
		for (ArrayList<ItemStack> list : equivalencyLists) {
			for (ItemStack currentStack : list) {
				if ((id == currentStack.itemID) && (meta == currentStack.getItemDamage())) {
					return list;
				}
			}
		}
		
		return null;
	}
	
	public static ItemStack getNextItemInEquivalencyList(Object obj) {
		ItemStack checkStack = Helper.convertObjectToItemStack(obj);
		
		if (checkStack == null)
			return null;
		
		return getNextItemInEquivalencyList(checkStack.itemID, checkStack.getItemDamage());
	}
	
	public static ItemStack getNextItemInEquivalencyList(int id, int meta) {
		System.out.println("id: " + id + ", meta: " + meta);
		ArrayList<ItemStack> list = getEquivalencyListForItem(id, meta);
		ItemStack currentStack;
		ItemStack returnStack = null;
		int i = 0;
		
		if (list != null) {
			System.out.println(list.toString());
			while (i < list.size()) {
				currentStack = list.get(i);
				if ((id == currentStack.itemID) && (meta == currentStack.getItemDamage())) {
					returnStack = list.get((i + 1) % list.size());
					System.out.println(list.toString());
					break;
				}
				++i;
			}
		}
		
		return returnStack;
	}
	
	public static ItemStack getNextBlockInEquivalencyList(int id, int meta) {
		ArrayList<ItemStack> list = getEquivalencyListForItem(id, meta);
		ItemStack currentStack;
		ItemStack returnStack = null;
		int i = 0;
		
		if (list != null) {
			while (i < list.size()) {
				currentStack = list.get(i);
				if ((id == currentStack.itemID) && (meta == currentStack.getItemDamage())) {
					for (int index = (i + 1) % list.size(); index < i; ++index) {
						if (Block.blocksList[list.get(index).itemID] != null) {
							returnStack = list.get(index);
							System.out.println(returnStack.toString());
							return returnStack;
						}
					}
				}
				++i;
			}
		}
		
		return returnStack;
	}

	public static void debugPrintEquivalencyList() {
		int i = 0;
		for (ArrayList list : equivalencyLists) {
			System.out.println("equivalencyList[" + i + "]: " + list.toString());
			++i;
		}
	}
}
