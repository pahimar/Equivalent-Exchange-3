package ee3.common.core.handlers;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

/**
 * FuelHandler
 * 
 * Class for querying and returning fuel values for items
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		// TODO Add in fuel values for EE3 fuel related items
		return 0;
	}

}
