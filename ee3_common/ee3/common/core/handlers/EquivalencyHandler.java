package ee3.common.core.handlers;

import java.util.ArrayList;

import ee3.common.core.helper.GeneralHelper;
import net.minecraft.src.ItemStack;

/**
 * EquivalencyHandler
 * 
 * Class to handle all the equivalency relationships between items/etc
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EquivalencyHandler {

	private static final EquivalencyHandler instance = new EquivalencyHandler(); 
	
	/**
	 * 2D list of items. Each Inner list is the items that can be swapped for one another.
	 * Ex: [ [Cobble, Dirt, Grass], [Oak Wood, Jungle Wood] ] would imply that Cobble = Dirt = Grass are equal. And Oak Wood = Jungle Wood are equal.
	 */
    private static ArrayList<ArrayList<ItemStack>> equivalencyList = new ArrayList<ArrayList<ItemStack>>();
    
    public static EquivalencyHandler instance() {
    	return instance;
    }
    
    public ArrayList<ArrayList<ItemStack>> getAllLists() {
    	return equivalencyList;
    }


	/**
	 * This adds a new equivalency between the two specified items.
	 */
    public void addObjects(Object obj1, Object obj2) {
        ItemStack stack1 = GeneralHelper.convertObjectToItemStack(obj1);
        ItemStack stack2 = GeneralHelper.convertObjectToItemStack(obj2);

        ArrayList<ItemStack> currentList = new ArrayList<ItemStack>();

        Integer stack1Index = getIndexInList(stack1);
        Integer stack2Index = getIndexInList(stack2);

		/**
		 * Okay, we want to add the new equivalency.
		 * Todo that, we must first check that this equivalency doesn't already exist.
		 * We know it exists if both items were found in the lists. So then we just return.
		 */
        if ((stack1Index != null) && (stack2Index != null)) {
            return;
        } 
		/**
		 * Otherwise, if at least one wasn't found, we know we can add this new equivalency.
		 * So here we check that one of the two is already in the list (in one of the inner lists).
		 * If so, we add the other item to that same inner list.
		 * I.e. the existing item will now know it is equal to the new item.
		 */
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
		/**
		 * Otherwise, if neither items are found, we can just make a new inner list
		 * and add both items to it. Since neither item is equivalent to anything else yet.
		 */
        else if ((stack1Index == null) && (stack2Index == null)) {
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

	/**
	 * Runs through the equivalency list, trying to find the object.
	 * If the object is found, returns the index (in the outer list) of the inner list it was found in.
	 */
    public Integer getIndexInList(Object obj) {
        ItemStack checkStack = GeneralHelper.convertObjectToItemStack(obj);
        ArrayList<ItemStack> currentList;
        int i = 0;

        while (i < equivalencyList.size()) {
            currentList = equivalencyList.get(i);
            for (ItemStack currentStack : currentList) {
                if (ItemStack.areItemStacksEqual(checkStack, currentStack)) {
                    return new Integer(i);
                }
            }
            ++i;
        }

        return null;
    }
    
    public Integer getIndexinList(int id, int meta) {
    	ArrayList<ItemStack> currentList;
    	int i = 0;

    	while (i < equivalencyList.size()) {
	    	currentList = equivalencyList.get(i);
	    	for (ItemStack currentStack : currentList) {
		    	if ((id == currentStack.itemID) && (meta == currentStack.getItemDamage())) {
		    		return new Integer(i);
		    	}
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
                if (ItemStack.areItemStacksEqual(checkStack, currentStack)) {
                    return list;
                }
            }
        }

        return null;
    }
    
    public ArrayList<ItemStack> getEquivalencyList(int id, int meta) {
    	for (ArrayList<ItemStack> list : equivalencyList) {
			for (ItemStack currentStack : list) {
				if ((id == currentStack.itemID) && (meta == currentStack.getItemDamage())) {
					return list;
				}
			}
		}

		return null;
    }
    
    public ItemStack getNextInList(Object obj) {
    	ItemStack checkStack = GeneralHelper.convertObjectToItemStack(obj);

    	if (checkStack != null) {
    		return getNextInList(checkStack.itemID, checkStack.getItemDamage());
    	}
    	
    	return null;
    }
    
    
	/**
	 * Find the next object in the inner list that the specified object is in.
	 * Ex: If the inner list is [ Cobble, Dirt, Grass ] and you specify Cobble, it will return Dirt.
	 *     If you specify Grass, it will return Cobble (start again from the start of the list)
	 */
    public ItemStack getNextInList(int id, int meta) {
    	ArrayList<ItemStack> list = getEquivalencyList(id, meta);

    	ItemStack currentStack;
    	ItemStack returnStack = null;
    	int i = 0;

    	if (list != null) {
    	    if (list.size() == 1) {
    	        return list.get(i);
    	    }
    	    
	    	while (i < list.size()) {
		    	currentStack = list.get(i);
		    	
				//Get the next item in the list. The % (modulus) is to make it wrap back around if on the last item in the list.
		    	if ((id == currentStack.itemID) && (meta == currentStack.getItemDamage())) {
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

    	if (checkStack != null) {
    		return getPrevInList(checkStack.itemID, checkStack.getItemDamage());
    	}
    	
    	return null;
    }
    
	/**
	 * Find the previous object in the inner list that the specified object is in.
	 * Ex: If the inner list is [ Cobble, Dirt, Grass ] and you specify Dirt, it will return Cobble.
	 *     If you specify Cobble, it will return Grass (start again from the start of the list)
	 */
    public ItemStack getPrevInList(int id, int meta) {
    	ArrayList<ItemStack> list = getEquivalencyList(id, meta);

    	ItemStack currentStack;
    	ItemStack returnStack = null;
    	int i = 0;

    	if (list != null) {
    	    if (list.size() == 1) {
                return list.get(i);
            }
    	    
	    	while (i < list.size()) {
		    	currentStack = list.get(i);
		    	
				//Get the previous item in the list. The + size % size is to make a -1 return the last object in the list.
		    	if ((id == currentStack.itemID) && (meta == currentStack.getItemDamage())) {
		    		int index = ((i - 1) + list.size()) % list.size();
		    		returnStack = list.get(index);
		    		break;
		    	}
		    	
		    	++i;
	    	}
    	}

    	return returnStack;
    }

    public void debug() {
        int i = 0;
        for (ArrayList list : equivalencyList) {
            System.out.println("equivalencyList[" + i + "]: " + list.toString());
            ++i;
        }
    }
}
