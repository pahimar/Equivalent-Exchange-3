package ee3.core.helper;

import ee3.lib.Sounds;
import ee3.lib.TransmuteEquivalencyList;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class TransmutationHelper {

	public static boolean transmuteInWorld(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
		if (world.getBlockMaterial(x, y, z) == Material.leaves) {
			meta = meta % 4;
		}
		
		ItemStack nextItem = TransmuteEquivalencyList.getNextBlockInEquivalencyList(id, meta, entityPlayer.isSneaking());
		
		if (nextItem != null) {
			if (Block.blocksList[nextItem.itemID] != null) {
				world.setBlockAndMetadataWithNotify(x, y, z, nextItem.itemID, nextItem.getItemDamage());
				world.playSoundAtEntity(entityPlayer, Sounds.TRANSMUTE, 0.5F, 1.0F);
				return true;
			}
		}
		
		return false;
	}
	
}
