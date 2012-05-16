package ee3.core;

import ee3.item.ItemLootBall;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.forge.IPickupHandler;

public class PickupHandler implements IPickupHandler {

	public PickupHandler() { }
	
	@Override
	public boolean onItemPickup(EntityPlayer player, EntityItem entityItem) {
		ItemStack itemstack = entityItem.item;
		
		if (itemstack == null || itemstack.stackSize <= 0)
			return false;
		
		if (entityItem.item.getItem() instanceof ItemLootBall) {
			System.out.println("Found a loot ball");
			
			ItemLootBall lootBall = (ItemLootBall) itemstack.getItem();
			ItemStack[] loot = lootBall.inventory.getInventoryContents();
			if (loot != null)
				for (int i = 0; i < loot.length; i++) {
					if (player.inventory.addItemStackToInventory(loot[i]) == false)
						player.dropPlayerItem(loot[i]);
				}
			
			// Destroy the itemstack
			itemstack.stackSize = 0;
		}
		
		return true;
	}

}
