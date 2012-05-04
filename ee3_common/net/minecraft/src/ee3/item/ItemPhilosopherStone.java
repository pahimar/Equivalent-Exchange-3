package net.minecraft.src.ee3.item;

import net.minecraft.src.ee3.interfaces.IItemChargeable;
import net.minecraft.src.ee3.interfaces.IItemModal;

public class ItemPhilosopherStone extends ItemEE implements IItemChargeable, IItemModal {

	private int maxCharge;
	
	public ItemPhilosopherStone(int i) {
		super(i);
		maxCharge = 4;
	}

	@Override
	public int getMaxCharge() {
		return maxCharge;
	}

	@Override
	public void increaseCharge() {
	}

	@Override
	public void decreaseCharge() {
	}

}
