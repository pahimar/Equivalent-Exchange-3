package ee3.common.core.handlers;

import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		// TODO Add in fuel values for EE3 fuel related items
		return 0;
	}

}
