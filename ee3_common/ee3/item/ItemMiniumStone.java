package ee3.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.mod_EE3;
import ee3.core.interfaces.ITransmuteStone;
import ee3.lib.CustomItemRarity;

public class ItemMiniumStone extends ItemEE implements ITransmuteStone {

	// Possible names: Vermillion Stone, Minium Stone, Red Stone
	
	public ItemMiniumStone(int i) {
		super(i);
	}
	
	/*
	 * Returns the custom item rarity type for the item
	 * @see net.minecraft.src.Item#getRarity(net.minecraft.src.ItemStack)
	 */
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return mod_EE3.proxy.getCustomEnumRarityType(CustomItemRarity.MAGICAL);
    }

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack ist) {
        return false;
    }
}
