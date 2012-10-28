package ee3.common.core.helper;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * GeneralHelper
 * 
 * General helper methods for EE3
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class GeneralHelper {

    public static ItemStack convertObjectToItemStack(Object obj) {
        if (obj instanceof Item) {
            return new ItemStack((Item) obj);
        } else if (obj instanceof Block) {
            return new ItemStack((Block) obj);
        } else if (obj instanceof ItemStack) {
            return (ItemStack) obj;
        } else {
            return null;
        }
    }

    public static Object[] convertSingleStackToPluralStacks(ItemStack stack) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        ItemStack currentStack;

        for (int i = 0; i < stack.stackSize; i++) {
            currentStack = new ItemStack(stack.itemID, 1, stack.getItemDamage());
            list.add(currentStack);
        }

        return list.toArray();
    }

}
