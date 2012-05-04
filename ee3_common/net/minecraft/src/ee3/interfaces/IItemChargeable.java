package net.minecraft.src.ee3.interfaces;

public interface IItemChargeable {

	public abstract int getMaxCharge();
	
	public abstract void increaseCharge();
	
	public abstract void decreaseCharge();
}
