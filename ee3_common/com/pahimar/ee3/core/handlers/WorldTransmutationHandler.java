package com.pahimar.ee3.core.handlers;

import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.event.WorldTransmutationEvent;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;

public class WorldTransmutationHandler {

    @ForgeSubscribe
    public void onWorldTransmutationEvent(WorldTransmutationEvent event) {

        int id = event.world.getBlockId(event.x, event.y, event.z);
        int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
        
        if (EquivalencyHandler.instance().areEquivalent(new ItemStack(id, 1, meta), new ItemStack(event.targetID, 1, event.targetMeta))) {
            TransmutationHelper.transmuteInWorld(event.world, event.player, event.player.getCurrentEquippedItem(), event.x, event.y, event.z, event.targetID, event.targetMeta);
        }
    }

}
