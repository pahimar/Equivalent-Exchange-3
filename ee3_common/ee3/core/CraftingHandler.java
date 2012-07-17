package ee3.core;

import ee3.mod_EE3;
import ee3.core.interfaces.IProxy;
import ee3.item.ItemPhilosopherStone;
import ee3.item.ModItems;
import ee3.lib.Reference;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.ICraftingHandler;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class CraftingHandler implements ICraftingHandler {
	
	@Override
	public void onTakenFromCrafting(EntityPlayer player, ItemStack stack, IInventory craftMatrix) {
		if (mod_EE3.proxy.isPortableCraftingGUIOpen()) {
			ItemStack currentInventoryItem = player.inventory.getCurrentItem();
			if (currentInventoryItem != null) {
				currentInventoryItem.damageItem(Reference.MINIUM_STONE_TRANSMUTE_COST, player);
			}
		}
		
		ItemStack currentItemStack;
		for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			currentItemStack = craftMatrix.getStackInSlot(i);
			if (currentItemStack != null) {
				if (currentItemStack.itemID == ModItems.miniumStone.shiftedIndex) {
					currentItemStack.damageItem(Reference.MINIUM_STONE_TRANSMUTE_COST, player);
					currentItemStack.stackSize++;
				}
				else if (currentItemStack.itemID == ModItems.philStone.shiftedIndex) {
					currentItemStack.stackSize++;
				}
			}
		}
	}

}
