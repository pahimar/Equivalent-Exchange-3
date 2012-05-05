package net.minecraft.src.ee3.core.interfaces;

public interface IItemModal {
	
	public abstract byte getCurrentMode();
	
	public abstract byte getMaxMode();
	
	public abstract byte cycleMode();
	
	public abstract void setMode(byte mode);
}
