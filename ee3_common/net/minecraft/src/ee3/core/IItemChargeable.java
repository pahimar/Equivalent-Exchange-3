package net.minecraft.src.ee3.core;

public interface IItemChargeable {

	public abstract int getMaxCharge();
	
	public abstract void increaseCharge();
	
	public abstract void decreaseCharge();
}
