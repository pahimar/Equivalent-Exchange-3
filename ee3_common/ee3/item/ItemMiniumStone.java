package ee3.item;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import ee3.core.mod_EE3;
import ee3.core.interfaces.ITransmuteStone;
import ee3.lib.CustomItemRarity;
import ee3.lib.Helper;
import ee3.lib.Sounds;
import ee3.lib.TransmuteEquivalencyList;

public class ItemMiniumStone extends ItemEE implements ITransmuteStone {
	
	public ItemMiniumStone(int i) {
		super(i);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, 
			int x, int y, int z, int l) {
		
		ItemStack nextItem = TransmuteEquivalencyList.getNextItemInEquivalencyList(world.getBlockId(x, y, z), world.getBlockMetadata(x, y, z));
		
		if (nextItem != null) {
			if (Block.blocksList[nextItem.itemID] != null) {
				System.out.println(world.setBlockAndMetadataWithNotify(x, y, z, nextItem.itemID, nextItem.getItemDamage()));
			}
		}
		
		
		world.playSoundAtEntity(entityPlayer, Sounds.TRANSMUTE, 1.0F, 1.0F);
		
		return false;
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
