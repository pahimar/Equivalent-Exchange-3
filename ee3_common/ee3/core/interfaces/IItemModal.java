package ee3.core.interfaces;

import net.minecraft.src.ItemStack;

public interface IItemModal {
	
	public abstract byte getCurrentMode(ItemStack ist);
	
	public abstract byte getMaxMode();
	
	public abstract byte cycleMode(ItemStack ist);
	
	public abstract void setMode(ItemStack ist, byte mode);
}
