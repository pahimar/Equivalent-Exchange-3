package net.minecraft.src.ee3.item;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ee3.core.interfaces.IItemChargeable;
import net.minecraft.src.ee3.core.interfaces.IItemModal;

public class ItemPhilosopherStone extends ItemEE implements IItemChargeable, IItemModal {

	private byte currentCharge;
	private byte maxCharge;
	private byte currentMode;
	private byte maxMode;
	
	public ItemPhilosopherStone(int i) {
		super(i);
		maxCharge = 4;
		maxMode = 2;
	}

	@Override
	public byte getMaxCharge() {
		return maxCharge;
	}

	@Override
	public void increaseCharge() {
		if (currentCharge < maxCharge)
			currentCharge++;			
	}

	@Override
	public void decreaseCharge() {
		if (currentCharge > 0)
			currentCharge--;
	}

	@Override
	public byte getCurrentCharge() {
		return currentCharge;
	}

	@Override
	public void setCurrentCharge(byte charge) {
		currentCharge = charge;
	}

	@Override
	public byte getCurrentMode() {
		return currentMode;
	}

	@Override
	public byte getMaxMode() {
		return maxMode;
	}

	@Override
	public byte cycleMode() {
		if (currentMode < maxMode)
			currentMode += 1;
		else
			currentMode = 0;
		return currentMode;
	}

	@Override
	public void setMode(byte mode) {
		this.currentMode = mode;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack ist) {
        return false;
    }
}
