package ee3.core.helper;

import ee3.core.mod_EE3;
import ee3.item.ItemPhilosopherStone;
import ee3.item.ModItems;
import ee3.lib.Sounds;
import ee3.lib.TransmuteEquivalencyList;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class TransmutationHelper {

	public static boolean transmuteInWorld(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		boolean transmuted = false;
		int charge;
		if(itemStack.itemID == ModItems.philStone.shiftedIndex){
			charge = new ItemPhilosopherStone(0).getCurrentCharge(itemStack);
		}else{
			charge = 0;
		}
		x -= getChargeSize(charge) / 2;
		z -= getChargeSize(charge) / 2;
		for(int i = 0; i <= getChargeSize(charge); i++){
			for(int j = 0; j <= getChargeSize(charge); j++){
				if(transmuteCoord(itemStack, entityPlayer, world, x + i, y, z + j)){
					transmuted = true;
				}
			}
		}
		return transmuted;
	}
	private static int getChargeSize(int charge){
		if(charge == 0){
			return 0;
		}else if(charge == 1){
			return 2;
		}else if(charge == 2){
			return 4;
		}else if(charge == 3){
			return 6;
		}else if(charge == 4){
			return 8;
		}else{
			return 10;
		}
	}
	public static boolean transmuteCoord(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z){
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
