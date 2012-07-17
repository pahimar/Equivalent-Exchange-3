package ee3.core.interfaces;

import net.minecraft.src.ItemStack;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public interface IItemModal {
	
	public abstract byte getCurrentMode(ItemStack ist);
	
	public abstract byte getMaxMode();
	
	public abstract byte cycleMode(ItemStack ist);
	
	public abstract void setMode(ItemStack ist, byte mode);
}
