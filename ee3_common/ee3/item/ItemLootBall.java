package ee3.item;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ItemLootBall extends ItemEE {

	public InventoryLootBall inventory;
	
	public ItemLootBall(int i) {
		super(i);
		inventory = new InventoryLootBall(new ItemStack(this), null);
	}

	public ItemLootBall(int i, ItemStack[] itemStack) {
		super(i);
		inventory = new InventoryLootBall(new ItemStack(this), itemStack);
	}
	
	public void setInventory(ItemStack[] items) {
		inventory = new InventoryLootBall(new ItemStack(this), items);
	}
}
