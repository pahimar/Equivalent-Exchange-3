package ee3.common.core.helper;

import ee3.common.lib.Sounds;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class TransmutationHelper {

    public static boolean transmuteInWorld(World world, EntityPlayer player, ItemStack stack, int x, int y, int z) {

        int id = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        if (world.getBlockMaterial(x, y, z) == Material.leaves) {
            meta = meta % 4;
        }

        /*
        ItemStack nextItem = EquivalencyHandler.getNextBlockInEquivalencyList(id, meta, player.isSneaking());

        if (nextItem != null) {
            if (Block.blocksList[nextItem.itemID] != null) {
                world.setBlockAndMetadataWithNotify(x, y, z, nextItem.itemID, nextItem.getItemDamage());
                world.playSoundAtEntity(player, Sounds.TRANSMUTE, 0.5F, 1.0F);
                return true;
            }
        }
        */
        
        return false;
    }

}
