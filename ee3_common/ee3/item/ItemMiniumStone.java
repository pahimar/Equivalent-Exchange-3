package ee3.item;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import ee3.core.mod_EE3;
import ee3.core.helper.Helper;
import ee3.core.interfaces.ITransmuteStone;
import ee3.lib.CustomItemRarity;
import ee3.lib.Sounds;
import ee3.lib.TransmuteEquivalencyList;

public class ItemMiniumStone extends ItemEE implements ITransmuteStone {
	
	public ItemMiniumStone(int i) {
		super(i);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, 
			int x, int y, int z, int l) {
		
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
		if (world.getBlockMaterial(x, y, z) == Material.leaves)
			meta = meta % 4;
		
		ItemStack nextItem = TransmuteEquivalencyList.getNextBlockInEquivalencyList(id, meta, entityPlayer.isSneaking());
		
		if (nextItem != null) {
			if (Block.blocksList[nextItem.itemID] != null) {
				world.setBlockAndMetadataWithNotify(x, y, z, nextItem.itemID, nextItem.getItemDamage());
			}
		}
		
		world.playSoundAtEntity(entityPlayer, Sounds.TRANSMUTE, 0.5F, 1.0F);
		
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
