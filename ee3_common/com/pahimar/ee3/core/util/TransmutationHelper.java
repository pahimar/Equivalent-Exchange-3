package com.pahimar.ee3.core.util;

import java.util.ArrayList;
import java.util.ListIterator;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.pahimar.ee3.core.handlers.EquivalencyHandler;

/**
 * Equivalent-Exchange-3
 * 
 * TransmutationHelper
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TransmutationHelper {

    public static ItemStack previousBlockStack = null;
    public static ItemStack currentBlockStack = null;
    public static ItemStack targetBlockStack = null;
	
	public static class TransmutationMemory{
		public final ItemStack current;
		public final ItemStack target;
		public final long timeStamp;
		
		public TransmutationMemory(ItemStack current, ItemStack target){
			this.current = current;
			this.target = target;
			timeStamp = java.lang.System.currentTimeMillis();
		}
		
		public TransmutationMemory(){
			current = currentBlockStack;
			target = targetBlockStack;
			timeStamp = java.lang.System.currentTimeMillis();
		}
		
	}
	
	public static ArrayList<TransmutationMemory> memories= new ArrayList<TransmutationMemory>();

    public static boolean transmuteInWorld(World world, EntityPlayer player, ItemStack stack, int x, int y, int z, int targetID, int targetMeta) {

        if (Block.blocksList[targetID] != null) {
            world.setBlock(x, y, z, targetID, targetMeta, 2);
            return true;
        }

        return false;
    }

    public static String formatTargetBlockInfo(ItemStack targetBlock) {

        if (targetBlock != null)
            return TransmutationHelper.targetBlockStack.itemID + ":" + TransmutationHelper.targetBlockStack.getItemDamage();
        else
            return "";
    }

    public static void updateTargetBlock(World world, int x, int y, int z) {

        int id = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        Block currentBlock = Block.blocksList[id];

        if (currentBlock != null) {
            meta = currentBlock.damageDropped(meta);
        

            currentBlockStack = new ItemStack(id, 1, meta);
        
	        //if a block doesn't have an associated item, treat it as untargetable
	        //this is to solve a compatability issue with Mystcraft
	        if(currentBlockStack.getItem() == null){
	            currentBlockStack = null;
	        	previousBlockStack = null;
	            targetBlockStack = null;
	        	return;
	        }
    
            if (previousBlockStack == null
            		|| previousBlockStack.itemID != currentBlockStack.itemID
            		|| previousBlockStack.getItemDamage() != currentBlockStack.getItemDamage()) {
                previousBlockStack = currentBlockStack;
                
                int i;
                for(i=0; i<memories.size(); i++){
                	if(ItemStack.areItemStacksEqual(memories.get(i).current, currentBlockStack)){
                		break;
                	}
                }
                if(i<memories.size()){
                	if((java.lang.System.currentTimeMillis() - memories.get(i).timeStamp) < 3000){
                		targetBlockStack = memories.get(i).target;
                	}
                	else {
                		targetBlockStack = getNextBlock(currentBlockStack.itemID, currentBlockStack.getItemDamage());
                		memories.set(i, new TransmutationMemory());
                	}
                }
                else {
                	targetBlockStack = getNextBlock(currentBlockStack.itemID, currentBlockStack.getItemDamage());
                	memories.add(new TransmutationMemory());
                }
            }
        }
    }

    public static ItemStack getNextBlock(int id, int meta) {

        ArrayList<ItemStack> list = EquivalencyHandler.instance().getEquivalencyList(id, meta);

        ItemStack nextStack = null;

        if (list != null)
            return getNextBlock(id, meta, id, meta);
        return nextStack;
    }

    private static ItemStack getNextBlock(int id, int meta, int origId, int origMeta) {

        ArrayList<ItemStack> list = EquivalencyHandler.instance().getEquivalencyList(id, meta);

        ItemStack nextStack = null;

        if (list != null) {
            nextStack = EquivalencyHandler.instance().getNextInList(id, meta);
            nextStack.stackSize = 1;

            /*
             * If the current item is the same as the original one we started
             * with, then we have recursed through the entire list and not found
             * a next block so return the original. This is the "base case" for
             * the recursion.
             */
            if (nextStack.itemID == origId && nextStack.getItemDamage() == origMeta)
                return nextStack;
            else {
                if (nextStack.getItem() instanceof ItemBlock)
                    return nextStack;
                else
                    return getNextBlock(nextStack.itemID, nextStack.getItemDamage(), origId, origMeta);
            }
        }

        // In the event the list is null, return null
        return nextStack;
    }

    public static ItemStack getPreviousBlock(int itemID, int meta) {

        ArrayList<ItemStack> list = EquivalencyHandler.instance().getEquivalencyList(itemID, meta);

        ItemStack prevStack = null;

        if (list != null)
            return getPreviousBlock(itemID, meta, itemID, meta);
        return prevStack;
    }

    private static ItemStack getPreviousBlock(int id, int meta, int origId, int origMeta) {

        ArrayList<ItemStack> list = EquivalencyHandler.instance().getEquivalencyList(id, meta);

        ItemStack prevStack = null;
        if (list != null) {
            prevStack = EquivalencyHandler.instance().getPrevInList(id, meta);
            prevStack.stackSize = 1;

            if (prevStack.itemID == origId && prevStack.getItemDamage() == origMeta)
                return prevStack;
            else {
                if (prevStack.getItem() instanceof ItemBlock)
                    return prevStack;
                else
                    return getPreviousBlock(prevStack.itemID, prevStack.getItemDamage(), origId, origMeta);
            }
        }

        // In the event the list is null, return null
        return prevStack;
    }

}
