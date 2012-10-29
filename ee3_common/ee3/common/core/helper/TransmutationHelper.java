package ee3.common.core.helper;

import java.util.ArrayList;

import ee3.common.core.handlers.EquivalencyHandler;
import ee3.common.lib.Sounds;
import net.minecraft.src.Block;
import net.minecraft.src.BlockSand;
import net.minecraft.src.BlockLeaves;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

/**
 * TransmutationHelper
 * 
 * Helper methods for transmutation related things
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TransmutationHelper {

    public static boolean transmuteInWorld(World world, EntityPlayer player, ItemStack stack, int x, int y, int z) {

        int id = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        if ((world.getBlockMaterial(x, y, z) == Material.leaves) && (Block.blocksList[id] instanceof BlockLeaves)) {
        	meta = meta % 4;
        }

        ItemStack nextItem = getNextBlock(id, meta, player.isSneaking());

        if (nextItem != null) {
            if (Block.blocksList[nextItem.itemID] != null) {
                world.setBlockAndMetadataWithNotify(x, y, z, nextItem.itemID, nextItem.getItemDamage());
                // TODO Send the sound event to everyone around the player, and not just play the sound on the current client
                world.playSoundAtEntity(player, Sounds.TRANSMUTE, 0.5F, 1.0F);
                return true;
            }
        }
        
        return false;
    }
    
    public static ItemStack getNextBlock(int id, int meta, boolean allowFalling) {
        ArrayList<ItemStack> list = EquivalencyHandler.instance().getEquivalencyList(id, meta);
        
        ItemStack nextStack = null;
        
        if (list != null) {
            return getNextBlock(id, meta, id, meta, allowFalling);
        }
        
        return nextStack;
    }
    
    private static ItemStack getNextBlock(int id, int meta, int origId, int origMeta, boolean allowFalling) {
        ArrayList<ItemStack> list = EquivalencyHandler.instance().getEquivalencyList(id, meta);
        
        ItemStack nextStack = null;
        
        if (list != null) {
            nextStack = EquivalencyHandler.instance().getNextInList(id, meta);

            /*
             * If the current item is the same as the original one we started with, then we have
             * recursed through the entire list and not found a next block so return the original.
             * This is the "base case" for the recursion.
             */
            if ((nextStack.itemID == origId) && (nextStack.getItemDamage() == origMeta)) {
            	return nextStack;
            }
            else {
            	/*
            	 * If we are allowing any block (including falling ones) just check to see if the 
            	 * next item is a block. If it is, return it. Otherwise continue the recursion.
            	 */
            	if (allowFalling) {
            		if (nextStack.getItem() instanceof ItemBlock) {
            			return nextStack;
            		}
            		else {
            			return getNextBlock(nextStack.itemID, nextStack.getItemDamage(), origId, origMeta, allowFalling);
            		}
            	}
            	/*
            	 * Else we need to check to make sure the next item is both a block and not an
            	 * instance of BlockSand (which all gravity affected blocks are a subclass of.
            	 * If the next item is a block, and is not a subclass of BlockSand return it,
            	 * otherwise, continue the recursion.
            	 */
            	else {
            		if ((nextStack.getItem() instanceof ItemBlock) && (!(Block.blocksList[nextStack.itemID] instanceof BlockSand))) {
            			return nextStack;
            		}
            		else {
            			return getNextBlock(nextStack.itemID, nextStack.getItemDamage(), origId, origMeta, allowFalling);
            		}
            	}
            }
        }
        
        // In the event the list is null, return null
        return nextStack;
    }
    
}
