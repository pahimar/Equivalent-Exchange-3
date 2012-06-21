package ee3.lib;

import java.util.ArrayList;
import java.util.logging.Level;

import ee3.addons.BuildCraftAddon;
import ee3.addons.ForestryAddon;
import ee3.addons.IndustrialCraftAddon;
import ee3.addons.RedPowerAddon;

import net.minecraft.src.BaseMod;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class Helper {

	public static ItemStack convertObjectToItemStack(Object obj) {
		if (obj instanceof Item) { return new ItemStack((Item)obj);	}
		else if (obj instanceof Block) { return new ItemStack((Block)obj); }
		else if (obj instanceof ItemStack){	return (ItemStack)obj; }
		else { return null;	}
	}

	public static Object[] convertSingleStackToPluralStacks(ItemStack stack) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		ItemStack currentStack;

		for (int i = 0; i < stack.stackSize; i++) {
			currentStack = new ItemStack(stack.itemID, 1, stack.getItemDamage());
			list.add(currentStack);
		}
		
		return list.toArray();
	}
	
	public static String getLogMessage(String logMessage) {
		return Reference.LOGGER_PREFIX + logMessage;
	}
	
	public static void initAddons() {
		ModLoader.getLogger().finer(getLogMessage("Checking for addons needing initializing"));
		boolean addonsFound = false;
		
		for (BaseMod mod : ModLoader.getLoadedMods()) {
			if (mod.toString().contains("mod_BuildCraftEnergy")) {
				BuildCraftAddon.init();
				addonsFound = true;
			}
			else if (mod.toString().contains("mod_RedPowerCore")) {
				RedPowerAddon.init();
				addonsFound = true;
			}
			else if (mod.toString().contains("mod_RedPowerWorld")) {
				RedPowerAddon.init();
				addonsFound = true;
			}
			else if (mod.toString().contains("mod_Forestry")) {
				ForestryAddon.init();
				addonsFound = true;
			}
			else if (mod.toString().contains("mod_IC2")) {
				IndustrialCraftAddon.init();
				addonsFound = true;
			}
		}
		if (addonsFound)
			ModLoader.getLogger().finer(getLogMessage("Done initializing addons"));
		else
			ModLoader.getLogger().finer(getLogMessage("No addons for loaded mods found"));
	}
}
