package ee3.core;

import ee3.core.interfaces.IProxy;
import ee3.item.ItemPhilosopherStone;
import ee3.item.ModItems;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler {
	
	@Override
	public void onTakenFromCrafting(EntityPlayer player, ItemStack stack, IInventory craftMatrix) {
		if (mod_EE3.proxy.isPortableCraftingGUIOpen()) {
			ItemStack currentInventoryItem = player.inventory.getCurrentItem();
			if (currentInventoryItem != null) {
				currentInventoryItem.damageItem(1, player);
			}
		}
		
		ItemStack currentItemStack;
		for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			currentItemStack = craftMatrix.getStackInSlot(i);
			if (currentItemStack != null) {
				if (currentItemStack.itemID == ModItems.miniumStone.shiftedIndex) {
					currentItemStack.damageItem(1, player);
					currentItemStack.stackSize++;
				}
				else if (currentItemStack.itemID == ModItems.philStone.shiftedIndex) {
					currentItemStack.stackSize++;
				}
			}
		}
	}

}
