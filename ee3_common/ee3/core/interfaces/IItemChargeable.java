package ee3.core.interfaces;

import net.minecraft.src.ItemStack;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public interface IItemChargeable {

	public abstract byte getCurrentCharge(ItemStack ist);
	
	public abstract byte getMaxCharge();
	
	public abstract void increaseCharge(ItemStack ist);
	
	public abstract void decreaseCharge(ItemStack ist);
	
	public abstract void setCurrentCharge(ItemStack ist, byte charge);
}
