package ee3.core;

import ee3.item.ItemPhilosopherStone;
import ee3.item.ModItems;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.forge.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler {

	public CraftingHandler() { }
	
	@Override
	public void onTakenFromCrafting(EntityPlayer player, ItemStack stack, IInventory craftMatrix) {
		// TODO Auto-generated method stub
		ItemStack currentItemStack;
		for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			currentItemStack = craftMatrix.getStackInSlot(i);
			if (currentItemStack != null) {
				if (currentItemStack.itemID == ModItems.philStone.shiftedIndex) {
					System.out.println(currentItemStack.getItem().getItemName());
					ItemPhilosopherStone stone = (ItemPhilosopherStone) currentItemStack.getItem();
				}
			}
		}
	}

}
