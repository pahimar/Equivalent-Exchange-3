package com.pahimar.ee3.core.handlers;

import com.pahimar.ee3.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PlayerInteractHandler {
    
    @ForgeSubscribe
    public void playerInteract(PlayerInteractEvent event) {
    	if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK
    		&& event.entityPlayer.getHeldItem().itemID < Block.blocksList.length
    		&& Block.blocksList[event.entityPlayer.getHeldItem().itemID] != null) {
			int x = event.x + (new int[] {0, 0, 0, 0, -1, 1})[event.face];
    		int y = event.y - 1 + (new int[] {-1, 1, 0, 0, 0, 0})[event.face];
    		int z = event.z + (new int[] {0, 0, -1, 1, 0, 0})[event.face];
    		if (event.entityPlayer.worldObj.getBlockId(x, y, z) == ModBlocks.aludel.blockID) {
    			event.setCanceled(true);
    		}
    	}
    }
}
