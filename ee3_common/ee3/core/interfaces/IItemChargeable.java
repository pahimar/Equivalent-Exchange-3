package ee3.core.interfaces;

import net.minecraft.src.ItemStack;

public interface IItemChargeable {

	public abstract byte getCurrentCharge(ItemStack ist);
	
	public abstract byte getMaxCharge();
	
	public abstract void increaseCharge(ItemStack ist);
	
	public abstract void decreaseCharge(ItemStack ist);
	
	public abstract void setCurrentCharge(ItemStack ist, byte charge);
}
