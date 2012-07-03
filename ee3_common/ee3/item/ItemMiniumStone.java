package ee3.item;

import java.util.ArrayList;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import ee3.core.mod_EE3;
import ee3.core.helper.Helper;
import ee3.core.helper.TransmutationHelper;
import ee3.core.interfaces.ITransmuteStone;
import ee3.lib.CustomItemRarity;

public class ItemMiniumStone extends ItemEE implements ITransmuteStone {
	
	public ItemMiniumStone(int i) {
		super(i);
		this.setMaxDamage(50);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int l) {
		boolean result = TransmutationHelper.transmuteInWorld(itemStack, entityPlayer, world, x, y, z);
		
		if (result) {
			itemStack.damageItem(1, entityPlayer);
		}
		
		return result;
    }
	
	/*
	 * Returns the custom item rarity type for the item
	 * @see net.minecraft.src.Item#getRarity(net.minecraft.src.ItemStack)
	 */
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return mod_EE3.proxy.getCustomEnumRarityType(CustomItemRarity.MAGICAL);
    }
}
