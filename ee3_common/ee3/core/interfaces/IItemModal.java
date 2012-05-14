package ee3.core.interfaces;

import net.minecraft.src.ItemStack;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public interface IItemModal {
	
	public abstract byte getCurrentMode(ItemStack ist);
	
	public abstract byte getMaxMode();
	
	public abstract byte cycleMode(ItemStack ist);
	
	public abstract void setMode(ItemStack ist, byte mode);
}
