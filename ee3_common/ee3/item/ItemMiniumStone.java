package ee3.item;

import java.util.ArrayList;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import ee3.mod_EE3;
import ee3.core.helper.Helper;
import ee3.core.helper.TransmutationHelper;
import ee3.core.interfaces.ITransmuteStone;
import ee3.lib.CustomItemRarity;
import ee3.lib.Reference;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemMiniumStone extends ItemEE implements ITransmuteStone {
	
	public ItemMiniumStone(int i) {
		super(i);
		this.setMaxDamage(Reference.MINIUM_STONE_DURABILITY);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int l) {
		boolean result = TransmutationHelper.transmuteInWorld(itemStack, entityPlayer, world, x, y, z);
		
		if (result) {
			itemStack.damageItem(Reference.MINIUM_STONE_TRANSMUTE_COST, entityPlayer);
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
