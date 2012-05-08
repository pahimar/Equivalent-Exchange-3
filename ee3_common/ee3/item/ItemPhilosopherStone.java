package ee3.item;

import ee3.core.interfaces.IItemChargeable;
import ee3.core.interfaces.IItemModal;
import net.minecraft.src.ItemStack;

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
	public void increaseCharge(ItemStack ist) {
		if (getCurrentCharge(ist) < getMaxCharge())
			setCurrentCharge(ist, (byte)(getCurrentCharge(ist) + 1));			
	}

	@Override
	public void decreaseCharge(ItemStack ist) {
		if (getCurrentCharge(ist) > 0)
			setCurrentCharge(ist, (byte)(getCurrentCharge(ist) - 1));	
	}

	@Override
	public byte getCurrentCharge(ItemStack ist) {
		return getByte(ist, "currentCharge");
	}

	@Override
	public void setCurrentCharge(ItemStack ist, byte charge) {
		setByte(ist, "currentCharge", charge);
	}

	@Override
	public byte getCurrentMode(ItemStack ist) {
		return getByte(ist, "currentMode");
	}

	@Override
	public byte getMaxMode() {
		return maxMode;
	}

	@Override
	public byte cycleMode(ItemStack ist) {
		if (getCurrentMode(ist) < getMaxMode())
			setMode(ist, (byte)(getCurrentMode(ist) + 1));
		else
			setMode(ist, (byte)0);
		return getCurrentMode(ist);
	}

	@Override
	public void setMode(ItemStack ist, byte mode) {
		setByte(ist, "currentMode", mode);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack ist) {
        return false;
    }
}
